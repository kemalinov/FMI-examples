package ejb;

import javax.ejb.Stateful;

@Stateful
public class Waiter extends User {

//    @EJB
//    private OrdersLocal orderServices;
    
    public Waiter(User u) {
	super(u.getId(), u.getName(), u.getPassword(), u.getRole());
    }
    
    
    
}
