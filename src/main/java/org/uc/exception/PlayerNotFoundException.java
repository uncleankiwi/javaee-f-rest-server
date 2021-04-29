package org.uc.exception;

public class PlayerNotFoundException extends Exception {
	public PlayerNotFoundException(Long id) {
		super("No player with id " + id + " exists.");
	}
}
