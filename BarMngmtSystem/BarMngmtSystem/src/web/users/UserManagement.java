package web.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import web.pojos.Role;
import web.pojos.User;
import web.utils.PasswordEncryption;
import web.utils.Utils;

import commons.constants.RolesType;
import commons.dtos.RoleDTO;
import commons.dtos.UserDTO;
import commons.roles.RoleListResponse;
import commons.users.PersistUserRequest;
import commons.users.UserListResponse;
import commons.users.UserResponseEntity;

import facade.Facade;

public class UserManagement {
    
    private static Logger log = Logger.getLogger(UserManagement.class.getName());

    private List<User> users;
    private Map<String, String> userRoleMap = new HashMap<String, String>();
    
    private List<Role> roles;

    private ServletContext context;

    private Facade services;
    

    // constructor
    public UserManagement(Facade services, ServletContext context) {
	this.context = context;
	this.services = services;
	initUsers();
	initRoles();
    }
    
    private void initUsers() {
	UserListResponse userListResponse = null;
	try {
	    userListResponse = services.findAllUsers();
	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while calling findAllUsers()", e);
	}
	if (userListResponse != null && userListResponse.getUsers() != null) {
	    users = new ArrayList<User>(userListResponse.getUsers().size());
	    for (UserDTO dto : userListResponse.getUsers()) {
		User u = Utils.UserDtoToUser(dto);
		addNewUser(u);
	    }
	}
    }
    
    public User getUserByName(String username) {
	for(User u : users) {
	    if (u.getName().equalsIgnoreCase(username)) {
		return u;
	    }
	}
	return null;
    }
    
    private void initRoles() {
	RoleListResponse roleListResponse = null;
	try {
	    roleListResponse = services.findAllRoles();
	} catch (Exception e) {
	    log.log(Level.SEVERE, "Exception while calling findAllRoles()", e);
	}
	if (roleListResponse != null && roleListResponse.getRoles() != null) {
	    roles = new ArrayList<Role>(roleListResponse.getRoles().size());
	    for (RoleDTO r : roleListResponse.getRoles()) {
		roles.add(Utils.RoleDtoToRole(r));
	    }
	}	
    }
    
    private void addNewUser(User u) {
	users.add(u);
	userRoleMap.put(u.getName(), u.getRole().getRole());
    }
    
    // synchronize reading/writing in the files because it might get corrupted

    // public void loadUsers() {
    // InputStream is = context.getResourceAsStream(users_file);
    // try {
    // users.load(is);
    // } catch (IOException e) {
    // e.printStackTrace();
    // } finally {
    // try {
    // is.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // }
    //
    // public void loadAdmins() {
    // InputStream is = context.getResourceAsStream(admins_file);
    // try {
    // admins.load(is);
    // } catch (IOException e) {
    // e.printStackTrace();
    // } finally {
    // try {
    // is.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // }

    public boolean checkUser(String username, String password) {
	if (users != null) {
        	for (User u : users) {
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
	    UserResponseEntity persistedEntity = null;
	    try {
		for(Role r : roles) {
		    if(r.getRole().equals(role)) {
			System.out.println("current role: " + r.getId() +", "+ r.getRole());
			persistedEntity = services.persistUser(new PersistUserRequest(new UserDTO(null, username.toLowerCase(), encryptedPass,
				new RoleDTO(r.getId(), r.getRole()))));
			break;
		    } 
		}
	    } catch (Exception e) {
		log.log(Level.SEVERE, "Exception while persists a new user: ", e);
		return false;
	    }
	    System.out.println("persisted user: " + persistedEntity.getUser().getId());
	    if(persistedEntity.getUser() != null) {
		UserDTO dto = persistedEntity.getUser();
		User user = Utils.UserDtoToUser(dto);
		addNewUser(user);
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
