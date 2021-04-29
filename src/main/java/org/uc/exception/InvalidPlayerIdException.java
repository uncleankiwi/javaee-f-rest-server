package org.uc.exception;

public class InvalidPlayerIdException extends Exception{
	public InvalidPlayerIdException(Long id) {
		super(id + " is not a valid player id.");
	}
}
