package commons.drinks;

public class DrinkIngredientsResponse {
    
    private String ingredients;
    
    public DrinkIngredientsResponse(String ingredients) {
	this.setIngredients(ingredients);
    }

    public String getIngredients() {
	return ingredients;
    }

    public void setIngredients(String ingredients) {
	this.ingredients = ingredients;
    }
    
}
