package commons.roles;

import java.util.List;

import commons.dtos.RoleDTO;


public class RoleListResponse {
    
    private List<RoleDTO> roles;
    
    public RoleListResponse(List<RoleDTO> roles) {
	this.roles = roles;
    }

    public List<RoleDTO> getRoles() {
	return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
	this.roles = roles;
    }
    
    

}
