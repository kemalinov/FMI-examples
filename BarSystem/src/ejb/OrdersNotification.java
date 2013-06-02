package ejb;

import java.util.ArrayList;
import java.util.List;

public class OrdersNotification implements Subject {

	private static final OrdersNotification ordersSubject = new OrdersNotification(); 
	
	private List<Observer> observers = null;
	
	private OrdersNotification() {
		observers = new ArrayList<Observer>();
	}
	
	public static OrdersNotification getInstance() {
		return ordersSubject;
	}
	
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}
	}
	
}
