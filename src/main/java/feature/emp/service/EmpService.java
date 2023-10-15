package feature.emp.service;

import core.coreService.CoreService;
import feature.emp.vo.EmpVo;
import org.springframework.stereotype.Service;

import java.util.List;
public interface EmpService extends CoreService {
    List<EmpVo> getAll();
    EmpVo getOneEmp(Integer empNo);
    EmpVo empLogin(EmpVo empVo);
}
