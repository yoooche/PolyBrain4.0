package feature.booking.controller;

import feature.booking.vo.BookingVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static core.util.CommonUtil.writePojo2Json;
import static core.util.Constants.JSON_MIME_TYPE;
import static feature.booking.util.BookConstants.SERVICE;

@WebServlet("/loginRequired/booking/one")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(JSON_MIME_TYPE);
        //獲得會員
        String memNoParam = request.getParameter("memNo");
        Integer memNo = Integer.parseInt(memNoParam);
        System.out.println(memNo);
        //獲取日期參數
        String stateParam = request.getParameter("state");
        int state = Integer.parseInt(stateParam);
        System.out.println(state);

        Date startDateParam = null;
        Date endDateParam = null;

        String startDateStr = request.getParameter("startDate");
        String endDateStr =  request.getParameter("endDate");

        if (startDateStr != null && !startDateStr.isEmpty()) {
            startDateParam = Date.valueOf(startDateStr);
        }

        if (endDateStr != null && !endDateStr.isEmpty()) {
            endDateParam = Date.valueOf(endDateStr);
        }

        //獲得訂單編號
        String bookingNoParam = request.getParameter("bookingno");
        Integer bookingNo = null;
        if(bookingNoParam != null){
            bookingNo = Integer.parseInt(bookingNoParam);
        }
        System.out.println("bookingNoParam:" + bookingNoParam);
        List<BookingVo> bookings = SERVICE.selectdate(state, startDateParam, endDateParam, bookingNo, memNo);
        writePojo2Json(response, bookings);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public BookingServlet() {
        super();

    }
}
