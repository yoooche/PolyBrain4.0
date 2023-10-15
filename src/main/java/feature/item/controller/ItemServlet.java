package feature.item.controller;


import core.coreVO.Core;
import core.util.CommonUtil;
import feature.item.dto.AddItemDTO;
import feature.item.service.ItemService;
import feature.item.service.ItemServiceImpl;
import feature.item.vo.ItemClass;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/general/selectServlet")
public class ItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ItemService service = new ItemServiceImpl();
    CommonUtil commonUtil = new CommonUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request);
        String value = request.getParameter("value");
        System.out.println("這是value="+value);
        final Core core = new Core();
        if (value == null) {
            core.setMessage("查無請求");
            core.setSuccess(false);
        }
        switch (value) {        //判斷請求的是甚麼
            case "selectAll":   //若請求是搜尋全部
                commonUtil.writePojo2Json(response, service.getAllItems());
                break;
            case "selectpage":  //若請求是搜尋頁面
                System.out.println("開始頁面配置");
                Integer page = Integer.valueOf(request.getParameter("page"));
                String set = request.getParameter("set");//丟入前端串接的HQL指令
                try {
                    String decodedString = URLDecoder.decode(set, StandardCharsets.UTF_8.toString());
                    System.out.println(decodedString);  //解碼兩次編碼的字串符
                commonUtil.writePojo2Json(response, service.getItempage(page,decodedString));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case "selectID":    //若請求是搜尋單個商品
                System.out.println("開始搜尋單項商品");
                Integer ID = Integer.valueOf(request.getParameter("itemID"));
                commonUtil.writePojo2Json(response, service.FindByItemId(ID));
                break;
            case "selectName":  //若請求是搜尋以商品搜尋
                String name = request.getParameter("name");
                commonUtil.writePojo2Json(response, service.getAllItems());
                break;
            case "selectClass":  //若請求是以商品類別搜尋
                System.out.println("開始抓取該商品類別所有商品");
                Integer classNO = Integer.valueOf(request.getParameter("ItemClass"));
                commonUtil.writePojo2Json(response, service.FindByItemClass(classNO));
                break;
        }
    }
}