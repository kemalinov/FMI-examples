package db.actions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import commons.FindUserByIdRequest;
import commons.FindUsersByRoleIdRequest;
import commons.PersistUserRequest;
import commons.UserListResponse;
import commons.UserResponseEntity;

import db.entities.RoleEntity;
import db.entities.UserEntity;
import db.utils.DBConnectionProvider;
import db.utils.DBUtils;
import dtos.UserDTO;

public class UserActions {
    
    public UserResponseEntity persistRole(PersistUserRequest persistUserRequest) {
	UserEntity ue = new UserEntity();
	ue.setName(persistUserRequest.getUser().getName());
	ue.setPassword(persistUserRequest.getUser().getPassword());
	
	EntityManager em = DBConnectionProvider.createEntityManager();
	
	Query q = em.createNamedQuery("RoleEntity.findRoleById");
	q.setParameter(1, persistUserRequest.getUser().getRoleDto().getId());
	RoleEntity r = (RoleEntity) q.getSingleResult();
	ue.setRole(r);
	
	em.getTransaction().begin();
	try {
	    if(ue.getId() == null) {
		em.persist(ue);
	    } else {
		ue = em.merge(ue);
	    }    
	} catch (Exception e) {
	    // TODO: handle exception
	} 
	em.getTransaction().commit();
	em.close();
	
	return new UserResponseEntity(DBUtils.UserEntityToUserDto(ue));
    }
    
    public UserResponseEntity findUserById(FindUserByIdRequest findUserByIdRequest) {
	EntityManager em = DBConnectionProvider.createEntityManager();
	
	Query q = em.createNamedQuery("UserEntity.findUserById");
	q.setParameter(1, findUserByIdRequest.getId());
	UserEntity ue =  (UserEntity) q.getSingleResult();

	em.close();
	return new UserResponseEntity(DBUtils.UserEntityToUserDto(ue));	
    }
    
    public UserListResponse findAllUsers() {
	EntityManager em = DBConnectionProvider.createEntityManager();
	
	Query q = em.createNamedQuery("UserEntity.findAll");
	List<UserEntity> users = q.getResultList();
	
	List<UserDTO> usersDtoList = new ArrayList<UserDTO>(users.size());
	for(UserEntity ue : users) {
	    RoleEntity r = ue.getRole();
	    usersDtoList.add(DBUtils.UserEntityToUserDto(ue));
	}
	em.close();
	return new UserListResponse(usersDtoList);
    }
    
    public UserListResponse findUsersByRoleId(FindUsersByRoleIdRequest findUsersByRoleIdRequest) {
	EntityManager em = DBConnectionProvider.createEntityManager();
	
	Query q = em.createNamedQuery("UserEntity.findUsersByRoleId");
	q.setParameter(1, findUsersByRoleIdRequest.getId());
	List<UserEntity> users = q.getResultList();
	
	List<UserDTO> usersDtoList = new ArrayList<UserDTO>(users.size());
	for(UserEntity ue : users) {
	    usersDtoList.add(DBUtils.UserEntityToUserDto(ue));
	}
	em.close();
	return new UserListResponse(usersDtoList);
    } 
}
