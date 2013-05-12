package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import commons.constants.OrderStatus;
import commons.consumers.FindByConsumerIdRequest;
import commons.dtos.DrinkDTO;
import commons.dtos.OrderDTO;
import commons.orders.FindByOrderIdRequest;
import commons.orders.OrderBillResponse;
import commons.orders.OrderListResponse;
import commons.orders.OrderResponseEntity;
import commons.orders.OrderStatusResponse;
import commons.orders.PersistOrderRequest;

import db.entities.ConsumerEntity;
import db.entities.OrderEntity;
import db.utils.DBUtils;

@Stateless
public class OrdersBean {

    @PersistenceContext(unitName = "BarSysPersistenceUnit")
    private EntityManager em;

    public OrdersBean() {
    }
    
    public OrderResponseEntity persistOrder(PersistOrderRequest persistOrderRequest) {
	OrderEntity oe = new OrderEntity();
	
	Query q = em.createNamedQuery("ConsumerEntity.findConsumerById");
	q.setParameter(1, persistOrderRequest.getOrder().getConsumerId().getConsumerId());
	ConsumerEntity c = (ConsumerEntity) q.getSingleResult();
	oe.setConsumerId(c);

	oe.setBill(persistOrderRequest.getOrder().getBill());
	oe.setStatus(persistOrderRequest.getOrder().getStatus());
	for(Entry<DrinkDTO, Integer> e : persistOrderRequest.getOrder().getDrinks().entrySet()) {
	    oe.addDrink(DBUtils.DrinkDtoToDrinkEntity(e.getKey()), e.getValue());    
	}
	
	try {
	    if(oe.getId() == null) {
		em.persist(oe);
		em.flush();
	    } else {
		oe = em.merge(oe);
	    }    
	} catch (Exception e) {
	    // TODO: handle exception
	} 
	return new OrderResponseEntity(DBUtils.OrderEntityToOrderDto(oe));
    }

    public OrderListResponse findAll() {
	Query q = em.createNamedQuery("OrderEntity.findAll");
	List<OrderEntity> users = q.getResultList();

	List<OrderDTO> ordersDtoList = new ArrayList<OrderDTO>(users.size());
	for (OrderEntity ue : users) {
	    ordersDtoList.add(DBUtils.OrderEntityToOrderDto(ue));
	}

	System.out.println("size: " + ordersDtoList.size());
	return new OrderListResponse(ordersDtoList);
    }

    public OrderResponseEntity findOrderById(
	    FindByOrderIdRequest findByOrderIdRequest) {
	Query q = em.createNamedQuery("OrderEntity.findOrderById");
	q.setParameter(1, findByOrderIdRequest.getId());

	OrderEntity entity = (OrderEntity) q.getSingleResult();
	return new OrderResponseEntity(DBUtils.OrderEntityToOrderDto(entity));
    }

    public OrderListResponse findOrdersByConsumer(
	    FindByConsumerIdRequest findByConsumerIdRequest) {
	Query q = em.createNamedQuery("OrderEntity.findOrdersByConsumerId");
	q.setParameter(1, findByConsumerIdRequest.getId());
	List<OrderEntity> users = q.getResultList();

	List<OrderDTO> ordersDtoList = new ArrayList<OrderDTO>(users.size());
	for (OrderEntity ue : users) {
	    ordersDtoList.add(DBUtils.OrderEntityToOrderDto(ue));
	}

	System.out.println("size: " + ordersDtoList.size());
	return new OrderListResponse(ordersDtoList);
    }

    public OrderBillResponse getBillForConsumer(
	    FindByConsumerIdRequest findByConsumerIdRequest) {
	Query q = em.createNamedQuery("OrderEntity.getBillForConsumerId");
	q.setParameter(1, findByConsumerIdRequest.getId());

	BigDecimal bill = (BigDecimal) q.getSingleResult();
	return new OrderBillResponse(bill);
    }

    public OrderStatusResponse getStatusForOrder(
	    FindByOrderIdRequest findByOrderIdRequest) {
	Query q = em.createNamedQuery("OrderEntity.getStatusForOrderId");
	q.setParameter(1, findByOrderIdRequest.getId());

	OrderStatus status = (OrderStatus) q.getSingleResult();
	return new OrderStatusResponse(status);
    }

}
