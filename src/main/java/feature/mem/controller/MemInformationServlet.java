package feature.mem.controller;

import feature.mem.service.MemInformationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MemInformationServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");


        if ("update".equals(action)) {

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            //--------------------編號------------------//
            Integer memNo = Integer.valueOf((req.getParameter("memNo")));

            //--------------------姓名------------------//
            String memName = req.getParameter("memName");
            String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (memName == null || memName.trim().length() == 0) {
                errorMsgs.put("memName","姓名: 請勿空白");
            } else if(!memName.trim().matches(memNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memName","姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            //--------------------信箱------------------//
            String memEmail = req.getParameter("memEmail");
            String memEmailReg = "^[(a-zA-Z0-9_@ .)]*$";
            if (memEmail == null || memEmail.trim().length() == 0) {
                errorMsgs.put("memEmail","信箱: 請勿空白");
            } else if(!memEmail.trim().matches(memEmailReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memEmail","信箱: 只能是英文字母、數字、_@.和空白");
            }

            //--------------------手機------------------//
            String memPh = req.getParameter("memPh");
            String memPhReg = "^[(0-9)]{10}$";
            if (memPh == null || memPh.trim().length() == 0) {
                errorMsgs.put("memPh","手機: 請勿空白");
            } else if(!memPh.trim().matches(memPhReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memPh","手機: 只能是數字 , 且長度只能是10");
            }


            //--------------------密碼------------------//
            String memPwd = req.getParameter("memPwd");
            String memPwdReg = "^[(a-zA-Z0-9)]*$";
            if (memPwd == null || memPwd.trim().length() == 0) {
                errorMsgs.put("memPwd","密碼: 請勿空白");
            } else if(!memPwd.trim().matches(memPwdReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memPwd","密碼: 只能是數字跟英文");
            }




            //--------------------身分證------------------//
            String memPid = req.getParameter("memPid");
            String memPidReg = "^[(a-zA-Z0-9)]{10}$";
            if (memPid == null || memPid.trim().length() == 0) {
                errorMsgs.put("memPid","身分證: 請勿空白");
            } else if(!memPid.trim().matches(memPidReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memPid","身分證: 只能是英文字母、數字 , 且長度只能是10");
            }



            //--------------------地址------------------//
            String memAddress = req.getParameter("memAddress");
            String memAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9, )]*$";
            if (memAddress == null || memAddress.trim().length() == 0) {
                errorMsgs.put("memAddress","地址: 請勿空白");
            } else if(!memAddress.trim().matches(memAddressReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memAddress","地址: 只能是英文字母、數字、中文、空白和,");
            }

            //--------------------生日------------------//
            java.sql.Date memBirth = null;
            try {
                memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
            } catch (IllegalArgumentException e) {
                errorMsgs.put("memBirth","生日: 請勿空白");
            }

            //--------------------性別------------------//
            Byte memGender = Byte.valueOf(req.getParameter("memGender"));



            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                errorMsgs.put("Exception","修改資料失敗:---------------");
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/view/member/UpdateMemInput.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            MemInformationService memSvc = new MemInformationService();
            memSvc.updateInformation(memNo ,memName , memEmail , memPh, memPwd, memPid, memAddress, memBirth , memGender );

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            res.sendRedirect(req.getContextPath() + "/view/member/Member_Information.jsp");
        }





    }
}
