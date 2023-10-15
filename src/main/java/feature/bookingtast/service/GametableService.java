package feature.bookingtast.service;
import java.util.List;

import feature.bookingtast.dao.GametableDAO;
import feature.bookingtast.dao.Gametable_interface;
import feature.bookingtast.vo.GametableVO;

public class GametableService {

	private Gametable_interface dao;
	
	public GametableService() {
		dao = new GametableDAO();
	}
	
	public List<GametableVO> getAll() {
		return dao.getAll();
	}
	
	public GametableVO getOneGametable(Integer TABLE_NO) {
		return dao.findByPrimaryKey(TABLE_NO);
	}

//	public Set<GametableVO> getEmpsByTABLE_NO(Integer TABLE_NO) {
//		return dao.getEmpsByTABLE_NO(TABLE_NO);
//	}

}
