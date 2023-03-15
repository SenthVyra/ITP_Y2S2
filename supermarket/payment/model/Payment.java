package supermarket.payment.model;

import java.math.BigDecimal;

public class Payment {
	
	private int empID;
	private String name;
	private BigDecimal paid;
	private java.sql.Date date;
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPaid() {
		return paid;
	}
	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}

}
