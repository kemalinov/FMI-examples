package eao;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	OrderEntity oe = new OrderEntity();

	Query q = em.createNamedQuery("ConsumerEntity.findConsumerById");
	q.setParameter(1, persistOrderRequest.getConsumerId().getId());
	ConsumerEntity c = (ConsumerEntity) q.getSingleResult();
	oe.setConsumerId(c);

	oe.setBill(persistOrderRequest.getBill());
	oe.setStatus(persistOrderRequest.getStatus());
	for (Entry<Drink, Integer> e : persistOrderRequest.getDrinks()
		.entrySet()) {
	    oe.addDrink(DBUtils.DrinkToDrinkEntity(e.getKey()), e.getValue());
	}

	try {
	    if (oe.getId() == null) {
		em.persist(oe);
		em.flush();
	    } else {
		oe = em.merge(oe);
	    }
	} catch (Exception e) {
	    // TODO: handle exception
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

}
