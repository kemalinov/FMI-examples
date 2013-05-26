package eao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import web.pojos.Drink;
import web.pojos.Order;
import constants.OrderStatus;
import db.utils.DBUtils;
import entities.ConsumerEntity;
import entities.OrderEntity;

@Stateless
public class OrdersBean {

    @PersistenceContext(unitName = "BarSysPersistenceUnit")
    private EntityManager em;

    public OrdersBean() {
    }

    public Order persistOrder(Order persistOrderRequest) {
	OrderEntity oe = null;
	if(persistOrderRequest.getId() == null) { // persist a new order
	    oe = new OrderEntity();
	    
	    Query q = em.createNamedQuery("ConsumerEntity.findConsumerById");
	    q.setParameter(1, persistOrderRequest.getConsumerId().getId());
	    ConsumerEntity ce = (ConsumerEntity) q.getSingleResult();
	    
	    oe.setId(persistOrderRequest.getId());
	    oe.setConsumerId(ce/*DBUtils.ConsumerToConsumerEntity(persistOrderRequest.getConsumerId())*/);
	    oe.setStatus(persistOrderRequest.getStatus());
	    oe.setBill(persistOrderRequest.getBill());
	    for (Entry<Drink, Integer> e : persistOrderRequest.getDrinks().entrySet()) {
    	    	oe.addDrink(DBUtils.DrinkToDrinkEntity(e.getKey()), e.getValue());
	    }
	}
	try {
	    if (persistOrderRequest.getId() == null) {
		em.persist(oe);
		em.flush();
	    } else {
		oe = em.find(OrderEntity.class, persistOrderRequest.getId());
		oe.setStatus(persistOrderRequest.getStatus());
		oe = em.merge(oe);		
		System.out.println("after EM update " + oe.getId());
	    }
	} catch (Exception e) {
	    System.err.println("Exception in persisting of an order method: " + e.getMessage());
	}
	return DBUtils.OrderEntityToOrder(oe);
    }

    public List<Order> findAll() {
	Query q = em.createNamedQuery("OrderEntity.findAll");
	List<OrderEntity> users = q.getResultList();

	List<Order> ordersDtoList = new ArrayList<Order>(users.size());
	for (OrderEntity ue : users) {
	    ordersDtoList.add(DBUtils.OrderEntityToOrder(ue));
	}

	System.out.println("size: " + ordersDtoList.size());
	return new ArrayList<Order>(ordersDtoList);
    }

    public Order findOrderById(int findByOrderIdRequest) {
	Query q = em.createNamedQuery("OrderEntity.findOrderById");
	q.setParameter(1, findByOrderIdRequest);

	OrderEntity entity = (OrderEntity) q.getSingleResult();
	return DBUtils.OrderEntityToOrder(entity);
    }

    public List<Order> findOrdersByConsumer(int findByConsumerIdRequest) {
	Query q = em.createNamedQuery("OrderEntity.findOrdersByConsumerId");
	q.setParameter(1, findByConsumerIdRequest);
	List<OrderEntity> users = q.getResultList();

	List<Order> ordersDtoList = new ArrayList<Order>(users.size());
	for (OrderEntity ue : users) {
	    ordersDtoList.add(DBUtils.OrderEntityToOrder(ue));
	}

	System.out.println("size: " + ordersDtoList.size());
	return new ArrayList<Order>(ordersDtoList);
    }

    public BigDecimal getBillForConsumer(int findByConsumerIdRequest) {
	Query q = em.createNamedQuery("OrderEntity.getBillForConsumerId");
	q.setParameter(1, findByConsumerIdRequest);

	BigDecimal bill = (BigDecimal) q.getSingleResult();
	return bill;
    }

    public OrderStatus getStatusForOrder(int findByOrderIdRequest) {
	Query q = em.createNamedQuery("OrderEntity.getStatusForOrderId");
	q.setParameter(1, findByOrderIdRequest);

	List<Object> status = null;
	try {
	    status = q.getResultList();
	} catch (Exception e) {
	    System.err.println("Exception in getStatusForOrder() method: " + e);
	}
	return OrderStatus.valueOf((String) status.get(0));
    }

    public List<Order> findAllActiveOrdersByUserId(int findByUserIdRequest) {
//	Query q = em.createNamedQuery("OrderEntity.getOrdersForActiveConsumersByUserId");
//	q.setParameter(1, findByUserIdRequest);
	StringBuilder query = new StringBuilder();
	query.append("SELECT od.order_id, od.consumer_consumer_id, c.place, d.name, odd.drinks, od.status, od.bill ");
	query.append("FROM app.orders od ");
	query.append("JOIN app.consumers c ");
	query.append("ON c.closed = false AND od.consumer_consumer_id = c.consumer_id ");
	if (findByUserIdRequest > 0) { // a valid one
	    query.append("AND c.user_user_id = ? "); 
	}
	query.append("JOIN app.ordered_drinks odd ");
	query.append("ON odd.orderentity_order_id = od.order_id ");
	query.append("JOIN app.drinks d ");
	query.append("ON d.drink_id = odd.drinks_key");
	
	Query q = em.createNativeQuery(query.toString(), OrderEntity.class);
	if (findByUserIdRequest > 0) { // a valid one
	    q.setParameter(1, findByUserIdRequest);
	}
	
	List<OrderEntity> l = q.getResultList();
	List<Order> res = new ArrayList<Order>(l.size());
	for(OrderEntity oe : l) {
	    Order o = DBUtils.OrderEntityToOrder(oe);
	    res.add(o);
	}
	Collections.sort(res, new Comparator<Order>() {
	    public int compare(Order o1, Order o2) {
		if (o1.getStatus().compareTo(OrderStatus.OVERDUE) == 0) {
		    return -1;
		} 
		return 1;		
	    }
	});
	return res;	
    }
    
    
}
