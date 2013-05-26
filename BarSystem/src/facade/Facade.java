//package facade;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//
//import services.DrinksLocal;
//import services.UsersLocal;
//
//import web.pojos.Consumer;
//import web.pojos.Drink;
//import web.pojos.Order;
//import web.pojos.Role;
//import constants.OrderStatus;
//import eao.ConsumersBean;
//import eao.DrinksBean;
//import eao.OrdersBean;
//import eao.RolesBean;
//import eao.UsersBean;
//import ejb.User;
//
//
////@RolesAllowed({RolesType.WAITER, RolesType.BARMAN, RolesType.MANAGER}) // TODO: how they should be used?
//@Stateless
//public class Facade {
//    
//    @EJB
//    private RolesBean roles;
//    
//    @EJB
//    private UsersBean users;
//    
//    @EJB
//    private DrinksBean drinks;
//    
//    @EJB
//    private ConsumersBean consumers;
//    
//    @EJB
//    private OrdersBean orders;
//    
////    @EJB(beanName="DrinkServices")
////    private DrinksLocal drinkServices;
////
////    @EJB(beanName="UserServices")
////    private UsersLocal userServices;
//    
////    @EJB
////    private DrinksLocal drinkServices;
////    
////    @EJB
////    private UsersLocal userServices;
////    
////    @EJB
////    private OrdersLocal orderServices;
//    
//    
//    public Facade(){
//    }
//    // Role services
//    public  Role findRoleById(int findRoleByIdRequest) {
//	return roles.findRoleById(findRoleByIdRequest);	
//    }
//
//    public List<Role> findAllRoles() {
//	return roles.findAllRoles();
//    }
//    
//    public Role findRoleByUserId(int findByUserIdRequest) {
//	return roles.findRoleByUserId(findByUserIdRequest);	
//    }
//    
//    // User services
//    public User persistUser(User persistUserRequest) {
//	return users.persistUser(persistUserRequest);
//    }
//    
//    public User findUserById(int findUserByIdRequest) {
//	return users.findUserById(findUserByIdRequest);	
//    }
//    
//    public List<User> findAllUsers() {
//	return users.findAllUsers();
//    }
//    
//    public List<User> findUsersByRoleId(int findByRoleIdRequest) {
//	return users.findUsersByRoleId(findByRoleIdRequest);
//    }
//    
// // Consumers services
//    public Consumer persistConsumer(Consumer persistConsumerRequest) {
//	return consumers.persistConsumer(persistConsumerRequest);
//    }
//
//    public List<Consumer> findAllConsumers() {
//	return consumers.findAll();
//    }
//    
//    public Consumer findConsumerById(int findByConsumerIdRequest) {
//	return consumers.findConsumerById(findByConsumerIdRequest);
//    }
//    
//    public User findUserByConsumerId(int findByConsumerIdRequest) {
//	return consumers.findUserByConsumerId(findByConsumerIdRequest);
//    }
//    
//    // Orders services
//    public Order persistOrder(Order persistOrderRequest) {
//	return orders.persistOrder(persistOrderRequest);
//    }
//    
//    public List<Order> findAll() {
//	return orders.findAll();
//    }
//
//    public Order findOrderById(int findByOrderIdRequest) {
//	return orders.findOrderById(findByOrderIdRequest);
//    }
//
//    public List<Order> findOrdersByConsumer(
//	    int findByConsumerIdRequest) {
//	return orders.findOrdersByConsumer(findByConsumerIdRequest);
//    }
//
//    public BigDecimal getBillForConsumer(
//	    int findByConsumerIdRequest) {
//	return orders.getBillForConsumer(findByConsumerIdRequest);
//    }
//
//    public OrderStatus getStatusForOrder(
//	    int findByOrderIdRequest) {
//	return orders.getStatusForOrder(findByOrderIdRequest);
//    }
//    
//    public Drink persistDrink(Drink persistDrinkRequest) {
//	return drinks.persistDrink(persistDrinkRequest);
//    }
//    
//    public List<Drink> findAllDrinks() {
//	return drinks.findAllDrinks();
//    }
//    
//    public Drink findDrinkById(int findByDrinkIdRequest) {
//	return drinks.findDrinkById(findByDrinkIdRequest);
//    }
//    
//    public BigDecimal getPriceOfDrink(int findByDrinkIdRequest) {
//	return drinks.getPriceOfDrink(findByDrinkIdRequest);
//    }
//    
//    public String getIngredientsOfDrink(int findByDrinkIdRequest) {
//	return drinks.getIngredientsOfDrink(findByDrinkIdRequest);
//    }
//    
//    
////    ////////////////////////////
//    
////    // Role services
////    public  RoleResponseEntity findRoleById(FindByRoleIdRequest findRoleByIdRequest) {
////	return roles.findRoleById(findRoleByIdRequest);	
////    }
////
////    public RoleListResponse findAllRoles() {
////	System.out.println(roles == null);
////	return roles.findAllRoles();
////    }
////    
////    public RoleResponseEntity findRoleByUserId(FindByUserIdRequest findByUserIdRequest) {
////	return roles.findRoleByUserId(findByUserIdRequest);	
////    }
////    
////    // User services
////    public UserResponseEntity persistUser(PersistUserRequest persistUserRequest) {
////	return users.persistUser(persistUserRequest);
////    }
////    
////    public UserResponseEntity findUserById(FindByUserIdRequest findUserByIdRequest) {
////	return users.findUserById(findUserByIdRequest);	
////    }
////    
////    public UserListResponse findAllUsers() {
////	return users.findAllUsers();
////    }
////    
////    public UserListResponse findUsersByRoleId(FindByRoleIdRequest findByRoleIdRequest) {
////	return users.findUsersByRoleId(findByRoleIdRequest);
////    }
//    
////    // Drinks services
////    public DrinkResponseEntity persistDrink(PersistDrinkRequest persistDrinkRequest) {
////	return drinks.persistDrink(persistDrinkRequest);
////    }
////    
////    public DrinkListResponse findAllDrinks() {
////	return drinks.findAllDrinks();
////    }
////    
////    public DrinkResponseEntity findDrinkById(FindByDrinkIdRequest findByDrinkIdRequest) {
////	return drinks.findDrinkById(findByDrinkIdRequest);
////    }
////    
////    public DrinkPriceResponse getPriceOfDrink(FindByDrinkIdRequest findByDrinkIdRequest) {
////	return drinks.getPriceOfDrink(findByDrinkIdRequest);
////    }
////    
////    public DrinkIngredientsResponse getIngredientsOfDrink(FindByDrinkIdRequest findByDrinkIdRequest) {
////	return drinks.getIngredientsOfDrink(findByDrinkIdRequest);
////    }
//    
////    // Consumers services
////    public ConsumerResponseEntity persistConsumer(PersistConsumerRequest persistConsumerRequest) {
////	return consumers.persistConsumer(persistConsumerRequest);
////    }
////    
////    public ConsumerListResponse findAllConsumers() {
////	return consumers.findAll();
////    }
////    
////    public ConsumerResponseEntity findConsumerById(FindByConsumerIdRequest findByConsumerIdRequest) {
////	return consumers.findConsumerById(findByConsumerIdRequest);
////    }
////    
////    public UserResponseEntity findUserByConsumerId(FindByConsumerIdRequest findByConsumerIdRequest) {
////	return consumers.findUserByConsumerId(findByConsumerIdRequest);
////    }
////    
////    // Orders services
////    public OrderResponseEntity persistOrder(PersistOrderRequest persistOrderRequest) {
////	return orders.persistOrder(persistOrderRequest);
////    }
////    
////    public OrderListResponse findAll() {
////	return orders.findAll();
////    }
////
////    public OrderResponseEntity findOrderById(FindByOrderIdRequest findByOrderIdRequest) {
////	    return orders.findOrderById(findByOrderIdRequest);
////    }
////
////    public OrderListResponse findOrdersByConsumer(
////	    FindByConsumerIdRequest findByConsumerIdRequest) {
////	return orders.findOrdersByConsumer(findByConsumerIdRequest);
////    }
////
////    public OrderBillResponse getBillForConsumer(
////	    FindByConsumerIdRequest findByConsumerIdRequest) {
////	return orders.getBillForConsumer(findByConsumerIdRequest);
////    }
////
////    public OrderStatusResponse getStatusForOrder(
////	    FindByOrderIdRequest findByOrderIdRequest) {
////	return orders.getStatusForOrder(findByOrderIdRequest);
////    }
// }
