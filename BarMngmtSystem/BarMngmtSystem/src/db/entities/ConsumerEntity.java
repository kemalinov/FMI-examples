package db.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "app.consumers")
@NamedNativeQueries({
	@NamedNativeQuery(name = "ConsumerEntity.findConsumerById", query = "SELECT * FROM app.consumers WHERE consumer_id = ?", resultClass = ConsumerEntity.class)
})
public class ConsumerEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consumer_id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_;

    @ElementCollection
    @CollectionTable(name="app.orders")	// maps drinks with the consumer_id
    @MapKeyColumn(name="drinks_key")
    private Map<DrinkEntity, Integer> drinks;	// "drinks" is the count - "Integer"

    private BigDecimal bill;
    private Integer user_id; // FK
    private String place;

    // DEPRECATED!!!
//    @JoinTable(name="app.orders")
//    private OrderEntity order;
    public ConsumerEntity() {
	drinks = new HashMap<DrinkEntity, Integer>();
    }
    
    public Integer getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(Integer consumer_id) {
        this.consumer_id = consumer_id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getTime_() {
        return time_;
    }

    public void setTime_(Date time_) {
        this.time_ = time_;
    }

    public BigDecimal getBill() {
        return bill;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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

//    public OrderEntity getOrder() {
//	return order;
//    }
//
//    public void setOrder(OrderEntity order) {
//	this.order = order;
//    }
    
}
