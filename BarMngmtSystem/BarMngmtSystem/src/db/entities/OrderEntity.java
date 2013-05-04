package db.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import commons.constants.OrderStatus;

@Entity
@Table(name = "app.Orders")
@NamedNativeQueries({
	@NamedNativeQuery(name = "OrderEntity.findOrderById", query = "SELECT * FROM app.orders WHERE order_id = ?", resultClass = OrderEntity.class)
})
public class OrderEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;

    private Integer consumer_id;
    
    @ElementCollection
    @CollectionTable(name="app.ordered_drinks")
    @MapKeyColumn(name="drinks_key")
    private Map<DrinkEntity, Integer> drinks;	// "drinks" is the count - "Integer"

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    @JoinTable(name="app.ordered_drinks")
    private BigDecimal bill;
    
    
    public Integer getId() {
        return order_id;
    }

    public void setId(Integer order_id) {
        this.order_id = order_id;
    }


    public Integer getConsumerId() {
        return consumer_id;
    }


    public void setConsumerId(Integer consumer_id) {
        this.consumer_id = consumer_id;
    }


    public OrderStatus getStatus() {
        return status;
    }


    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    public BigDecimal getBill() {
        return bill;
    }


    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    public OrderEntity() {
	drinks = new HashMap<DrinkEntity, Integer>();
    }

    
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
