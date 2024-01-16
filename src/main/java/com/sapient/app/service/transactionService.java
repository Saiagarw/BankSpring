package com.sapient.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.app.model.Transaction;

@Service
public interface transactionService {
	
	public void openFd(Transaction transaction,int customerId);
	public void applyLoan(Transaction transaction,int customerId);
	public void depositAmount(Transaction transaction,String email,float amount);
	public void withdrawAmount(Transaction transaction,String email,float amount);
	public List<Transaction> getTransactionHistory(String email);
}
