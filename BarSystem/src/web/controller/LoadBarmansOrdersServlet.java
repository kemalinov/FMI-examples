package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.pojos.Order;
import constants.OrderStatus;
import ejb.Barman;
import ejb.User;

@WebServlet(name="AbstractLoadOrdersServlet", urlPatterns = { "/LoadBarmansOrders" })
public class LoadBarmansOrdersServlet extends AbstractLoadOrdersServlet { //HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoadBarmansOrdersServlet() {
		super();
	}

	// TODO: ASK!: why if the list is only set as an attribute and provided by the dispatcher, loads a "whole new page" in the table not only the table's content? 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

}
