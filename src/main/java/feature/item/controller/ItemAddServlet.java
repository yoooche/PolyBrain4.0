package feature.item.controller;

import core.util.CommonUtil;
import feature.item.dto.AddItemDTO;
import feature.item.service.ItemImgService;
import feature.item.service.ItemImgServiceImpl;
import feature.item.service.ItemService;
import feature.item.service.ItemServiceImpl;
import feature.item.vo.Item;
import org.hibernate.internal.build.AllowSysOut;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/general/item/addItem")
public class ItemAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ItemService service = new ItemServiceImpl();
    private ItemImgService service2 = new ItemImgServiceImpl();
    CommonUtil commonUtil = new CommonUtil();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        AddItemDTO addItemDTO = CommonUtil.json2Pojo(request, AddItemDTO.class);
        Item item = addItemDTO.getItem();
        List<String> itemImageList = addItemDTO.getItemImageList();

        //如果進來的東西是空的 代表新增失敗
        if (item == null) {
            item = new Item();
            item.setMessage("無商品資訊");
            item.setSuccess(false);
            CommonUtil.writePojo2Json(response, item);
            return;
        }
        //如果商品代碼未產生 代表商品還不存在 進入新增方法
        if (item.getItemNo() == null) {
            System.out.println("新增商品");
            Item newItem = service.AddItem(item);
            Integer newItemNo = newItem.getItemNo();
            System.out.println("新商品 = " + newItem);
            System.out.println("商品序號 = " + newItemNo);
            service2.AddItemImg(itemImageList, newItemNo);
            CommonUtil.writePojo2Json(response, newItem);
            return;
        }
        //如果商品代碼已產生 代表商品已存在 進入修改方法
        if (item.getItemNo() != null) {
            System.out.println("修改商品");
            item = service.edit(item);
            if(!itemImageList.isEmpty()) {          //檢查是否有新上傳圖片 沒有則不進入修改
                System.out.println("有修改圖片");
                Integer itemNo = item.getItemNo();
                service2.editImg(itemImageList, itemNo);
            }
            CommonUtil.writePojo2Json(response,item);
            return;
        }
    }
}