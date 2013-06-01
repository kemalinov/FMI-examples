package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import web.pojos.Order;
import web.users.OrdersManagement;

@WebServlet(name="OrdersAlertName", urlPatterns = { "/OrdersAlert" })
public class OrdersAlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrdersAlertServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OrdersManagement ordersM = (OrdersManagement) getServletContext().getAttribute("ordersM");
		List<Order> ordersList  = ordersM.getAlerterOrders();
	
		if (ordersList != null && !ordersList.isEmpty()) {
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			
			String wOrders = ordersList.get(0).getId().toString();
			for (int i=1; i< ordersList.size(); i++) {
				wOrders = wOrders.concat(", " + ordersList.get(i).getId());
			}
	        JSONObject obj = new JSONObject();
	        obj.put("size", ordersList.size());
	        obj.put("arr", "<p> Waiting orders :" + wOrders + "! </p>");
	        out.write(obj.toString());

	        System.out.println("alert for " + wOrders);
//			out.print("<p> Waiting orders :" + wOrders + "</p>");
		}
		
		//req.setAttribute("param", "hoho");
//		System.out.println("order's "+ ordersList +" alert sent to " + selectedScreen);
		//req.getRequestDispatcher(selectedScreen).forward(req, resp);
		//resp.sendRedirect("http://localhost:8080/BarMngmtSystem" + selectedScreen + "?param=hoho");
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
