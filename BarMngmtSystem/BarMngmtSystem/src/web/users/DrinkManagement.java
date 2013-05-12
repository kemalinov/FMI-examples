package web.users;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import web.pojos.Drink;
import web.pojos.Order;
import web.pojos.User;
import web.utils.Utils;

import commons.constants.OrderStatus;
import commons.consumers.ConsumerResponseEntity;
import commons.consumers.FindByConsumerIdRequest;
import commons.consumers.PersistConsumerRequest;
import commons.drinks.DrinkListResponse;
import commons.drinks.DrinkResponseEntity;
import commons.drinks.FindByDrinkIdRequest;
import commons.drinks.PersistDrinkRequest;
import commons.dtos.ConsumerDTO;
import commons.dtos.DrinkDTO;
import commons.dtos.RoleDTO;
import commons.dtos.UserDTO;
import commons.orders.OrderResponseEntity;
import commons.orders.PersistOrderRequest;

import facade.Facade;

public class DrinkManagement {
    
    private static Logger log = Logger.getLogger(DrinkManagement.class.getName());
    
    private List<Drink> drinks;
    private ServletContext context;

    private Facade services;
    
    public DrinkManagement(Facade services, ServletContext context) {
	this.context = context;
	this.services = services;
	loadDrinks();
    }
    
    private void loadDrinks() {
	DrinkListResponse drinkListResponse = null;
	try {
	    drinkListResponse = services.findAllDrinks();
	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while calling findAllDrinks()", e);
	}
	if (drinkListResponse != null && drinkListResponse.getDrinks() != null) {
	    drinks = new ArrayList<Drink>(drinkListResponse.getDrinks().size());
	    for (DrinkDTO dto : drinkListResponse.getDrinks()) {
		Drink d = Utils.DrinkDtoToDrink(dto);
		addNewDrink(d);
	    }	
	    System.out.println("drinks size: " + drinks.size());
	}
    }

    private void addNewDrink(Drink d) {
	drinks.add(d);
    }
    
////////////////////    Test methods
    
    public void testPersistingDrink() {
	System.out.println("Test of persisting a drink...");
	
	// persist a drink
	PersistDrinkRequest req = new PersistDrinkRequest(new DrinkDTO(null, "vodka", "beluga,50", new BigDecimal("5.50")));

	try {
	    DrinkResponseEntity de = services.persistDrink(req);
	    addNewDrink(Utils.DrinkDtoToDrink(de.getDrink()));
	    System.out.println("New drink ID: " + de.getDrink().getId());

	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while persists a new drink: ", e);
	}

	System.out.println("Test of persisting a drink finished well!");
    }
    
    public void testPersistingConsumer(String loggedUserName) {
	System.out.println("Test of persisting a consumer...");
	
	// persist a consumer - get the logged user! 
	UserManagement userM = (UserManagement) context.getAttribute("usersM");
	User loggedUser = userM.getUserByName(loggedUserName);
	
	System.out.println(loggedUser);
	UserDTO ud = new UserDTO(loggedUser.getId(), loggedUser.getName(), loggedUser.getPassword(), new RoleDTO(loggedUser.getRole().getId(), loggedUser.getRole().getRole()));
	PersistConsumerRequest req = new PersistConsumerRequest(new ConsumerDTO(null, new Date(), "masa_1", ud));
	try {
	    ConsumerResponseEntity ce = services.persistConsumer(req);
	    System.out.println(ce.getConsumer().getConsumerId());
	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while persists a new consumer: ", e);
	}

	System.out.println("Test of persisting a consumer finished well!");
    }
    
    public void testPersistingOrder() { // need consumer_id
	System.out.println("Test of persisting an order...");
	
	// persist a consumer - get the logged user! 
	ConsumerResponseEntity cre = services.findConsumerById(new FindByConsumerIdRequest(new Integer(1)));
	
	Map<DrinkDTO, Integer> drinks = new HashMap<DrinkDTO, Integer>();
	DrinkResponseEntity dre = services.findDrinkById(new FindByDrinkIdRequest(new Integer(1)));
	drinks.put(dre.getDrink(), 3);
	
	Order newOrder = new Order();
	newOrder.setConsumerId(Utils.ConsumerDtoToConsumer(cre.getConsumer()));
	newOrder.setDrinks(drinks);
	newOrder.setStatus(OrderStatus.PENDING);
	newOrder.setCalculatedBill();
	
	PersistOrderRequest req = new PersistOrderRequest(Utils.OrderToOrderDto(newOrder));
	try {
	    OrderResponseEntity ce = services.persistOrder(req);
	    System.out.println(ce.getOrder().getId());
	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while persists a new order: ", e);
	}

	System.out.println("Test of persisting a order finished well!");
    }
//////////////////////////////
    
}
