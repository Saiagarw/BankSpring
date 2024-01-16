package com.sapient.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sapient.app.model.Customer;
import com.sapient.app.model.User;
import com.sapient.app.repository.UserRepository;
import com.sapient.app.repository.customerRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

//	@Autowired
//	UserRepository userRepository;
	
	@Autowired
	customerRepository custRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	 	Customer customer= custRepository.findByCustomerEmail(username);
	 	return customer;
	}
}
