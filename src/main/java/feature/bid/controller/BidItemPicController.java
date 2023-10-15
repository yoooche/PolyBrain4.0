package feature.bid.controller;

import feature.bid.service.BiddingService;
import feature.bid.service.BiddingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serial;

@WebServlet("/bid/BidItemPic")
public class BidItemPicController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/gif");
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        try {
            Integer bidItemNo = Integer.valueOf(req.getParameter("bidItemNo"));
            BiddingService biddingService = new BiddingServiceImpl();
//            servletOutputStream.write(.getBytes());
        } catch (Exception e){
            InputStream inputStream = getServletContext().getResourceAsStream("/view/bid/images/null.jpg");
            byte[] nullPic = new byte[inputStream.available()];
            inputStream.read(nullPic);
            servletOutputStream.write(nullPic);
            inputStream.close();
        }
    }
}
