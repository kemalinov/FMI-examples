package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import web.pojos.Role;
import eao.RolesBean;
import eao.UsersBean;
import ejb.User;

@Stateless(name="UserServices")
public class UserServices implements UsersLocal {

    @EJB
    private RolesBean roles;
    
    @EJB
    private UsersBean users;
    
    public UserServices() {
    }
    
    // Role services
    public  Role findRoleById(int findRoleByIdRequest) {
	return roles.findRoleById(findRoleByIdRequest);	
    }

    public List<Role> findAllRoles() {
	System.out.println(roles == null);
	return roles.findAllRoles();
    }
    
    public Role findRoleByUserId(int findByUserIdRequest) {
	return roles.findRoleByUserId(findByUserIdRequest);	
    }
    
    // User services
    public User persistUser(User persistUserRequest) {
	return users.persistUser(persistUserRequest);
    }
    
    public User findUserById(int findUserByIdRequest) {
	return users.findUserById(findUserByIdRequest);	
    }
    
    public List<User> findAllUsers() {
	return users.findAllUsers();
    }
    
    public List<User> findUsersByRoleId(int findByRoleIdRequest) {
	return users.findUsersByRoleId(findByRoleIdRequest);
    }

}
