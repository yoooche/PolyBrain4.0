package feature.bid.service;

import feature.bid.dao.BidItemPicDao;
import feature.bid.dao.BidItemPicDaoImpl;
import feature.bid.vo.BidItemPicVo;
import feature.item.vo.ItemImg;

import java.util.Base64;
import java.util.List;

public class BidItemPicServiceImpl implements BidItemPicService {

    private BidItemPicDao bidItemPicDao;

    public BidItemPicServiceImpl() {
        bidItemPicDao = new BidItemPicDaoImpl();
    }

    @Override
    public void addBidItemPic(List<String> bidItemPic, Integer bidItemNo) {

        System.out.println("競標圖片新增");

        for (String base64String : bidItemPic) {
            base64String = base64String.substring(base64String.indexOf(",") + 1);
            byte[] file = Base64.getDecoder().decode(base64String);
            System.out.println(file);
            BidItemPicVo bidItemPicVo = new BidItemPicVo();
            bidItemPicVo.setBidItemPic(file);
            bidItemPicVo.setBidItemNo(bidItemNo);
            bidItemPicDao.insert(bidItemPicVo);

        }
    }

    @Override
    public boolean removeByBidItemNo(Integer bidItemNo) {
        System.out.println("刪除成功 (圖片)");
        return bidItemPicDao.deleteByBidItemNo(bidItemNo) > 0;
    }

    @Override
    public void editBidItemPics(List<String> bidItemPic, Integer bidItemNo) {
        System.out.println("編輯圖片");
        bidItemPicDao.deleteByBidItemNo(bidItemNo);
        for(String base64String : bidItemPic) {
            base64String = base64String.substring(base64String.indexOf(",") + 1);
            System.out.println(base64String);
            BidItemPicVo bidItemPicVo = new BidItemPicVo();
            byte[] img = Base64.getDecoder().decode(base64String);
            bidItemPicVo.setBidItemNo(bidItemNo);
            bidItemPicVo.setBidItemPic(img);
            final int resultCount = bidItemPicDao.update(bidItemPicVo);
        }
    }
}
