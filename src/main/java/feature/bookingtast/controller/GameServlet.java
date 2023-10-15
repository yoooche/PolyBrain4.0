package feature.bookingtast.controller;

import com.google.gson.Gson;
import feature.bookingtast.service.BookinglistService;
import feature.bookingtast.service.GametableService;
import feature.bookingtast.service.TablebookingService;
import feature.bookingtast.vo.GametableVO;
import feature.bookingtast.vo.TablebookingVO;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.*;


@WebServlet("/bookingtast/GameServlet")
public class GameServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        // action equals(==) insert的方法
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            // 轉換String到Date

            // 這裡的關鍵是 JSP的資料串是name="ename"

            java.sql.Date TABLE_DATE = null;
            try {
                TABLE_DATE = java.sql.Date.valueOf(req.getParameter("TABLE_DATE").trim());
            } catch (IllegalArgumentException e) {
                errorMsgs.put("TABLE_DATE", "預約日期: 請勿空白");
            }
            System.out.println("Received TABLE_DATE: " + req.getParameter("TABLE_DATE"));

            // 找出TABLE_NO的參數請求 沒有這段就沒有東西
            Integer TABLE_NO = null;
            try {
                TABLE_NO = Integer.valueOf(req.getParameter("TABLE_NO").trim());
                System.out.println(TABLE_NO);
            } catch (NumberFormatException e) {
                errorMsgs.put("TABLE_NO", "Table number must be an integer");
            }

            Integer PERIOD_TIME = null;
            try {
                PERIOD_TIME = Integer.valueOf(req.getParameter("PERIOD_TIME").trim());
                System.out.println(PERIOD_TIME);
            } catch (NumberFormatException e) {
                errorMsgs.put("PERIOD_TIME", "Table number must be an integer");
            }

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/view/bookingtast/membertest.jsp");
                failureView.forward(req, res);
                return;
            }
            //前端的name名稱是發送請求的KEY有這個KEY才能打開servlet的大門去往後找
            String selectedDateValue = req.getParameter("selectedDate");

            /*************************** 2.開始新增資料 ***************************************/
            BookinglistService glistSvc = new BookinglistService();
            glistSvc.addBookinglist(TABLE_NO, TABLE_DATE, PERIOD_TIME);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/view/head/index.html"; // 新增成功跑到這個畫面
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉移到listAllEmp.jsp
            successView.forward(req, res);

            /*************************** 調用取得桌子資料 ***************************************/
//            public GametableVO getAvailablePeriods(java.sql.Date TABLE_DATE, Integer TABLE_NO) {
//                // 實現查詢功能，這裡您可能需要調用相對應的DAO
//                // 返回查詢結果
//            }

                //這裡由於前面已經使用useBean直接去調用service內容所以是廢扣

        }
    }
}
