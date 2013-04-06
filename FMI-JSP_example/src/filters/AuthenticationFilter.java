package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import users.UserManagement;

@WebFilter(urlPatterns = { "/protected/*", "/monitor/*" })
public class AuthenticationFilter implements Filter {

	private ServletContext context;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(true);
		String pathToForward = null;

		if (session.getAttribute("username") == null) {
			pathToForward = "/public/login.jsp";
			httpRequest.getRequestDispatcher(pathToForward).forward(request, response);
			return;
		} else {
			String user = (String) session.getAttribute("username");
			UserManagement users = (UserManagement) context.getAttribute("users");
			boolean isAdmin = users.isAdminUser(user);
			if(httpRequest.getServletPath().startsWith("/monitor") && !isAdmin) {
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
				httpResponse.getWriter().print("You dont have permissions to see this page!");
				// create error page for handling this case - homework?
			}
		}
		

		chain.doFilter(request, response);
		
		// operations here are executed after the recursion has executed - could measure time 
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

}
