package commons.users;

import commons.dtos.UserDTO;


public class PersistUserRequest {

    private UserDTO user;

    public PersistUserRequest(UserDTO userDto) {
	this.setUser(userDto);
    }

    public UserDTO getUser() {
	return user;
    }

    public void setUser(UserDTO user) {
	this.user = user;
    }

}
