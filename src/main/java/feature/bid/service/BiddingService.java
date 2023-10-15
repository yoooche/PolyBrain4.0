package feature.bid.service;

import core.coreService.CoreService;
import feature.bid.dto.BidItemListDto;
import feature.bid.dto.BidItemDto;
import feature.bid.vo.BidEventVo;
import feature.bid.vo.BidItemPicVo;
import feature.bid.vo.BidItemVo;
import feature.bid.vo.BidOrderVo;

import java.util.List;
import java.util.Map;

public interface BiddingService extends CoreService {

    // ========== about bidding item ==========
    List<BidItemVo> viewAll();
    public BidItemVo addAnItem(BidItemVo bidItemVo);
    public BidItemVo getOneItem(Integer bidItemNo);
    public boolean removeOneItem(Integer bidItemNo);
    List<String> viewAllName();
    public void addPics(BidItemPicVo bidItemPicVo);
    List<BidItemListDto> getHomePageList();
    List<BidItemDto> getTableData();
    List<String> selectAllPicsB64();
    BidItemVo edit(BidItemVo bidItemVo);

    // ========== about bidding event ==========
    List<BidEventVo> viewAllEvent();
    public void addAnEvent(BidEventVo bidEventVo);
    public BidEventVo getEventByNo(Integer bidEventNo);
    public Map<String, String> getStartTimeByNo(Integer bidEventNo);
    public void removeEventById(Integer bidEventNo);
    // ========== about bidding order ==========
    public void createOneOrder(Integer bidEventNo);
    public BidOrderVo orderWithoutBid(Integer bidEventNo, Integer memNo);
    public List<byte[]> getItemPicsByEveNo(Integer bidEventNo);
}
