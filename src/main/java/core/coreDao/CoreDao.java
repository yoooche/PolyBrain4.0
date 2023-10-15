package core.coreDao;

import core.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

import static core.util.HibernateUtil.getSessionFactory;

public interface CoreDao<Vo, Integer>{
//    Integer insert(Vo vo);
//    Integer deleteById(Integer id);
//    Integer update(Vo vo);
//    Vo selectById(Integer id);
    List<Vo> selectAll();
    default Session getSession() {
//        return getSessionFactory().openSession();
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

}
