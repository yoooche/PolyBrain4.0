package feature.forum.controller;

import feature.forum.service.ArtService;
import feature.forum.service.RpyService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
@WebServlet("/RpyServlet")
public class RpyServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        doPost(req,res);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("insert".equals(action)) {
            System.out.println("______________________");

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            Integer memNo = Integer.valueOf(req.getParameter("memNo"));

            Integer artNo = Integer.valueOf(req.getParameter("artNo"));


            String rpyCon = req.getParameter("rpyCon");
            if (rpyCon == null || rpyCon.trim().length() == 0) {
                errorMsgs.put(rpyCon, "留言內容請勿空白");
            }
            String artNoParam = req.getParameter("artNo");

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/view/forum/innerpage/detail.jsp?artNo=" + artNoParam);
                failureView.forward(req, res);
                return;
            }
//            InputStream in = req.getPart("upFiles").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
//            byte[] upFiles = null;
//            if (in.available() != 0) {
//                upFiles = new byte[in.available()];
//                in.read(upFiles);
//                in.close();
//            }

            RpyService rpySvc = new RpyService();
            rpySvc.addRpy(memNo,artNo, rpyCon);

            req.setAttribute("success", "-(新增成功)");

            String url = "/view/forum/innerpage/detail.jsp?artNo=" + artNoParam;
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);

        }
        if("delete".equals(action)){
            System.out.println("刪除++++++++++++++++");
            Map<String,String> errorMsgs =new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs",errorMsgs);

            Integer rpyNo =Integer.valueOf(req.getParameter("rpyNo"));

            RpyService rpySvc =new RpyService();
            rpySvc.deleteRpy(rpyNo);

            req.setAttribute("success","-(刪除成功)");

            String artNoParam = req.getParameter("artNo");
            String url = "/view/forum/innerpage/detail.jsp?artNo=" + artNoParam;
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req,res);
        }
    }

}
