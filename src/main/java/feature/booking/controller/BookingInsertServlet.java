package feature.booking.controller;

import core.util.CommonUtil;
import feature.booking.service.BookingService;
import feature.booking.service.BookingServiceImpl;
import feature.booking.vo.BookingVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import feature.booking.service.BookMailServ;
import static core.util.CommonUtil.*;
import static core.util.Constants.JSON_MIME_TYPE;

@WebServlet("/loginRequired/booking/insert")
public class BookingInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingService service = new BookingServiceImpl();
    CommonUtil commonUtil = new CommonUtil();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType(JSON_MIME_TYPE);
        BookingVo bookingVo = json2Pojo(request, BookingVo.class);

        System.out.println(request.getRequestURI());
        System.out.println("bookingVo:" + bookingVo);

        //insert
        commonUtil.writePojo2Json(response, service.insert(bookingVo));
        Integer number = bookingVo.getBookingno();

        Integer mem = bookingVo.getMemno();
//        System.out.println("number" + number);
//        System.out.println("mem" + mem);
        //BookingVo memno = service.selectById(mem);
        BookMailServ bookMailServ = new BookMailServ();
        bookMailServ.sendMail(number);

    }
}
