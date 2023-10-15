package feature.emp.service;

import feature.emp.dao.EmpListDao;
import feature.emp.dao.EmpListDaoImpl;
import feature.emp.vo.EmpVo;

import javax.servlet.ServletException;

public class empAddService {
    private EmpListDaoImpl dao;

    public empAddService() {dao = new EmpListDao();}


    public String Add(String name, Byte gender, String email, String password, String phone, Byte state) throws ServletException {

        // 创建一个新的 MemberVO 对象
        EmpVo emp = new EmpVo();
        emp.setEmpName(name);
        emp.setEmpGender(gender);
        emp.setEmpEmail(email);
        emp.setEmpPwd(password);
        emp.setEmpPh(phone);
        emp.setEmpState(state);

        // 调用DAO的createMember方法将会员数据存入数据库
        try {
            dao.createEmp(emp);
            return "success"; // 注册成功
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("注册失败，请稍后重试", e); // 注册失败
        }
    }
}