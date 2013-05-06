package commons.drinks;

import java.math.BigDecimal;

public class DrinkPriceResponse {
    
    private BigDecimal price;

    public DrinkPriceResponse(BigDecimal price) {
	this.setPrice(price);
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

}
