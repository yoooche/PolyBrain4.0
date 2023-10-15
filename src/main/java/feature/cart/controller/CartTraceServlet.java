package feature.cart.controller;


import feature.cart.service.CartTraceService;
import feature.cart.vo.CartItemImgDTO;
import feature.cart.vo.CartTraceVO;
import feature.item.dao.impl.ItemDAOimplPeter;
import feature.item.vo.Item;
import feature.mail.service.MailService;
import feature.mem.dao.MemDaoImpl;
import feature.mem.vo.MemVo;
import feature.order.service.OrderService;
import feature.order.vo.ItemOrderDetailVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/loginRequired/ConfirmOrder")//這要確認一下
public class CartTraceServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        System.out.println("ABCDEFG:::"+action); //這邊在做一個判斷式

        if ("getAll".equals(action)) {
            Integer memNo = (Integer)session.getAttribute("memNo");
            System.out.println(memNo);

            CartTraceService cartTraceService = new CartTraceService();
            List<CartItemImgDTO> cartItemImgDTOList = cartTraceService.getAllCartItem(memNo); //用會員編號查商品編號 會員編號 數量的資訊

            session.setAttribute("cartItemImgDTOList", cartItemImgDTOList);

            if (cartItemImgDTOList.size() == 0) {
                RequestDispatcher failureView = req.getRequestDispatcher(""); //這要記得改改改
                failureView.forward(req, res);
                return;
            }

        }


        if ("orderConfirm".matches(action)) {
            Integer memNo = (Integer)session.getAttribute("memNo");
            HashMap<String, String> errorMsgs = new HashMap<String, String>();
            String receiverName = req.getParameter("receiverName").trim();
            if (receiverName == null || receiverName.trim().isEmpty()) {
                errorMsgs.put("receiverName", "請填寫收件人姓名");
            }

            String county = req.getParameter("county");
            System.out.println(county);
            String district = req.getParameter("district");
            System.out.println(district);


            String receiverAddress = req.getParameter("receiverAddress").trim();
            if (receiverAddress == null || receiverAddress.trim().isEmpty()) {
                errorMsgs.put("receiverAddress", "請填寫收件地址");
            }    //要記得地址欄不得為空 jsp input 最好還是有值

            receiverAddress = county + district + receiverAddress;

            String receiverPhone = req.getParameter("receiverPhone").trim();
            String rPhoneReg = "^09[0-9]{8}$";
            if (receiverPhone == null || receiverPhone.trim().isEmpty()) {
                errorMsgs.put("receiverPhone", "收件人電話不為空");
            } else if (!receiverPhone.trim().matches(rPhoneReg)) {
                errorMsgs.put("receiverPhone", "手機號碼格式不正確");
            }

            String receiverMethodTemp = req.getParameter("receiverMethod");
            if (receiverMethodTemp == null || receiverMethodTemp.matches("default")) {
                errorMsgs.put("receiverMethod", "你沒有選擇寄送方式喔! ");
            }
            Integer receiverMethod = null;
            switch (receiverMethodTemp) {
                case "mail":
                    receiverMethod = 0;
                    break;
                case "storePickup":
                    receiverMethod = 1;
                    break;
            }

            //之後改成錢包餘額 > 總金額才可以結帳 若小於就無法跳至下一頁(成功畫面) return
            if (false) {
                errorMsgs.put("noConfirmOrder", "付款失敗");
                System.out.println("未完成付款(true)");
            }

            if (!errorMsgs.isEmpty()) {

                req.setAttribute("errorMsgs", errorMsgs);
                String url = "/PolyBrain/loginRequired/ConfirmOrder";
                RequestDispatcher failureView = req.getRequestDispatcher(url);
                failureView.forward(req, res);
                return;
            }

            Integer orderTotal = Integer.valueOf(req.getParameter("orderTotal"));
            System.out.println("orderTotal"+orderTotal);


            Integer orderState = 0;

            OrderService orderService = new OrderService();
            Integer orderNo = orderService.addOrder(memNo, orderTotal, orderState, receiverName, receiverAddress,
                    receiverPhone, receiverMethod);   //訂單編號

            CartTraceService cartTraceService = new CartTraceService();
            List<CartItemImgDTO>  cartItemImgDTOList = cartTraceService.getAllCartItem(memNo);

            orderService.addAnOrderDetail(cartItemImgDTOList, orderNo, memNo);
            //前端 History把session清起來

            new MailService().sendMail(orderNo);

            String url = "/view/order/listAllOrder.jsp"; //到下一頁
            RequestDispatcher View = req.getRequestDispatcher(url);
            View.forward(req, res);

        }

    }

}
