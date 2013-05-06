package commons.orders;

public class OrderCountResponse {

    private Integer count;
    
    public OrderCountResponse(Integer count) {
	this.setCount(count);
    }

    public Integer getCount() {
	return count;
    }

    public void setCount(Integer count) {
	this.count = count;
    }
}
