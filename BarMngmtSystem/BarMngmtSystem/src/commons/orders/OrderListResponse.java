package commons.orders;

import java.util.List;

import commons.dtos.OrderDTO;


public class OrderListResponse {
    
    private List<OrderDTO> orders;
    
    public OrderListResponse(List<OrderDTO> orders) {
	this.orders = orders;
    }

    public List<OrderDTO> getOrders() {
	return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
	this.orders = orders;
    }
}
