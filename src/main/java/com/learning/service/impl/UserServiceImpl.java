package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.entity.User;
import com.learning.exceptions.AlreadyExistsException;
import com.learning.exceptions.IdNotFoundException;
import com.learning.repository.UserRepo;
import com.learning.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	@Transactional(rollbackFor = AlreadyExistsException.class)
	public User addUser(User register) throws AlreadyExistsException {
		// TODO Auto-generated method stub
		if(userRepo.existsByEmail(register.getEmail())) {
			throw new AlreadyExistsException("This record already exists");
		}
		return userRepo.save(register);
	}

	@Override
	public Optional<List<User>> getAllUsers() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepo.findAll());
	}

	@Override
	public Optional<User> getUserById(Long id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> optional = userRepo.findById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("Sorry user with "+ id +" not found");
		}
		return optional;
	}

	@Override
	public User updateUser(User register, Long id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		if (userRepo.findById(id).isEmpty()) {
			throw new IdNotFoundException("Sorry user with "+ id +" not found");
		}
		return userRepo.save(register);
	}

	@Override
	@Transactional(rollbackFor = IdNotFoundException.class)
	public String deleteUser(Long id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> optional = this.getUserById(id);
		if (optional.isEmpty())
			throw new IdNotFoundException("Sorry user with "+ id +" not found");
		else {
			userRepo.deleteById(id);
			return "Success";
		}
	}

}