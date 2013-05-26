package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.pojos.Drink;
import web.pojos.Order;
import web.users.UsersManagement;
import ejb.Barman;

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
	String selectedScreen = null;

	UsersManagement users = (UsersManagement) getServletContext().getAttribute("usersM");
	Barman barman = (Barman) users.getLoggedUserByName((String) session.getAttribute("username"));

	if (barman != null) {
	    List<Order> ordersList = barman.getActiveOrders();
	    request.setAttribute("orders", ordersList);
	    request.setAttribute("loggedUser", barman);
	    
	    System.out.println("in barmans servlet!...");
	    	
	    if (action != null) {
	        if (action.equalsIgnoreCase("Add Drink")) { // create a drink
	            String drinkName = request.getParameter("drinkName");
	            String drinkIngredients = request.getParameter("ingredients");
	            String drinkPrice = request.getParameter("price");
	            
	            Drink drink  = barman.createADrink(drinkName, drinkIngredients, drinkPrice);
	            
	            System.out.println("added drink " + drink.getId() + " by " + barman.getName());
                	    
	        } else if (action.equalsIgnoreCase("Done")) { 	// accept an order
	            
	            String orderId = request.getParameter("acceptedOrderIdName");
	            for(Order o : ordersList) {
	        	if(o.getId().compareTo(Integer.valueOf(orderId)) == 0) {
	        	    barman.finishAnOrder(o);
	        	    break;
	        	}
	            }
	            System.out.println("Done order id " + orderId + " by " + barman.getName());
	    	}
	    }
	    
	    selectedScreen = "/protected/barmans.jsp";
        } else {
            selectedScreen = "";
        }
	
	// get host, port, context,... programatically
	request.getRequestDispatcher(selectedScreen).forward(request, response);
	//response.sendRedirect("http://localhost:8080/BarMngmtSystem" + selectedScreen);	// doesn't provide the request's attributes
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
    }

}
