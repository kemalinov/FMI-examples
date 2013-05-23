package services;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import web.pojos.Consumer;
import web.pojos.Order;
import constants.OrderStatus;
import eao.ConsumersBean;
import eao.OrdersBean;
import ejb.User;

@Stateless(name="OrderServices")
public class OrderServices implements OrdersLocal, ConsumersLocal {

    @EJB
    private ConsumersBean consumers;
    
    @EJB
    private OrdersBean orders;
    
    public OrderServices() {
    }

 // Consumers services
    @Override
    public Consumer persistConsumer(Consumer persistConsumerRequest) {
	return consumers.persistConsumer(persistConsumerRequest);
    }

    @Override
    public List<Consumer> findAllConsumers() {
	return consumers.findAll();
    }
    
    @Override
    public Consumer findConsumerById(int findByConsumerIdRequest) {
	return consumers.findConsumerById(findByConsumerIdRequest);
    }
    
    @Override
    public User findUserByConsumerId(int findByConsumerIdRequest) {
	return consumers.findUserByConsumerId(findByConsumerIdRequest);
    }

    @Override
    public List<Consumer> findActiveConsumersByUserId(int findByUserIdRequest) {
	return consumers.findActiveConsumersByUserId(findByUserIdRequest);
    }
    
    
    // Orders services
    @Override
    public Order persistOrder(Order persistOrderRequest) {
	return orders.persistOrder(persistOrderRequest);
    }
    
    @Override
    public List<Order> findAll() {
	return orders.findAll();
    }

    @Override
    public Order findOrderById(int findByOrderIdRequest) {
	    return orders.findOrderById(findByOrderIdRequest);
    }

    @Override
    public List<Order> findOrdersByConsumer(
	    int findByConsumerIdRequest) {
	return orders.findOrdersByConsumer(findByConsumerIdRequest);
    }

    @Override
    public BigDecimal getBillForConsumer(
	    int findByConsumerIdRequest) {
	return orders.getBillForConsumer(findByConsumerIdRequest);
    }

    @Override
    public OrderStatus getStatusForOrder(
	    int findByOrderIdRequest) {
	return orders.getStatusForOrder(findByOrderIdRequest);
    }
    
}
