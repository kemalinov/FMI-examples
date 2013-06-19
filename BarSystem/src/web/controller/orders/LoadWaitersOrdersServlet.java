package web.controller.orders;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import web.pojos.Order;
import constants.OrderStatus;
import ejb.User;
import ejb.Waiter;

@WebServlet(name="LoadWaitersOrdersName", urlPatterns = { "/LoadWaitersOrders" })
public class LoadWaitersOrdersServlet extends AbstractLoadOrdersServlet {
	private static final long serialVersionUID = 1L;

	public LoadWaitersOrdersServlet() {
		super();
	}

	@Override
	protected List<Order> getOrdersListForUser(User user) {
		Waiter waiter = (Waiter) user;
		return waiter.getMyOrders();
	}

	@Override
	protected void writeTheRadioButtons(Order o, PrintWriter out) {
		if (! o.getStatus().equals(OrderStatus.DONE)){
			out.print("<td><INPUT TYPE=\"radio\" NAME=\"orderIDRadioBtn\" VALUE=\"" + o.getId() +"\" disabled=\"disabled\"></td>");
		} else {
			out.print("<td><INPUT TYPE=\"radio\" NAME=\"orderIDRadioBtn\" VALUE=\"" + o.getId() +"\" ></td>");
		}
	}
	
	@Override
	protected String gerRedirectionLink() {
		return "/protected/waiters.jsp";
	}

}
