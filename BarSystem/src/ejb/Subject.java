package ejb;

import web.pojos.Order;

public interface Subject {
//    public void registerObserver(Observer observer);
//
//    public void removeObserver(Observer observer);

    public void notifyObservers(Order o);
}

