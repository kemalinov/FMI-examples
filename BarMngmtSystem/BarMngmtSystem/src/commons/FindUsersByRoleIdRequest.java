package commons;

public class FindUsersByRoleIdRequest {
    
    private Integer id;
    
    public FindUsersByRoleIdRequest(Integer id) {
	this.setId(id);
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }
    
    

}
