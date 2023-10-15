package feature.item.service;

import feature.item.dao.ItemImgDao;
import feature.item.dao.ItemImgDaoImpl;
import feature.item.vo.ItemImg;

import java.util.Base64;
import java.util.List;


public class ItemImgServiceImpl implements ItemImgService{
    private ItemImgDao Dao;

    public ItemImgServiceImpl(){
            Dao = new ItemImgDaoImpl();
    }
    //新增商品資訊
    public void AddItemImg(List<String> itemImageList, Integer itemNo){
        System.out.println("新增圖片");
        for(String str : itemImageList) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItemNo(itemNo);
            itemImg.setItemImg(str);
            final int resultCount = Dao.insert(itemImg);
        }
    }

    public void editImg(List<String> itemImageList, Integer itemNo){
        System.out.println("編輯圖片");
        Dao.deleteByItem(itemNo);
        for(String str : itemImageList) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItemNo(itemNo);
            itemImg.setItemImg(str);
            final int resultCount = Dao.update(itemImg);
        }
    }
    public  boolean removeImg(Integer itemNo){
        System.out.println("刪除該產品所有照片");
        return Dao.deleteByItem(itemNo) > 0;
    }
}
