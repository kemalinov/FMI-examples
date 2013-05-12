package web.listeners;

import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import facade.Facade;

import web.users.DrinkManagement;
import web.users.UserManagement;

@WebListener
public final class ApplicationStartedListener implements ServletContextListener {

    private static Logger log = Logger
	    .getLogger(ApplicationStartedListener.class.getName());

    private ServletContext context = null;

    private Facade services;

    // initialize DB, allocate some big stuff
    public void contextInitialized(ServletContextEvent event) {
	context = event.getServletContext();

	try {
	    services = (Facade) new InitialContext()
		    .lookup("java:global/BarMngmtSystem/Facade");
	} catch (NamingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	if (services != null) {
	    UserManagement users = new UserManagement(services, context);
	    context.setAttribute("usersM", users); // accessible from JSPs,
						  // servlets,..

	    DrinkManagement drinks = new DrinkManagement(services, context);
	    context.setAttribute("drinksM", drinks);

	    System.out.println("application started...");
	}
    }

    public void contextDestroyed(ServletContextEvent event) {
	// context.removeAttribute("auditLog");
	context.removeAttribute("userM");
	System.out.println("application stopping...");
    }
}
