package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.users.UsersManagement;
import constants.RolesType;
import ejb.User;

/**
 * Servlet implementation class ControllerSevlet
 */
@WebServlet(urlPatterns = { "/public/controller" })
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
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
	// System.out.println("users size: " + users.getAllRoles().size());

	if (action.equalsIgnoreCase("login")) {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    if (users.checkUser(username, password)) {	// if it is a valid one it is auto logged then! 
		session.setAttribute("username", username);
		User u = users.getLoggedUserByName(username);
		session.setAttribute("loggedUser", u);
		
		if (u.getRole().getRole().equals(RolesType.WAITER)) {
		    selectedScreen = "/protected/waiters";
		} else if (u.getRole().getRole().equals(RolesType.BARMAN)) {
		    selectedScreen = "/protected/barmans";
		} else if (u.getRole().getRole().equals(RolesType.MANAGER)) {
		    selectedScreen = "/protected/managers.jsp";
		}
		System.out.println("authentication successfully for user " + username);
		
	    } else {
		// TODO: error handling?
		System.out.println("authentication failed for user " + username);
		selectedScreen = "/public/login.jsp";
	    }
	} else if (action.equalsIgnoreCase("register")) {
	    String username = request.getParameter("username");
	    String password1 = request.getParameter("password");
	    String password2 = request.getParameter("password1");
	    String role = request.getParameter("role");

	    if (!password1.equals(password2)) {
		System.out.println("pass1 != pass2");
	    } else {
		System.out.println("pass1 = pass2 --> go next");
		if (users.registerUser(username, password1, role)) {
		    System.out.println("registration ok...");
		} else {
		    System.out.println("registration failed...");
		}
	    }
	    // after successful creation of the profile forward to login page
	    selectedScreen = "/public/login.jsp";

	} else if (action.equals("logout")) {
	    String username = (String) session.getAttribute("username");
	    users.logoutUser(username);
	    session.invalidate();
	    selectedScreen = "/public/login.jsp";
	    System.out.println("logout of " + username);
	    // auditLog.log("logout", username, true, request.getRemoteAddr());
	} else { // cancel
	    selectedScreen = "/public/login.jsp";
	}

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
