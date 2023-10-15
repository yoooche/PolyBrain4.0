package feature.bid.dao;

import feature.bid.vo.BidOrderVo;

import java.util.List;

public interface BidOrderDao {
    public BidOrderVo insert(BidOrderVo bidOrderVo);
    public BidOrderVo selectById(Integer bidOrderNo);

}
