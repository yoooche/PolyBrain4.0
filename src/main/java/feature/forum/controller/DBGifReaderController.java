package feature.forum.controller;

import feature.forum.service.ArtService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/Art/DBGifReader")
public class DBGifReaderController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

        res.setContentType("image/gif");
        ServletOutputStream out = res.getOutputStream();

        try {
            Integer artNo = Integer.valueOf(req.getParameter("artNo"));
            ArtService artSvc = new ArtService();
            out.write(artSvc.getOneArt(artNo).getUpFiles());
        }catch (Exception e){
            InputStream in =getServletContext().getResourceAsStream("/view/NoData/nopic.jpg");
            byte[] buf = new byte[in.available()];
            in.read(buf);
            out.write(buf);
            in.close();
        }


    }
}
