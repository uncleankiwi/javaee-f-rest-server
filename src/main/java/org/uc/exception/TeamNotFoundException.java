package org.uc.exception;

public class TeamNotFoundException extends Exception {
	public TeamNotFoundException(Long id) {
		super("No team with id " + id + " exists.");
	}
}
