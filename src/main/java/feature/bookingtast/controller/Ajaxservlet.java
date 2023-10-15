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
import java.util.List;


@WebServlet("/bookingtast/Ajaxservlet")
@MultipartConfig
public class Ajaxservlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");


        TablebookingService tabSvc = new TablebookingService();
        List<TablebookingVO> list = tabSvc.getAll();

        req.setAttribute("tablebookingList", list);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/bookingtast/3.jsp");
        dispatcher.forward(req, res);
    }
    }










