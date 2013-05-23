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

    private List<User> allUsersList;
    private Map<String, String> userRoleMap = new HashMap<String, String>();

    private List<Observer> barObservers = new ArrayList<Observer>();
    
    private Map<String, Role> nameRoleMap;
    
//    private ServletContext context;
    
    private UsersLocal services;

    
    // constructor
    public UsersManagement(UsersLocal services, ServletContext context) {
//	this.context = context;
	this.services = services;
	System.out.println("userServices: " + this.services);
	initUsers();
	initRoles();
    }
    
    public List<Observer> getObservers() {
	return barObservers;
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
	    allUsersList = new ArrayList<User>(userList.size());
	    for (User u : userList) {
//		User u = Utils.UserDtoToUser(dto);
		addNewUser(u);
	    }
	}
    }
    
    public List<Role> getAllRoles() {
	return new ArrayList<Role>(nameRoleMap.values());
    }
    
    public User getUserByName(String username) {
	for(User u : allUsersList) {
	    if (u.getName().equalsIgnoreCase(username)) {
		return u;
	    }
	}
	return null;
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
    
    private void addNewUser(User u) {
	userRoleMap.put(u.getName(), u.getRole().getRole());
	if (u.getRole().getRole().equals(RolesType.WAITER) ) {
	    allUsersList.add(new Waiter(u));
	} else if (u.getRole().getRole().equals(RolesType.BARMAN)) {
	    Barman b = new Barman(u);
	    allUsersList.add(b);
	    barObservers.add(b);
	} else if (u.getRole().getRole().equals(RolesType.MANAGER)) {
	    allUsersList.add(new Manager(u));
	}
    }
    
    public boolean checkUser(String username, String password) {
	if (allUsersList != null) {
        	for (User u : allUsersList) {
        	    if (u.getName().equalsIgnoreCase(username) && PasswordEncryption.checkPassords(password, u.getPassword())) {
        		System.out.println("User found: " + u.getName());
        		System.out.println("Role: " + u.getRole().getRole());
        		return true;
        	    }
        	}
	}
	return false;
    }
    
    public boolean registerUser(String username, String password, String role) {
	if (!userRoleMap.containsKey(username)) {
	    String encryptedPass = PasswordEncryption.encryptPassword(password);
	    User persistedEntity = null;
	    try {
		Role r = nameRoleMap.get(role);
//		System.out.println("current role: " + r.getId() +", "+ r.getRole());
		User u = new User(null, username.toLowerCase(), encryptedPass, r);
		persistedEntity = services.persistUser(u);
	    } catch (Exception e) {
		log.log(Level.SEVERE, "Exception while persists a new user: ", e);
		return false;
	    }
	    System.out.println("persisted user: " + persistedEntity.getId());
	    if(persistedEntity != null) {
//		UserDTO dto = persistedEntity.getUser();
//		User user = Utils.UserDtoToUser(dto);
		addNewUser(persistedEntity);
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
