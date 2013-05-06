package commons.orders;

import commons.constants.OrderStatus;

public class OrderStatusResponse {

    private OrderStatus status;
    
    public OrderStatusResponse(OrderStatus status) {
	this.setStatus(status);
    }

    public OrderStatus getStatus() {
	return status;
    }

    public void setStatus(OrderStatus status) {
	this.status = status;
    }
}
