package web.utils;

import dtos.RoleDTO;
import dtos.UserDTO;
import web.pojos.Role;
import web.pojos.User;

public class Utils {
    
    public static User UserDtoToUser(UserDTO dto) {
	return new User(dto.getId(), dto.getName(), dto.getPassword(), RoleDtoToRole(dto.getRoleDto()));
    }
    
    public static Role RoleDtoToRole(RoleDTO dto) {
	return new Role(dto.getId(), dto.getRole());
    }

}
