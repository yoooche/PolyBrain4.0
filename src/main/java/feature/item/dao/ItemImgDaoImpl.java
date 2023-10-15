package feature.item.dao;


import feature.item.vo.Item;
import feature.item.vo.ItemImg;
import org.eclipse.core.internal.runtime.Product;

import java.util.List;

public class ItemImgDaoImpl implements ItemImgDao{
    public Integer insert(ItemImg itemImg) {
        getSession().persist(itemImg);
        return 1;
    }

    public Integer deleteById(Integer itemImgNo){
        ItemImg itemImg = getSession().get(ItemImg.class, itemImgNo);
        getSession().remove(itemImg);
        return -1;
    }

    public Integer deleteByItem(Integer itemNo){
        final  String  hql = "DELETE FROM ItemImg WHERE itemNo = :itemNo";
        return getSession()
                .createQuery(hql)
                .setParameter("itemNo", itemNo)
                .executeUpdate();
    }

    public Integer update(ItemImg itemImg){
        try{
            getSession().merge(itemImg);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    //沒有要使用
    public List<ItemImg> selectAll() {
        final String hql = "FROM ItemImg ORDER BY itemImgNo";
        return getSession().createQuery(hql, ItemImg.class).getResultList();
    }

}
