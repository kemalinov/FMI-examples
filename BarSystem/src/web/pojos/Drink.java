package web.pojos;

import java.math.BigDecimal;

public class Drink {

<<<<<<< HEAD
	private Integer id;
=======
    private Integer id;
>>>>>>> branch 'master' of https://github.com/kemalinov/FMI-examples.git

	private String name;
	private String ingredients; // [<sustavka,gr>;<...>]
	private BigDecimal price;

<<<<<<< HEAD
	public Drink(Integer id, String name, String ingredients, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.price = price;
	}
=======
    
    public Drink(Integer id, String name, String ingredients,
	    BigDecimal price) {
	this.id = id;
	this.name = name;
	this.ingredients = ingredients;
	this.price = price;
    }
>>>>>>> branch 'master' of https://github.com/kemalinov/FMI-examples.git

<<<<<<< HEAD
	public Integer getId() {
		return id;
	}
=======
    public Integer getId() {
	return id;
    }
>>>>>>> branch 'master' of https://github.com/kemalinov/FMI-examples.git

<<<<<<< HEAD
	public void setId(Integer drink_id) {
		this.id = drink_id;
	}
=======
    public void setId(Integer drink_id) {
	this.id = drink_id;
    }
>>>>>>> branch 'master' of https://github.com/kemalinov/FMI-examples.git

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
