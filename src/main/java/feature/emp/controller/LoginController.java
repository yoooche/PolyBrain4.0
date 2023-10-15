package feature.emp.controller;

import feature.emp.service.EmpService;
import feature.emp.service.EmpServiceImpl;
import feature.emp.service.back_loginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/view/emp/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        back_loginService authService = new back_loginService();

        boolean isValidUser = authService.empLogin(email, password);

        if (isValidUser) {
            int empNo = authService.getEmpNoByEmail(email);

            HttpSession session = request.getSession();
            session.setAttribute("account", empNo);
            // 登入成功，執行相關操作（例如：設定Session、重定向到其他頁面）
            response.sendRedirect(request.getContextPath() + "/view/emp/allEmpList.jsp");
        } else {
            // 登入失敗，可以重新導向回登入頁面或顯示錯誤信息
            String encodedMessage = URLEncoder.encode(" ", "UTF-8");
            response.sendRedirect(request.getContextPath() +"/view/emp/login.jsp" + encodedMessage);
        }





    }
}