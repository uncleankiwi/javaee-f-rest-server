package org.uc.exception;

public class UsernameExistsException extends Exception {
	public UsernameExistsException(String username) {
		super("User with " + username + " already exists.");
	}
}

