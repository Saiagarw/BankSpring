package com.sapient.app.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sapient.app.model.Customer;
import com.sapient.app.repository.customerRepository;

@Repository
public class customerDaoImpl implements customerDao{
	
	@Autowired
	customerRepository customerRepository;
	
	@Override
	public Customer depositAmount(String email, float amount)
	{
//		@SuppressWarnings("deprecation")
		Customer cust = customerRepository.getCustomerByCustomerEmail(email);
		cust.setBalance(cust.getBalance() + amount);
		return cust;
	}

	@Override
	public Customer withdrawAmount(String email, float amount) {
//		@SuppressWarnings("deprecation")
		Customer cust = customerRepository.getCustomerByCustomerEmail(email);
		cust.setBalance(cust.getBalance() - amount);
		return cust;
	}

	
}
