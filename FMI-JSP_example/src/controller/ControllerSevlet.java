package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import log.AuditLog;

import users.UserManagement;

/**
 * Servlet implementation class ControllerSevlet
 */
@WebServlet(urlPatterns = {"/public/controller"})
public class ControllerSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String selectedScreen = null;
        AuditLog auditLog = (AuditLog) getServletContext().getAttribute("auditLog");
        UserManagement users = (UserManagement) getServletContext().getAttribute("users");
        
        if(action.equalsIgnoreCase("login")) {
        	// read user and password and try to authenticate
        	String username = request.getParameter("username");
        	String password = request.getParameter("password");
        	
        	if(users.checkUser(username, password)) {
        		session.setAttribute("username", username);
        		selectedScreen = "/protected/homepage.jsp";
        		System.out.println("authentication successfully for user " + username);
        		auditLog.log("login", username, true, request.getRemoteAddr());
        	} else {
        		// error handling?
        		System.out.println("authentication failed for user " + username);
        		auditLog.log("login", username, false, request.getRemoteAddr());
        		selectedScreen = "/public/login.jsp";
        	}
        	
        } else if(action.equalsIgnoreCase("register")) {
        	// read params and create profile..
        	String username = request.getParameter("username");
        	String password1 = request.getParameter("password");
        	String password2 = request.getParameter("password1");
        	if(!password1.equals(password2)) {
        		System.out.println("pass1 != pass2");
        		auditLog.log("register", username, false, request.getRemoteAddr(), "pass1 != pass2");
        	} else {
        		System.out.println("pass1 = pass2 --> go next");
        		if(users.registerUser(username, password1)) {
        			System.out.println("registration ok...");
        			auditLog.log("register", username, true, request.getRemoteAddr());
        		} else {
        			auditLog.log("register", username, false, request.getRemoteAddr());
        		}
        	}        	
        	
        	// after successful creation of the profile forward to login page
        	
        	selectedScreen = "/public/login.jsp";
        } else if(action.equals("logout")) {
        	String username = (String) session.getAttribute("username");
        	session.invalidate();
        	selectedScreen = "/public/login.jsp";
        	auditLog.log("logout", username, true, request.getRemoteAddr());
        }
        
        //get host, port, context,... programatically
        response.sendRedirect("http://localhost:8080/fmi" + selectedScreen);
//        request.getRequestDispatcher(selectedScreen).forward(request, response); 
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  // what will happen if we do not call doGet?
	  doGet(request, response);
	}

}
