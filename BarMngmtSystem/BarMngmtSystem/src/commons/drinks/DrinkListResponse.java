package commons.drinks;

import java.util.List;

import commons.dtos.DrinkDTO;


public class DrinkListResponse {
    
    private List<DrinkDTO> drinks;
    
    public DrinkListResponse(List<DrinkDTO> drinks) {
	this.drinks = drinks;
    }

    public List<DrinkDTO> getDrinks() {
	return drinks;
    }

    public void setDrinks(List<DrinkDTO> drinks) {
	this.drinks = drinks;
    }

}
