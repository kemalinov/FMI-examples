package db.utils;

import db.entities.RoleEntity;
import db.entities.UserEntity;
import dtos.RoleDTO;
import dtos.UserDTO;

public class DBUtils {

    public static UserDTO UserEntityToUserDto(UserEntity entity) {
	RoleDTO rDto = RoleEntityToRoleDto(entity.getRole());
	return new UserDTO(entity.getId(), entity.getName(), entity.getPassword(), rDto);
    }
    
    public static RoleDTO RoleEntityToRoleDto(RoleEntity entity) {
	return new RoleDTO(entity.getId(), entity.getRoleName());
    }
    
    
}
