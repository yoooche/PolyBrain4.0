package feature.bid.controller;

import core.coreVO.Core;
import feature.bid.service.BidItemPicService;
import feature.bid.service.BidItemPicServiceImpl;
import feature.bid.service.BiddingService;
import feature.bid.service.BiddingServiceImpl;
import feature.bid.vo.BidItemVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/general/bidItemRemove")
public class BidItemRemove extends HttpServlet {

    private BidItemPicService bidItemPicService;
    private BiddingService biddingService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bidItemPicService = new BidItemPicServiceImpl();
        biddingService = new BiddingServiceImpl();
        System.out.println(req);

        Integer bidItemNo = json2Pojo(req, BidItemVo.class).getBidItemNo();
        System.out.println("Test");
        Core core = new Core();
        if (bidItemNo == null) {
            core.setMessage("無該產品編號");
            core.setSuccess(false);
        } else {
            core.setSuccess(biddingService.removeOneItem(bidItemNo));
            core.setSuccess(bidItemPicService.removeByBidItemNo(bidItemNo));
            System.out.println(core);
        }
        writePojo2Json(resp, core);
    }
}
