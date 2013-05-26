package ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;

import web.pojos.Consumer;
import web.pojos.Drink;
import web.pojos.Order;
import web.users.OrdersManagement;

@Stateful
public class Waiter extends User implements Observer {

    private List<Order> allMyActiveOrders = new ArrayList<Order>();
    private List<Consumer> myCurrentClients = new ArrayList<Consumer>();
    
    private OrdersManagement ordersM;
    
    
    public Waiter(User u, OrdersManagement oM) {
	super(u.getId(), u.getName(), u.getPassword(), u.getRole());
	this.ordersM = oM;
	
	loadAllMyActiveClients();
	loadAllActiveOrderForMe();
    }
    
    public List<Consumer> getAllMyClients() {
	return myCurrentClients;
    }
    
    private void loadAllMyActiveClients() {
	List<Consumer> list = ordersM.findAllActiveConsumersByUserId(this.getId());
	myCurrentClients.clear();
	for(Consumer c : list) {
	    myCurrentClients.add(c);
	}
    }
    
    private void loadAllActiveOrderForMe() {
	List<Order> l = ordersM.findAllActiveOrdersByWaiterId(super.getId());
	allMyActiveOrders.clear();
	for (Order o : l) {
	    allMyActiveOrders.add(o);
	}
    }
//@Produces?
    public List<Order> getMyActiveOrders() {
	return allMyActiveOrders; 
    }
    
    public Order addAnOdrerTo(String place, Map<Drink, Integer> drinks) {
	Consumer cons = getConsumerByPlace(place);
	Order order = ordersM.addOrderToConsumer(cons, drinks);
	allMyActiveOrders.add(order);
	return order;
    }
    
    private Consumer getConsumerByPlace(String place) {
	for(Consumer c : myCurrentClients) {
	    if (c.getPlace().equals(place))
		return c;
	}
	return null;
    }
    
    public Consumer createConsumerWOrder(Date date, String place, Map<Drink, Integer> drinks) {
	Consumer consumer = ordersM.persistConsumer(this, date, place);
	myCurrentClients.add(consumer);
	addAnOdrerTo(consumer.getPlace(), drinks);
	return consumer;
    }
    
    @Override
    public void update() {
	System.out.println("waiter id: " + super.getId() + " has been observerd for change in orders' list!");
	loadAllActiveOrderForMe();
	loadAllMyActiveClients();
    }
    
}
