package com.learning.service;

import java.util.List;

import com.learning.entity.User;
import com.learning.exceptions.AccountExistsException;
import com.learning.exceptions.IdNotFoundException;

public interface UserService {
	
	User addUser(User user) throws AccountExistsException;

	String authenticateUser(User user);

	List<User> getAllUsers();
	
	User getUserById(long id) throws IdNotFoundException;
	
	String deleteUserById(long id) throws IdNotFoundException;

	User updateUserById(long id, User user) throws IdNotFoundException;
	

}
