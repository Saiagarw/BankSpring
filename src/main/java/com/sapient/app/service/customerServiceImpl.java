package com.sapient.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sapient.app.Dao.customerDao;
import com.sapient.app.exception.serviceException;
import com.sapient.app.model.Customer;
import com.sapient.app.model.User;
import com.sapient.app.repository.customerRepository;

import jakarta.validation.constraints.Null;

@Service
public class customerServiceImpl implements customerService{
	
	@Autowired
	customerDao custDao;
	@Autowired
	customerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	
	@Override
	public Customer getCustomer(int id) {
		
		return customerRepository.getCustomerById(id);
	}

	@Override
	public Customer addCustomer(Customer customer) throws serviceException{
		
//		customerRepository.save(customer);
//		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		String email=customer.getCustomerEmail();
		Customer localCustomer=customerRepository.getCustomerByCustomerEmail(email);
		System.out.println(localCustomer);
		if(localCustomer!=null)
		{
			System.out.println("inside service addcustomer 1");
			throw new serviceException("601","Customer is already present with the given aadhar");
		}
		
		try {
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));
			System.out.println("inside service addcustomer 2");
			return customerRepository.save(customer);
		}
		catch (serviceException e) {
			System.out.println("inside service addcustomer 3");
			throw new serviceException(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e){
			System.out.println("inside service addcustomer 4");
			throw new serviceException("602","Something went wrong in service layer");
		}	
	}
	
	
	@Override
	public float depositAmount(String email, float amount) {
		
		Customer localCustomer = custDao.depositAmount(email,amount);
		customerRepository.save(localCustomer);	
		
		return localCustomer.getBalance();
	}


	@Override
	public float withdrawAmount(String email, float amount) {
		Customer localCustomer = custDao.withdrawAmount(email,amount);
		customerRepository.save(localCustomer);
		
		return localCustomer.getBalance();
	}


	@Override
	public void deleteCustomer(int id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Customer getCustomerByEmail(String aadhar) {
		
		return customerRepository.getCustomerByCustomerEmail(aadhar);
	}
	
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//	 	Customer cusotmer = customerRepository.findByEmail(username);
//	 	
//	 	return cusotmer;
//	}
}
