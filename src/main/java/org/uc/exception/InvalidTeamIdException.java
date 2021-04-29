package org.uc.exception;

public class InvalidTeamIdException extends Exception{
	public InvalidTeamIdException(Long id) {
		super(id + " is not a valid player id.");
	}
}