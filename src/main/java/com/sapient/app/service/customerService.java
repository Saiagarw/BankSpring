package com.sapient.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.app.model.Customer;

@Service
public interface customerService {
	
	public List<Customer> getCustomers();
	public Customer getCustomer(int id);
	public Customer addCustomer(Customer customer);
	public float depositAmount(String email,float amount);
	public float withdrawAmount(String email,float amount);
	public void deleteCustomer(int id);
	public Customer getCustomerByEmail(String aadhar);
}
