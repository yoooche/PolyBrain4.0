package feature.booking.dao;

import feature.booking.vo.BookingVo;
import feature.mem.vo.MemVo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Query;
import java.sql.Date;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao {
    //新增的!
    @Override
    public BookingVo  insert(BookingVo bookingvo){
        Session session = getSession();
        try{
            session.persist(bookingvo);
            System.out.println(bookingvo);
            return bookingvo;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(bookingvo);
            return null;
        }

    }
    @Override
    public List<BookingVo> selectAll(){
        //final  String hql = "FROM BookingVo ORDER BY bookingno";
        final String hql = "SELECT b FROM BookingVo b JOIN FETCH b.memvo ORDER BY b.bookingno";
        return getSession().createQuery(hql, BookingVo.class).getResultList();
    }
    //用在mailservice方法
    @Override
    public BookingVo selectById(Integer bookingno){
        String hql = "FROM BookingVo b WHERE b.bookingno = :bookingno";
        BookingVo bookvo = getSession().get(BookingVo.class, bookingno);
        //將查到的bookingno放到selectByIdMem去查詢對應的memno
        Integer memno = selectByIdMem(bookingno);
        // 將 memno 設置回 BookingVo、並一起把其他預設置填上
        bookvo.setMemno(memno);
        bookvo.setBookingcheckstate(2);
        bookvo.setBookingstate(1);
        return bookvo;
    }
    @Override
    public Integer selectByIdMem(Integer bookingno) {
        Session session = getSession();
        try{
            final String hql = "SELECT b.memno FROM BookingVo b WHERE b.bookingno = :bookingno";
            Query query = session.createQuery(hql, Integer.class);
            query.setParameter("bookingno", bookingno);
            Integer memno = (Integer) ((org.hibernate.query.Query<?>) query).uniqueResult();
            return memno;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("daoWrong:");
            return -1;
        }
    }
    //查詢含(查全部、條件)
    @Override
    public List<BookingVo> selectByDate(int state, Date startDate, Date endDate, Integer bookingNo,Integer memNo){
        List<Integer> statesToQuery = new ArrayList<>();
        String hql = "SELECT b FROM BookingVo b WHERE b.memno = :memNo";
        // 如果状态是2，将其转换为状态1和状态0
        if (state == 2) {
            statesToQuery.add(1);
            statesToQuery.add(0);
        } else {
            statesToQuery.add(state);
        }
        if(!statesToQuery.isEmpty()) {
            hql += " AND bookingstate IN :state";
        }
        if (startDate != null && endDate != null) {

            hql += " AND tabledate BETWEEN :startDate AND :endDate";
        }
        if(bookingNo != null){
            hql += " AND bookingno = :bookno";
        }
        Session session = getSession();
        Query query = session.createQuery(hql, BookingVo.class);
        if (!statesToQuery.isEmpty()){
            query.setParameter("state",statesToQuery);
            query.setParameter("memNo", memNo);
        }
        if(state == 2){
            query.setParameter("state",1);
        }

        if (startDate != null && endDate != null){
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
        }
        if(bookingNo != null){
            query.setParameter("bookno", bookingNo);
        }

        List<BookingVo> bookings = query.getResultList();
        return bookings;
    }

    @Override
    public BookingVo cancel(Integer bookingNo, Integer newState){
        Session session = getSession();
        try {
            String hql = "UPDATE BookingVo SET bookingstate = :newState WHERE bookingno = :bookingNo";
            Query query = session.createQuery(hql);
            query.setParameter("newState", newState);
            query.setParameter("bookingNo", bookingNo);
            int rowCount = query.executeUpdate();
            System.out.println(rowCount);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public BookingVo checkState(Integer bookingNo, Integer newState){
        Session session = getSession();
        try {
            String hql = "UPDATE BookingVo SET bookingcheckstate = :newState WHERE bookingno = :bookingNo";
            Query query = session.createQuery(hql);
            query.setParameter("newState", newState);
            query.setParameter("bookingNo", bookingNo);
            int rowCount = query.executeUpdate();
            System.out.println(rowCount);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    //用不到
    @Override
    public Integer deletbyId(Integer bookingno) {
        BookingVo bookvo = getSession().get(BookingVo.class, bookingno);
        getSession().remove(bookvo);
        return null;
    }
    //用不到
    @Override
    public Integer updateById(BookingVo bookingVo) {
        return null;
    }

}

