package feature.item.dao;

import feature.item.vo.Item;
import feature.order.vo.ItemOrderVO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ItemDaoImpl implements ItemDao{

    //增加商品
    public Integer insert(Item item) {
        getSession().persist(item);
        return 1;
    }
    //依照ID搜尋刪除商品
    public Integer deleteById(Integer itemNo){
        Item item = getSession().get(Item.class, itemNo);
        if (item != null) {
            getSession().remove(item);
            return 1;
        } else {
            return -1; // 表示找不到對應的記錄
        }
    }
    //更新商品
    public Integer update(Item item){
        try{
            getSession().merge(item);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    //單筆查詢
    public Item SelectByItemId(Integer itemid) {
        return getSession().get(Item.class, itemid);}

    //搜尋依照商品名稱搜尋
    public List<Item> SelectByItemName(String itemName) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);
        criteriaQuery.where(criteriaBuilder.like(root.get("itemName"), "%" + itemName + "%"));

        return session.createQuery(criteriaQuery).list();
    }

    //搜尋全部商品
    public List<Item> selectAll() {
        final String hql = "FROM Item ORDER BY itemNo";
        return getSession()
                .createQuery(hql, Item.class)
                .getResultList();
    }

    //依照分頁去搜尋
    public Map<String, Object> selectpage(Integer page,String set) {
        int number = (page - 1) * 8;    //每頁顯示第幾到第幾個項目 以8個為一頁
        final String hql = ("FROM Item WHERE itemState IN (1, 2)"+ set);
        System.out.println("hql="+hql);
        final String countHql = ("SELECT COUNT(*) FROM Item WHERE itemState IN (1, 2)"+ set );
        System.out.println("countHql="+countHql);
        Query<Item> query = getSession()
                .createQuery(hql, Item.class)
                .setFirstResult(number) // 略過筆數
                .setMaxResults(8);   // 顯示筆數

        List<Item> items = query.getResultList();
        Long totalCount = getSession()
                .createQuery(countHql, Long.class)
                .uniqueResult();

        int totalPages = (int) Math.ceil((double) totalCount / 8);
        System.out.println(totalPages);
        Map<String, Object> result = new HashMap<>();
        result.put("items", items);
        result.put("totalPages", totalPages);

        return result;
    }

    //搜尋依照類別分類商品
    public List<Item> SelectByItemClass(Integer classNo) {
        final String hql = "FROM Item WHERE itemClassNo = :classNo ORDER BY itemClassNo ASC";
        return getSession()
                .createQuery(hql, Item.class)
                .setParameter("classNo", classNo)
                .getResultList();
    }

    public static void main(String[] args) {

        List<Item> listAAA = new ArrayList<Item>();
        ItemDaoImpl itemDao = new ItemDaoImpl();



        Item item = new Item();
        item = itemDao.SelectByItemId(101);
        System.out.println("印出"+item);


        System.out.println(new ItemDaoImpl().SelectByItemId(194).getItemNo());

    }

 }
