package web.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import services.UsersLocal;
import web.pojos.Role;
import web.utils.PasswordEncryption;
import constants.RolesType;
import ejb.Observer;
import ejb.OrdersNotification;
import ejb.User;
import ejb.UserFactory;

public class UsersManagement {

	private static Logger log = Logger.getLogger(UsersManagement.class.getName());

	private List<User> allRegisteredUsersList;

	private Map<String, String> userRoleMap = new HashMap<String, String>();
	private Map<String, User> loggedUsersMap = new HashMap<String, User>();

	private Map<String, Role> nameRoleMap;

	private ServletContext context;

	private UsersLocal services;

	// constructor
	public UsersManagement(UsersLocal services, ServletContext context) {
		this.context = context;
		this.services = services;
		System.out.println("userServices: " + this.services);
		initUsers();
		initRoles();
	}

	private void initUsers() {
		List<User> userList = null;
		try {
			userList = services.findAllUsers();
			System.out.println("userList: " + userList.size());
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception while calling findAllUsers()", e);
		}
		if (userList != null) {
			allRegisteredUsersList = new ArrayList<User>(userList.size());
			for (User u : userList) {
				userRoleMap.put(u.getName(), u.getRole().getRole());
				allRegisteredUsersList.add(u);
			}
		}
	}

	public List<Role> getAllRoles() {
		return new ArrayList<Role>(nameRoleMap.values());
	}

	private void initRoles() {
		List<Role> roleList = null;
		try {
			roleList = services.findAllRoles();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception while calling findAllRoles()", e);
		}
		if (roleList != null) {
			nameRoleMap = new HashMap<String, Role>(roleList.size());
			for (Role r : roleList) {
				nameRoleMap.put(r.getRole(), r);
			}
		}
	}

	public boolean checkUser(String username, String password) {
		if (allRegisteredUsersList != null) {
			for (User u : allRegisteredUsersList) {
				if (u.getName().equalsIgnoreCase(username) && PasswordEncryption.checkPassords(password, u.getPassword())) {
					System.out.println("User found: " + u.getName());
					System.out.println("Role: " + u.getRole().getRole());
					addNewLoggedUser(u);
					return true;
				}
			}
		}
		return false;
	}

	private void addNewLoggedUser(User u) {
		OrdersManagement ordersM = (OrdersManagement) context.getAttribute("ordersM");
		DrinksManagement drinksM = (DrinksManagement) context.getAttribute("drinksM");
		User user = UserFactory.createUserMethod(u, ordersM, drinksM);
		loggedUsersMap.put(user.getName(), user);
		if (user instanceof Observer) {
			OrdersNotification.getInstance().registerObserver((Observer) user);
		}
	}

//	private User createAConcreteUser(User u) {
//		if (u.getRole().getRole().equals(RolesType.WAITER)) {
//			u = new Waiter(u, (OrdersManagement) context.getAttribute("ordersM"));
//		} else if (u.getRole().getRole().equals(RolesType.BARMAN)) {
//			u = new Barman(u, (OrdersManagement) context.getAttribute("ordersM"), (DrinksManagement) context.getAttribute("drinksM"));
//		} else if (u.getRole().getRole().equals(RolesType.MANAGER)) {
//			u = new Manager(u);
//		}
//		return u;
//	}

	public synchronized User getLoggedUserByName(String username) {
		System.out.println("logged users " + loggedUsersMap.size());
		return loggedUsersMap.get(username);
	}

	public void logoutUser(User user) {
		System.out.println("logouting user " + user.getName());
		User u = loggedUsersMap.remove(user.getName());
		if (u instanceof Observer) {
			OrdersNotification.getInstance().registerObserver((Observer) u);
		}
		System.out.println("logged users size: " + loggedUsersMap.size());
	}
	
	public List<User> getAllLoggedUsers() {
		return new ArrayList<User>(loggedUsersMap.values());
	} 

	public boolean registerUser(String username, String password, String role) {
		if (!userRoleMap.containsKey(username)) {
			String encryptedPass = PasswordEncryption.encryptPassword(password);
			User newRegedUser = null;
			try {
				Role r = nameRoleMap.get(role);
				User u = new User(null, username.toLowerCase(), encryptedPass, r);
				newRegedUser = services.persistUser(u);
			} catch (Exception e) {
				log.log(Level.SEVERE, "Exception while persists a new user: ", e);
				return false;
			}
			System.out.println("persisted user: " + newRegedUser.getId());
			if (newRegedUser != null) {
				userRoleMap.put(newRegedUser.getName(), newRegedUser.getRole().getRole());
				allRegisteredUsersList.add(newRegedUser);
				return true;
			}
		} else {
			System.out.println("USER ALREADY EXISTS....");
		}
		return false;
	}

	public boolean isManagerUser(String username) {
		return RolesType.MANAGER.equals(userRoleMap.get(username));
	}

}
