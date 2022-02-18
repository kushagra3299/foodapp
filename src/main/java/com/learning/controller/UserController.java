package com.learning.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.User;
import com.learning.exceptions.IdNotFoundException;
import com.learning.payload.response.MessageResponse;
import com.learning.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	GET request for retrieving all users
	@GetMapping("/")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getAllUser() {
		Optional<List<User>> optional = userService.getAllUsers();
		if (optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("No record found"));
		}
		return ResponseEntity.ok(optional.get());
	}
	
//	GET request for retrieving user by id
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) throws IdNotFoundException {
		Optional<User> optional = userService.getUserById(id);
		return ResponseEntity.ok(optional.get());
	}
	
//	PUT request for updating user by id
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) throws IdNotFoundException {
		User result = userService.updateUser(user, id);
		return ResponseEntity.status(200).body(result);
	}
	
//	DELETE request for deleting user by id
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) throws IdNotFoundException {
		userService.deleteUser(id);
		return ResponseEntity.status(200).body(new MessageResponse("User Deleted Successfully"));
	}

}