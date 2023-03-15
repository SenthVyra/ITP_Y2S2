package supermarket.purchase.model;

import java.math.BigDecimal;

public class Purchase {
	
	private int purchaseID;
	private int distributorID;
	private int orderID;
	private BigDecimal amount;
	private java.sql.Timestamp dateTime;
	private String distributorName;
	public int getPurchaseID() {
		return purchaseID;
	}
	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}
	public int getDistributorID() {
		return distributorID;
	}
	public void setDistributorID(int distributorID) {
		this.distributorID = distributorID;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public java.sql.Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(java.sql.Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
}
