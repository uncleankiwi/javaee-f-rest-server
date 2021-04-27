package com.kovunov.service;

import com.kovunov.entity.League;
import com.kovunov.entity.Team;

public interface LeagueService {
	void createLeague(League league);
	void addTeamToLeague(League league, Team team);
}
