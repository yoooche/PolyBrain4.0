package feature.emp.dao;

import feature.emp.vo.EmpVo;

import java.util.List;

public interface EmpListDaoImpl {

    public List<EmpVo> getAll();

    public EmpVo findByPrimaryKey(Integer empNo);

    public void delete(Integer empNo);

    public void update(EmpVo empNo);

    void createEmp(EmpVo emp);
}
