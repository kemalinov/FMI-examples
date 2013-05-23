package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import web.pojos.Order;

@Stateful
public class Barman extends User implements Observer {

//    @EJB
//    private OrdersLocal orderServices;
    
    private List<Order> urgentOrders = new ArrayList<Order>();
    
    public Barman(User u) {
	super(u.getId(), u.getName(), u.getPassword(), u.getRole());
    }
    
    public void acceptAnOrder(Order order) {
	// TODO:
//	orderServices.persistOrder(null);
    }

    @Override
    public void update(Order o) {
	urgentOrders.add(o);
    }
    
    
    
}
