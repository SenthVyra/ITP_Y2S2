package supermarket.product.model;
import java.math.BigDecimal;

public class Product {
   private String name;
   private String description;
   private BigDecimal price;
   private String category;
   private String image;
   private int productID;
   private String barcode;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public BigDecimal getPrice() {
	return price;
}
public void setPrice(BigDecimal price) {
	this.price = price;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public int getProductID() {
	return productID;
}
public void setProductID(int productID) {
	this.productID = productID;
}
public String getBarcode() {
	return barcode;
}
public void setBarcode(String barcode) {
	this.barcode = barcode;
}
}
