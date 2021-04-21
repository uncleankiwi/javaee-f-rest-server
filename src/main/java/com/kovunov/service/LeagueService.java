package com.kovunov.service;

import com.kovunov.entity.Team;

public interface LeagueService {
	void createLeague();
	void addTeamToLeague(Team team);
}
