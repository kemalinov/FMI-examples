package commons.dtos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import commons.constants.OrderStatus;
import db.entities.DrinkEntity;

public class OrderDTO {

    private Integer order_id;
    private ConsumerDTO consumer_id;
//    private Integer consumer_id;	// to entity
    private Map<DrinkDTO, Integer> drinks; // "drinks" is the count - "Integer"
    private OrderStatus status;
    private BigDecimal bill;

    // constructor
    public OrderDTO(Integer id, ConsumerDTO consumerId, Map<DrinkEntity, Integer> drinks, OrderStatus orderStatus, BigDecimal bill) {
	this.order_id = id;
	this.consumer_id = consumerId;
	this.status = orderStatus;
	this.bill = bill;	
	
	this.drinks = new HashMap<DrinkDTO, Integer>(drinks.size());
	copyMapItems(drinks);
    }
    
    private void copyMapItems(Map<DrinkEntity, Integer> drinks) {
	for(Entry<DrinkEntity, Integer> e : drinks.entrySet()) {
	    DrinkEntity de = e.getKey();
	    DrinkDTO dto = new DrinkDTO(de.getId(), de.getName(), de.getIngredients(), de.getPrice());
	    this.drinks.put(dto, new Integer(e.getValue()));
	}
    }

    public Integer getId() {
	return order_id;
    }

    public void setId(Integer order_id) {
	this.order_id = order_id;
    }

    public ConsumerDTO getConsumerId() {
	return consumer_id;
    }

    public void setConsumerId(ConsumerDTO consumer_id) {
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

    // public void addDrink(DrinkDTO d, Integer count) {
    // if (!drinks.containsKey(d)) {
    // drinks.put(d, count);
    // } else {
    // Integer orderedCount = drinks.get(d);
    // drinks.put(d, orderedCount + count);
    // }
    // }
}
