package services;


import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import web.pojos.Order;
import constants.OrderStatus;

@Local
public interface OrdersLocal extends ConsumersLocal {

    // Orders services
    public Order persistOrder(Order persistOrderRequest);

    public List<Order> findAll();

    public Order findOrderById(int findByOrderIdRequest);

    public List<Order> findOrdersByConsumer(int findByConsumerIdRequest);

    public BigDecimal getBillForConsumer(int findByConsumerIdRequest);

    public OrderStatus getStatusForOrder(int findByOrderIdRequest);

}