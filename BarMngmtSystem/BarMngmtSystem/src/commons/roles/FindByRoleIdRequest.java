package commons.roles;

public class FindByRoleIdRequest {
    
    private Integer id;
    
    public FindByRoleIdRequest(Integer id) {
	this.setId(id);
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }
    
    

}
