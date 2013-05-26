package web.pojos;

import java.util.Timer;
import java.util.TimerTask;

import web.users.OrdersManagement;

import constants.OrderStatus;

public class OrderTimer {
    
    private static final long TIMEOUT = 30 * 1000; // milliseconds
    
    private Timer sendNotificationTimer;
    private Timer changeStatusTimer;
    
    private Order order;
    private OrdersManagement ordersM;

    
    public OrderTimer(OrdersManagement ordersM, Order order) {
	this.ordersM = ordersM;
	this.order = order;
	
	sendNotificationTimer = new Timer();
	sendNotificationTimer.schedule(new RemindTask(), TIMEOUT);
    }
    
    class RemindTask extends TimerTask {
	
	public RemindTask() {
	}
	
	public void run() {
	    System.out.println("RemindTask's time is up!");
	    
	    System.out.println("Check the order status of " + order.getId());
	    System.out.println(ordersM);
	    OrderStatus status = ordersM.getOrderStatus(order); 

	    if (status.equals(OrderStatus.PENDING)) {
		ordersM.notifyObservers();
		System.out.println("Sent notification to the barmans!");
		System.out.println("Started a new timer...");
		changeStatusTimer = new Timer();
		changeStatusTimer.schedule(new ChangeStatusTask(), TIMEOUT);
	    }
	    
	    sendNotificationTimer.cancel(); //Not necessary because we call System.exit
	}
    }
    
    class ChangeStatusTask extends TimerTask {
	
	public ChangeStatusTask() {
	}
	
	public void run() {
	    System.out.println("ChangeStatusTask's time is up!");
	    Order o = ordersM.findOrderById(order.getId());
	    if (! o.getStatus().equals(OrderStatus.DONE)) {
		order.setStatus(OrderStatus.OVERDUE);
		ordersM.updateAnOrder(order);
		
		System.out.println("Changed the order status");		
	    }

	    changeStatusTimer.cancel(); //Not necessary because we call System.exit
	}
    }

}
