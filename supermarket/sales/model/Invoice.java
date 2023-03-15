package supermarket.sales.model;

import java.math.BigDecimal;

public class Invoice {
    private int invoiceId;
    private BigDecimal total;
    private java.sql.Timestamp dateTime;
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public java.sql.Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(java.sql.Timestamp dateTime) {
		this.dateTime = dateTime;
	}
}
