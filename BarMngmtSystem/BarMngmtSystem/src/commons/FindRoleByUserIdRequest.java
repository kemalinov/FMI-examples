package commons;

public class FindRoleByUserIdRequest {
    
    private Integer id;
    
    public FindRoleByUserIdRequest(Integer id) {
	this.setId(id);
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }
    
    

}
