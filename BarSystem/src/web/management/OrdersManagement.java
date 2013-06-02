package web.management;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import services.OrdersLocal;
import web.pojos.Consumer;
import web.pojos.Drink;
import web.pojos.Order;
import web.pojos.OrderTimer;
import web.pojos.Role;
import constants.OrderStatus;
import ejb.OrdersNotification;
import ejb.User;

public class OrdersManagement {

	private static Logger log = Logger.getLogger(OrdersManagement.class.getName());

	private List<Order> alertedOrders = new ArrayList<Order>();
	
	private ServletContext context;

	private OrdersLocal services;

	public OrdersManagement(OrdersLocal services, ServletContext context) {
		this.context = context;
		this.services = services;
	}

	// returns all orders for barmans
	public List<Order> findAllActiveOrdersForBarmans() {
		List<Order> l = services.findAllActiveOrdersByUserId(-1); // returns all orders with status "Pending" and "Overdue"
		return l;
	}

	// returns all active Orders for provided waiter's id
	public List<Order> findAllActiveOrdersForWaiterById(int waiterId) {
		List<Order> l = services.findAllActiveOrdersByUserId(waiterId);
		return l;
	}

	// returns all active Consumers
	public List<Consumer> findAllActiveConsumersByUserId(int userId) {
		List<Consumer> l = services.findAllActiveConsumersByUserId(userId);
		return l;
	}
	
	public Consumer persistConsumer(User user, Date date, String place) {
		System.out.println("Test of persisting a consumer...");

		System.out.println(user);
		Role r = new Role(user.getRole().getId(), user.getRole().getRole());
		User u = new User(user.getId(), user.getName(), user.getPassword(), r);

		Consumer ce = new Consumer(null, date, place, false, u);
		Consumer newC = null;
		try {
			newC = services.persistConsumer(ce);
			System.out.println(newC.getId());
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception while persists a new consumer: ", e);
		}
		if (newC != null && newC.getId() != null) {
			System.out.println("Test of persisting a consumer finished well! " + newC.getId());
		} else {
			System.out.println("Test of persisting a consumer failed!");
		}
		return newC;
	}

	public Order addOrderToConsumer(Consumer consumer, Map<Drink, Integer> drinks) {
		System.out.println("Adding a new order to...");

		Order newOrder = new Order();
		newOrder.setConsumerId(consumer);
		newOrder.setDrinks(drinks);
		newOrder.setStatus(OrderStatus.PENDING);
		newOrder.setCalculatedBill();

		Order ce = null;
		try {
			ce = services.persistOrder(newOrder);
			System.out.println("The new persisted order id " + ce.getId());
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception while persists a new order: ", e);
		}

		if (ce != null && ce.getId() != null) {
			new OrderTimer(this, ce);
			OrdersNotification.getInstance().notifyObservers();
			System.out.println("Persist of an order finished well! Notificated observers!");
			return ce;
		} else {
			System.err.println("Persist of an order returned NULL!");
		}
		return null;
	}

	public OrderStatus getOrderStatus(Order order) {
		OrderStatus status = null;
		try {
			System.out.println("Order " + order.getId() + "old status: " + order.getStatus());
			status = services.getStatusForOrder(order.getId());
			System.out.println("Order " + order.getId() + "new status: " + status);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception while getting the order's (" + order.getId() + ") status ", e);
		}
		return status;
	}

	public synchronized void updateAnOrder(Order order) {
		System.out.println("The order " + order.getId() + " is about to be updated...");
		Order resOrder = null;
		try {
			System.out.println("Updating an order: " + order.getId());
			resOrder = services.persistOrder(order);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception while updating the order with id: " + order.getId(), e);
		}

		if (resOrder != null) {
			System.out.println("Updated order: " + resOrder.getId());
		}
		OrdersNotification.getInstance().notifyObservers();
		System.out.println("Test of updating an order finished well!");
	}

	public void acceptAnOrder(Order order) {
		synchronized (alertedOrders) {
			order.setStatus(OrderStatus.ACCEPTED);
			for (Order o : alertedOrders) {
				if (o.getId().compareTo(order.getId()) == 0) {
					alertedOrders.remove(o);
				}
			}
			updateAnOrder(order);	
		}
		
	}

	public synchronized void finishAnOrder(Order order) {
		order.setStatus(OrderStatus.DONE);
		updateAnOrder(order);
	}
	
	public void closeAConsumer(Consumer consumer) {
		consumer.setClosed(Boolean.TRUE);
		services.persistConsumer(consumer);
	}

	public Order findOrderById(int orderId) {
		return services.findOrderById(orderId);
	}
	
	public List<Order> getAlerterOrders() {
		return alertedOrders;
	}
	
	public void addOrderToAlert(Order o) {
		alertedOrders.add(o);
	}

}
