package feature.emp.service;

import feature.emp.dao.EmpDao;
import feature.emp.dao.EmpDaoImpl;
import feature.emp.vo.EmpVo;


import java.util.List;


public class back_loginService {

    private EmpDao dao;
    public back_loginService(){dao = new EmpDaoImpl();}


    public boolean empLogin(String email, String password) {
        // 查詢所有會員
        List<EmpVo> members = dao.selectAll();

        // 遍歷所有會員，檢查是否有匹配的電子郵件和密碼
        for (EmpVo member : members) {
            if (email.equals(member.getEmpEmail()) && password.equals(member.getEmpPwd())) {
                return true; // 有效的用戶
            }
        }

        return false; // 無效的用戶
    }

    public int getEmpNoByEmail(String email) {
        return dao.getEmpNoByEmail(email);
    }



}
