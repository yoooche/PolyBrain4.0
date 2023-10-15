package feature.bookingtast.controller;

import feature.bookingtast.service.TablebookingService;
import feature.bookingtast.vo.TablebookingVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/bookingtast/inserservlet")
@MultipartConfig   //有用到檔案上傳的接收入口設置
public class Updateservlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");


        if ("getOne_For_Update".equals(action)) { // 1.jsp的請求

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數****************************************/
            Integer TABLE_BOOK_NO = Integer.valueOf(req.getParameter("TABLE_NO"));
            //拿值後不做操作在返回的原來的值
            /***************************2.開始查詢資料****************************************/
            TablebookingService tabSvc = new TablebookingService();
            TablebookingVO tablebookingVO = tabSvc.getOneTablebooking(TABLE_BOOK_NO);
//
//            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            String param = "?TABLE_BOOK_NO=" + tablebookingVO.getTABLE_BOOK_NO() +
                    "&TABLE_DATE=" + tablebookingVO.getTABLE_DATE() +
                    "&TABLE_NO=" + tablebookingVO.getTABLE_NO() +
                    "&TABLE_MOR=" + tablebookingVO.getTABLE_MOR() +
                    "&TABLE_EVE=" + tablebookingVO.getTABLE_EVE() +
                    "&TABLE_NIGHT=" + tablebookingVO.getTABLE_NIGHT();
            String url = "/view/bookingtast/2.jsp" + param;
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }


        if ("updatetab".equals(action)) { // 2.jsp的請求
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer TABLE_BOOK_NO = Integer.valueOf(req.getParameter("TABLE_BOOK_NO").trim());


            java.sql.Date TABLE_DATE = java.sql.Date.valueOf(req.getParameter("TABLE_DATE").trim());

            Integer TABLE_NO = Integer.valueOf(req.getParameter("TABLE_NO").trim());


            Integer TABLE_MOR = Integer.valueOf(req.getParameter("TABLE_MOR").trim());
            Integer TABLE_EVE = Integer.valueOf(req.getParameter("TABLE_EVE").trim());
            Integer TABLE_NIGHT = Integer.valueOf(req.getParameter("TABLE_NIGHT").trim());

            /***************************2.開始修改資料*****************************************/
            TablebookingService tabSvc = new TablebookingService();
            TablebookingVO tablebookingVO = tabSvc.updatetab(TABLE_BOOK_NO, TABLE_DATE, TABLE_NO, TABLE_MOR, TABLE_EVE, TABLE_NIGHT);

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            String url = "/view/bookingtast/2.jsp";
            req.setAttribute("tablebookingVO", tablebookingVO);
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);


        }

    }
}
