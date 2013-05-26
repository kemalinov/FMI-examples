package eao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import web.pojos.Role;
import db.utils.DBUtils;
import entities.RoleEntity;

@Stateless
public class RolesBean {

	@PersistenceContext(unitName = "BarSysPersistenceUnit")
	private EntityManager em;

	public RolesBean() {
	}

	// public void persistRole(PersistRoleRequest persistRoleRequest) {
	// Role role = new Role();
	// role.setName(persistRoleRequest.getName());
	//
	// EntityManager em = DBConnectionProvider.createEntityManager();
	// em.getTransaction().begin();
	// em.persist(role);
	// em.getTransaction().commit();
	// em.close();
	// }

	public Role findRoleById(int findRoleByIdRequest) {
		Query q = em.createNamedQuery("RoleEntity.findRoleById");
		q.setParameter(1, findRoleByIdRequest);
		RoleEntity re = (RoleEntity) q.getSingleResult();

		return DBUtils.RoleEntityToRole(re);
	}

	public List<Role> findAllRoles() {
		Query q = em.createNamedQuery("RoleEntity.findAll");
		List<RoleEntity> roles = q.getResultList();

		List<Role> rolesDtoList = new ArrayList<Role>(roles.size());
		for (RoleEntity re : roles) {
			rolesDtoList.add(DBUtils.RoleEntityToRole(re));
		}
		System.out.println("size: " + rolesDtoList.size());
		return new ArrayList<Role>(rolesDtoList);
	}

	// TODO: is it necessary this method?
	public Role findRoleByUserId(int findByUserIdRequest) {
		throw new NotImplementedException();

		// EntityManager em = DBConnectionProvider.createEntityManager();
		//
		// Query q = em.createNamedQuery("RoleEntity.findRoleByUserId");
		// q.setParameter(1, findRoleByUserIdRequest.getId());
		// RoleEntity re = (RoleEntity) q.getSingleResult();
		//
		// em.close();
		// return new RoleResponseEntity(DBUtils.RoleEntityToRoleDto(re));
	}
}
