package web.pojos;

import java.math.BigDecimal;

public class Drink {

	private Integer id;

	private String name;
	private String ingredients; // [<sustavka,gr>;<...>]
	private BigDecimal price;

	public Drink(Integer id, String name, String ingredients, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer drink_id) {
		this.id = drink_id;
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
