package feature.emp.controller;

import feature.emp.service.EmpService;
import feature.emp.service.EmpServiceImpl;
import feature.emp.vo.EmpVo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view/emp/EmpListController")
public class EmpListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String test = req.getParameter("test");

        if ("getOneEmp".equals(test)) {
            String str = req.getParameter("empNo");
            Integer empNo = Integer.valueOf(str);

            EmpService empSvc = new EmpServiceImpl();
            EmpVo empVo = empSvc.getOneEmp(empNo);
            req.setAttribute("empVo", empVo);

            String url = "empList.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, resp);
        }

        String all = req.getParameter("getAllEmp");
        if ("getAll".equals(all)) {
            String url = "allEmpList.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, resp);
        }

    }
}