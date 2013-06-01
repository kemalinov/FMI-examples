package eao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import web.pojos.Consumer;
import db.utils.DBUtils;
import ejb.User;
import entities.ConsumerEntity;
import entities.UserEntity;

@Stateless
public class ConsumersBean {

	@PersistenceContext(unitName = "BarSysPersistenceUnit")
	private EntityManager em;

	public ConsumersBean() {
	}

	public Consumer persistConsumer(Consumer persistConsumerRequest) {
		ConsumerEntity ce = null;
		if (persistConsumerRequest.getId() == null) {
			ce = new ConsumerEntity();
			ce.setId(persistConsumerRequest.getId());
			ce.setDate(persistConsumerRequest.getDate());
			ce.setPlace(persistConsumerRequest.getPlace());

			// Query q = em.createNamedQuery("UserEntity.findUserById");
			// q.setParameter(1, persistConsumerRequest.getUserId().getId());
			// UserEntity ue = (UserEntity) q.getSingleResult();
			ce.setUserId(DBUtils.UserToUserEntity(persistConsumerRequest.getUserId()));
			ce.setClosed(persistConsumerRequest.isClosed());
		}
		try {
			if (persistConsumerRequest.getId() == null) {
				em.persist(ce);
				em.flush();
			} else {
				System.out.println("updating a consumer " + persistConsumerRequest.getId() + " to status" + persistConsumerRequest.isClosed());
				ce = em.find(ConsumerEntity.class, persistConsumerRequest.getId());
				ce.setClosed(persistConsumerRequest.isClosed());

				ce = em.merge(ce);
				System.out.println("updating a consumer " + ce.getId() + ", with status" + ce.isClosed());
			}
		} catch (Exception e) {
			System.err.println("Exception in persisting of a client method: " + e.getMessage());
		}

		return DBUtils.ConsumerEntityToConsumer(ce);
	}

	public List<Consumer> findAll() {
		Query q = em.createNamedQuery("ConsumerEntity.findAll");
		List<ConsumerEntity> entities = q.getResultList();

		List<Consumer> consumersList = new ArrayList<Consumer>(entities.size());
		for (ConsumerEntity ce : entities) {
			consumersList.add(DBUtils.ConsumerEntityToConsumer(ce));
		}
		System.out.println("size: " + consumersList.size());
		return new ArrayList<Consumer>(consumersList);
	}

	public Consumer findConsumerById(int findByConsumerIdRequest) {
		Query q = em.createNamedQuery("ConsumerEntity.findConsumerById");
		q.setParameter(1, findByConsumerIdRequest);
		ConsumerEntity ce = (ConsumerEntity) q.getSingleResult();

		return DBUtils.ConsumerEntityToConsumer(ce);
	}

	public User findUserByConsumerId(int findByConsumerIdRequest) {
		Query q = em.createNamedQuery("ConsumerEntity.findUserIdByConsumerId");
		q.setParameter(1, findByConsumerIdRequest);
		UserEntity ue = (UserEntity) q.getSingleResult();

		return DBUtils.UserEntityToUser(ue);
	}

	public List<Consumer> findActiveConsumersByUserId(int findByUserIdRequest) {
		Query q = em.createNamedQuery("ConsumerEntity.findActiveConsumersByUserId");
		q.setParameter(1, findByUserIdRequest);
		List<ConsumerEntity> ceList = q.getResultList();

		List<Consumer> consumersList = new ArrayList<Consumer>(ceList.size());
		for (ConsumerEntity ce : ceList) {
			consumersList.add(DBUtils.ConsumerEntityToConsumer(ce));
		}
		return consumersList;
	}

	public Consumer findActiveConsumereByPlace(String place) {
		Query q = em.createNamedQuery("findActiveConsumersByPlace");
		q.setParameter(1, place);
		ConsumerEntity ce = (ConsumerEntity) q.getSingleResult();

		return DBUtils.ConsumerEntityToConsumer(ce);
	}
}
