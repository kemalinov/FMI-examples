package constants;

public enum OrderStatus {

    PENDING("pending"), ACCEPTED("accepted"), DONE("done"), OVERDUE("overdue"); 
    
    private String name;
    
    private OrderStatus(String name){
	this.name  = name;
    }

    public String getName() {
	return name;
    }

}
