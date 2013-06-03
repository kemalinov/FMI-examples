package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.pojos.Drink;
import web.pojos.Order;
import constants.OrderStatus;
import ejb.Barman;
import ejb.User;

@WebServlet(name="LoadBarmansOrdersName", urlPatterns = { "/LoadBarmansOrders" })
public class LoadBarmansOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoadBarmansOrdersServlet() {
		super();
	}

	// TODO: ASK!: why if the list is only set as an attribute and provided by the dispatcher, loads a "whole new page" in the table not only the table's content? 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
//		UsersManagement users = (UsersManagement) getServletContext().getAttribute("usersM");
		User user = (User) session.getAttribute("loggedUser");
		
		String selectedScreen = "";
		List<Order> ordersList = null;
		
		if (user != null) {
    			Barman barman = (Barman) user;
    			
    			ordersList = barman.getActiveOrders();
    			req.setAttribute("orders", ordersList);
    			
    			selectedScreen = "/protected/barmans.jsp";
    	
    		if (ordersList != null) {
    			resp.setContentType("text/html");
    			PrintWriter out = resp.getWriter();
    			
    			createTable(ordersList, out);
    		}
    		System.out.println("sent orders to barman " + barman.getName() +", "+ selectedScreen);
		} else {
			System.out.println("In LoadOrdersServlet user is null!");
		}
		//req.getRequestDispatcher(selectedScreen).forward(req, resp);
		//resp.sendRedirect("http://localhost:8080/BarMngmtSystem" + selectedScreen);
	}

	private void createTable(List<Order> ordersList, PrintWriter out) {
		out.print("<table>");
		out.print("<colgroup>" +
				"<col width=\"20px\" />" +
				"<col width=\"40px\" /><col width=\"80px\" /><col width=\"150px\" />" +
				"<col width=\"100px\" /><col width=\"80px\" /><col width=\"60px\" />" +
				"</colgroup>");
		out.print("<tbody>");
		for(Order o : ordersList) {
			if (o.getStatus().equals(OrderStatus.OVERDUE)) {
				out.print("<tr style=\"background-color: red;\">");
			} else {
				out.print("<tr>");
			}
			if (o.getStatus().equals(OrderStatus.PENDING) || o.getStatus().equals(OrderStatus.OVERDUE)) {
				out.print("<td><INPUT TYPE=\"radio\" NAME=\"orderIDRadioBtn\" VALUE=\"" + o.getId() +"\" ></td>");
			} else if (o.getStatus().equals(OrderStatus.ACCEPTED)) {
				out.print("<td><INPUT TYPE=\"radio\" NAME=\"orderIDRadioBtn\" VALUE=\"" + o.getId() +"\" disabled=\"disabled\"></td>");
			}
			out.print("<td>" + o.getId() + "</td>");
			out.print("<td>" + o.getConsumerId().getPlace() + "</td>");
			
			String drinks = "";
			String counts = "";
			for(Entry<Drink, Integer> e : o.getDrinks().entrySet()) {
				drinks += e.getKey().getName() + ";";
				counts += e.getValue() + ";";
			}
			out.print("<td>" + drinks + "</td>");
			out.print("<td>" + counts + "</td>");
			out.print("<td>" + o.getStatus().toString() + "</td>");
			out.print("<td>" + o.getBill() + "</td>");
			out.print("</tr>");
		}
		out.print("</tbody>");
		out.print("</table>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
