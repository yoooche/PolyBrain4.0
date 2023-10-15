package feature.mem.service;

import feature.mem.dao.MemDao;
import feature.mem.dao.MemDaoImpl;
import feature.mem.vo.MemVo;

import javax.servlet.ServletException;

public class RegistService {
    private MemDao memDao;

    public RegistService() {memDao = new MemDaoImpl();}
    public String checkDuplicate(String pid, String email, String phone) {
        // 检查是否有重复的信箱、身份证或电话号码
        if (memDao.isEmailExists(email) || memDao.isPidExists(pid) || memDao.isPhoneExists(phone)) {
            return "duplicate"; // 存在重复数据
        } else {
            return "success"; // 没有重复数据
        }
    }

    public String registerMember(String name, String pid, int gender, String email, String password, String phone, String address, String birth) throws ServletException {
        // 先检查是否存在重复的电子邮件、身份证或电话号码
        String duplicateCheckResult = checkDuplicate(pid, email, phone);

        if ("duplicate".equals(duplicateCheckResult)) {
            return "duplicate"; // 返回错误标识
        }

        MemVo mem = new MemVo();
        mem.setMemName(name);
        mem.setMemPid(pid);
        mem.setMemGender((byte) gender);
        mem.setMemEmail(email);
        mem.setMemPwd(password);
        mem.setMemPh(phone);
        mem.setMemAddress(address);
        mem.setMemBirth(java.sql.Date.valueOf(birth)); // 将生日字符串转换为日期

        // 调用DAO的createMember方法将会员数据存入数据库
        try {
            memDao.createMemberJDBC(mem);
            return "success"; // 注册成功
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("注册失败，请稍后重试", e); // 注册失败
        }
    }
}
