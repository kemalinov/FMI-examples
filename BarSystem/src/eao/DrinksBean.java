package eao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import web.pojos.Drink;
import db.utils.DBUtils;
import entities.DrinkEntity;

@Stateless
public class DrinksBean {

	@PersistenceContext(unitName = "BarSysPersistenceUnit")
	private EntityManager em;

	public DrinksBean() {
	}

	public Drink persistDrink(Drink persistDrinkRequest) {
		DrinkEntity de = new DrinkEntity();
		de.setId(persistDrinkRequest.getId());
		de.setIngredients(persistDrinkRequest.getIngredients());
		de.setName(persistDrinkRequest.getName());
		de.setPrice(persistDrinkRequest.getPrice());

		try {
			if (de.getId() == null) {
				em.persist(de);
				em.flush();
			} else {
				de = em.merge(de);
			}
		} catch (Exception e) {
			System.err.println("Exception in persisting of a client method: " + e.getMessage());
		}

		return DBUtils.DrinkEntityToDrink(de);
	}

	public List<Drink> findAllDrinks() {
		Query q = em.createNamedQuery("DrinkEntity.findAll");
		List<DrinkEntity> entities = q.getResultList();

		List<Drink> entitiesDtoList = new ArrayList<Drink>(entities.size());
		for (DrinkEntity de : entities) {
			entitiesDtoList.add(DBUtils.DrinkEntityToDrink(de));
		}
		System.out.println("size: " + entitiesDtoList.size());
		return new ArrayList<Drink>(entitiesDtoList);
	}

	public Drink findDrinkById(int findByDrinkIdRequest) {
		Query q = em.createNamedQuery("DrinkEntity.findDrinkById");
		q.setParameter(1, findByDrinkIdRequest);

		DrinkEntity entity = (DrinkEntity) q.getSingleResult();
		return DBUtils.DrinkEntityToDrink(entity);
	}

	public BigDecimal getPriceOfDrink(int findByDrinkIdRequest) {
		Query q = em.createNamedQuery("DrinkEntity.findPriceOfDrinkById");
		q.setParameter(1, findByDrinkIdRequest);

		BigDecimal price = (BigDecimal) q.getSingleResult();
		return price;
	}

	public String getIngredientsOfDrink(int findByDrinkIdRequest) {
		Query q = em.createNamedQuery("DrinkEntity.findIngredientsOfDrinkById");
		q.setParameter(1, findByDrinkIdRequest);

		String ingredients = (String) q.getSingleResult();
		return ingredients;
	}
}
