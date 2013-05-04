package facade;

import javax.annotation.security.RolesAllowed;

import commons.FindRoleByIdRequest;
import commons.FindRoleByUserIdRequest;
import commons.FindUserByIdRequest;
import commons.FindUsersByRoleIdRequest;
import commons.PersistUserRequest;
import commons.RoleListResponse;
import commons.RoleResponseEntity;
import commons.UserListResponse;
import commons.UserResponseEntity;
import commons.constants.RolesType;

import db.actions.RoleActions;
import db.actions.UserActions;

@RolesAllowed({RolesType.WAITER, RolesType.BARMAN, RolesType.MANAGER})
public class Facade {
    
    private static RoleActions roles = new RoleActions();
    
    private static UserActions users = new UserActions();
    
    
    // Role actions
    public static RoleResponseEntity findRoleById(FindRoleByIdRequest findRoleByIdRequest) {
	return roles.findRoleById(findRoleByIdRequest);	
    }

    public static RoleListResponse findAllRoles() {
	return roles.findAllRoles();
    }
    
    public static RoleResponseEntity findRoleByUserId(FindRoleByUserIdRequest findRoleByUserIdRequest) {
	return roles.findRoleByUserId(findRoleByUserIdRequest);	
    }
    
    // User actions
    public static UserResponseEntity persistRole(PersistUserRequest persistUserRequest) {
	return users.persistRole(persistUserRequest);
    }
    
    public static UserResponseEntity findUserById(FindUserByIdRequest findUserByIdRequest) {
	return users.findUserById(findUserByIdRequest);	
    }
    
    public static UserListResponse findAllUsers() {
	return users.findAllUsers();
    }
    
    public static UserListResponse findUsersByRoleId(FindUsersByRoleIdRequest findUsersByRoleIdRequest) {
	return users.findUsersByRoleId(findUsersByRoleIdRequest);
    }

}
