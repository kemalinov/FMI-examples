package ejb;

import web.pojos.Order;

public interface Observer {
    
    public void update(Order o);
    
}
