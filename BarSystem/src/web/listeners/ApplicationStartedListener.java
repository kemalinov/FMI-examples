package web.listeners;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import services.DrinksLocal;
import services.OrdersLocal;
import services.UsersLocal;
import web.management.DrinksManagement;
import web.management.OrdersManagement;
import web.management.UsersManagement;

@WebListener
public final class ApplicationStartedListener implements ServletContextListener {

	private static Logger log = Logger.getLogger(ApplicationStartedListener.class.getName());

	private ServletContext context = null;

	@EJB(beanName = "DrinkServices")
	private DrinksLocal drinkServices;

	@EJB(beanName = "UserServices")
	private UsersLocal userServices;

	@EJB(beanName = "OrderServices")
	private OrdersLocal orderServices;

	// initialize DB, allocate some big stuff
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();

		// try {
		// services = (Facade) new
		// InitialContext().lookup("java:global/BarSystem/Facade");
		// } catch (NamingException e) {d
		// log.log(Level.SEVERE,
		// "Exception on performing a JNDI lookup to the Facade: ", e);
		// }

		if (userServices != null && orderServices != null && drinkServices != null) {
			DrinksManagement drinksM = new DrinksManagement(drinkServices, context);
			context.setAttribute("drinksM", drinksM);

			OrdersManagement ordersM = new OrdersManagement(orderServices, context);
			context.setAttribute("ordersM", ordersM);

			UsersManagement usersM = new UsersManagement(userServices, context);
			context.setAttribute("usersM", usersM); // accessible from JSPs,
													// servlets,...
			context.setAttribute("roles", usersM.getAllRoles());

			System.out.println("application started...");
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		// context.removeAttribute("auditLog");
		context.removeAttribute("userM");
		context.removeAttribute("drinksM");
		context.removeAttribute("ordersM");

		System.out.println("application stopping...");
	}
}
