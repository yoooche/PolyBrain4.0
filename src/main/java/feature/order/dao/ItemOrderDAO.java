package feature.order.dao;

import core.coreDao.CoreDao;
import feature.order.vo.ItemOrderDetailVO;
import feature.order.vo.ItemOrderVO;

import java.util.List;

public interface ItemOrderDAO extends CoreDao<ItemOrderVO, Integer>{


    List<ItemOrderDetailVO> getDetailByOrderNumber(Integer orderNo);

    List<ItemOrderVO> selectByMemberNumber(Integer memNo);

    ItemOrderVO updateAnOrder(ItemOrderVO itemordervo);

}
