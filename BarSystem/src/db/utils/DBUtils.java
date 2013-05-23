package db.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import web.pojos.Consumer;
import web.pojos.Drink;
import web.pojos.Order;
import web.pojos.Role;
import ejb.User;
import entities.ConsumerEntity;
import entities.DrinkEntity;
import entities.OrderEntity;
import entities.RoleEntity;
import entities.UserEntity;

public class DBUtils {

    public static Consumer ConsumerEntityToConsumer(ConsumerEntity ce) {
	Role r = new Role(ce.getUserId().getRole().getId(), ce.getUserId().getRole().getRoleName());
	User u = new User(ce.getUserId().getId(), ce.getUserId().getName(), ce.getUserId().getPassword(), r);
	return new Consumer(ce.getId(), ce.getDate(), ce.getPlace(), ce.isClosed(), u);
    }
    
    public static User UserEntityToUser(UserEntity entity) {
	Role rDto = RoleEntityToRole(entity.getRole());
	return new User(entity.getId(), entity.getName(), entity.getPassword(), rDto);
    }
    
    public static Role RoleEntityToRole(RoleEntity entity) {
	return new Role(entity.getId(), entity.getRoleName());
    }
    
    public static Drink DrinkEntityToDrink(DrinkEntity de) {
	return new Drink(de.getId(), de.getName(), de.getIngredients(), de.getPrice());
    }
    
    public static Order OrderEntityToOrder(OrderEntity oe) {
  	Role rd = new Role(oe.getConsumerId().getUserId().getRole().getId(), oe.getConsumerId().getUserId().getRole().getRoleName());
  	User ud = new User(oe.getConsumerId().getUserId().getId(), oe.getConsumerId().getUserId().getName(), oe.getConsumerId().getUserId().getPassword(), rd);
  	Consumer cd = new Consumer(oe.getConsumerId().getId(), oe.getConsumerId().getDate(), oe.getConsumerId().getPlace(), oe.getConsumerId().isClosed(), ud);
  	Map<Drink, Integer> drinksMap = new HashMap<Drink, Integer>(oe.getDrinks().size());
  	for(Entry<DrinkEntity, Integer> e : oe.getDrinks().entrySet()) {
  	    drinksMap.put(DBUtils.DrinkEntityToDrink(e.getKey()), e.getValue());
  	}
  	return new Order(oe.getId(), cd, drinksMap, oe.getStatus(), oe.getBill());
   }
    
    public static DrinkEntity DrinkToDrinkEntity(Drink dto) {
	DrinkEntity de = new DrinkEntity();
	de.setId(dto.getId());
	de.setIngredients(dto.getIngredients());
	de.setName(dto.getName());
	de.setPrice(dto.getPrice());
	return de;
    }
    
//////////////// methods for DTOs and Request/Response objects ///////////////////////
    
    
//    public static UserDTO UserEntityToUserDto(UserEntity entity) {
//	RoleDTO rDto = RoleEntityToRoleDto(entity.getRole());
//	return new UserDTO(entity.getId(), entity.getName(), entity.getPassword(), rDto);
//    }
//    
//    public static RoleDTO RoleEntityToRoleDto(RoleEntity entity) {
//	return new RoleDTO(entity.getId(), entity.getRoleName());
//    }
//
//    public static DrinkDTO DrinkEntityToDrinkDto(DrinkEntity de) {
//	return new DrinkDTO(de.getId(), de.getName(), de.getIngredients(), de.getPrice());
//    }
//
//    public static DrinkEntity DrinkDtoToDrinkEntity(DrinkDTO dto) {
//	DrinkEntity de = new DrinkEntity();
//	de.setId(dto.getId());
//	de.setIngredients(dto.getIngredients());
//	de.setName(dto.getName());
//	de.setPrice(dto.getPrice());
//	return de;
//    }
//    
//    public static ConsumerDTO ConsumerEntityToConsumerDTO(ConsumerEntity ce) {
//	RoleDTO r = new RoleDTO(ce.getUserId().getRole().getId(), ce.getUserId().getRole().getRoleName());
//	UserDTO u = new UserDTO(ce.getUserId().getId(), ce.getUserId().getName(), ce.getUserId().getPassword(), r);
//	return new ConsumerDTO(ce.getId(), ce.getDate(), ce.getPlace(), u);
//    }
//
//
//    
//    public static OrderDTO OrderEntityToOrderDto(OrderEntity oe) {
//	RoleDTO rd = new RoleDTO(oe.getConsumerId().getUserId().getRole().getId(), oe.getConsumerId().getUserId().getRole().getRoleName());
//	UserDTO ud = new UserDTO(oe.getConsumerId().getUserId().getId(), oe.getConsumerId().getUserId().getName(), oe.getConsumerId().getUserId().getPassword(), rd);
//	ConsumerDTO cd = new ConsumerDTO(oe.getConsumerId().getId(), oe.getConsumerId().getDate(), oe.getConsumerId().getPlace(), ud);
//	Map<DrinkDTO, Integer> drinksMap = new HashMap<DrinkDTO, Integer>(oe.getDrinks().size());
//	for(Entry<DrinkEntity, Integer> e : oe.getDrinks().entrySet()) {
//	    drinksMap.put(DBUtils.DrinkEntityToDrinkDto(e.getKey()), e.getValue());
//	}
//	return new OrderDTO(oe.getId(), cd, drinksMap, oe.getStatus(), oe.getBill());
//    }

}
