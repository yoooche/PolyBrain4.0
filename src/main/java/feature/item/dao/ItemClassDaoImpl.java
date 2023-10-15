package feature.item.dao;


import feature.item.vo.ItemClass;

import java.util.List;

public class ItemClassDaoImpl implements ItemClassDao {
    public Integer insert(ItemClass item) {
        getSession().persist(item);
        return 1;
    }

    public Integer update(ItemClass itemClass){
        try{
            getSession().merge(itemClass);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    //依照ID搜尋刪除商品
    public Integer deleteById(Integer itemClassNo){
        ItemClass item = getSession().get(ItemClass.class, itemClassNo);
        if (item != null) {
            getSession().remove(item);
            return 1;
        } else {
            return -1; // 表示找不到對應的記錄
        }
    }

    public List<ItemClass> selectAll() {
        final String hql = "FROM ItemClass ORDER BY itemClassNo";
        return getSession().createQuery(hql, ItemClass.class).getResultList();
    }

    public ItemClass selectByItemClassNo(Integer itemClassName) {
        final String hql = "FROM ItemClassVo WHERE itemClassName = :itemClassName";
        return getSession().createQuery(hql, ItemClass.class)
                .setParameter("itemClassName", itemClassName)
                .uniqueResult();
    }
}