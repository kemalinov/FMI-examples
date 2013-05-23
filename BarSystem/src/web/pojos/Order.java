package web.pojos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import constants.OrderStatus;

public class Order implements Comparable<Order>{

    private Integer order_id;
    private Consumer consumer_id;
    private Map<Drink, Integer> drinks; // "drinks" is the count - "Integer"
    private OrderStatus status;
    private BigDecimal bill;

    public Order(Integer id, Consumer consumer, Map<Drink, Integer> drinks, OrderStatus status, BigDecimal bill) {
	this.order_id = id;
	this.consumer_id = consumer;
	this.status = status;
	this.bill = bill;
	
	this.drinks = new HashMap<Drink, Integer>(drinks.size());
	this.drinks.putAll(drinks);
    }
    
    // constructor
    public Order() {
	drinks = new HashMap<Drink, Integer>();
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

    public Map<Drink, Integer> getDrinks() {
	return drinks;
    }

    public void setDrinks(Map<Drink, Integer> drinks) {
	this.drinks = drinks;
    }

    public void setCalculatedBill() {
	setBill(calculateBill());
    }

    private BigDecimal calculateBill(){
	BigDecimal b = new BigDecimal(0);
	System.out.println("Order's drnks size " + this.drinks.size());
	for(Entry<Drink, Integer> drinks : this.drinks.entrySet()) {
	    // add the bill of every ordered drink (drink*count) to the whole bill
	    System.out.println(drinks.getKey().getName());
	    System.out.println(drinks.getKey().getPrice());
	    System.out.println(drinks.getValue());
	    b = b.add(drinks.getKey().getPrice().multiply(new BigDecimal(drinks.getValue())));  
	}
	System.out.println("calculated bill for the order " + order_id + " is " + b);
	return b;
    }

    @Override
    public int compareTo(Order o) {
	return status.compareTo(o.getStatus());
    }
    
//    public void addDrink(DrinkDTO d, Integer count) {
//	if (!drinks.containsKey(d)) {
//	    drinks.put(d, count);
//	} else {
//	    Integer orderedCount = drinks.get(d);
//	    drinks.put(d, orderedCount + count);
//	}
//    }

}
