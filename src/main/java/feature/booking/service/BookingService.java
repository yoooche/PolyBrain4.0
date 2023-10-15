package feature.booking.service;

import core.coreService.CoreService;
import feature.booking.vo.BookingVo;

import java.sql.Date;
import java.util.List;

public interface BookingService extends CoreService {
    //查全部
    List<BookingVo> getAllBooking();

    List<BookingVo> selectone(Integer bookingState);
    List<BookingVo> selectdate(int state, Date startDate, Date endDate, Integer bookingNo, Integer memNo);

    BookingVo cancelBooking(Integer bookno, Integer newState);
    BookingVo checkBookState(Integer bookno, Integer newState);

    boolean remove(Integer bookingno);

    boolean save(BookingVo booking);

    //新的
    BookingVo insert(BookingVo bookingVo);
    BookingVo selectById(Integer memno);


}
