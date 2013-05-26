package web.users;

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
import ejb.Barman;
import ejb.Manager;
import ejb.Observer;
import ejb.User;
import ejb.Waiter;

public class UsersManagement {
    
    private static Logger log = Logger.getLogger(UsersManagement.class.getName());

    private List<User> allRegisteredUsersList;
    
    private Map<String, String> userRoleMap = new HashMap<String, String>();
    private Map<String, User> loggedUsersList = new HashMap<String, User>();
    
    private List<Observer> ordersObservers = new ArrayList<Observer>();
    
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
    
    public List<Observer> getObservers() {
	return ordersObservers;
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
	User user = createAConcreteUserByRole(u);
	loggedUsersList.put(user.getName(), user);
	if (u instanceof Observer){
	    ordersObservers.add((Observer) user);
	}
    }
        
    private User createAConcreteUserByRole(User u) {
	if (u.getRole().getRole().equals(RolesType.WAITER) ) {
	    return new Waiter(u, (OrdersManagement) context.getAttribute("ordersM"));
	} else if (u.getRole().getRole().equals(RolesType.BARMAN)) {
	    return new Barman(u, (OrdersManagement) context.getAttribute("ordersM"),
		    (DrinksManagement) context.getAttribute("drinksM"));
	} else if (u.getRole().getRole().equals(RolesType.MANAGER)) {
	    return new Manager(u);
	}
	System.out.println("different roled user!!! add \"User\" object");
	return u;
    }

    public User getLoggedUserByName(String username) {
	System.out.println("logged users " + loggedUsersList.size());
	return loggedUsersList.get(username);
    }
    
    public void logoutUser(String username) {
	System.out.println("logout user " + username);
	User user = loggedUsersList.remove(username);
	if (user instanceof Observer) {
	    ordersObservers.remove(user);
	}
	System.out.println("logged users size: "+ loggedUsersList.size());
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
	    if(newRegedUser != null) {
//		addNewUser(newRegedUser);
		userRoleMap.put(newRegedUser.getName(), newRegedUser.getRole().getRole());
		allRegisteredUsersList.add(newRegedUser);
		return true;
	    }
	} else {
	    System.out.println("USER ALREADY EXISTS....");
	}
	return false;
    }
    //
    // public boolean registerAdmin(String username, String password) {
    // if(admins.getProperty(username) == null) {
    // admins.put(username, password);
    // try {
    // String pathname = context.getRealPath(admins_file);
    // System.out.println(pathname);
    // admins.store(new FileWriter(pathname), "new user added");
    // } catch (IOException e) {
    // e.printStackTrace();
    // admins.remove(username);
    // return false;
    // }
    // }
    // return false;
    // }

    public boolean isManagerUser(String username) {
	return RolesType.MANAGER.equals(userRoleMap.get(username));
    }

}
