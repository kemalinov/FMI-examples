package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.users.UserManagement;

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
	// TODO Auto-generated constructor stub
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

	UserManagement users = (UserManagement) getServletContext()
		.getAttribute("usersM");

	if (action.equalsIgnoreCase("login")) {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    if (users.checkUser(username, password)) {
		session.setAttribute("username", username);
		selectedScreen = "/protected/homepage.jsp";
		System.out.println("authentication successfully for user " + username);
	    } else {
		// error handling?
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
		 if(users.registerUser(username, password1, role)) {
		     System.out.println("registration ok...");
		 } else {
		     System.out.println("registration failed...");
		 }
	    }
	    // after successful creation of the profile forward to login page
	    selectedScreen = "/public/login.jsp";
	    
	} else if (action.equals("clear")) {
	    	    
	    
	} else if (action.equals("logout")) {
	    String username = (String) session.getAttribute("username");
	    session.invalidate();
	    selectedScreen = "/public/login.jsp";
	    System.out.println("logout of " + username);
	    // auditLog.log("logout", username, true, request.getRemoteAddr());
	}

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
