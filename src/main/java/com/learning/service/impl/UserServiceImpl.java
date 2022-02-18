package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.User;
import com.learning.exceptions.AccountExistsException;
import com.learning.exceptions.IdNotFoundException;
import com.learning.repository.UserRepository;
import com.learning.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User addUser(User user) throws AccountExistsException {
		if (userRepository.findByEmail(user.getEmail()).isPresent())
			throw new AccountExistsException("User exists!!");
		return (userRepository.save(user) != null) ? user : null;
	}

	@Override
	public String authenticateUser(User user) {
		boolean exists = userRepository.existsByEmailAndPassword(user.getEmail(), user.getPassword());
		return exists ? "success" : "fail";

	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) throws IdNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty())
			throw new IdNotFoundException("Sorry user with " + id + " not found!");
		return user.get();
	}

	@Override
	public User updateUserById(long id, User user) throws IdNotFoundException {
		Optional<User> existing = userRepository.findById(id);
		if (existing.isEmpty())
			throw new IdNotFoundException("Sorry user with " + user.getId() + " not found!");

		// user id shouldn't change so it shouldn't change
		user.setId(existing.get().getId());

		// filling the missing details with existing details as the save will overrides
		// the existing
		if (user.getAddress() == null)
			user.setAddress(existing.get().getAddress());
		if (user.getName() == null)
			user.setName(existing.get().getName());
		if (user.getPassword() == null)
			user.setPassword(existing.get().getPassword());
		if (user.getEmail() == null)
			user.setEmail(existing.get().getEmail());

		return userRepository.save(user);
	}

	@Override
	public String deleteUserById(long id) throws IdNotFoundException {
		if (!userRepository.existsById(id))
			throw new IdNotFoundException("Sorry user with " + id + " not found!");
		userRepository.deleteById(id);
		return "success";
	}

}
