package commons;

import dtos.RoleDTO;

public class RoleResponseEntity {

    private RoleDTO role;
    
    public RoleResponseEntity(RoleDTO role) {
	this.role = role;
    }

    public RoleDTO getRole() {
	return role;
    }

    public void setRole(RoleDTO role) {
	this.role = role;
    }
}
