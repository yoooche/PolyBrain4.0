package feature.item.service;

import core.coreService.CoreService;
import feature.item.vo.Item;

import java.util.List;
import java.util.Map;


public interface ItemService extends CoreService {
    //新增商品資訊
    Item AddItem(Item item);
    //移除商品資訊
    boolean remove(Integer itemNo);
    //編輯商品資訊
    Item edit(Item item);
    //取得全部商品資訊
    List<Item> getAllItems();

    //取得number代表的數字數量的商品資訊
    Map<String, Object> getItempage(Integer page,String set);
    Item FindByItemId(Integer itemid);

    //以ID取得商品資訊
    //以類別取得該類別全部商品資訊
    List<Item> FindByItemClass(Integer classNo);
//    Integer updateItem(Item item);
//    boolean updateItemByItemName(Item newItem);

}
