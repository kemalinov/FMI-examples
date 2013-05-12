package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import commons.dtos.RoleDTO;
import commons.roles.FindByRoleIdRequest;
import commons.roles.RoleListResponse;
import commons.roles.RoleResponseEntity;
import commons.users.FindByUserIdRequest;

import db.entities.RoleEntity;
import db.utils.DBUtils;

@Stateless
public class RolesBean {
    
    @PersistenceContext(unitName="BarSysPersistenceUnit")
    private EntityManager em;
    
    public RolesBean() {
    }
    
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
    
    public RoleResponseEntity findRoleById(FindByRoleIdRequest findRoleByIdRequest) {
	Query q = em.createNamedQuery("RoleEntity.findRoleById");
	q.setParameter(1, findRoleByIdRequest.getId());
	RoleEntity re = (RoleEntity) q.getSingleResult();

	return new RoleResponseEntity(DBUtils.RoleEntityToRoleDto(re));	
    }
    
    public RoleListResponse findAllRoles() {
	Query q = em.createNamedQuery("RoleEntity.findAll");
	List<RoleEntity> roles = q.getResultList();
	
	List<RoleDTO> rolesDtoList = new ArrayList<RoleDTO>(roles.size());
	for(RoleEntity re : roles) {
	    rolesDtoList.add(DBUtils.RoleEntityToRoleDto(re));
	}
	System.out.println("size: " + rolesDtoList.size());
	return new RoleListResponse(rolesDtoList);
    }
    
    // TODO: is it necessary this method? 
    public RoleResponseEntity findRoleByUserId(FindByUserIdRequest findByUserIdRequest) {
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
