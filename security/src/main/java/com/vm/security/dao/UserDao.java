package com.vm.security.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vm.security.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

	User findByUserEmail(String username);
	
}
