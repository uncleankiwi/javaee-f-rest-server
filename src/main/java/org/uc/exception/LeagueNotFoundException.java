package org.uc.exception;

public class LeagueNotFoundException extends Exception {
	public LeagueNotFoundException(Long id) {
		super("No league with id " + id + " exists.");
	}
}
