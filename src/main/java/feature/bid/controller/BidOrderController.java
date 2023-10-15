package feature.bid.controller;

import feature.bid.service.BiddingService;
import feature.bid.service.BiddingServiceImpl;
import feature.bid.vo.BidOrderVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/loginRequired/bidOrderCreate")
public class BidOrderController extends HttpServlet {
    private BiddingService biddingService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bidEventNo = req.getParameter("bidEventId");
        Integer bidEventParam = Integer.valueOf(bidEventNo);
        HttpSession session = req.getSession();
        Integer memNo = (Integer) session.getAttribute("memNo");
        System.out.println(memNo);
        biddingService = new BiddingServiceImpl();
        BidOrderVo bidOrderVo =  biddingService.orderWithoutBid(bidEventParam, memNo);
        System.out.println(bidOrderVo);
        writePojo2Json(resp, bidOrderVo);
    }
}
