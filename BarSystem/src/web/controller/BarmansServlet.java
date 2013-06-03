package web.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.management.UsersManagement;
import web.pojos.Drink;
import web.pojos.Order;
import ejb.Barman;
import ejb.User;

/**
 * Servlet implementation class ControllerSevlet
 */
@WebServlet(urlPatterns = { "/protected/barmans" })
public class BarmansServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BarmansServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String selectedScreen = "";

		UsersManagement users = (UsersManagement) getServletContext().getAttribute("usersM");
		User user = (User) users.getLoggedUserByName((String) session.getAttribute("username"));
//		Barman barman = (Barman) session.getAttribute("loggedUser"); 

		if (user != null) {
			Barman barman = (Barman) user;
			System.out.println("in barmans servlet!...");
			
			if (action != null) {
				System.out.println("action: " + action);
				
				if (action.equalsIgnoreCase("Add Drink")) { // create a drink
					System.out.println("Adding a drink...");
					
					String drinkName = request.getParameter("drinkName");
					String drinkIngredients = request.getParameter("ingredients");
					String drinkPrice = request.getParameter("price");

					Drink drink = barman.createADrink(drinkName, drinkIngredients, drinkPrice);

					System.out.println("added drink " + drink.getId() + " by " + barman.getName());

				} else if (action.equalsIgnoreCase("Done")) { // accept an order
					String orderId = request.getParameter("acceptedOrderIdName");
					System.out.println("Finishing an order " + orderId + " by " + barman.getName());
					
					Order o = barman.getOrderById(Integer.valueOf(orderId));
					if (o != null) {
						barman.finishAnOrder(o);
					} else {
						System.err.println("Finishing an order " + orderId + " failed!");
					}
					
					System.out.println("Done order id " + orderId + " by " + barman.getName());
				}
			}
			
			List<Order> ordersList = barman.getActiveOrders();
			request.setAttribute("orders", ordersList);
			
			selectedScreen = "/protected/barmans.jsp";
		}

		// get host, port, context,... programatically
		request.getRequestDispatcher(selectedScreen).forward(request, response);
		// response.sendRedirect("http://localhost:8080/BarMngmtSystem" +
		// selectedScreen); // doesn't provide the request's attributes
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
