package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import commons.dtos.UserDTO;
import commons.roles.FindByRoleIdRequest;
import commons.users.FindByUserIdRequest;
import commons.users.PersistUserRequest;
import commons.users.UserListResponse;
import commons.users.UserResponseEntity;

import db.entities.RoleEntity;
import db.entities.UserEntity;
import db.utils.DBUtils;

@Stateless
public class UsersBean {
    
    @PersistenceContext(unitName="BarSysPersistenceUnit")
    private EntityManager em;
    
    public UsersBean(){
    }
    
    public UserResponseEntity persistUser(PersistUserRequest persistUserRequest) {
	UserEntity ue = new UserEntity();
	ue.setName(persistUserRequest.getUser().getName());
	ue.setPassword(persistUserRequest.getUser().getPassword());
	
	Query q = em.createNamedQuery("RoleEntity.findRoleById");
	q.setParameter(1, persistUserRequest.getUser().getRoleDto().getId());
	RoleEntity r = (RoleEntity) q.getSingleResult();
	ue.setRole(r);
	
	try {
	    if(ue.getId() == null) {
		em.persist(ue);
		em.flush();	// to return the persisted object 
	    } else {
		ue = em.merge(ue);
	    }    
	} catch (Exception e) {
	    // TODO: handle exception
	} 
	
	return new UserResponseEntity(DBUtils.UserEntityToUserDto(ue));
    }
    
    public UserListResponse findAllUsers() {
	Query q = em.createNamedQuery("UserEntity.findAll");
	List<UserEntity> users = q.getResultList();
	
	List<UserDTO> usersDtoList = new ArrayList<UserDTO>(users.size());
	for(UserEntity ue : users) {
	    usersDtoList.add(DBUtils.UserEntityToUserDto(ue));
	}
	
	System.out.println("size: " + usersDtoList.size());
	return new UserListResponse(usersDtoList);
    }
    
    public UserResponseEntity findUserById(FindByUserIdRequest findUserByIdRequest) {
	Query q = em.createNamedQuery("UserEntity.findUserById");
	q.setParameter(1, findUserByIdRequest.getId());
	UserEntity ue =  (UserEntity) q.getSingleResult();

	return new UserResponseEntity(DBUtils.UserEntityToUserDto(ue));	
    }
    
    public UserListResponse findUsersByRoleId(FindByRoleIdRequest findByRoleIdRequest) {
	Query q = em.createNamedQuery("UserEntity.findUsersByRoleId");
	q.setParameter(1, findByRoleIdRequest.getId());
	List<UserEntity> users = q.getResultList();
	
	List<UserDTO> usersDtoList = new ArrayList<UserDTO>(users.size());
	for(UserEntity ue : users) {
	    usersDtoList.add(DBUtils.UserEntityToUserDto(ue));
	}

	return new UserListResponse(usersDtoList);
    } 
}
