package com.sapient.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.app.service.customerService;
import com.sapient.app.service.customerServiceImpl;
import com.sapient.app.service.transactionService;

import io.swagger.annotations.Authorization;

import com.sapient.app.exception.controllerException;
import com.sapient.app.exception.serviceException;
import com.sapient.app.model.Customer;
import com.sapient.app.model.Transaction;
import com.sapient.app.model.customerDTO;
import com.sapient.app.security.JwtHelper;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/customer")
public class BankController {
	
	@Autowired
	customerService custService;
	
	@Autowired
	transactionService tranService;
	
	@Autowired
	Transaction transaction;
	
	@Autowired
	JwtHelper jwtHelper;
	
	@GetMapping("/getAllCustomer")
	public List<Customer> getCustomers() {
		
		return custService.getCustomers();
	}
	
	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<customerDTO> getCustomer(@PathVariable ("id") int id)
	{
		Customer localCustomer= custService.getCustomer(id);
		if (localCustomer != null) 
		{
           customerDTO custDTO = customerDTO.fromEntity(localCustomer);
           return new ResponseEntity<>(custDTO, HttpStatus.OK);
        }
		else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
//	@PostMapping("/addCustomer")
//	public ResponseEntity<?> addCustomer(@RequestBody Customer customer)
//	{	try {
//			Customer cust=custService.addCustomer(customer);
//			System.out.println("inside controller 1");
//			return new ResponseEntity<>(customer,HttpStatus.OK);
//		}
//		catch(serviceException e)
//		{
//			System.out.println("inside controller 2");
//			controllerException ce=new controllerException(e.getErrorCode(),e.getErrorMessage());
//			return new ResponseEntity<>(ce,HttpStatus.NOT_FOUND);
//		}
//		catch (Exception e) {
//			System.out.println("inside controller 3");
//			controllerException ce=new controllerException("603","Something went wrong in controller");
//			return new ResponseEntity<>(ce,HttpStatus.NOT_FOUND);
//		}
//	}
//	@CrossOrigin(origins = "http://localhost:3000")
	
	
	@PutMapping("depositAmount/{email}/{amount}")
	public float depositAmount(@PathVariable ("email") String email,@PathVariable ("amount") float amount)
	{
//		String email=jwtHelper.getUsernameFromToken(authorizationToken);
		float curramount=custService.depositAmount(email,amount);
		tranService.depositAmount(transaction,email,amount);
		
		return curramount;
	}
	
	@PutMapping("withdrawAmount/{email}/{amount}")
	public float withdrawAmount(@PathVariable ("email") String email,@PathVariable ("amount") float amount)
	{
		float currAmount=custService.withdrawAmount(email,amount);
		tranService.withdrawAmount(transaction,email,amount);
		
		return currAmount;
	}
	
	@DeleteMapping("deleteCustomer/{id}")
	public void deleteCustomer(@PathVariable ("id") int id)
	{
		custService.deleteCustomer(id);
	}
	
	@PostMapping("/openFd/{id}")
	public void openFd(@RequestBody Transaction transaction,@PathVariable ("id") int customerId)
	{
		tranService.openFd(transaction,customerId);
	}
	
	@PostMapping("/applyLoan/{id}")
	public void applyLoan(@RequestBody Transaction transaction,@PathVariable ("id") int customerId)
	{
		tranService.applyLoan(transaction,customerId);
	}
	
	
	
	@GetMapping("getTransactionHistory/{email}")
	public List<Transaction> getTransactionHistory(@PathVariable ("email") String email)
	{
		return tranService.getTransactionHistory(email);
	}
}
	

