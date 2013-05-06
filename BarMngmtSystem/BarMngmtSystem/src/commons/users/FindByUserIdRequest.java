package commons.users;

public class FindByUserIdRequest {
    
    private Integer id;
    
    public FindByUserIdRequest(Integer id) {
	this.setId(id);
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }
    
    

}
