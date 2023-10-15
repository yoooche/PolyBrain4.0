package feature.bid.dao;

import feature.bid.vo.BidItemVo;

import java.util.List;

public interface BidItemDao {
    public Integer insert(BidItemVo bidItemVo);
    public List<BidItemVo> selectAll();
    public Integer deleteById(Integer bidItemNo);
    public BidItemVo selectById(Integer bidItemNo);
    public Integer update(BidItemVo bidItemVo);

    }
