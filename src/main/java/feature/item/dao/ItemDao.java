package feature.item.dao;


import core.coreDao.CoreDao;
import feature.item.vo.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao extends CoreDao<Item, Integer> {
//    boolean  updateById(Item newItemVo);
    //新增商品
    Integer insert(Item item);
    //刪除商品
    Integer deleteById(Integer itemNo);
    //更新商品
    Integer update(Item item);
    //搜尋所有商品
    List<Item> selectAll();
    //依照分頁去搜尋
    Map<String, Object> selectpage(Integer page,String set) ;
    //以ID搜尋商品
    Item SelectByItemId(Integer itemid);
    //以名稱搜尋商品
    List<Item>  SelectByItemName(String itemname);
    //以類別搜尋商品
    List<Item> SelectByItemClass(Integer classNo);
    //以人數搜尋商品


}
