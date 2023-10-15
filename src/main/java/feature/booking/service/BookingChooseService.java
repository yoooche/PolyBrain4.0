package feature.booking.service;

import core.coreService.CoreService;
import feature.booking.vo.TableBookingVo;


import java.sql.Date;
import java.util.List;

public interface BookingChooseService extends CoreService {
    List<TableBookingVo> selectByDate(int tableno);
    //更新狀態
    TableBookingVo selectByState(Integer stateNo,Date bookDate,Integer tableNo);
    TableBookingVo selectCancelState(Integer stateNo,Date bookDate,Integer tableNo);
}
