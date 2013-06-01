package web.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import services.DrinksLocal;
import web.pojos.Drink;

public class DrinksManagement {

	private static Logger log = Logger.getLogger(DrinksManagement.class.getName());

	private Map<String, Drink> nameDrinkMap;
	// private ServletContext context;

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

	public Drink persistADrink(Drink drink) {
		System.out.println("Test of persisting a drink...");
		Drink dr = null;
		try {
			dr = services.persistDrink(drink);
			addNewDrink(dr);
			System.out.println("New drink ID: " + dr.getId());

		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception while persists a new drink: ", e);
		}

		System.out.println("Test of persisting a drink finished well!");

		return dr;
	}

}
