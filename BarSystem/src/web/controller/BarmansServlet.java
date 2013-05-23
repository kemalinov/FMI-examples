package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	// create a drink
	// accept an order
	UsersManagement users = (UsersManagement) getServletContext().getAttribute("usersM");
	Barman barman = (Barman) users.getUserByName((String) session.getAttribute("username"));

	// System.out.println("drinks size:" + drinksM.getAllDrinks().size());

	if (action.equalsIgnoreCase("add drink")) {
	    System.out.println("added drink by " + barman.getName());

	} else if (action.equalsIgnoreCase("accept")) {
	    // get selected one from list/combo/table/radio button?
	    System.out.println("accepted order id " + "::" + " by " + barman.getName());
	}

	selectedScreen = "/protected/barmans.jsp";
	
	// get host, port, context,... programatically
	response.sendRedirect("http://localhost:8080/BarMngmtSystem" + selectedScreen);
	// request.getRequestDispatcher(selectedScreen).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// what will happen if we do not call doGet?
	doGet(request, response);
    }

}
