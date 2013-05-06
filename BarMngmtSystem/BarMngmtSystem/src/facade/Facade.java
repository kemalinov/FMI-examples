package facade;

import javax.annotation.security.RolesAllowed;

import commons.constants.RolesType;
import commons.roles.FindByRoleIdRequest;
import commons.roles.RoleListResponse;
import commons.roles.RoleResponseEntity;
import commons.users.FindByUserIdRequest;
import commons.users.PersistUserRequest;
import commons.users.UserListResponse;
import commons.users.UserResponseEntity;

import db.actions.RoleActions;
import db.actions.UserActions;

@RolesAllowed({RolesType.WAITER, RolesType.BARMAN, RolesType.MANAGER})
public class Facade {
    
    private static RoleActions roles = new RoleActions();
    
    private static UserActions users = new UserActions();
    
    
    // Role actions
    public static RoleResponseEntity findRoleById(FindByRoleIdRequest findRoleByIdRequest) {
	return roles.findRoleById(findRoleByIdRequest);	
    }

    public static RoleListResponse findAllRoles() {
	return roles.findAllRoles();
    }
    
    public static RoleResponseEntity findRoleByUserId(FindByUserIdRequest findByUserIdRequest) {
	return roles.findRoleByUserId(findByUserIdRequest);	
    }
    
    // User actions
    public static UserResponseEntity persistRole(PersistUserRequest persistUserRequest) {
	return users.persistRole(persistUserRequest);
    }
    
    public static UserResponseEntity findUserById(FindByUserIdRequest findUserByIdRequest) {
	return users.findUserById(findUserByIdRequest);	
    }
    
    public static UserListResponse findAllUsers() {
	return users.findAllUsers();
    }
    
    public static UserListResponse findUsersByRoleId(FindByRoleIdRequest findByRoleIdRequest) {
	return users.findUsersByRoleId(findByRoleIdRequest);
    }

}
