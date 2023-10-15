package feature.bid.dao;

import feature.bid.vo.BidEventVo;

import java.util.List;

public interface BidEventDao {
    public void insert(BidEventVo bidEventVo);
    public List<BidEventVo> selectAll();
    public void deleteById(Integer bidEventNo);
    public BidEventVo selectById(Integer bidEventNo);
    public String selectItemNameByEveNo(Integer bidEventNo);
    public Integer selectItemNoByEveNo(Integer bidEventNo);
}
