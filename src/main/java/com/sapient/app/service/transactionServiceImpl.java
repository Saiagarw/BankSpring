package com.sapient.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.app.model.Customer;
import com.sapient.app.model.Transaction;
import com.sapient.app.repository.customerRepository;
import com.sapient.app.repository.transactionRepository;

@Service
public class transactionServiceImpl implements transactionService {

	@Autowired
	transactionRepository tranRepository;
	@Autowired
	customerRepository custRepositiory;
	
	@Override
	public void openFd(Transaction transaction,int customerId) {
		Customer localCustomer=custRepositiory.getById(customerId);
		transaction.setCustomer(localCustomer);
		tranRepository.save(transaction);
	}

	@Override
	public void applyLoan(Transaction transaction, int customerId) {
		Customer localCustomer=custRepositiory.getById(customerId);
		transaction.setCustomer(localCustomer);
		tranRepository.save(transaction);
	}

	@Override
	public void depositAmount(Transaction transaction, String email,float amount) {
//		@SuppressWarnings("deprecation")
		Customer localCustomer=custRepositiory.getCustomerByCustomerEmail(email);
		transaction.setCustomer(localCustomer);
		transaction.setTransactionType("deposit");
		transaction.setAmount(amount);
		transaction.setTransactionDate("10-01-2023");
		tranRepository.save(transaction);
	}

	@Override
	public void withdrawAmount(Transaction transaction,String email,float amount) {
		Customer localCustomer=custRepositiory.getCustomerByCustomerEmail(email);
		transaction.setCustomer(localCustomer);
		transaction.setTransactionType("withdraw");
		transaction.setAmount(amount);
		transaction.setTransactionDate("10-01-2023");
		tranRepository.save(transaction);
	}

	@Override
	public List<Transaction> getTransactionHistory(String email) {
		
		Customer cust = custRepositiory.findByCustomerEmail(email);
		return tranRepository.findByCustomer_Id(cust.getId());
	}

}
