package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import commons.drinks.DrinkIngredientsResponse;
import commons.drinks.DrinkListResponse;
import commons.drinks.DrinkPriceResponse;
import commons.drinks.DrinkResponseEntity;
import commons.drinks.FindByDrinkIdRequest;
import commons.drinks.PersistDrinkRequest;
import commons.dtos.DrinkDTO;

import db.entities.DrinkEntity;
import db.utils.DBUtils;

@Stateless
public class DrinksBean {

    @PersistenceContext(unitName="BarSysPersistenceUnit")
    private EntityManager em;
    
    public DrinksBean(){
    }
    
    public DrinkResponseEntity persistDrink(PersistDrinkRequest persistDrinkRequest) {
	DrinkEntity de = new DrinkEntity();
	de.setIngredients(persistDrinkRequest.getDrink().getIngredients());
	de.setName(persistDrinkRequest.getDrink().getName());
	de.setPrice(persistDrinkRequest.getDrink().getPrice());
	
	try {
	    if(de.getId() == null) {
		em.persist(de);
		em.flush();
	    } else {
		de = em.merge(de);
	    }    
	} catch (Exception e) {
	    // TODO: handle exception
	} 
	
	return new DrinkResponseEntity(DBUtils.DrinkEntityToDrinkDto(de));
    }
    
    public DrinkListResponse findAllDrinks() {
	Query q = em.createNamedQuery("DrinkEntity.findAll");
	List<DrinkEntity> entities = q.getResultList();
	
	List<DrinkDTO> entitiesDtoList = new ArrayList<DrinkDTO>(entities.size());
	for(DrinkEntity de : entities) {
	    entitiesDtoList.add(DBUtils.DrinkEntityToDrinkDto(de));
	}
	System.out.println("size: " + entitiesDtoList.size());
	return new DrinkListResponse(entitiesDtoList);
    }
    
    public DrinkResponseEntity findDrinkById(FindByDrinkIdRequest findByDrinkIdRequest) {
	Query q = em.createNamedQuery("DrinkEntity.findDrinkById");
	q.setParameter(1, findByDrinkIdRequest.getId());
	
	DrinkEntity entity = (DrinkEntity) q.getSingleResult();
	return new DrinkResponseEntity(DBUtils.DrinkEntityToDrinkDto(entity));
    }
    
    public DrinkPriceResponse getPriceOfDrink(FindByDrinkIdRequest findByDrinkIdRequest) {
	Query q = em.createNamedQuery("DrinkEntity.findPriceOfDrinkById");
	q.setParameter(1, findByDrinkIdRequest.getId());
	
	BigDecimal price = (BigDecimal) q.getSingleResult();
	return new DrinkPriceResponse(price);
    }
    
    public DrinkIngredientsResponse getIngredientsOfDrink(FindByDrinkIdRequest findByDrinkIdRequest) {
	Query q = em.createNamedQuery("DrinkEntity.findIngredientsOfDrinkById");
	q.setParameter(1, findByDrinkIdRequest.getId());
	
	String ingredients = (String) q.getSingleResult();
	return new DrinkIngredientsResponse(ingredients);
    }
}
