package feature.item.service;


import feature.item.dao.ItemDao;
import feature.item.dao.ItemDaoImpl;
import feature.item.vo.Item;

import java.util.List;
import java.util.Map;

public class ItemServiceImpl implements ItemService{
    private ItemDao Dao;

    public  ItemServiceImpl(){
        Dao = new ItemDaoImpl();
    }

    //增加商品
    public Item AddItem(Item item){
        final int resultCount = Dao.insert(item);
        if(resultCount < 1){
            item.setMessage("新增失敗，請聯絡管理員!");
            item.setSuccess(false);
        }else {
        item.setMessage("新增成功");
        item.setSuccess(true);
        }
        return item;
    }

    //刪除商品
    public boolean remove(Integer itemNo) {
        // 交易機制由Filter掌管，這邊不執行交易
        System.out.println("啟動刪除");
        return Dao.deleteById(itemNo) > 0;
    }

    //編輯商品
    public Item edit(Item item) {
        final int resultCount = Dao.update(item);
        item.setSuccess(resultCount > 0);
        item.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
        return item;
    }

    //照分頁搜尋商品
    public Map<String, Object> getItempage(Integer page,String set){
        System.out.println("依照分頁列出遊戲");
        return Dao.selectpage(page,set);
    }

    //搜尋全部遊戲
    @Override
    public List<Item> getAllItems() {
        System.out.println("查詢所有遊戲");
        return Dao.selectAll();
    }

    //拿取單筆
    @Override
    public Item FindByItemId(Integer itemid) {
        System.out.println("以編號查詢遊戲");
        return Dao.SelectByItemId(itemid);
    }

    //拿取單筆
    @Override
    public List<Item> FindByItemClass(Integer classNo) {
        System.out.println("以類別查詢遊戲");
        return Dao.SelectByItemClass(classNo);
    }
}
