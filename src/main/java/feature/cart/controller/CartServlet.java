package feature.cart.controller;

import com.google.gson.Gson;
import feature.cart.dao.impl.CartTraceDAOimpl;
import feature.cart.service.CartTraceService;
import feature.cart.vo.CartItemImgDTO;
import feature.cart.vo.CartTraceVO;
import feature.item.dao.impl.ItemDAOimplPeter;
import feature.item.service.ItemService;
import feature.item.service.ItemServiceImpl;
import feature.item.vo.Item;
import feature.item.vo.ItemImg;
import feature.mem.vo.MemVo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/loginRequired/CartServlet")
public class CartServlet extends HttpServlet {
    CartTraceService cartTraceService = new CartTraceService();
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        System.out.println("從doPost進來囉~");
        //這邊在做一個判斷式 如果
        List<CartItemImgDTO> cartItemImgDTOList =  new ArrayList<CartItemImgDTO>();
        String action = req.getParameter("action");
        System.out.println(action);
        Integer memNo = (Integer)session.getAttribute("memNo");


        if ("getAll".equals(action)) {
            System.out.println("算甚麼男人:" + memNo);

            cartItemImgDTOList = cartTraceService.getAllCartItem(memNo); //用會員編號查商品編號 會員編號 數量的資訊
            session.setAttribute("cartItemImgDTOList", cartItemImgDTOList);

            String url = "/view/CartTrace/Cart.jsp";
            RequestDispatcher View = req.getRequestDispatcher(url);
            View.forward(req, res);

        }

        //商品若沒有數量則無法加入購物車
        if ("UpdateItemQuantity".equals(action)) {
            System.out.println("in");
            Integer itemNo = Integer.valueOf(req.getParameter("itemNo"));
            Integer quantity = Integer.valueOf(req.getParameter("quantity"));
            System.out.println("購物車商品數量:" + quantity);
            Integer deleteARow = Integer.valueOf(req.getParameter("deleteARow"));
            System.out.println("刪除列"+deleteARow);

            if (quantity == 0 || deleteARow == 0 ) {
                quantity = 0 ;
                cartTraceService.deleteByMemItemNo(memNo, itemNo);
                System.out.println("刪除成功");
            }

            if (quantity > 0) {
                CartTraceVO cartTraceVO = new CartTraceVO(memNo, itemNo, quantity);
                cartTraceService.updateArow(cartTraceVO);
            }

//            writePojo2Json(res, CartItemImgDTO.class);
            Map<String, String> test = new HashMap<>();
            test.put("message", "success");
            Gson gson = new Gson();
            String jsonMsg = gson.toJson(test);
            res.setContentType("application/json");
            PrintWriter out = res.getWriter();
            out.println(jsonMsg);


        }
    }
}


