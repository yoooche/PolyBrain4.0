package feature.emp.dao;

import core.util.HibernateUtil;
import feature.emp.vo.EmpVo;
import org.hibernate.Session;

import java.util.List;
public class EmpDaoImpl implements EmpDao {
    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public Integer insert(EmpVo empVo) {
        session.save(empVo);
        return 1;
    }

    @Override
    public Integer deleteById(Integer id) {
        return null;
    }

    public Integer update(EmpVo empVo) {
        return null;
    }

    @Override
    public Integer updateById(EmpVo empVo) {
        return null;
    }

    @Override
    public EmpVo selectById(Integer empNo) {
        return session.get(EmpVo.class, empNo);
    }

    @Override
    public List<EmpVo> selectAll() {
        final String hql = "FROM employee ORDER BY empNo";
        return session.createQuery(hql, EmpVo.class).getResultList();
    }

    @Override
    public EmpVo selectForLogin(String empEmail, String empPwd) {
        final String sql = "select * from EMPLOYEE where EMP_EMAIL = :empEmail and EMP_PWD = :empPwd";
        return session
                .createNativeQuery(sql, EmpVo.class)
                .setParameter("empEmail", empEmail)
                .setParameter("empPwd", empPwd)
                .uniqueResult();
    }


    @Override
    public List<EmpVo> GETEmailPwd() {
        final String sql = "SELECT EMP_EMAIL,EMP_PWD FROM member";;
        return session.createQuery(sql, EmpVo.class).getResultList();
    }

    @Override
    public int getEmpNoByEmail(String email) {
        String sql = "SELECT EMP_NO FROM employee WHERE EMP_EMAIL = :email";
        Integer empNo = (Integer) session.createNativeQuery(sql)
                .setParameter("email", email)
                .uniqueResult();
        return empNo != null ? empNo : -1; // 返回-1表示未找到员工
    }
}
