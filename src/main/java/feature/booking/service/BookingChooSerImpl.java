package feature.booking.service;

import feature.booking.dao.TableBookingDao;
import feature.booking.dao.TableBookingImpl;
import feature.booking.vo.TableBookingVo;
import org.hibernate.SessionFactory;


import java.sql.Date;
import java.util.List;

public class BookingChooSerImpl implements BookingChooseService{
    private SessionFactory sessionFactory;
    private TableBookingDao Dao;
    public BookingChooSerImpl(){
        Dao = new TableBookingImpl();
    }
    @Override
    public List<TableBookingVo> selectByDate(int tableno){
//        beginTransaction();
        List<TableBookingVo> result = Dao.SelectByChoose(tableno);
//        commit();
        return result;
    }
    //改變狀態
    public TableBookingVo selectByState(Integer stateNo,Date tabledate,Integer tableNo){
//        beginTransaction();
        TableBookingVo result = Dao.updateByState(stateNo, tabledate, tableNo);
//        commit();
        return result;
        //return Dao.updateByState(stateNo, tabledate, tableNo);
    }
    //取消狀態，返還時段
    public TableBookingVo selectCancelState(Integer stateNo,Date bookDate,Integer tableNo){
//        beginTransaction();
        TableBookingVo result = Dao.cancelByState(stateNo,bookDate,tableNo);
//        commit();
        return result;
        //return Dao.cancelByState(stateNo,bookDate,tableNo);
    }

}
