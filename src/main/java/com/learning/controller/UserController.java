package com.learning.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Address;
import com.learning.entity.User;
import com.learning.exceptions.AccountExistsException;
import com.learning.exceptions.IdNotFoundException;
import com.learning.service.AddressService;
import com.learning.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	AddressService addressService;

	@PostMapping(path = "/register")
	public ResponseEntity<?> addUser(@RequestBody Map<String, String> json) throws AccountExistsException {
		// address is considered as an seperate entity so, data from request is taken as map and instantiated object 
		Address address = addressService.addAddress(new Address(json.get("address")));
		User user = new User(json.get("email"), json.get("name"), json.get("password"), address);
		user = userService.addUser(user);
		json.put("id", String.valueOf(user.getId()));
		return ResponseEntity.status(201).body(json);

	}

	@PostMapping(path = "/users/authenticate")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		HashMap<String, String> resp = new HashMap<>();
		resp.put("message", userService.authenticateUser(user));

		if (resp.get("message").equals("success"))
			return ResponseEntity.status(200).body(resp);
		return ResponseEntity.status(403).body(resp);

	}

	@GetMapping(path = "/users")
	public ResponseEntity<?> getUsers() {
		return ResponseEntity.status(200).body(userService.getAllUsers());

	}

	@PutMapping(path = "/users/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable long userId, @RequestBody Map<String, String> json)
			throws IdNotFoundException {
		Address address = null;
		if (json.get("address") != null)
			address = addressService.addAddress(new Address(json.get("address")));
		User user = new User(json.get("email"), json.get("name"), json.get("password"), address);
		user.setId(userId);
		user = userService.updateUserById(userId, user);
		return ResponseEntity.status(200).body(user);
	}

	@GetMapping(path = "/users/{userId}")
	public ResponseEntity<?> getUser(@PathVariable long userId) throws IdNotFoundException {
		User user = userService.getUserById(userId);
		return ResponseEntity.status(200).body(user);
	}

	@DeleteMapping(path = "/users/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable long userId) throws IdNotFoundException {
		userService.deleteUserById(userId);
		HashMap<String, String> map = new HashMap<>();
		map.put("Message", "User deleted successfully");
		return ResponseEntity.status(200).body(map);
	}

}
