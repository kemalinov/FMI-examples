package services;

import java.util.List;

import javax.ejb.Local;

import web.pojos.Role;
import ejb.User;

@Local
public interface UsersLocal {

	// Role services
	public Role findRoleById(int findRoleByIdRequest);

	public List<Role> findAllRoles();

	public Role findRoleByUserId(int findByUserIdRequest);

	// User services
	public User persistUser(User persistUserRequest);

	public User findUserById(int findUserByIdRequest);

	public List<User> findAllUsers();

	public List<User> findUsersByRoleId(int findByRoleIdRequest);

}