package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class DBConnectionProvider {

    private static final String PERSISTENCE_UNIT_NAME = "BarSysPersistenceUnit";
    private static EntityManagerFactory factory = Persistence
	    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public static EntityManager createEntityManager() {
	return factory.createEntityManager();
    }

}
