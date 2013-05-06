package commons.users;

import java.util.List;

import commons.dtos.UserDTO;


public class UserListResponse {

    private List<UserDTO> users;

    public UserListResponse(List<UserDTO> users) {
	this.users = users;
    }

    public List<UserDTO> getUsers() {
	return users;
    }

    public void setUsers(List<UserDTO> users) {
	this.users = users;
    }

}
