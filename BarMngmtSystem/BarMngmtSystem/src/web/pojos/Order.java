package web.pojos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import commons.constants.OrderStatus;
import commons.dtos.DrinkDTO;

public class Order {

    private Integer order_id;

    private Consumer consumer_id;

    private Map<DrinkDTO, Integer> drinks; // "drinks" is the count - "Integer"

    private OrderStatus status;

    private BigDecimal bill;

    // constructor
    public Order() {
	drinks = new HashMap<DrinkDTO, Integer>();
    }

    public Integer getId() {
	return order_id;
    }

    public void setId(Integer order_id) {
	this.order_id = order_id;
    }

    public Consumer getConsumerId() {
	return consumer_id;
    }

    public void setConsumerId(Consumer consumer_id) {
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

    public Map<DrinkDTO, Integer> getDrinks() {
	return drinks;
    }

    public void setDrinks(Map<DrinkDTO, Integer> drinks) {
	this.drinks = drinks;
    }

    public void addDrink(DrinkDTO d, Integer count) {
	if (!drinks.containsKey(d)) {
	    drinks.put(d, count);
	} else {
	    Integer orderedCount = drinks.get(d);
	    drinks.put(d, orderedCount + count);
	}
    }

}
