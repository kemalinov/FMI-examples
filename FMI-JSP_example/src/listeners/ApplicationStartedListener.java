package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import log.AuditLog;

import users.UserManagement;

@WebListener
public final class ApplicationStartedListener implements ServletContextListener {

	private ServletContext context = null;

	// initialize DB, allocate some big stuff 
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		AuditLog auditLog = new AuditLog(context);
		context.setAttribute("auditLog", auditLog);
		UserManagement users = new UserManagement(context);	
		users.loadUsers();
		users.loadAdmins();
		context.setAttribute("users", users); // accessible from JSPs, servlets,..
		System.out.println("application started...");
	}

	public void contextDestroyed(ServletContextEvent event) {		
		context.removeAttribute("auditLog");
		context.removeAttribute("users");
		System.out.println("application stopping...");
	}
}
