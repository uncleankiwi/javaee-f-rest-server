package org.uc.service;

import org.uc.entity.League;
import org.uc.entity.Team;
import org.uc.exception.InvalidLeagueIdException;
import org.uc.exception.InvalidTeamIdException;
import org.uc.exception.LeagueNotFoundException;
import org.uc.exception.TeamNotFoundException;

import java.util.List;

public interface LeagueService {
	void createLeague(League league);
	void addTeamToLeague(Long leagueId, Team team)
			throws InvalidLeagueIdException, LeagueNotFoundException, InvalidTeamIdException, TeamNotFoundException;
	League getById(Long id) throws InvalidLeagueIdException, LeagueNotFoundException;
	List<League> getLeagueList();
}
