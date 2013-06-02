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

import web.management.UsersManagement;
import web.pojos.Drink;
import web.pojos.Order;
import constants.OrderStatus;
import constants.RolesType;
import ejb.Barman;
import ejb.User;
import ejb.Waiter;

@WebServlet(name="LoadOrdersName", urlPatterns = { "/LoadOrders" })
public class LoadOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoadOrdersServlet() {
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
    		if (user.getRole().getRole().equals(RolesType.BARMAN)) {
    			Barman barman = (Barman) user;
    			
    			ordersList = barman.getActiveOrders();
    			req.setAttribute("orders", ordersList);
    			
    			selectedScreen = "/protected/barmans.jsp";
    		} else if (user.getRole().getRole().equals(RolesType.WAITER)) {
    			Waiter waiter = (Waiter) user;
    			
    			ordersList = waiter.getMyOrders();
    			req.setAttribute("orders", ordersList);
    		
    			selectedScreen = "/protected/waiters.jsp";
    		}
    	
    		if (ordersList != null) {
    			resp.setContentType("text/html");
    			PrintWriter out = resp.getWriter();
    			
    			createTable(user, ordersList, out);
    		}
    		System.out.println("sent orders to " + user.getName() + ", " + selectedScreen);
		} else {
			System.out.println("In LoadOrdersServlet user is null!");
		}
		//req.getRequestDispatcher(selectedScreen).forward(req, resp);
		//resp.sendRedirect("http://localhost:8080/BarMngmtSystem" + selectedScreen);
	}

	private void createTable(User user, List<Order> ordersList, PrintWriter out) {
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
			if (user.getRole().getRole().equals(RolesType.WAITER) && !o.getStatus().equals(OrderStatus.DONE)){
				out.print("<td><INPUT TYPE=\"radio\" NAME=\"orderIDRadioBtn\" VALUE=\"" + o.getId() +"\" disabled=\"disabled\"></td>");
			} else {
				out.print("<td><INPUT TYPE=\"radio\" NAME=\"orderIDRadioBtn\" VALUE=\"" + o.getId() +"\" ></td>");
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



//resp.setContentType("text/html");
//PrintWriter out = resp.getWriter();
//
//out.print("<table>");
//out.print("<colgroup><col width=\"20px\" /><col width=\"40px\" /><col width=\"80px\" /><col width=\"150px\" />" +
//		"<col width=\"100px\" /><col width=\"80px\" /><col width=\"60px\" />" +
//		"</colgroup>");
//out.print("<tbody>");
//out.print("<c:forEach items=\"${orders}\" var=\"order\">");
//out.print("<c:if test=\"${order.status=='OVERDUE'}\">");
//out.print("<tr style=\"background-color: red;\">");
//out.print("</c:if>");
//out.print("<c:if test=\"${order.status!='OVERDUE'}\">");
//out.print("<tr>");
//out.print("</c:if>");
//out.print("<c:if test=\"${(order.status=='OVERDUE') || (order.status=='PENDING') || (order.status=='ACCEPTED')}\">");
//out.print("<td><INPUT TYPE=\"radio\" NAME=\"doneAnOrderRadioBtn\" VALUE=\"${order.id}\" disabled=\"disabled\"></td>");
//out.print("</c:if>");
//out.print("<c:if test=\"${order.status=='DONE'}\">");
//out.print("<td><INPUT TYPE=\"radio\" NAME=\"doneAnOrderRadioBtn\" VALUE=\"${order.id}\" ></td>");
//out.print("</c:if>");
//out.print("<td><c:out value=\"${order.id}\" /></td>");
//out.print("<td><c:out value=\"${order.consumerId.place}\" /></td>");
//out.print("<td><c:out value=\"pitieta..\" /></td>");
//out.print("<td><c:out value=\"broi;...\" /></td>");
//out.print("<td><c:out value=\"${order.status}\" /></td>");
//out.print("<td><c:out value=\"${order.bill}\" /></td>");
//out.print("</tr>");
//out.print("</c:forEach>");	
//out.print("</tbody>");
//out.print("</table>");
