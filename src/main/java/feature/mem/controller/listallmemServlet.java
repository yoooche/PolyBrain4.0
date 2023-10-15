package feature.mem.controller;

import feature.mem.service.listallmemService;
import feature.mem.vo.MemVo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class listallmemServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        /***************************************修改***************************************/
        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求    /修改/

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數****************************************/
            Integer memNo = Integer.valueOf(req.getParameter("memNo"));

            /***************************2.開始查詢資料****************************************/
            listallmemService memSvc = new listallmemService();
            MemVo memberVo = memSvc.getOneMem(memNo);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            String param = "?memNo="  +memberVo.getMemNo()+
                    "&memName="  +memberVo.getMemName()+
                    "&memPid="    +memberVo.getMemPid()+
                    "&memEmail="+memberVo.getMemEmail()+
                    "&memPh="    +memberVo.getMemPh()+
                    "&memAddress="   +memberVo.getMemAddress()+
                    "&memBirth=" +memberVo.getMemBirth()+
                    "&memAuth="   +memberVo.getMemAuth();
            String url = "/view/member/UpdateMemInput.jsp"+param;
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 UpdateMemInput.jsp
            successView.forward(req, res);
        }



        /***************************************刪除***************************************/
        if ("delete".equals(action)) {
            Integer memNo = Integer.valueOf(req.getParameter("memNo"));
            listallmemService memSvc = new listallmemService();
            memSvc.deleteMem(memNo);

            String url = "/view/member/listAllmem.jsp";
            req.getRequestDispatcher(url).forward(req, res);
        }


        /***************************************修改完畢***************************************/
        if ("update".equals(action)) {

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            //--------------------編號------------------//
            Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());

            //--------------------姓名------------------//
            String memName = req.getParameter("memName");
            String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (memName == null || memName.trim().length() == 0) {
                errorMsgs.put("memName","會員姓名: 請勿空白");
            } else if(!memName.trim().matches(memNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memName","會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            //--------------------身分證------------------//
            String memPid = req.getParameter("memPid");
            String memPidReg = "^[(a-zA-Z0-9)]{10}$";
            if (memPid == null || memPid.trim().length() == 0) {
                errorMsgs.put("memPid","身分證: 請勿空白");
            } else if(!memPid.trim().matches(memPidReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memPid","身分證: 只能是英文字母、數字 , 且長度只能是10");
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

            //--------------------地址------------------//
            String memAddress = req.getParameter("memAddress");
            String memAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9, )]*$";
            if (memAddress == null || memAddress.trim().length() == 0) {
                errorMsgs.put("memAddress","地址: 請勿空白");
            } else if(!memAddress.trim().matches(memAddressReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memAddress","地址: 只能是英文字母、數字、中文、,空白");
            }

            //--------------------生日------------------//
            java.sql.Date memBirth = null;
            try {
                memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
            } catch (IllegalArgumentException e) {
                errorMsgs.put("memBirth","生日: 請勿空白");
            }

            //--------------------權限------------------//
            Byte memAuth = Byte.valueOf(req.getParameter("memAuth"));




            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                errorMsgs.put("Exception","修改資料失敗:---------------");
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/view/member/UpdateMemInput.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            listallmemService memSvc = new listallmemService();
            MemVo memVo = memSvc.updateMem(memNo, memName, memPid, memEmail, memPh, memAddress, memBirth, memAuth);

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("success", "- (修改成功)");
            req.setAttribute("memVo", memVo); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/view/member/Member_Information.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }

        /***************************************修改完畢***************************************/
        if ("update1".equals(action)) {

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            //--------------------編號------------------//
            Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());

            //--------------------姓名------------------//
            String memName = req.getParameter("memName");
            String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (memName == null || memName.trim().length() == 0) {
                errorMsgs.put("memName","會員姓名: 請勿空白");
            } else if(!memName.trim().matches(memNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memName","會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            //--------------------身分證------------------//
            String memPid = req.getParameter("memPid");
            String memPidReg = "^[(a-zA-Z0-9)]{10}$";
            if (memPid == null || memPid.trim().length() == 0) {
                errorMsgs.put("memPid","身分證: 請勿空白");
            } else if(!memPid.trim().matches(memPidReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memPid","身分證: 只能是英文字母、數字 , 且長度只能是10");
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

            //--------------------地址------------------//
            String memAddress = req.getParameter("memAddress");
            String memAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9, )]*$";
            if (memAddress == null || memAddress.trim().length() == 0) {
                errorMsgs.put("memAddress","地址: 請勿空白");
            } else if(!memAddress.trim().matches(memAddressReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memAddress","地址: 只能是英文字母、數字、中文、,空白");
            }

            //--------------------生日------------------//
            java.sql.Date memBirth = null;
            try {
                memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
            } catch (IllegalArgumentException e) {
                errorMsgs.put("memBirth","生日: 請勿空白");
            }

            //--------------------權限------------------//
            Byte memAuth = Byte.valueOf(req.getParameter("memAuth"));




            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                errorMsgs.put("Exception","修改資料失敗:---------------");
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/view/member/UpdateMemInput.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            listallmemService memSvc = new listallmemService();
            MemVo memVo = memSvc.updateMem(memNo, memName, memPid, memEmail, memPh, memAddress, memBirth, memAuth);

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("success", "- (修改成功)");
            req.setAttribute("memVo", memVo); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/view/member/listOneMem.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }

    }

}