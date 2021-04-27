package com.kovunov.service;

import com.kovunov.entity.League;
import com.kovunov.entity.Team;

import java.util.List;

public interface LeagueService {
	void createLeague(League league);
	void addTeamToLeague(League league, Team team);
	League getById(Long id);
	List<League> getLeagueList();
}
