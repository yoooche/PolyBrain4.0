package feature.bid.controller;

import com.google.gson.Gson;
import core.util.CommonUtil;
import feature.bid.dto.BidItemDto;
import feature.bid.service.BidItemPicService;
import feature.bid.service.BidItemPicServiceImpl;
import feature.bid.service.BiddingService;
import feature.bid.service.BiddingServiceImpl;
import feature.bid.vo.BidItemPicVo;
import feature.bid.vo.BidItemVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/general/BidItemList")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BidItemListServlet extends HttpServlet {
    public BiddingService biddingService;
    public BidItemPicService bidItemPicService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        biddingService = new BiddingServiceImpl();
        bidItemPicService = new BidItemPicServiceImpl();
        System.out.println(req);
        String value = req.getParameter("value");
        String action = req.getParameter("action");

        //---------- insert an item into bid_item table ----------
        if("insert".equals(action)){
            String bidItemName = req.getParameter("bidItemName");
            Integer itemClassNo = Integer.valueOf(req.getParameter("itemClassNo"));
            String gamePublisher = req.getParameter("gamePublisher");
            String bidItemDescribe = req.getParameter("bidItemDescribe");

            BidItemVo bidItemVo = new BidItemVo();
            bidItemVo.setBidItemName(bidItemName);
            bidItemVo.setItemClassNo(itemClassNo);
            bidItemVo.setGamePublisher(gamePublisher);
            bidItemVo.setBidItemDescribe(bidItemDescribe);

            biddingService.addAnItem(bidItemVo);
            Integer bidItemNo = bidItemVo.getBidItemNo();

            Collection<Part> parts = req.getParts();
            for(Part part : parts){
                String filename = part.getSubmittedFileName();
                if (filename!= null && filename.length() != 0 && part.getContentType() != null) {
                    InputStream inputStream = part.getInputStream();
                    byte[] file = inputStream.readAllBytes();
                    BidItemPicVo bidItemPicVo = new BidItemPicVo();
                    bidItemPicVo.setBidItemPic(file);
                    bidItemPicVo.setBidItemNo(bidItemNo);
                    biddingService.addPics(bidItemPicVo);
                    inputStream.close();
                }
            }
        }
        if("delete".equals(action)){
            Integer bidItemNo = Integer.valueOf(req.getParameter("bidItemNo"));
            System.out.println(bidItemNo);
            biddingService.removeOneItem(bidItemNo);

            resp.sendRedirect(req.getContextPath() + "/view/bid/BidItemList.jsp");
        }
        if("selectAllPics".equals(action)){ //沒在用
            resp.setContentType("text/html; charset=utf-8");
            Gson gson = new Gson();
            String img = gson.toJson(biddingService.getTableData());
            System.out.println(img);
            PrintWriter out = resp.getWriter();
            out.print(img);
            out.flush();
        }
        if("selectAllBidItem".equals(value)){
            System.out.println("查詢所有競標商品");
//                writePojo2Json(resp, biddingService.viewAll());
            writePojo2Json(resp, biddingService.getTableData());
        }
    }
}
