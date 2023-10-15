package feature.item.dao;


import core.coreDao.CoreDao;
import feature.item.vo.ItemClass;

import java.util.List;

public interface ItemClassDao extends CoreDao<ItemClass, Integer> {
    //搜尋所有商品類別
    List<ItemClass> selectAll();
    //增加商品類別
    Integer insert(ItemClass item);
    //刪除商品
    Integer deleteById(Integer itemClassNo);
    //修改商品類別
   Integer update(ItemClass itemclass);

    ItemClass selectByItemClassNo(Integer itemClassNo);
}
