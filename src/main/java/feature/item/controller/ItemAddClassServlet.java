package feature.item.controller;

import core.util.CommonUtil;
import feature.item.service.ItemClassService;
import feature.item.service.ItemClassServiceImpl;
import feature.item.vo.ItemClass;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/general/item/addItemClass")
public class ItemAddClassServlet extends HttpServlet{
        private static final long serialVersionUID = 1L;
        private ItemClassService service = new ItemClassServiceImpl();
        CommonUtil commonUtil = new CommonUtil();

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) {
            ItemClass item = CommonUtil.json2Pojo(request, ItemClass.class);
            //如果進來的東西是空的 代表新增失敗
            if (item == null) {
                item = new ItemClass();
                item.setMessage("無收到任何資訊");
                item.setSuccess(false);
                CommonUtil.writePojo2Json(response, item);
                return;
            }
            //如果該類別未產生 代表類別還不存在 進入新增方法
            if (item.getItemClassNo() == null) {
                System.out.println("新增類別");
                item = service.AddClass(item);
                CommonUtil.writePojo2Json(response, item);
            }
            //如果該類別未產生 代表類別還不存在 進入修改方法
            if (item.getItemClassNo() != null) {
                System.out.println("修改類別");
                CommonUtil.writePojo2Json(response, service.editClass(item));
            }
        }
}
