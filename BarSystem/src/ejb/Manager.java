package ejb;

import javax.ejb.Stateful;

@Stateful
public class Manager extends User {

	public Manager(User u) {
		super(u.getId(), u.getName(), u.getPassword(), u.getRole());
	}

}
