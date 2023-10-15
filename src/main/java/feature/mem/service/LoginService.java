package feature.mem.service;

import feature.mem.vo.MemVo;

public interface LoginService {
    boolean validateInfo(String memEmail, String memPwd);
    MemVo getMemByValidatedInfo(String memEmail, String memPwd);
    MemVo getMemByMemNo(Integer memNo);
}
