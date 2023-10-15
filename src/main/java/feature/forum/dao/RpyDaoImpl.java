package feature.forum.dao;

import feature.forum.vo.ArtVo;
import feature.forum.vo.RpyVo;

import java.util.List;

public interface RpyDaoImpl {
    public void insert(RpyVo rpyvo);
    public void update(RpyVo rpyvo);
    public void delete(Integer rpyNo);
    public  RpyVo findByPrimaryKey(Integer rpyNo);
    public List<RpyVo> getAll();
    public List<RpyVo> findByArt(Integer artNo);

}
