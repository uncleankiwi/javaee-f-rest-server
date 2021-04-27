package com.kovunov.service;

import com.kovunov.entity.Player;
import com.kovunov.entity.Team;
import com.kovunov.entity.TeamUpdateDto;

import java.util.List;

public interface TeamService {
	void createTeam(Team team);
	void addPlayerToTeam(Team team, Player player);
	Team updateTeamName(TeamUpdateDto dto, Team teamToUpdate);
	Team getById(Long id);
	List<Team> getTeamList();
}
