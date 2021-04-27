package com.kovunov.service;

import com.kovunov.entity.Player;
import com.kovunov.entity.Team;

import java.util.List;

public interface TeamService {
	void createTeam(Team team);
	void addPlayerToTeam(Player player);
	void updateTeamName(String name);
	Team getById(Long id);
	List<Team> getTeamList();
}
