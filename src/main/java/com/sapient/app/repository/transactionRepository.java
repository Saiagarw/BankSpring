package com.sapient.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.app.model.Transaction;

public interface transactionRepository extends JpaRepository<Transaction,Integer>{
	List<Transaction> findByCustomer_Id(int customerId);
}
