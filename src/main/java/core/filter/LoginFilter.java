package core.filter;

import core.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/loginRequired/*")
public class LoginFilter extends HttpFilter {
    private static final long serialVersionUID = 1L;
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException {
        String clientPath = req.getRequestURI();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("sessionId") == null) {
            System.out.println("LoginFilter:未登入狀態, sessionId未授權, sessionId:" + httpSession.getAttribute("sessionId"));
            System.out.println(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //未授權狀態碼，給前端判斷並重導使用
        } else {
            System.out.println("LoginFilter:登入狀態,memNo:" + httpSession.getAttribute("memNo") + ", sessionId:" + httpSession.getAttribute("sessionId"));
        }

        try {
            Transaction transaction = session.beginTransaction();
            chain.doFilter(req, resp);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }
}
