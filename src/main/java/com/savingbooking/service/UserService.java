package com.savingbooking.service;

import com.savingbooking.generic.GenericService;
import com.savingbooking.model.User;

public interface UserService extends GenericService<User> {

	boolean authenticate(String email, String password);
	
	User findByEmail(String email);
	
}
