package commons;

public class FindUserByIdRequest {
    
    private Integer id;
    
    public FindUserByIdRequest(Integer id) {
	this.setId(id);
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }
    
    

}
