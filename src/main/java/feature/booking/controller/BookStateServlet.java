package feature.booking.controller;



import feature.booking.vo.TableBookingVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

import static core.util.CommonUtil.writePojo2Json;
import static feature.booking.util.BookConstants.SERVICETable;


@WebServlet("/loginRequired/booking/state")
public class BookStateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String stateParam = request.getParameter("periodtime");
        String bookDateParam = request.getParameter("tabledate");
        String tableNoParam = request.getParameter("tableno");
        System.out.println("狀態:" + stateParam);
        System.out.println("日期:" + bookDateParam);
        System.out.println("桌號:" + tableNoParam);
        Integer stateNo = Integer.parseInt(String.valueOf(stateParam));
        Date bookDate = Date.valueOf(bookDateParam);
        Integer tableNo = Integer.parseInt(String.valueOf(tableNoParam));
        System.out.println(stateNo);
        Integer newState = 0;
        TableBookingVo change = SERVICETable.selectByState(stateNo, bookDate, tableNo);
        System.out.println("回傳:" +change);
        //response.setContentType(JSON_MIME_TYPE);
        writePojo2Json(response, change);

    }
}