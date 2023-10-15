package feature.emp.controller;

import feature.emp.service.allEmpListService;
import feature.emp.vo.EmpVo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class allEmpListServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        /***************************************修改***************************************/
        if ("getOne_For_Update".equals(action)) {

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數****************************************/
            Integer empNo = Integer.valueOf(req.getParameter("empNo"));

            /***************************2.開始查詢資料****************************************/
            allEmpListService empSvc = new allEmpListService();
            EmpVo empVo = empSvc.getOneEmp(empNo);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            String param = "?empNo="  +empVo.getEmpNo()+
                    "&empName="  +empVo.getEmpName()+
                    "&empGender="    +empVo.getEmpGender()+
                    "&empEmail="+empVo.getEmpEmail()+
                    "&empPwd="    +empVo.getEmpPwd()+
                    "&empPh="   +empVo.getEmpPh()+
                    "&empState=" +empVo.getEmpState();

            String url = "/view/emp/UpdateEmpInput.jsp"+param;
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 UpdateMemInput.jsp
            successView.forward(req, res);
        }



        /***************************************刪除***************************************/
        if ("delete".equals(action)) {

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數***************************************/
            Integer empNo = Integer.valueOf(req.getParameter("empNo"));

            /***************************2.開始刪除資料***************************************/
            allEmpListService empSvc = new allEmpListService();
            empSvc.deleteEmp(empNo);

            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            req.setAttribute("success", "- (刪除成功)");
            String url = "/view/emp/allEmpList.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }


        /***************************************修改完畢***************************************/
        if ("update".equals(action)) {

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            //--------------------編號------------------//
            Integer empNo = Integer.valueOf(req.getParameter("empNo").trim());

            //--------------------姓名------------------//
            String empName = req.getParameter("empName");
            String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (empName == null || empName.trim().length() == 0) {
                errorMsgs.put("memName","會員姓名: 請勿空白");
            } else if(!empName.trim().matches(empNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("memName","會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            //--------------------性別------------------//
            Byte empGender = Byte.valueOf(req.getParameter("empGender"));

            //--------------------信箱------------------//
            String empEmail = req.getParameter("empEmail");
            String empEmailReg = "^[(a-zA-Z0-9_@ .)]*$";
            if (empEmail == null || empEmail.trim().length() == 0) {
                errorMsgs.put("empEmail","信箱: 請勿空白");
            } else if(!empEmail.trim().matches(empEmailReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("empEmail","信箱: 只能是英文字母、數字、_@.和空白");
            }

            //--------------------密碼------------------//
            String empPwd = req.getParameter("empPwd");
            String empPwdReg = "^[(a-zA-Z0-9_@ .)]*$";
            if (empPwd == null || empPwd.trim().length() == 0) {
                errorMsgs.put("empPwd","信箱: 請勿空白");
            } else if(!empPwd.trim().matches(empPwdReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("empPwd","信箱: 只能是英文字母、數字、_@.和空白");
            }

            //--------------------手機------------------//
            String empPh = req.getParameter("empPh");
            String empPhReg = "^[(0-9)]{10}$";
            if (empPh == null || empPh.trim().length() == 0) {
                errorMsgs.put("empPh","手機: 請勿空白");
            } else if(!empPh.trim().matches(empPhReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("empPh","手機: 只能是數字 , 且長度只能是10");
            }

            //--------------------狀態------------------//
            Byte empState = Byte.valueOf(req.getParameter("empState"));





            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                errorMsgs.put("Exception","修改資料失敗:---------------");
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/view/emp/UpdateEmpInput.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            allEmpListService empSvc = new allEmpListService();
            EmpVo empVo = empSvc.updateEmp(empNo, empName, empGender, empEmail, empPwd, empPh, empState );

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("success", "- (修改成功)");
            req.setAttribute("empVo", empVo); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/view/emp/listOneEmp.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }


    }

}