package feature.item.controller;


import core.coreVO.Core;
import core.util.CommonUtil;
import feature.item.service.ItemClassService;
import feature.item.service.ItemClassServiceImpl;
import feature.item.vo.ItemClass;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static core.util.CommonUtil.json2Pojo;

@WebServlet("/general/item/ClassRemove")
public class ItemRemoveClassServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ItemClassService service = new ItemClassServiceImpl();
    CommonUtil commonUtil = new CommonUtil();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final Integer id = json2Pojo(request, ItemClass.class).getItemClassNo();
        final Core core = new Core();
        if (id == null) {
            core.setMessage("無該產品編號");
            core.setSuccess(false);
        } else {
            core.setSuccess(service.remove(id));
        }
        commonUtil.writePojo2Json(response, core);
    }
}
