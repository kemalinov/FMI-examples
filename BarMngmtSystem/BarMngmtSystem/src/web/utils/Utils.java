package web.utils;

import commons.dtos.DrinkDTO;
import commons.dtos.RoleDTO;
import commons.dtos.UserDTO;

import web.pojos.Drink;
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

}
