package commons.dtos;

import java.math.BigDecimal;

public class DrinkDTO {
    
    private Integer drink_id;
    private String name;
    private String ingredients; // [<sustavka,gr>;<...>]
    private BigDecimal price;
    
    public DrinkDTO(Integer drinkId, String name, String ingredients, BigDecimal price) {
	this.drink_id = drinkId;
	this.name = name;
	this.ingredients = ingredients;
	this.price = price;
    }

    public Integer getId() {
	return drink_id;
    }

    public void setId(Integer drink_id) {
	this.drink_id = drink_id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getIngredients() {
	return ingredients;
    }

    public void setIngredients(String ingredients) {
	this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }
}
