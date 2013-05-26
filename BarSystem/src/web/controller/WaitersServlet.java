package web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.pojos.Consumer;
import web.pojos.Drink;
import web.pojos.Order;
import web.users.DrinksManagement;
import web.users.UsersManagement;
import ejb.Waiter;

/**
 * Servlet implementation class ControllerSevlet
 */
@WebServlet(urlPatterns = { "/protected/waiters" })
public class WaitersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaitersServlet() {
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

	UsersManagement usersM = (UsersManagement) getServletContext().getAttribute("usersM");
	Waiter waiter = (Waiter) usersM.getLoggedUserByName((String) session.getAttribute("username"));
	
	DrinksManagement drinksM = (DrinksManagement) getServletContext().getAttribute("drinksM");

	System.out.println("in waiters' servlet!... " + waiter.getName() +" , id:" + waiter.getId());
	
	if (waiter != null) {
	    System.out.println("in waiters' servlet!..." + action);
	    	
	    if (action != null) {
        	if (action.equals("Add")) { // add consumer/and oreder
        	    String radioAction = request.getParameter("radioAction");
        	    String place = request.getParameter("place");
        	    String dateParam = request.getParameter("dateHid");
        	    String orderedDrinkCount = request.getParameter("orderedDrinkNumber");
        	    String consumer = request.getParameter("clientSelect");

        	    System.out.println(radioAction.equals("0") ? "Adding a client..." : "Adding an order...");
        	    
        	    if (radioAction.equals("0")) {	// "Adding a client..."
        		System.out.println(place);
            	    	System.out.println(dateParam);	
            	    	
            	    	System.out.println("ordered drinks count: "+ orderedDrinkCount);
            	    	Map<Drink, Integer> drinks = loadTheDrinks(request, drinksM, orderedDrinkCount);
        	    	createTheOrder(waiter, place, dateParam, drinks);

        	    } else {	// "Adding an order..."
        		
        		System.out.println(consumer);
            	    	
        		// get the consumer with call to the DB
        		// persist the 
        		
        		//waiter.addAnOdrer(consumer, drinks);
        	    }
        	} else if (action.equalsIgnoreCase("Close an Order")) {
        	    String doneOrder = request.getParameter("doneAnOrderRadioBtn");
        	    System.out.println("Done an order: " + doneOrder);
        	    
        	}
	    }
	    
	    List<Order> ordersList = waiter.getMyActiveOrders();
	    List<Consumer> consumersList = waiter.getAllMyClients();
	    request.setAttribute("orders", ordersList);
	    request.setAttribute("consumers", consumersList);
	    request.setAttribute("loggedUser", waiter);

	    selectedScreen = "/protected/waiters.jsp";
	} else {
	    selectedScreen = "";
	}
	
	// get host, port, context,... programatically
	//response.sendRedirect("http://localhost:8080/BarMngmtSystem" + selectedScreen);
	request.getRequestDispatcher(selectedScreen).forward(request, response);
    }

    private void createTheOrder(Waiter waiter, String place, String dateParam, Map<Drink, Integer> drinks) {
	Date date = null;
	try {
	    date = new SimpleDateFormat("dd/MM/yy hh:mm:ss").parse(dateParam);
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	Consumer c = waiter.createConsumerWOrder(date, place, drinks);
	if (c != null && c.getId() != null) {
	    System.out.println("created new consumer " + c.getId() +" successfuly");
	} else {
	    System.out.println("creation of the new consumer failed!");
	}
    }

    private Map<Drink, Integer> loadTheDrinks(HttpServletRequest request, DrinksManagement drinksM, String orderedDrinkCount) {
	Map<String, Drink> allDrinksMap = drinksM.getAllDrinks();
	Map<Drink, Integer> drinks = new HashMap<Drink, Integer>();
	int numberOfDrinks  = Integer.valueOf(orderedDrinkCount.trim());
	for(int i=0; i < numberOfDrinks; i++) {
	    String tmpDrinkName = request.getParameter("drinkName_"+i);
	    String tmpDrinkCount = request.getParameter("drinkCount_"+i);
	    
	    if(allDrinksMap.containsKey(tmpDrinkName)) {
		Drink drink = allDrinksMap.get(tmpDrinkName);
		drinks.put(drink, new Integer(tmpDrinkCount));            	    	    
	    }
	    System.out.println("drinkName_"+ i + ": " + request.getParameter("drinkName_"+i));
	    System.out.println("drinCount_"+ i + ": " + request.getParameter("drinkCount_"+i));
	}
	return drinks;
    }

    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
    }
    
    

}
