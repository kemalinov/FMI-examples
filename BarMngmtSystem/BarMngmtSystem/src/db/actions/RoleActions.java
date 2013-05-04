package db.actions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import commons.FindRoleByIdRequest;
import commons.FindRoleByUserIdRequest;
import commons.RoleListResponse;
import commons.RoleResponseEntity;

import db.entities.RoleEntity;
import db.utils.DBConnectionProvider;
import db.utils.DBUtils;
import dtos.RoleDTO;

public class RoleActions {
    
//    public void persistRole(PersistRoleRequest persistRoleRequest) {
//	Role role = new Role();
//	role.setName(persistRoleRequest.getName());
//	
//	EntityManager em = DBConnectionProvider.createEntityManager();
//	em.getTransaction().begin();
//	em.persist(role);
//	em.getTransaction().commit();
//	em.close();
//    }
    
    public RoleResponseEntity findRoleById(FindRoleByIdRequest findRoleByIdRequest) {
	EntityManager em = DBConnectionProvider.createEntityManager();
	
	Query q = em.createNamedQuery("RoleEntity.findRoleById");
	q.setParameter(1, findRoleByIdRequest.getId());
	RoleEntity re = (RoleEntity) q.getSingleResult();
	em.close();
	return new RoleResponseEntity(DBUtils.RoleEntityToRoleDto(re));	
    }
    
    public RoleListResponse findAllRoles() {
	EntityManager em = DBConnectionProvider.createEntityManager();
	
	Query q = em.createNamedQuery("RoleEntity.findAll");
	List<RoleEntity> roles = q.getResultList();
	
	List<RoleDTO> rolesDtoList = new ArrayList<RoleDTO>(roles.size());
	for(RoleEntity re : roles) {
	    rolesDtoList.add(DBUtils.RoleEntityToRoleDto(re));
	}
	em.close();
	return new RoleListResponse(rolesDtoList);
    }
    
    // TODO: is it necessary this method? 
    public RoleResponseEntity findRoleByUserId(FindRoleByUserIdRequest findRoleByUserIdRequest) {
	throw new NotImplementedException();
	
//	EntityManager em = DBConnectionProvider.createEntityManager();
//	
//	Query q = em.createNamedQuery("RoleEntity.findRoleByUserId");
//	q.setParameter(1, findRoleByUserIdRequest.getId());
//	RoleEntity re = (RoleEntity) q.getSingleResult();
//	
//	em.close();
//	return new RoleResponseEntity(DBUtils.RoleEntityToRoleDto(re));
    }
}
