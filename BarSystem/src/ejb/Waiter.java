package ejb;

import javax.ejb.Stateful;

import web.pojos.Order;

@Stateful
public class Waiter extends User implements Observer {

//    @EJB
//    private OrdersLocal orderServices;
    
    public Waiter(User u) {
	super(u.getId(), u.getName(), u.getPassword(), u.getRole());
    }
    
    @Override
    public void update(Order o) {
	System.out.println("waiter id: " + super.getId() + " has been observerd for change in order: " + o.getId());
    }
    
}
