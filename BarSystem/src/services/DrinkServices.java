package services;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import web.pojos.Drink;
import eao.DrinksBean;

@Stateless(name = "DrinkServices")
public class DrinkServices implements DrinksLocal {

	@EJB
	private DrinksBean drinks;

	public DrinkServices() {
	}

	// Drinks services
	public Drink persistDrink(Drink persistDrinkRequest) {
		return drinks.persistDrink(persistDrinkRequest);
	}

	public List<Drink> findAllDrinks() {
		return drinks.findAllDrinks();
	}

	public Drink findDrinkById(int findByDrinkIdRequest) {
		return drinks.findDrinkById(findByDrinkIdRequest);
	}

	public BigDecimal getPriceOfDrink(int findByDrinkIdRequest) {
		return drinks.getPriceOfDrink(findByDrinkIdRequest);
	}

	public String getIngredientsOfDrink(int findByDrinkIdRequest) {
		return drinks.getIngredientsOfDrink(findByDrinkIdRequest);
	}
}
