package org.uc.service;

import org.uc.entity.League;
import org.uc.entity.Team;
import org.uc.exception.InvalidLeagueIdException;
import org.uc.exception.LeagueNotFoundException;

import java.util.List;

public interface LeagueService {
	void createLeague(League league);
	void addTeamToLeague(League league, Team team);
	League getById(Long id) throws InvalidLeagueIdException, LeagueNotFoundException;
	List<League> getLeagueList();
}
