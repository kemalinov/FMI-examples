package commons.orders;

public class FindByOrderIdRequest {
    
    private Integer id;
    
    public FindByOrderIdRequest(Integer id) {
	this.setId(id);
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

}
