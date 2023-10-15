package feature.bookingtast.dao;

import core.util.HibernateUtil;
import feature.bookingtast.vo.GametableVO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GametableDAO implements Gametable_interface {

	Session session = HibernateUtil.getSessionFactory().openSession();

	@Override
	public void insert(GametableVO gametableVO) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(gametableVO);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}

	@Override
	public void update(GametableVO gametableVO) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(gametableVO);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}

	@Override
	public void delete(Integer TABLE_NO) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			GametableVO gametableVO = session.get(GametableVO.class, TABLE_NO);
			if (gametableVO != null) {
				session.delete(gametableVO);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}

	@Override
	public GametableVO findByPrimaryKey(Integer TABLE_NO) {
		return session.get(GametableVO.class, TABLE_NO);
	}

	@Override
	public List<GametableVO> getAll() {
		String hql = "FROM GametableVO";
		return session.createQuery(hql, GametableVO.class).getResultList();
	}


	@Override
	public List<GametableVO> selectAll() {
		return null;
	}
}
