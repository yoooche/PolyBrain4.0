package feature.bookingtast.service;

import java.util.List;

import feature.bookingtast.dao.BookinglistDAO;
import feature.bookingtast.dao.Bookinglist_interface;
import feature.bookingtast.vo.BookinglistVO;

public class BookinglistService {

	private Bookinglist_interface dao;

	public BookinglistService() {
		dao = new BookinglistDAO();
	}


	
	public BookinglistVO addBookinglist(Integer TABLE_NO, java.sql.Date TABLE_DATE, Integer PERIOD_TIME) {

		BookinglistVO bookinglistVO = new BookinglistVO();
	
		bookinglistVO.setTABLE_NO(TABLE_NO);
		bookinglistVO.setTABLE_DATE(TABLE_DATE);
		bookinglistVO.setPERIOD_TIME(PERIOD_TIME);
		
		dao.insert(bookinglistVO);

		return bookinglistVO;
	}
	
	//預留給 Struts 2 或 Spring MVC 用
		public void addBookinglist(BookinglistVO bookinglistVO) {
			dao.insert(bookinglistVO);
		}
		
		public BookinglistVO updatebookinglist(Integer BOOKING_NO,Integer TABLE_NO, java.sql.Date TABLE_DATE, Integer PERIOD_TIME) {
			
			BookinglistVO bookinglistVO = new BookinglistVO();
			bookinglistVO.setBOOKING_NO(BOOKING_NO);
			bookinglistVO.setTABLE_NO(TABLE_NO);
			bookinglistVO.setTABLE_DATE(TABLE_DATE);
			bookinglistVO.setPERIOD_TIME(PERIOD_TIME);
			dao.update(bookinglistVO);

			return dao.findByPrimaryKey(BOOKING_NO);
		}
		
		//預留給 Struts 2 或 Spring MVC 用
		public void updateBookinglis(BookinglistVO bookinglistVO) {
			dao.update(bookinglistVO);
		}
		


		public BookinglistVO getOneBookinglis(Integer BOOKING_NO) {
			return dao.findByPrimaryKey(BOOKING_NO);
		}
		
		public List<BookinglistVO> getAll() {
			return dao.getAll();
		}
		
}
