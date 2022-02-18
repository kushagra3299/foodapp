package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.User;
import com.learning.exceptions.AlreadyExistsException;
import com.learning.exceptions.IdNotFoundException;

public interface UserService {
	
	public User addUser(User user) throws AlreadyExistsException;
	public Optional<List<User>> getAllUsers();
	public Optional<User> getUserById(Long id) throws IdNotFoundException;
	public User updateUser(User user, Long id) throws IdNotFoundException;
	public String deleteUser(Long id) throws IdNotFoundException;

}