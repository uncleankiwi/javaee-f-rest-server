package org.uc.exception;

public class InvalidLeagueIdException extends Exception{
	public InvalidLeagueIdException(Long id) {
		super(id + " is not a valid league id.");
	}
}
