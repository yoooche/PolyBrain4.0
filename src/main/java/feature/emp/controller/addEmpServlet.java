package feature.emp.controller;

import feature.emp.service.empAddService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class addEmpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求的字符编码为UTF-8
        request.setCharacterEncoding("UTF-8");

        // 获取用户提交的注册信息
        String name = request.getParameter("name");
        Byte gender = Byte.valueOf(request.getParameter("gender"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        Byte state = Byte.valueOf(request.getParameter("state"));

        // 创建RegistService实例
        empAddService empaddservice = new empAddService();

        // 在RegistService中执行注册业务逻辑
        String result = empaddservice.Add(name, gender, email, password, phone, state);

        if ("success".equals(result)) {
            // 注册成功，重定向到注册成功页面
            response.sendRedirect(request.getContextPath() + "/view/emp/allEmpList.jsp");
        } else {
            // 注册失败，设置错误消息并返回到注册页面
            response.sendRedirect(request.getContextPath() + "/view/emp/addempfail.html");
        }
    }
}