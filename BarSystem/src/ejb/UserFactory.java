package ejb;

import web.management.DrinksManagement;
import web.management.OrdersManagement;
import constants.RolesType;

public class UserFactory {

	private UserFactory() {
	}
	
	public static User createUserMethod(User u, OrdersManagement ordersM, DrinksManagement drinksM) {
		if (u.getRole().getRole().equals(RolesType.WAITER)) {
			u = new Waiter(u, ordersM);
		} else if (u.getRole().getRole().equals(RolesType.BARMAN)) {
			u = new Barman(u, ordersM, drinksM);
		} else if (u.getRole().getRole().equals(RolesType.MANAGER)) {
			u = new Manager(u);
		}
		return u;
	}

}
