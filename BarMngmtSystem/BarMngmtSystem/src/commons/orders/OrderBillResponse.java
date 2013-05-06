package commons.orders;

import java.math.BigDecimal;

public class OrderBillResponse {

    private BigDecimal bill;
    
    public OrderBillResponse(BigDecimal bill) {
	this.setBill(bill);
    }

    public BigDecimal getBill() {
	return bill;
    }

    public void setBill(BigDecimal bill) {
	this.bill = bill;
    }
}
