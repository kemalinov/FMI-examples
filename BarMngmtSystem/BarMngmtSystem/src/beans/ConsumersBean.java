package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import commons.consumers.ConsumerListResponse;
import commons.consumers.ConsumerResponseEntity;
import commons.consumers.FindByConsumerIdRequest;
import commons.consumers.PersistConsumerRequest;
import commons.dtos.ConsumerDTO;
import commons.users.UserResponseEntity;

import db.entities.ConsumerEntity;
import db.entities.UserEntity;
import db.utils.DBUtils;

@Stateless
public class ConsumersBean {

    @PersistenceContext(unitName = "BarSysPersistenceUnit")
    private EntityManager em;

    public ConsumersBean() {
    }

    public ConsumerResponseEntity persistConsumer(PersistConsumerRequest persistConsumerRequest) {
	ConsumerEntity ce = new ConsumerEntity();
	ce.setDate(persistConsumerRequest.getConsumer().getTime_());
	ce.setPlace(persistConsumerRequest.getConsumer().getPlace());
	
	Query q = em.createNamedQuery("UserEntity.findUserById");
	q.setParameter(1, persistConsumerRequest.getConsumer().getUserId().getId());
	UserEntity ue = (UserEntity) q.getSingleResult();
	ce.setUserId(ue);
	try {
	    if(ce.getId() == null) {
		em.persist(ce);
		em.flush();
	    } else {
		ce = em.merge(ce);
	    }    
	} catch (Exception e) {
	    // TODO: handle exception
	} 
	
	return new ConsumerResponseEntity(DBUtils.ConsumerEntityToConsumerDTO(ce));
    }
    
    public ConsumerListResponse getAllConsumers() {
	Query q = em.createNamedQuery("ConsumerEntity.findAll");
	List<ConsumerEntity> entities = q.getResultList();
	
	List<ConsumerDTO> entitiesDtoList = new ArrayList<ConsumerDTO>(entities.size());
	for(ConsumerEntity ce : entities) {
	    entitiesDtoList.add(DBUtils.ConsumerEntityToConsumerDTO(ce));
	}
	System.out.println("size: " + entitiesDtoList.size());
	return new  ConsumerListResponse(entitiesDtoList);
    }
    
    public ConsumerResponseEntity getConsumerById(FindByConsumerIdRequest findByConsumerIdRequest) {
	Query q = em.createNamedQuery("ConsumerEntity.findConsumerById");
	q.setParameter(1, findByConsumerIdRequest.getId());
	ConsumerEntity ce =  (ConsumerEntity) q.getSingleResult();

	return new ConsumerResponseEntity(DBUtils.ConsumerEntityToConsumerDTO(ce));
    }
    
    public UserResponseEntity getUserByConsumerId(FindByConsumerIdRequest findByConsumerIdRequest) {
	Query q = em.createNamedQuery("ConsumerEntity.findUserIdByConsumerId");
	q.setParameter(1, findByConsumerIdRequest.getId());
	UserEntity ue =  (UserEntity) q.getSingleResult();

	return new UserResponseEntity(DBUtils.UserEntityToUserDto(ue));
    }

}
