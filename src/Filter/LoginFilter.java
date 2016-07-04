package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @date 2016年4月12日 LoginFilter.java
 * @author CZP
 * @parameter
 */
public class LoginFilter implements Filter {

	private String[] excludeUrls;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String url = request.getRequestURI();
		for (String u : excludeUrls) {
			if (url.contains(u.trim())) {
				chain.doFilter(req, resp);
				//一定要return 下同
				 return;
			}
		}
		HttpSession session = request.getSession(true);
		if (session.getAttribute("currentUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			 return;
		} else {
			chain.doFilter(req, resp);
			 return;
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String excludeUrl = config.getInitParameter("excludeUrl");
		if (excludeUrl != null) {
			this.excludeUrls = excludeUrl.split(",");
		}
	}

}
