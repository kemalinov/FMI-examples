package test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.ConsumerEntity;
import entities.DrinkEntity;

public class DrinksTest {

	public static void main(String[] args) {

		EntityManager em = DBConnectionProvider.createEntityManager();

		// reads all users
		Query q = em.createNamedQuery("DrinkEntity.findDrinkById");
		q.setParameter(1, 2);
		List<DrinkEntity> drinks = q.getResultList();
		// for (DrinkEntity d : drinks) {
		// System.out.println(d.getName());
		// }
		// System.out.println("Size: " + drinks.size());

		// Create new drink
		// em.getTransaction().begin();
		// DrinkEntity d = new DrinkEntity();
		// d.setName("vodka");
		// d.setIngredients("beluga,50;"); // <sustavka,gr>;<...>
		// d.setPrice(new BigDecimal("5.00"));
		// em.persist(d);

		// q = em.createNamedQuery("OrderEntity.findOrderById");
		// q.setParameter(1, 1);
		// List<OrderEntity> ords = q.getResultList();
		// for (OrderEntity o : ords) {
		// System.out.println(o.getId());
		// System.out.println(o.getDrinks().size());
		// for(Entry<DrinkEntity, Integer> e : o.getDrinks().entrySet()) {
		// System.out.println(e.getKey().getName() + ", " + e.getValue());
		// }
		// }
		//
		// em.getTransaction().begin();
		// OrderEntity o = new OrderEntity();
		// o.setId(1);
		// o.addDrink(drinks.get(0), 10);
		// em.persist(o);
		// em.getTransaction().commit();

		// consumers
		q = em.createNamedQuery("ConsumerEntity.findConsumerById");
		q.setParameter(1, 1);
		List<ConsumerEntity> ords = q.getResultList();
		em.getTransaction().begin();

		ConsumerEntity c = ords.get(0);// new ConsumerEntity();
		c.setPlace("masa_6");
		c.setDate(new Date());
		// c.addDrink(drinks.get(0), 5);
		// c.setUserId(2); // get the current user
		em.persist(c);

		em.getTransaction().commit();

		// q = em.createNamedQuery("ConsumerEntity.findConsumerById");
		// q.setParameter(1, 1);
		// List<ConsumerEntity> ords = q.getResultList();
		// for (ConsumerEntity o : ords) {
		// System.out.println(o.getConsumer_id());
		// System.out.println(o.getTime_());
		// System.out.println(o.getDrinks().size());
		// for(Entry<DrinkEntity, Integer> e : o.getDrinks().entrySet()) {
		// System.out.println(e.getKey().getName() + ", " + e.getValue());
		// }
		// }
		// GREAT!!!

		em.close();
	}
}