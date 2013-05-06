package commons.orders;

import commons.dtos.OrderDTO;

public class PersistOrderRequest {

    private OrderDTO order;
    
    public PersistOrderRequest(OrderDTO order) {
	this.order = order;
    }

    public OrderDTO getOrder() {
	return order;
    }

    public void setOrder(OrderDTO order) {
	this.order = order;
    }
}
