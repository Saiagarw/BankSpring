package com.sapient.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;
import com.sapient.app.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

//	Object findByEmail(String username);

	public User findByEmail(String username);
	
}
