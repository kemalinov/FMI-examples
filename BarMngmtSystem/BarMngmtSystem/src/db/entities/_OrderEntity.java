package db.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "app.Orders")
@NamedNativeQueries({
	@NamedNativeQuery(name = "OrderEntity.findOrderById", query = "SELECT * FROM app.orders WHERE order_id = ?", resultClass = _OrderEntity.class)
})
@Deprecated
// TODO: how to wrap the drinks-map in a object/entity!
public class _OrderEntity implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public _OrderEntity() {
	drinks = new HashMap<DrinkEntity, Integer>();
    }

    @EmbeddedId
    @ElementCollection
    @CollectionTable(name="app.Orders")
    @MapKeyColumn(name="drinks_key")
    private Map<DrinkEntity, Integer> drinks;	// "drinks" is the count - "Integer"

    public Map<DrinkEntity, Integer> getDrinks() {
	return drinks;
    }

    public void setDrinks(Map<DrinkEntity, Integer> drinks) {
	this.drinks = drinks;
    }

    public void addDrink(DrinkEntity de, Integer count){
	if (!drinks.containsKey(de)) {
	    drinks.put(de, count);
	} else {
	    Integer orderedCount = drinks.get(de);
	    drinks.put(de, orderedCount + count);
	}	
    }

}
