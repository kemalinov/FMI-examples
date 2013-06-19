package web.controller.orders;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import web.pojos.Order;
import constants.OrderStatus;
import ejb.Barman;
import ejb.User;

@WebServlet(name="LoadBarmansOrdersName", urlPatterns = { "/LoadBarmansOrders" })
public class LoadBarmansOrdersServlet extends AbstractLoadOrdersServlet {
	private static final long serialVersionUID = 1L;

	public LoadBarmansOrdersServlet() {
		super();
	}

	@Override
	protected List<Order> getOrdersListForUser(User user) {
		Barman barman = (Barman) user;
		return barman.getActiveOrders();
	}

	@Override
	protected void writeTheRadioButtons(Order o, PrintWriter out) {
		if (o.getStatus().equals(OrderStatus.PENDING) || o.getStatus().equals(OrderStatus.OVERDUE)) {
			out.print("<td><INPUT TYPE=\"radio\" NAME=\"orderIDRadioBtn\" VALUE=\"" + o.getId() +"\" ></td>");
		} else if (o.getStatus().equals(OrderStatus.ACCEPTED)) {
			out.print("<td><INPUT TYPE=\"radio\" NAME=\"orderIDRadioBtn\" VALUE=\"" + o.getId() +"\" disabled=\"disabled\"></td>");
		}		
	}
	
	@Override
	protected String gerRedirectionLink() {
		return "/protected/barmans.jsp";
	}

}
