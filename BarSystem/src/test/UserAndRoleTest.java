package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.RoleEntity;

public class UserAndRoleTest {

	public static void main(String[] args) {

		// pass encryption
		// PooledStringDigester digester = new PooledStringDigester();
		// digester.setPoolSize(4); // This would be a good value for a 4-core
		// system
		// digester.setAlgorithm("SHA-1");
		// digester.setIterations(50000);
		//
		// System.out.println(digester.digest("123"));

		// "gosho" on SHA-1: "+jrezvvEdeumYQJ0wRC8SbFV72Tn0Mgb9ZQBsA=="
		// "gosho" on MD5: "B/K4T+CSrMvhzKBbMlZpr6mu6e2nsGge"

		// "123" on MD5: "bb7/XuP9AebEQK5I4Ue8eUrMKw0BoTNc"
		// "123" on SHA-1: "7un1rrYUkHE7tGdUhgTtxwvMiotUFlC2iX4OQA=="

		// Persistence test
		// factory =
		// Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = DBConnectionProvider.createEntityManager();
		// Read the existing entries and write to console

		// reads all users
		// Query q = em.createNamedQuery("User.findAll");
		// List<User> userList = q.getResultList();
		// for (User user : userList) {
		// System.out.println(user.getName());
		// }
		// System.out.println("Size: " + userList.size());

		// Create new user
		// em.getTransaction().begin();
		// UserEntity user = new UserEntity();
		// user.setName("ivo");
		// user.setPassword("1");
		// em.persist(user);
		// em.getTransaction().commit();

		// userList = q.getResultList();
		// System.out.println("Size: " + userList.size());

		// create a role
		// em.getTransaction().begin();
		// Role role = new Role();
		// role.setName(RolesEnum.WAITER);
		// em.persist(role);
		// em.getTransaction().commit();

		// reads all roles
		// Query q = em.createNamedQuery("Role.findAll");
		// List<Role> roleList = q.getResultList();
		// for (Role r : roleList) {
		// System.out.println(r.getRoleName());
		// }

		// map an user and role
		// Query q = em.createNamedQuery("UserEntity.findUserById");
		// q.setParameter(1, "1");
		// UserEntity u = (UserEntity) q.getSingleResult();
		// System.out.println(u.getRole());

		// Query q = em.createNamedQuery("RoleEntity.findRoleById");
		// q.setParameter(1, "1");
		// RoleEntity r = (RoleEntity) q.getSingleResult();

		Query q = em.createNamedQuery("UserEntity.findUsersByRoleId");
		q.setParameter(1, 7);
		List<RoleEntity> r = q.getResultList();
		for (RoleEntity re : r) {
			System.out.println(re.getRoleName());
		}
		// user.setRole(r);
		// em.getTransaction().begin();
		// em.persist(user);
		// em.getTransaction().commit();
		//
		// em.close();
	}
}