package web.users;

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
import ejb.Observer;
import ejb.Subject;
import ejb.User;

public class OrdersManagement implements Subject {

    private static Logger log = Logger.getLogger(OrdersManagement.class.getName());

    private List<Order> activeOrdersList;
    private ServletContext context;

    // private Map<Order, OrderStatus> orderStatusMap = new HashMap<Order, OrderStatus>();

    private OrdersLocal services;

    public OrdersManagement(OrdersLocal services, ServletContext context) {
	this.context = context;
	this.services = services;
	loadAllActiveOrders();
    }

    private void loadAllActiveOrders() {
	List<Order> l = services.findAllActiveOrdersByUserId(-1);	// a negative Id to get all order independent of the waiters who serves them
	this.activeOrdersList = new ArrayList<Order>(l.size());
	for (Order o : l) {
	    activeOrdersList.add(o);
	}
    }

    public List<Order> findAllActiveOrdersByWaiterId(int waiterId) {
	List<Order> l = services.findAllActiveOrdersByUserId(waiterId);
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
	System.out.println("Test of creation of an order...");

//	Consumer cre = services.findActiveConsumereByPlace(place);

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
	    activeOrdersList.add(ce);
	    new OrderTimer(this, ce);
	    System.out.println("orders size: " + activeOrdersList.size());
	    System.out.println("Test of persisting a order finished well!");
	    
	    notifyObservers();
	    return ce;
	} else {
	    System.out.println("Test of persisting a order failed!");
	}
	return null;
    }
    
    public List<Consumer> findAllActiveConsumersByUserId(int userId) {
	List<Consumer> l = services.findAllActiveConsumersByUserId(userId);
	return l;
    }

    public OrderStatus getOrderStatus(Order order) {
	OrderStatus status = null;
	try {
	    System.out.println("Order " + order.getId() + "old status: " + order.getStatus());
	    status = services.getStatusForOrder(order.getId());
	    System.out.println("Order " + order.getId() + "new status: " + status);
	} catch (Exception e) {
	    // TODO: handle exception
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
//	    if (resOrder.getStatus() != OrderStatus.PENDING || resOrder.getStatus() != OrderStatus.OVERDUE) {
//		removeAnOrderFromWaitingList(resOrder);
//	    } else {
//		activeOrdersList.add(resOrder);
//	    }
	    System.out.println("Updated order: " + resOrder.getId());
	}
	loadAllActiveOrders();
	notifyObservers();
	System.out.println("Test of updating an order finished well!");
    }

    private void removeAnOrderFromWaitingList(Order order) {
	if (!activeOrdersList.isEmpty()) {
	    for (Order o : activeOrdersList) {
		if (o.getId().compareTo(order.getId()) == 0) {
		    activeOrdersList.remove(o);
		}
	    }
	}
    }

    public synchronized void acceptAnOrder(Order order) {
	updateAnOrder(order);
    }

    public synchronized void finishAnOrder(Order order) {
	updateAnOrder(order);
    }

    public List<Order> getAllActiveOrdersList() {
	return activeOrdersList;
    }
    
    public Order findOrderById(int orderId) {
	return services.findOrderById(orderId);
    }
    
    @Override
    public void notifyObservers() {
	UsersManagement users = (UsersManagement) context.getAttribute("usersM");
	List<Observer> observers = users.getObservers();
	for (Observer o : observers) {
	    o.update();
	}
    }

}
