package com.sapient.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.app.model.Customer;

public interface customerRepository extends JpaRepository<Customer, Integer>{

	Customer getCustomerById(int id);

//	Customer getCustomerByCustomerAadhar(String aadhar);

	Customer getCustomerByCustomerEmail(String email);

	Customer findByCustomerEmail(String username);

//	Customer findByEmail(String username);
}
