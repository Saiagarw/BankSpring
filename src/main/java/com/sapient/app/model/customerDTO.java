package com.sapient.app.model;

public class customerDTO {

	int id;
	String customerName,customerEmail,password,customerAadhar,customerPhone;
	float balance;
	
	
	
	 public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public String getCustomerEmail() {
		return customerEmail;
	}



	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getCustomerAadhar() {
		return customerAadhar;
	}



	public void setCustomerAadhar(String customerAadhar) {
		this.customerAadhar = customerAadhar;
	}



	public String getCustomerPhone() {
		return customerPhone;
	}



	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}



	public float getBalance() {
		return balance;
	}



	public void setBalance(float balance) {
		this.balance = balance;
	}



	public static customerDTO fromEntity(Customer customer) {
        customerDTO dto = new customerDTO();
        dto.setId(customer.getId());
        dto.setCustomerName(customer.getCustomerName());
        dto.setCustomerEmail(customer.getCustomerEmail());
        dto.setPassword(customer.getPassword());
        dto.setCustomerAadhar(customer.getCustomerAadhar());
        dto.setCustomerPhone(customer.getCustomerPhone());
        dto.setBalance(customer.getBalance());
        return dto;
    }
}
