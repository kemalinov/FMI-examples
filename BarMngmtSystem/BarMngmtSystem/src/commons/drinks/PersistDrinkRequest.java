package commons.drinks;

import commons.dtos.DrinkDTO;

public class PersistDrinkRequest {

    private DrinkDTO drink;
    
    public PersistDrinkRequest(DrinkDTO drink) {
	this.setDrink(drink);
    }

    public DrinkDTO getDrink() {
	return drink;
    }

    public void setDrink(DrinkDTO drink) {
	this.drink = drink;
    }
}
