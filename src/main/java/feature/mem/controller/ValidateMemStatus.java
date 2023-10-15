package feature.mem.controller;

import feature.mem.dto.LoginStatusDto;
import feature.mem.service.LoginService;
import feature.mem.service.LoginServiceImpl;
import feature.mem.vo.MemVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/general/validateMemStatus")
public class ValidateMemStatus extends HttpServlet {
    private LoginService loginService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("UTF-8");
        HttpSession session = req.getSession();

        LoginStatusDto loginStatusDto = new LoginStatusDto();
        if(session.getAttribute("sessionId") != null){
            loginService = new LoginServiceImpl();
            MemVo memVo = loginService.getMemByMemNo((Integer) session.getAttribute("memNo"));
            loginStatusDto.setMemNo(memVo.getMemNo());
            loginStatusDto.setMemName(memVo.getMemName());
            loginStatusDto.setLoginStatus(true);
            writePojo2Json(resp, loginStatusDto);
        }else{
            loginStatusDto.setLoginStatus(false);
            writePojo2Json(resp, loginStatusDto);
        }
    }
}
