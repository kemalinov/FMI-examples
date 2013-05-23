package web.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.DrinksLocal;
import services.OrdersLocal;

import ejb.Waiter;

import web.users.DrinksManagement;
import web.users.OrdersManagement;
import web.users.UsersManagement;

/**
 * Servlet implementation class ControllerSevlet
 */
@WebServlet(urlPatterns = { "/protected/waiters" })
public class WaitersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
//    @EJB(beanName = "DrinkServices")
//    private DrinksLocal drinkServices;
//
//    @EJB(beanName = "OrderServices")
//    private OrdersLocal orderServices;
//    
//    private DrinksManagement drinksM;
//    
//    private OrdersManagement ordersM;
    
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
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	String action = request.getParameter("action");
	String selectedScreen = null;
	
//	System.out.println("waiter, drinks size: " + drinksM.getAllDrinks().size());
	
	// add consumer/and oreder
	// add order
	UsersManagement users = (UsersManagement) getServletContext().getAttribute("usersM");
	Waiter waiter =  (Waiter) users.getUserByName((String)session.getAttribute("username"));
	
	if (action.equalsIgnoreCase("createAnOrder")) {
	    
	} else if (action.equalsIgnoreCase("add order")) {
	    
	    
	    System.out.println("added order " + waiter.getName());    
	}

	selectedScreen = "/protected/waiters.jsp";
	// get host, port, context,... programatically
	response.sendRedirect("http://localhost:8080/BarMngmtSystem" + selectedScreen);
	// request.getRequestDispatcher(selectedScreen).forward(request, response);
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	// what will happen if we do not call doGet?
	doGet(request, response);
    }

}
