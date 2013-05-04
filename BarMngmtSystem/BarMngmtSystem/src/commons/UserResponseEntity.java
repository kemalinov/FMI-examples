package commons;

import dtos.UserDTO;

public class UserResponseEntity {

    private UserDTO user;
    
    public UserResponseEntity(UserDTO role) {
	this.user = role;
    }

    public UserDTO getUser() {
	return user;
    }

    public void setUser(UserDTO role) {
	this.user = role;
    }
}
