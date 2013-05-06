package db.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "App.drinks")
@NamedNativeQueries({
	@NamedNativeQuery(name = "DrinkEntity.findAll", query = "SELECT * FROM app.drinks", resultClass = DrinkEntity.class),
	@NamedNativeQuery(name = "DrinkEntity.findDrinkById", query = "SELECT * FROM app.drinks WHERE drink_id = ?", resultClass = DrinkEntity.class),
	@NamedNativeQuery(name = "DrinkEntity.findPriceOfDrinkById", query = "SELECT price FROM app.drinks WHERE drink_id = ?", resultClass = BigDecimal.class),
	@NamedNativeQuery(name = "DrinkEntity.findIngredientsOfDrinkById", query = "SELECT ingredients FROM app.drinks WHERE drink_id = ?", resultClass = String.class) })
public class DrinkEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer drink_id;

    private String name;
    private String ingredients;	// [<sustavka,gr>;<...>]
    private BigDecimal price;

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
