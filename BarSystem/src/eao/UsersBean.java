package eao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import db.utils.DBUtils;
import ejb.User;
import entities.RoleEntity;
import entities.UserEntity;

@Stateless
public class UsersBean {

	@PersistenceContext(unitName = "BarSysPersistenceUnit")
	private EntityManager em;

	public UsersBean() {
	}

	public User persistUser(User persistUserRequest) {
		UserEntity ue = new UserEntity();
		ue.setId(persistUserRequest.getId());
		ue.setName(persistUserRequest.getName());
		ue.setPassword(persistUserRequest.getPassword());

		Query q = em.createNamedQuery("RoleEntity.findRoleById");
		q.setParameter(1, persistUserRequest.getRole().getId());
		RoleEntity r = (RoleEntity) q.getSingleResult();
		ue.setRole(r);

		try {
			if (ue.getId() == null) {
				em.persist(ue);
				em.flush(); // to return the persisted object
			} else {
				ue = em.merge(ue);
			}
		} catch (Exception e) {
			System.err.println("Exception in persisting of a user method: " + e.getMessage());
		}

		return DBUtils.UserEntityToUser(ue);
	}

	public List<User> findAllUsers() {
		Query q = em.createNamedQuery("UserEntity.findAll");
		List<UserEntity> users = q.getResultList();

		List<User> usersDtoList = new ArrayList<User>(users.size());
		for (UserEntity ue : users) {
			usersDtoList.add(DBUtils.UserEntityToUser(ue));
		}

		System.out.println("size: " + usersDtoList.size());
		return new ArrayList<User>(usersDtoList);
	}

	public User findUserById(int findUserByIdRequest) {
		Query q = em.createNamedQuery("UserEntity.findUserById");
		q.setParameter(1, findUserByIdRequest);
		UserEntity ue = (UserEntity) q.getSingleResult();

		return DBUtils.UserEntityToUser(ue);
	}

	public List<User> findUsersByRoleId(int findByRoleIdRequest) {
		Query q = em.createNamedQuery("UserEntity.findUsersByRoleId");
		q.setParameter(1, findByRoleIdRequest);
		List<UserEntity> users = q.getResultList();

		List<User> usersDtoList = new ArrayList<User>(users.size());
		for (UserEntity ue : users) {
			usersDtoList.add(DBUtils.UserEntityToUser(ue));
		}

		return new ArrayList<User>(usersDtoList);
	}
}
