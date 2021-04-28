package org.uc.service;

import org.uc.entity.League;
import org.uc.entity.Team;

import java.util.List;

public interface LeagueService {
	void createLeague(League league);
	void addTeamToLeague(League league, Team team);
	League getById(Long id);
	List<League> getLeagueList();
}
