package core.filter;


import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import core.util.HibernateUtil;

import java.io.IOException;

@WebFilter(filterName="customFilter",urlPatterns={"/general/*","*.jsp"})
public class HibernateFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

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