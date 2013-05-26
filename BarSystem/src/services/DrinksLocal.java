package services;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import web.pojos.Drink;

@Local
public interface DrinksLocal {

	public Drink persistDrink(Drink persistDrinkRequest);

	public List<Drink> findAllDrinks();

	public Drink findDrinkById(int findByDrinkIdRequest);

	public BigDecimal getPriceOfDrink(int findByDrinkIdRequest);

	public String getIngredientsOfDrink(int findByDrinkIdRequest);
}