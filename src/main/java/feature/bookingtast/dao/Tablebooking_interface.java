package feature.bookingtast.dao;

import java.util.List;
import java.util.Map;

import feature.bookingtast.vo.TablebookingVO;

public interface Tablebooking_interface extends core.coreDao.CoreDao<TablebookingVO, Integer>{
	
	public void insert(TablebookingVO tablebookingVO);
    public void update(TablebookingVO tablebookingVO);
    public TablebookingVO findByPrimaryKey(Integer TABLE_BOOK_NO);
    public List<TablebookingVO> getAll();

    public List<TablebookingVO> query(java.sql.Date TABLE_DATE, Integer TABLE_NO);
    //查詢某部門的員工(一對多)(回傳 Set)
    public List<TablebookingVO> getten();

}

