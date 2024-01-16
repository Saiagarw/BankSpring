package com.sapient.app.Dao;

import org.springframework.stereotype.Repository;

import com.sapient.app.model.Customer;

@Repository
public interface customerDao{
	
	public Customer depositAmount(String email, float amount);
	public Customer withdrawAmount(String email, float amount);
}
