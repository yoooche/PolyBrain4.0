package feature.emp.service;

import feature.emp.dao.EmpDao;
import feature.emp.dao.EmpDaoImpl;
import feature.emp.service.EmpService;
import feature.emp.vo.EmpVo;

import java.util.List;
public class EmpServiceImpl implements EmpService {
    private EmpDao empDao;
    public EmpServiceImpl(){
        empDao = new EmpDaoImpl();
    }

    public List<EmpVo> getAll(){
        return empDao.selectAll();
    }
    public EmpVo getOneEmp(Integer empNo){
        return empDao.selectById(empNo);
    }

    public EmpVo empLogin(EmpVo empVo){
        final String email = empVo.getEmpEmail();
        final String password = empVo.getEmpPwd();

        if (email == null || email.trim().isEmpty()) {
            empVo.setMessage("管理員帳號未輸入");
            empVo.setSuccess(false);
            return empVo;
        }
        if (password == null || password.trim().isEmpty()) {
            empVo.setMessage("管理員密碼未輸入");
            empVo.setSuccess(false);
            return empVo;
        }
        empDao = new EmpDaoImpl();
        empVo = empDao.selectForLogin(email, password);

        if (empVo == null) {
            empVo = new EmpVo();
            empVo.setMessage("管理員帳號或密碼錯誤");
            empVo.setSuccess(false);
            return empVo;
        }
        empVo.setMessage("登入成功");
        empVo.setSuccess(true);
        return empVo;
    }
}
