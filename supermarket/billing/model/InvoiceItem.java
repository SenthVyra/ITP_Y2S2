package supermarket.billing.model;

import java.math.BigDecimal;

public class InvoiceItem {
   private String name;
   private int quantity;
   private BigDecimal price;
   private int discount;
   private int productID;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public BigDecimal getPrice() {
	return price;
}
public void setPrice(BigDecimal price) {
	this.price = price;
}
public int getDiscount() {
	return discount;
}
public void setDiscount(int discount) {
	this.discount = discount;
}
public int getProductID() {
	return productID;
}
public void setProductID(int invoiceID) {
	this.productID = invoiceID;
}
}
