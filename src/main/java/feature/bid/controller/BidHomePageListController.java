package feature.bid.controller;

import com.google.gson.Gson;
import feature.bid.dto.BidItemListDto;
import feature.bid.service.BiddingService;
import feature.bid.service.BiddingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/general/bidHomePageList")
public class BidHomePageListController extends HttpServlet {
    private BiddingService biddingService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        biddingService = new BiddingServiceImpl();
        String message = req.getParameter("message");
        System.out.println(message);
        switch (message){
            case "getBidHomePageList":
                System.out.println("yyy");
                writePojo2Json(resp, biddingService.getHomePageList());
        }

    }
}
