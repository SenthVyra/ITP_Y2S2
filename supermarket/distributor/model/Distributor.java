package supermarket.distributor.model;

public class Distributor {

	private String distributorName;
	private String address;
	private String phoneNumber;
	private String email;
	private int distributorID;
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDistributorID() {
		return distributorID;
	}
	public void setDistributorID(int distributorID) {
		this.distributorID = distributorID;
	}
}
