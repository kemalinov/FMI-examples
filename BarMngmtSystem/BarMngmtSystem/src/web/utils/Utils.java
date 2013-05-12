package web.utils;

import commons.dtos.ConsumerDTO;
import commons.dtos.DrinkDTO;
import commons.dtos.OrderDTO;
import commons.dtos.RoleDTO;
import commons.dtos.UserDTO;

import web.pojos.Consumer;
import web.pojos.Drink;
import web.pojos.Order;
import web.pojos.Role;
import web.pojos.User;

public class Utils {
    
    public static User UserDtoToUser(UserDTO dto) {
	return new User(dto.getId(), dto.getName(), dto.getPassword(), RoleDtoToRole(dto.getRoleDto()));
    }
    
    public static Role RoleDtoToRole(RoleDTO dto) {
	return new Role(dto.getId(), dto.getRole());
    }

    public static Drink DrinkDtoToDrink(DrinkDTO dto) {
	return new Drink(dto.getId(), dto.getName(), dto.getIngredients(), dto.getPrice());
    }
    
    public static Consumer ConsumerDtoToConsumer(ConsumerDTO dto) {
	return new Consumer(dto.getConsumerId(), dto.getDate(), dto.getPlace(), UserDtoToUser(dto.getUserId())); 
    }
    
    public static RoleDTO RoleToRoleDto(Role r) {
	return new RoleDTO(r.getId(), r.getRole());
    }
    
    public static UserDTO UserToUserDto(User u) {
	return new UserDTO(u.getId(), u.getName(), u.getPassword(), RoleToRoleDto(u.getRole()));
    } 
    
    public static ConsumerDTO ConsumerToConsumerDto(Consumer c) {
	return new ConsumerDTO(c.getId(), c.getDate(), c.getPlace(), UserToUserDto(c.getUserId())); 
    }
    
    public static Order OrderDtoToOrder(OrderDTO dto) {
	return new Order(dto.getId(), ConsumerDtoToConsumer(dto.getConsumerId()), dto.getDrinks(), dto.getStatus(), dto.getBill());
    }
    
    public static OrderDTO OrderToOrderDto(Order o) {
	return new OrderDTO(o.getId(),  ConsumerToConsumerDto(o.getConsumerId()), o.getDrinks(), o.getStatus(), o.getBill());
    }

}
