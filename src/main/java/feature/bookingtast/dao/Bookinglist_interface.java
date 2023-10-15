package feature.bookingtast.dao;


import feature.bookingtast.vo.BookinglistVO;
import feature.bookingtast.vo.TablebookingVO;

import java.util.List;

public interface Bookinglist_interface extends core.coreDao.CoreDao<TablebookingVO, Integer>{

	public void insert(BookinglistVO bookinglistVO);
    public void update(BookinglistVO bookinglistVO);

    public BookinglistVO findByPrimaryKey(Integer BOOKING_NO);
    public List<BookinglistVO> getAll();
    
}
