package commons.orders;

import commons.dtos.OrderDTO;

public class OrderResponseEntity {

    private OrderDTO order;
    
    public OrderResponseEntity(OrderDTO order) {
	this.order = order;
    }

    public OrderDTO getOrder() {
	return order;
    }

    public void setOrder(OrderDTO order) {
	this.order = order;
    }
}
