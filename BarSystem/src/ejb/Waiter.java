package ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;

import web.management.OrdersManagement;
import web.pojos.Consumer;
import web.pojos.Drink;
import web.pojos.Order;

@Stateful
public class Waiter extends User implements Observer {

	private List<Order> ordersList = new ArrayList<Order>();
	private Map<String, Consumer> clientsMap = new HashMap<String, Consumer>();

	private OrdersManagement ordersM;

	
	public Waiter(User u, OrdersManagement oM) {
		super(u.getId(), u.getName(), u.getPassword(), u.getRole());
		this.ordersM = oM;

		loadAllMyActiveClients();
		loadAllActiveOrdersForMe();
	}

	public List<Consumer> getMyClients() {
		return new ArrayList<Consumer>(clientsMap.values());
	}

	private void loadAllMyActiveClients() {
		List<Consumer> list = ordersM.findAllActiveConsumersByUserId(this.getId());
		clientsMap.clear();
		for (Consumer c : list) {
			clientsMap.put(c.getPlace(), c);
		}
	}

	private void loadAllActiveOrdersForMe() {
		List<Order> l = ordersM.findAllActiveOrdersForWaiterById(super.getId());
		ordersList.clear();
		for (Order o : l) {
			ordersList.add(o);
		}
	}

	// @Produces?
	public List<Order> getMyOrders() {
		return ordersList;
	}
	
	public Order addAnOdrerTo(String place, Map<Drink, Integer> drinks) {
		Consumer cons = getConsumerByPlace(place);
		if (cons != null) {
			Order order = ordersM.addOrderToConsumer(cons, drinks);
			return order;
		} else {
			return null;
		}
	}

	private Consumer getConsumerByPlace(String place) {
		if (clientsMap.containsKey(place)) {
			System.out.println("The consumer is found!");
			return clientsMap.get(place);
		}
		System.out.println("The consumer was not found!");
		return null;
	}

	public String getPlaceOfOrder(int orderId) {
		for (Order o : ordersList) {
			if (orderId == o.getId()) {
				return o.getConsumerId().getPlace();
			}
		}
		return null;
	}
	
	public Consumer createConsumerWOrder(Date date, String place, Map<Drink, Integer> drinks) {
		Consumer consumer = ordersM.persistConsumer(this, date, place);
		clientsMap.put(consumer.getPlace(), consumer);
		addAnOdrerTo(consumer.getPlace(), drinks);
		return consumer;
	}
	
	public boolean closeAnOrder(String place) {
		if (clientsMap.containsKey(place)) {
			Consumer c = clientsMap.remove(place);
			ordersM.closeAConsumer(c);
			update();
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		System.out.println("waiter id: " + super.getId() + " has been observerd for change in orders' list!");
		loadAllActiveOrdersForMe();
		loadAllMyActiveClients();
	}

}
