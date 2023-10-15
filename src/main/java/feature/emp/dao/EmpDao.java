package feature.emp.dao;

import feature.emp.vo.EmpVo;

import java.util.List;
public interface EmpDao extends core.coreDao.CoreDao<EmpVo, Integer> {
        Integer insert(EmpVo empVo);
        Integer deleteById(Integer empNo);
        Integer updateById(EmpVo empVo);
        EmpVo selectById(Integer empNo);
        List<EmpVo> selectAll();
        EmpVo selectForLogin(String empEmail, String empPwd);

        int getEmpNoByEmail(String email);




        public List<EmpVo> GETEmailPwd();
}
