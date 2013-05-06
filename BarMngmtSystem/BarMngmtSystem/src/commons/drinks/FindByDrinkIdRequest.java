package commons.drinks;

public class FindByDrinkIdRequest {
    
    private Integer id;
    
    public FindByDrinkIdRequest(Integer drinkId) {
	this.setId(drinkId);
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

}
