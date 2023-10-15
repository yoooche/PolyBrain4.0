package feature.booking.controller;

import feature.booking.vo.BookingVo;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static core.util.CommonUtil.writePojo2Json;
import static feature.booking.util.BookConstants.SERVICE;


@WebServlet("/loginRequired/booking/cancel")
public class BookcancelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void cancelBooking(HttpServletRequest request, HttpServletResponse response) {
           String bookingNoParam = request.getParameter("bookingNo");
           System.out.println(bookingNoParam);
            // 在这里编写取消预订的逻辑
            Integer bookno = Integer.parseInt(String.valueOf(bookingNoParam));
            System.out.println(bookno);
            Integer newState = 1;

            BookingVo cancelled = SERVICE.cancelBooking(bookno, newState); // 这里应该是你的取消预订逻辑
            writePojo2Json(response, cancelled);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        cancelBooking(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        cancelBooking(request, response);
    }
    public BookcancelServlet(){
        super();
    }

}
