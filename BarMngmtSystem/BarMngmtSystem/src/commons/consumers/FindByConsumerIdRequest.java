package commons.consumers;

public class FindByConsumerIdRequest {
    
    private Integer id;
    
    public FindByConsumerIdRequest(Integer id) {
	this.setId(id);
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

}
