package feature.order.controller;

import feature.order.service.OrderService;
import feature.order.vo.ItemOrderVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/view/order/order.tw")
public class ItemOrderServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res )
            throws ServletException, IOException
    {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        HttpSession session  = req.getSession();
        req.setCharacterEncoding("UTF-8");
        System.out.println("helloMoto");

        String action = req.getParameter("test1");
        System.out.println("action"+action);
        if("getAllOrder".equals(action)){
            List<ItemOrderVO> orderVOList = new OrderService().getAll();
            session.setAttribute("orderVOList", orderVOList);
            System.out.println("你走後你別在笑我");
            String url = "/view/order/listAllOrder.jsp";
            RequestDispatcher red =req.getRequestDispatcher(url);
            red.forward(req, res);
        }

        if("getOne_For_Display".equals(action)){
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
//
//
//
            String str = req.getParameter("orderNo");
            System.out.println(str);
            if( str == null || (str.trim()).length() == 0){
                errorMsgs.add("請輸入訂單編號");
            }
//
            if(!errorMsgs.isEmpty()){                    //這裡的斜線(第一條)代表專案路徑(webapp)
                RequestDispatcher failureView = req.getRequestDispatcher("/view/order/select.jsp");
                failureView.forward(req, res);
                return;        //程式中斷
            }

            Integer orderNo = null;
//            Integer orderNo = Integer.valueOf(str);
//            orderNo = Integer.valueOf(str);

            try {
                orderNo = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("訂單編號格式不對喔~");
            }
//
            if(!errorMsgs.isEmpty()){
                RequestDispatcher failureView = req.getRequestDispatcher("/view/order/select.jsp");
                failureView.forward(req, res);
                return;        //程式中斷
            }
//
            OrderService odSvc = new OrderService();
            ItemOrderVO itemordervo = odSvc.getOneOrder(orderNo);
            if(itemordervo == null){
                errorMsgs.add("查無資料");
            }
            if(!errorMsgs.isEmpty()){
                RequestDispatcher failureView = req.getRequestDispatcher("/view/order/select.jsp");
                failureView.forward(req, res);
                return;        //程式中斷
            }

            req.setAttribute("itemorder", itemordervo);
            System.out.println(itemordervo);
            String url = "/view/order/listOneEmp.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);

        }


        if("getOne_For_Update".equals(action)){
            List<String> errorMsgs =  new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            Integer orderNo = Integer.valueOf(req.getParameter("orderNo").trim());
            System.out.println(orderNo);
            OrderService odSvc = new OrderService();
            ItemOrderVO itemordervo = odSvc.getOneOrder(orderNo);
            if(itemordervo == null){
                return;
            }

            req.setAttribute("itemorder", itemordervo);
            String url = "/view/order/updateOrder_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if("update".equals(action)){

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            Integer orderNo = Integer.valueOf(req.getParameter("orderNo"));
            Integer memNo = Integer.valueOf(req.getParameter("memNo"));


            String receiverName = req.getParameter("receiverName").trim();
            String rnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9-)]{2,10}$";
            if(receiverName == null || receiverName.trim().length()==0){
                errorMsgs.add("收件人姓名請勿空白");

            }else if (!receiverName.trim().matches(rnameReg)){
                errorMsgs.add("收件人姓名只能是中英文,數字,- 且長度介於2-10之間");
            }

            String receiverAddress = req.getParameter("receiverAddress").trim();
            String rAddReg = "^(.{0,30})(.*?)$";
            if(receiverAddress == null || receiverAddress.trim().length() == 0){
                errorMsgs.add("收件地址不為空");
            }else if (!receiverAddress.trim().matches(rAddReg)){
                errorMsgs.add("最好你家地址這麼長");
            }

            String receiverPhone = req.getParameter("receiverPhone").trim();
            String rPhoneReg ="^09[0-9]{8}$";
            if(receiverPhone == null || receiverPhone.trim().length() == 0){
                errorMsgs.add("收件人電話不為空");
            } else if (!receiverPhone.trim().matches(rPhoneReg)) {
                errorMsgs.add("你是在亂打膩 = =");
            }

            OrderService odsvc = new OrderService();
            ItemOrderVO oldVO = odsvc.getOneOrder(orderNo);

            ItemOrderVO itemordervo = new ItemOrderVO();
            itemordervo.setOrderNo(orderNo);
            itemordervo.setMemNo(memNo);
            itemordervo.setReceiverName(receiverName);
            itemordervo.setReceiverAddress(receiverAddress);
            itemordervo.setReceiverPhone(receiverPhone);

//            itemordervo.setReceiverMethod(oldVO.getReceiverMethod());
//            itemordervo.setOrderState(oldVO.getOrderState());
//            itemordervo.setOrderTotal(oldVO.getOrderTotal());
//            itemordervo.setTranTime(oldVO.getTranTime());

            if(!errorMsgs.isEmpty()){
                req.setAttribute("itemorder", itemordervo);
                RequestDispatcher failureView = req.getRequestDispatcher("/view/order/updateOrder_input.jsp");
                failureView.forward(req, res);
                return;
            }


            ItemOrderVO new123 = odsvc.updateBackOrder(itemordervo);

            req.setAttribute("itemorder", new123);
            String url = "/view/order/listOneEmp.jsp";
            RequestDispatcher successview = req.getRequestDispatcher(url);
            successview.forward(req, res);
        }

        if("delete".equals(action)){
            Integer orderNo = Integer.valueOf(req.getParameter("orderNo"));
            System.out.println("有來這嗎"+orderNo);
            OrderService odsvc = new OrderService();
            odsvc.deleteById(orderNo);
            String url = "/view/order/listAllOrder.jsp";
            RequestDispatcher requestDispatcher =req.getRequestDispatcher(url);
            requestDispatcher.forward(req, res);
        }
    }
}
