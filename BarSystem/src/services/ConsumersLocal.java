package services;

import java.util.List;

import javax.ejb.Local;

import web.pojos.Consumer;
import ejb.User;

@Local
public interface ConsumersLocal {

	// Consumers services
	public Consumer persistConsumer(Consumer persistConsumerRequest);

	public List<Consumer> findAllConsumers();

<<<<<<< HEAD
	public Consumer findConsumerById(int findByConsumerIdRequest);

	public User findUserByConsumerId(int findByConsumerIdRequest);

	public List<Consumer> findAllActiveConsumersByUserId(int findByUserIdRequest);

	public Consumer findActiveConsumereByPlace(String place);
=======
    public User findUserByConsumerId(int findByConsumerIdRequest);
    
    public List<Consumer> findAllActiveConsumersByUserId(int findByUserIdRequest);
    
    public Consumer findActiveConsumereByPlace(String place);
>>>>>>> branch 'master' of https://github.com/kemalinov/FMI-examples.git
}
