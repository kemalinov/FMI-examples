package ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import web.pojos.Drink;
import web.pojos.Order;
import web.users.DrinksManagement;
import web.users.OrdersManagement;
import constants.OrderStatus;

@Stateful
public class Barman extends User implements Observer {

<<<<<<< HEAD
	private List<Order> activeOrders = new ArrayList<Order>();

	private OrdersManagement ordersM;
	private DrinksManagement drinksM;

	public Barman(User u, OrdersManagement oM, DrinksManagement drinksM) {
		super(u.getId(), u.getName(), u.getPassword(), u.getRole());
		this.ordersM = oM;
		this.drinksM = drinksM;

		loadAllActiveOrdersForMe();
	}

	public void acceptAnOrder(Order order) {
		ordersM.acceptAnOrder(order);
	}

	public void finishAnOrder(Order order) {
		ordersM.finishAnOrder(order);
	}

	private void loadAllActiveOrdersForMe() {
		List<Order> l = ordersM.getAllActiveOrdersList();
		activeOrders.clear();
		for (Order o : l) {
			activeOrders.add(o);
		}
	}

	// @Produces?
	public List<Order> getActiveOrders() {
		return activeOrders;
	}

	public Order getOrderById(int id) {
		for (Order o : activeOrders) {
			if (o.getId().compareTo(id) == 0) {
				return o;
			}
		}
		return null;
	}

	public Drink createADrink(String drinkName, String drinkIngredients, String drinkPrice) {
		return drinksM.persistADrink(new Drink(null, drinkName, drinkIngredients, new BigDecimal(drinkPrice)));
	}

	@Override
	public void update() {
		System.out.println("barman id: " + super.getId() + " has been observerd for change in orders' list!");

		loadAllActiveOrdersForMe();
	}
=======
    private List<Order> activeOrders = new ArrayList<Order>();
    
    private OrdersManagement ordersM;
    private DrinksManagement drinksM;
    
    public Barman(User u, OrdersManagement oM, DrinksManagement drinksM) {
	super(u.getId(), u.getName(), u.getPassword(), u.getRole());
	this.ordersM = oM;
	this.drinksM = drinksM;
	
	loadAllActiveOrdersForMe();
    }
    
    public void acceptAnOrder(Order order) {
	order.setStatus(OrderStatus.ACCEPTED);
	ordersM.acceptAnOrder(order);
    }
    
    public void finishAnOrder(Order order) {
	order.setStatus(OrderStatus.DONE);
	ordersM.finishAnOrder(order);
    }

    private void loadAllActiveOrdersForMe() {
	List<Order> l = ordersM.getAllActiveOrdersList();
	activeOrders.clear();
	for(Order o : l) {
	    activeOrders.add(o);
	}
    }
  //@Produces?
    public List<Order> getActiveOrders() {
	return activeOrders;
    }
    
    public Order getOrderById(int id) {
	for(Order o : activeOrders) {
	    if(o.getId().compareTo(id) == 0) {
		return o;
	    }
	}
	return null;
    }

    public Drink createADrink(String drinkName, String drinkIngredients, String drinkPrice) {
	return drinksM.persistADrink(new Drink(null, drinkName, drinkIngredients, new BigDecimal(drinkPrice)));
    }
    
    @Override
    public void update() {
	System.out.println("barman id: " + super.getId() + " has been observerd for change in orders' list!");

	loadAllActiveOrdersForMe();
    }
>>>>>>> branch 'master' of https://github.com/kemalinov/FMI-examples.git

}
