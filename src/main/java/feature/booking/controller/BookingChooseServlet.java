package feature.booking.controller;

import feature.booking.vo.TableBookingVo;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


import static core.util.CommonUtil.writePojo2Json;
import static core.util.Constants.JSON_MIME_TYPE;
import static feature.booking.util.BookConstants.SERVICETable;

@WebServlet("/loginRequired/tablebooking")
public class BookingChooseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType(JSON_MIME_TYPE);
//        Date DateChooseParam = null;
//        String DateChoose = request.getParameter("chooseDate");
//        System.out.println("DateChoose:" + DateChoose);


        String tableParam = request.getParameter("table");
        System.out.println("桌子:" + tableParam);
       Integer tableno = Integer.parseInt(tableParam );
        System.out.println("桌號:" + tableno);
//        if (DateChoose != null && !DateChoose.isEmpty()) {
//            DateChooseParam = Date.valueOf(DateChoose);
//        }
//        System.out.println("DateChooseParam: " + DateChooseParam);

        List<TableBookingVo> tablebook = SERVICETable.selectByDate(tableno);
        writePojo2Json(response, tablebook);
    }
public BookingChooseServlet(){
        super();
}
}
