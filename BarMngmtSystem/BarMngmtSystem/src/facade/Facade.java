package facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import beans.ConsumersBean;
import beans.DrinksBean;
import beans.OrdersBean;
import beans.RolesBean;
import beans.UsersBean;

import commons.consumers.ConsumerListResponse;
import commons.consumers.ConsumerResponseEntity;
import commons.consumers.FindByConsumerIdRequest;
import commons.consumers.PersistConsumerRequest;
import commons.drinks.DrinkIngredientsResponse;
import commons.drinks.DrinkListResponse;
import commons.drinks.DrinkPriceResponse;
import commons.drinks.DrinkResponseEntity;
import commons.drinks.FindByDrinkIdRequest;
import commons.drinks.PersistDrinkRequest;
import commons.orders.FindByOrderIdRequest;
import commons.orders.OrderBillResponse;
import commons.orders.OrderListResponse;
import commons.orders.OrderResponseEntity;
import commons.orders.OrderStatusResponse;
import commons.orders.PersistOrderRequest;
import commons.roles.FindByRoleIdRequest;
import commons.roles.RoleListResponse;
import commons.roles.RoleResponseEntity;
import commons.users.FindByUserIdRequest;
import commons.users.PersistUserRequest;
import commons.users.UserListResponse;
import commons.users.UserResponseEntity;


//@RolesAllowed({RolesType.WAITER, RolesType.BARMAN, RolesType.MANAGER}) // TODO: how they should be used?
@Stateless
public class Facade {
    
    @EJB
    private RolesBean roles;
    
    @EJB
    private UsersBean users;
    
    @EJB
    private DrinksBean drinks;
    
    @EJB
    private ConsumersBean consumers;
    
    @EJB
    private OrdersBean orders;
    
    public Facade(){
    }
    
    // Role services
    public  RoleResponseEntity findRoleById(FindByRoleIdRequest findRoleByIdRequest) {
	return roles.findRoleById(findRoleByIdRequest);	
    }

    public RoleListResponse findAllRoles() {
	System.out.println(roles == null);
	return roles.findAllRoles();
    }
    
    public RoleResponseEntity findRoleByUserId(FindByUserIdRequest findByUserIdRequest) {
	return roles.findRoleByUserId(findByUserIdRequest);	
    }
    
    // User services
    public UserResponseEntity persistUser(PersistUserRequest persistUserRequest) {
	return users.persistUser(persistUserRequest);
    }
    
    public UserResponseEntity findUserById(FindByUserIdRequest findUserByIdRequest) {
	return users.findUserById(findUserByIdRequest);	
    }
    
    public UserListResponse findAllUsers() {
	return users.findAllUsers();
    }
    
    public UserListResponse findUsersByRoleId(FindByRoleIdRequest findByRoleIdRequest) {
	return users.findUsersByRoleId(findByRoleIdRequest);
    }
    
    // Drinks services
    public DrinkResponseEntity persistDrink(PersistDrinkRequest persistDrinkRequest) {
	return drinks.persistDrink(persistDrinkRequest);
    }
    
    public DrinkListResponse findAllDrinks() {
	return drinks.findAllDrinks();
    }
    
    public DrinkResponseEntity findDrinkById(FindByDrinkIdRequest findByDrinkIdRequest) {
	return drinks.findDrinkById(findByDrinkIdRequest);
    }
    
    public DrinkPriceResponse getPriceOfDrink(FindByDrinkIdRequest findByDrinkIdRequest) {
	return drinks.getPriceOfDrink(findByDrinkIdRequest);
    }
    
    public DrinkIngredientsResponse getIngredientsOfDrink(FindByDrinkIdRequest findByDrinkIdRequest) {
	return drinks.getIngredientsOfDrink(findByDrinkIdRequest);
    }
    
    // Consumers services
    public ConsumerResponseEntity persistConsumer(PersistConsumerRequest persistConsumerRequest) {
	return consumers.persistConsumer(persistConsumerRequest);
    }
    
    public ConsumerListResponse getAllConsumers() {
	return consumers.getAllConsumers();
    }
    
    public ConsumerResponseEntity getConsumerById(FindByConsumerIdRequest findByConsumerIdRequest) {
	return consumers.getConsumerById(findByConsumerIdRequest);
    }
    
    public UserResponseEntity getUserByConsumerId(FindByConsumerIdRequest findByConsumerIdRequest) {
	return consumers.getUserByConsumerId(findByConsumerIdRequest);
    }
    
    // Orders services
    public OrderResponseEntity persistOrder(PersistOrderRequest persistOrderRequest) {
	return orders.persistOrder(persistOrderRequest);
    }
    
    public OrderListResponse findAll() {
	return orders.findAll();
    }

    public OrderResponseEntity findOrderById(FindByOrderIdRequest findByOrderIdRequest) {
	    return orders.findOrderById(findByOrderIdRequest);
    }

    public OrderListResponse findOrdersByConsumer(
	    FindByConsumerIdRequest findByConsumerIdRequest) {
	return orders.findOrdersByConsumer(findByConsumerIdRequest);
    }

    public OrderBillResponse getBillForConsumer(
	    FindByConsumerIdRequest findByConsumerIdRequest) {
	return orders.getBillForConsumer(findByConsumerIdRequest);
    }

    public OrderStatusResponse getStatusForOrder(
	    FindByOrderIdRequest findByOrderIdRequest) {
	return orders.getStatusForOrder(findByOrderIdRequest);
    }
}
