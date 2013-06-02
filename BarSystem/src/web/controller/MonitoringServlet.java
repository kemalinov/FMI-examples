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
import ejb.Manager;
import ejb.User;

@WebServlet(urlPatterns = { "/protected/managers" })
public class MonitoringServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		public MonitoringServlet() {
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

			UsersManagement usersM = (UsersManagement) getServletContext().getAttribute("usersM");
			Manager manager = (Manager) session.getAttribute("loggedUser"); 

			if (manager != null) {
				System.out.println("in monitoring servlet....");
				
				List<User> loggedUsers = usersM.getAllLoggedUsers();
				request.setAttribute("loggedUsers", loggedUsers);
				
				
				selectedScreen = "/protected/managers.jsp";
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
