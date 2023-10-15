package feature.booking.dao;

import core.coreDao.CoreDao;
import feature.booking.vo.BookingVo;

import java.sql.Date;
import java.util.List;

public interface BookingDao extends CoreDao{

    BookingVo insert(BookingVo book);

    Integer deletbyId(Integer bookingno);
    List<BookingVo> selectAll();

    //Table booking 只會有state狀態改變
    Integer updateById(BookingVo newbook);

//    List<BookingVo> selectById(Integer bookingno);

    //查一個
    BookingVo selectById(Integer bookingno);
    //獲取對應的會員編號，以利發信
    Integer selectByIdMem(Integer bookingno);

    //查日期
    public List<BookingVo> selectByDate(int state, Date startDate, Date endDate, Integer bookingNo,Integer memNo);

    //取消
//    Integer cancel (Integer bookno);

    BookingVo cancel(Integer bookingNo, Integer newState);
    BookingVo checkState(Integer bookingNo, Integer newState);
}
