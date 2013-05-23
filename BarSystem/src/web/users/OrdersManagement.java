package web.users;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
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
    
    private PriorityQueue<Order> pendingOrders;
    private ServletContext context;

//    private Map<Order, OrderStatus> orderStatusMap = new HashMap<Order, OrderStatus>();
    
    private OrdersLocal services;
    
    
    public OrdersManagement(OrdersLocal services, ServletContext context) {
	this.context = context;
	this.services = services;
	this.pendingOrders = new PriorityQueue<Order>();
    }

    
    public Consumer createNewClientOrder(User loggedUserName, Date date, String place, Map<Drink, Integer> drinks) {
	// persist consumer
	Consumer c = persistConsumer(loggedUserName, date, place);
	// persist the order
	addOrderToConsumer(c, drinks);
	return c;
    }
    
    public Order addOrderToConsumer(Consumer consumer, Map<Drink, Integer> drinks) {
	System.out.println("Test of creation of an order...");
	
	// persist a consumer - get the logged user! 
//	Consumer cre = services.findConsumerById(1);
//	
//	Map<Drink, Integer> drnks = new HashMap<Drink, Integer>();
//	Drink dre = services.findDrinkById(4);
//	drnks.put(dre, 2);
	
	Order newOrder = new Order();
	newOrder.setConsumerId(consumer);
	newOrder.setDrinks(drinks);
	newOrder.setStatus(OrderStatus.PENDING);
	newOrder.setCalculatedBill();
	
//	PersistOrderRequest req = new PersistOrderRequest(Utils.OrderToOrderDto(newOrder));
	Order ce = null;
	try {
	    ce = services.persistOrder(newOrder);
	    System.out.println("The new persisted order id " + ce.getId());
	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while persists a new order: ", e);
	}
	
	if (ce != null) {
	    pendingOrders.add(ce);
	    new OrderTimer(this, ce);
	    System.out.println("orders size: " + pendingOrders.size());
	    System.out.println("Test of persisting a order finished well!");
	    return ce;
	}
	return null;
    }

    private Consumer persistConsumer(User loggedUser, Date date, String place) {
	System.out.println("Test of persisting a consumer...");
	
	// persist a consumer - get the logged user! 
//	UsersManagement userM = (UsersManagement) context.getAttribute("usersM");
//	User loggedUser = userM.getUserByName(loggedUserName);
	
	System.out.println(loggedUser);
	User u = new User(loggedUser.getId(), loggedUser.getName(), loggedUser.getPassword(), new Role(loggedUser.getRole().getId(), loggedUser.getRole().getRole()));
	
	// date
	// place
	Consumer c = new Consumer(null, date, place, false, u);
	Consumer ce = null;
	try {
	     ce = services.persistConsumer(c);
	    System.out.println(ce.getId());
	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while persists a new consumer: ", e);
	}

	System.out.println("Test of persisting a consumer finished well!");
	return ce;
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
    
    public void updateAnOrder(Order order) {
//	PersistOrderRequest req = new PersistOrderRequest(Utils.OrderToOrderDto(order));
	Order ce = null;
	try {
	    System.out.println("Updating an order: " + order.getId());
	    ce = services.persistOrder(order);
	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while updating the order with id: " + order.getId(),  e);
	}
	
	if (ce != null) {
	   // Order o = Utils.OrderDtoToOrder(ce.getOrder()); 
	    if (ce.getStatus() != OrderStatus.PENDING || ce.getStatus() != OrderStatus.OVERDUE) {
		removeAnOrderFromWaitingList(ce);
	    }
	    System.out.println("Updated order: " + ce.getId());
	}
	System.out.println("Test of updating an order finished well!");
    }
    
    private void removeAnOrderFromWaitingList(Order order) {
	for(Order o : pendingOrders) {
	    if (o.getId().compareTo(order.getId()) == 0) {
		pendingOrders.remove(o);
	    }
	}
    }
    
    public synchronized Order acceptTheNextOrder() {
	Order order = pendingOrders.poll();
	if (order != null && (order.getStatus().equals(OrderStatus.PENDING) || 
		order.getStatus().equals(OrderStatus.OVERDUE))) {
	    order.setStatus(OrderStatus.ACCEPTED);
	    updateAnOrder(order);
	    return order;
	}
	return null;
    }
    
    public synchronized void finishAnOrder(Order order) {
	order.setStatus(OrderStatus.DONE);
	updateAnOrder(order);
    }
    
    
    
    @Override
    public void notifyObservers(Order order) {
	UsersManagement users = (UsersManagement) context.getAttribute("usersM");
	List<Observer> observers = users.getObservers();
	for(Observer o : observers) {
	    o.update(order);
	}
    }	
    
    
    
    
}
