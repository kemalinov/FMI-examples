package commons.drinks;

import commons.dtos.DrinkDTO;

public class DrinkResponseEntity {

    private DrinkDTO drink;
    
    public DrinkResponseEntity(DrinkDTO drink) {
	this.drink = drink;
    }

    public DrinkDTO getDrink() {
	return drink;
    }

    public void setDrink(DrinkDTO drink) {
	this.drink = drink;
    }
}
