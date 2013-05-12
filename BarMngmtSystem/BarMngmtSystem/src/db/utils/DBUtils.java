package db.utils;

import commons.dtos.ConsumerDTO;
import commons.dtos.DrinkDTO;
import commons.dtos.OrderDTO;
import commons.dtos.RoleDTO;
import commons.dtos.UserDTO;

import db.entities.ConsumerEntity;
import db.entities.DrinkEntity;
import db.entities.OrderEntity;
import db.entities.RoleEntity;
import db.entities.UserEntity;

public class DBUtils {

    public static UserDTO UserEntityToUserDto(UserEntity entity) {
	RoleDTO rDto = RoleEntityToRoleDto(entity.getRole());
	return new UserDTO(entity.getId(), entity.getName(), entity.getPassword(), rDto);
    }
    
    public static RoleDTO RoleEntityToRoleDto(RoleEntity entity) {
	return new RoleDTO(entity.getId(), entity.getRoleName());
    }

    public static DrinkDTO DrinkEntityToDrinkDto(DrinkEntity de) {
	return new DrinkDTO(de.getId(), de.getName(), de.getIngredients(), de.getPrice());
    }

    public static DrinkEntity DrinkDtoToDrinkEntity(DrinkDTO dto) {
	DrinkEntity de = new DrinkEntity();
	de.setId(dto.getId());
	de.setIngredients(dto.getIngredients());
	de.setName(dto.getName());
	de.setPrice(dto.getPrice());
	return de;
    }
    
    public static ConsumerDTO ConsumerEntityToConsumerDTO(ConsumerEntity ce) {
	RoleDTO r = new RoleDTO(ce.getUserId().getRole().getId(), ce.getUserId().getRole().getRoleName());
	UserDTO u = new UserDTO(ce.getUserId().getId(), ce.getUserId().getName(), ce.getUserId().getPassword(), r);
	return new ConsumerDTO(ce.getId(), ce.getDate(), ce.getPlace(), u);
    }

    public static OrderDTO OrderEntityToOrderDto(OrderEntity oe) {
	RoleDTO rd = new RoleDTO(oe.getConsumerId().getUserId().getRole().getId(), oe.getConsumerId().getUserId().getRole().getRoleName());
	UserDTO ud = new UserDTO(oe.getConsumerId().getUserId().getId(), oe.getConsumerId().getUserId().getName(), oe.getConsumerId().getUserId().getPassword(), rd);
	ConsumerDTO cd = new ConsumerDTO(oe.getConsumerId().getId(), oe.getConsumerId().getDate(), oe.getConsumerId().getPlace(), ud);
	return new OrderDTO(oe.getId(), cd, oe.getDrinks(), oe.getStatus(), oe.getBill());
    }

}
