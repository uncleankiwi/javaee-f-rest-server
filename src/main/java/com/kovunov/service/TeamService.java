package com.kovunov.service;

import com.kovunov.entity.Player;
import com.kovunov.entity.Team;

public interface TeamService {
	void createTeam(Team team);
	void addPlayerToTeam(Player player);
	void updateTeamName(String name);
}
