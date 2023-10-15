package feature.mem.service;

import feature.mem.dao.MemDao;
import feature.mem.dao.MemDaoImpl;
import feature.mem.vo.MemVo;

import java.util.List;

public class LoginServiceImpl implements LoginService{
    private MemDao memDao;

    public LoginServiceImpl(){
        memDao = new MemDaoImpl();
    }

    public boolean isValidUser(String email, String password) {
        // 查詢所有會員
        List<MemVo> members = memDao.getAllMembers();
        // 檢查是否有匹配的電子郵件和密碼
        for (MemVo member : members) {
            if (email.equals(member.getMemEmail()) && password.equals(member.getMemPwd())) {
                return true; // 有效的用戶
            }
        }
        return false; // 無效的用戶
    }

    @Override
    public boolean validateInfo(String memEmail, String memPwd) {
        if(memDao.selectByMemEmailAndPwd(memEmail, memPwd) != null){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public MemVo getMemByValidatedInfo(String memEmail, String memPwd) {
        return memDao.selectByMemEmailAndPwd(memEmail, memPwd);

    }

    @Override
    public MemVo getMemByMemNo(Integer memNo) {
        return memDao.selectById(memNo);
    }
}
