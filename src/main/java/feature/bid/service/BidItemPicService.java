package feature.bid.service;

import java.util.List;

public interface BidItemPicService {
    public void addBidItemPic(List<String> bidItemPic, Integer bidItemNo);
    public boolean removeByBidItemNo(Integer bidItemNo);
    public void editBidItemPics(List<String> bidItemPic, Integer bidItemNo);
}
