package feature.mem.controller;


import feature.mem.service.RegistService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class addmemServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求的字符编码为UTF-8
        request.setCharacterEncoding("UTF-8");

        // 获取用户提交的注册信息
        String name = request.getParameter("name");
        String pid = request.getParameter("pid");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String birth = request.getParameter("birth");

        // 创建RegistService实例
        RegistService registService = new RegistService();

        // 在RegistService中执行注册业务逻辑
        String result = registService.registerMember(name, pid, gender, email, password, phone, address, birth);

        if ("success".equals(result)) {
            // 注册成功，重定向到注册成功页面
            response.sendRedirect(request.getContextPath() + "/view/member/listAllmem.jsp");
        } else {
            // 注册失败，设置错误消息并返回到注册页面
            response.sendRedirect(request.getContextPath() + "/view/member/addmemfail.html");
        }
    }
}