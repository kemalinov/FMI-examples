package web.users;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.servlet.ServletContext;

import services.DrinksLocal;
import web.pojos.Drink;

public class DrinksManagement {

    private static Logger log = Logger.getLogger(DrinksManagement.class.getName());

    private Map<String, Drink> nameDrinkMap;
    private ServletContext context;

    private DrinksLocal services;

    public DrinksManagement(DrinksLocal services, ServletContext context) {
	// this.context = context;
	 this.services = services;
	loadDrinks();
    }

    private void loadDrinks() {
	List<Drink> drinkList = null;
	try {
	    drinkList = services.findAllDrinks();
	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while calling findAllDrinks()", e);
	}
	if (drinkList != null) {
	    nameDrinkMap = new HashMap<String, Drink>(drinkList.size());
	    for (Drink d : drinkList) {
		// Drink d = Utils.DrinkToDrink(dto);
		addNewDrink(d);
	    }
	    System.out.println("drinks size: " + nameDrinkMap.size());
	}
    }

    private void addNewDrink(Drink d) {
	nameDrinkMap.put(d.getName(), d);
    }

    public Map<String, Drink> getAllDrinks() {
	return nameDrinkMap;
    }

    public void persistADrink(Drink drink) {
	System.out.println("Test of persisting a drink...");

	// persist a drink
	// PersistDrinkRequest req = new PersistDrinkRequest(new DrinkDTO(null,
	// "airqn", "voda,200;mlqko,200", new BigDecimal("1.20")));
	Drink d = new Drink(null, "airqn", "voda,200;mlqko,200",
		new BigDecimal("1.20"));

	try {
	    Drink dr = services.persistDrink(d);
	    addNewDrink(dr);
	    System.out.println("New drink ID: " + dr.getId());

	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while persists a new drink: ", e);
	}

	System.out.println("Test of persisting a drink finished well!");
    }

    // ////////////////// Test methods

    // public void testPersistingOrder() { // need consumer_id
    // System.out.println("Test of persisting an order...");
    //
    // // persist a consumer - get the logged user!
    // ConsumerResponseEntity cre = services.findConsumerById(new
    // FindByConsumerIdRequest(new Integer(1)));
    //
    // Map<Drink, Integer> drinks = new HashMap<Drink, Integer>();
    // DrinkResponseEntity dre = services.findDrinkById(new
    // FindByDrinkIdRequest(new Integer(1)));
    // drinks.put(Utils.DrinkDtoToDrink(dre.getDrink()), 3);
    //
    // Order newOrder = new Order();
    // newOrder.setConsumerId(Utils.ConsumerDtoToConsumer(cre.getConsumer()));
    // newOrder.setDrinks(drinks);
    // newOrder.setStatus(OrderStatus.PENDING);
    // newOrder.setCalculatedBill();
    //
    // PersistOrderRequest req = new
    // PersistOrderRequest(Utils.OrderToOrderDto(newOrder));
    // try {
    // OrderResponseEntity ce = services.persistOrder(req);
    // System.out.println(ce.getOrder().getId());
    // } catch (Exception e) {
    // log.log(Level.SEVERE, "Exception while persists a new order: ", e);
    // }
    //
    // System.out.println("Test of persisting a order finished well!");
    // }
    // ////////////////////////////

}
