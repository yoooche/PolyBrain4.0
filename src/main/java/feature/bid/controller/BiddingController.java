package feature.bid.controller;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.TableImpl;
import feature.bid.service.BiddingService;
import feature.bid.service.BiddingServiceImpl;
import feature.bid.vo.BidEventVo;
import feature.bid.vo.BidItemVo;
import feature.item.service.ItemClassService;
import feature.item.service.ItemClassServiceImpl;
import feature.item.vo.ItemClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.json2PojoForDatetime;

@WebServlet("/general/bidding")
public class BiddingController extends HttpServlet {
    private BiddingService biddingService;
    private ItemClassService itemClassService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String bidEventId = req.getParameter("bidEventId"); // url的QueryString
        String bidEventNo_table = req.getParameter("bidEventNo");

        biddingService = new BiddingServiceImpl();
        itemClassService = new ItemClassServiceImpl();

        if ("closed".equals(message)) { //結標時間到，發請求到後端自動生成訂單
            Integer bidEventNo = Integer.valueOf(bidEventId);
            biddingService.createOneOrder(bidEventNo);
        }
        if ("rangeBarSetting".equals((message))) { //讓 Range Bar自動根據競標活動的編號設置對應的底價、直購價等
            Integer bidEventNo = Integer.valueOf(bidEventId);
            BidEventVo bidEventVo = biddingService.getEventByNo(bidEventNo);
            Integer floorPrice = bidEventVo.getFloorPrice();
            Integer directivePrice = bidEventVo.getDirectivePrice();
            Integer leastOffers = bidEventVo.getLeastOffers();
            Map<String, Integer> priceRange = new HashMap<>();
            priceRange.put("floorPrice", floorPrice);
            priceRange.put("directivePrice", directivePrice);
            priceRange.put("leastOffers", leastOffers);
            resp.setContentType("application/json");
            Gson gson = new Gson();
            String jsonPrice = gson.toJson(priceRange);
            PrintWriter out = resp.getWriter();
            out.print(jsonPrice);
            out.flush();
        }
        if ("getBiddingTimer".equals(message)) { //根據活動編號自動帶入競標時間
            Integer bidEventNo = Integer.valueOf(bidEventId);
            Map<String, String> timer = biddingService.getStartTimeByNo(bidEventNo);
            Gson gson = new Gson();
            String jsonTimer = gson.toJson(timer);
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(jsonTimer);
            out.flush();
        }
        if ("selectItem".equals(message)) { //在新增競標活動時，選競標商品用的下拉式選單可以自動生成內容
            List<BidItemVo> allItem = biddingService.viewAll();
            Gson gson = new Gson();
            String jsonItem = gson.toJson(allItem);
            resp.setContentType("text/html; charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.print(jsonItem);
            out.flush();
        }
        if ("selectClass".equals(message)) { //在新增競標商品時，選競標類別用的下拉式選單可以自動生成內容
            List<ItemClass> itemClass = itemClassService.getAllClasses();
            Gson gson = new Gson();
            String jsonItemClass = gson.toJson(itemClass);
            resp.setContentType("text/html; charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.print(jsonItemClass);
            out.flush();
        }
        if ("selectAllEvent".equals(message)) {
            List<BidEventVo> allEvent = biddingService.viewAllEvent();
            Gson gson = new Gson();
            String jsonEvent = gson.toJson(allEvent);
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(jsonEvent);
            out.flush();
        }
        if ("bidEventEditByPk".equals(message)) {
            Integer bidEventNo_edit = Integer.valueOf(bidEventNo_table);
            BidEventVo bidEventVo = biddingService.getEventByNo(bidEventNo_edit);
            Gson gson = new Gson();
            String jsonEvent = gson.toJson(bidEventVo);
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(jsonEvent);
            out.flush();
        }
        if ("deleteEvent".equals(message)) {
            Integer bidEventNo_delete = Integer.valueOf(bidEventNo_table);
            biddingService.removeEventById(bidEventNo_delete);
            Map<String, Integer> success = new HashMap<>();
            success.put("remove", 1);
            Gson gson = new Gson();
            String jsonSuccess = gson.toJson(success);
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(jsonSuccess);
            out.flush();
        }
        if ("getBiddingItemPics".equals(message)) {
            Integer bidEventNo = Integer.valueOf(bidEventId);
            List<byte[]> bidItemPics = biddingService.getItemPicsByEveNo(bidEventNo);
            resp.setContentType("application/json");
            Set<String> bidMap = new HashSet<>();

            PrintWriter out = resp.getWriter();
            for (byte[] bidItemPic : bidItemPics) {
                String base64Img = Base64.getEncoder().encodeToString(bidItemPic);
                bidMap.add(base64Img);
            }
            Gson gson = new Gson();
            String jsonPic = gson.toJson(bidMap);
            out.write(jsonPic);
            out.close();
        }
        if ("getBiddingItemInfo".equals(message)) { //商品詳情頁面的資訊
            Integer bidEventNo = Integer.valueOf(bidEventId);
            BidEventVo bidEventVo = biddingService.getEventByNo(bidEventNo);
            String jsonItemInfo = getString(bidEventVo);
            resp.setContentType("text/html; charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.print(jsonItemInfo);
            out.flush();
        }
        if ("addAnEvent".equals(message)) {
            System.out.println("有進到這邊");
            Integer bidItemNo = Integer.valueOf(req.getParameter("bidItemNo"));
            Timestamp startTime = Timestamp.valueOf(req.getParameter("startTime"));
            Timestamp closeTime = Timestamp.valueOf(req.getParameter("closeTime"));
            Integer floorPrice = Integer.valueOf(req.getParameter("floorPrice"));
            Integer leastOffers = Integer.valueOf(req.getParameter("leastOffers"));
            Integer directivePrice = Integer.valueOf(req.getParameter("directivePrice"));

            BidEventVo bidEventVo = new BidEventVo();
            bidEventVo.setBidItemNo(bidItemNo);
            bidEventVo.setStartTime(startTime);
            bidEventVo.setCloseTime(closeTime);
            bidEventVo.setFloorPrice(floorPrice);
            bidEventVo.setLeastOffers(leastOffers);
            bidEventVo.setDirectivePrice(directivePrice);
            System.out.println("快到了");
            biddingService.addAnEvent(bidEventVo);

        }


        BidEventVo bidEventVo = json2PojoForDatetime(req, BidEventVo.class);
        biddingService.addAnEvent(bidEventVo);
    }


    private static String getString(BidEventVo bidEventVo) {
        BidItemVo bidItemVo = bidEventVo.getBidItemVo();
        ItemClass itemClass = bidItemVo.getItemClass();

        String bidItemName = bidItemVo.getBidItemName();
        String gamePublisher = bidItemVo.getGamePublisher();
        String bidItemDescribe = bidItemVo.getBidItemDescribe();
        String itemClassName = itemClass.getItemClassName();
        Integer floorPrice = bidEventVo.getFloorPrice();
        Integer directivePrice = bidEventVo.getDirectivePrice();

        Map<String, Object> bidItemInfo = new HashMap();
        bidItemInfo.put("bidItemName", bidItemName);
        bidItemInfo.put("gamePublisher", gamePublisher);
        bidItemInfo.put("bidItemDescribe", bidItemDescribe);
        bidItemInfo.put("itemClassName", itemClassName);
        bidItemInfo.put("floorPrice", floorPrice);
        bidItemInfo.put("directivePrice", directivePrice);

        Gson gson = new Gson();
        String jsonItemInfo = gson.toJson(bidItemInfo);
        return jsonItemInfo;
    }


}
