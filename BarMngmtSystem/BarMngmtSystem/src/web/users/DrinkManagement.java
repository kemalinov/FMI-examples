package web.users;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import web.pojos.Drink;
import web.utils.Utils;

import commons.drinks.DrinkListResponse;
import commons.drinks.DrinkResponseEntity;
import commons.drinks.PersistDrinkRequest;
import commons.dtos.DrinkDTO;

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
    
    public void testPersistingDrink() {
	System.out.println("Test of persisting a drink...");
	
	PersistDrinkRequest req = new PersistDrinkRequest(new DrinkDTO(null, "vodka", "beluga,50", new BigDecimal("5.50")));
	try {
	    DrinkResponseEntity de = services.persistDrink(req);
	    System.out.println("New drink ID: " + de.getDrink().getId());
	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while persists a new drink: ", e);
	}

	System.out.println("Test of persisting a drink finished well!");
	
    }
}
