package feature.bookingtast.dao;

import core.coreDao.CoreDao;
import feature.bookingtast.vo.GametableVO;

import java.util.*;



public interface Gametable_interface extends CoreDao<GametableVO, Integer> {
	public void insert(GametableVO gametableVO);
    public void update(GametableVO gametableVO);
    public void delete(Integer TABLE_NO);
    public GametableVO findByPrimaryKey(Integer TABLE_NO);
    public List<GametableVO> getAll();

}
