package com.learning.exceptions;

public class AccountExistsException extends Exception {

	public AccountExistsException(String message) {
		super(message);
	}
}
