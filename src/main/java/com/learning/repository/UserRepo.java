package com.learning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
//	Checking the email exists in the user table
	Boolean existsByEmail(String email);

}