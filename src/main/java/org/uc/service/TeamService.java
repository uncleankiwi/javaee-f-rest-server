package org.uc.service;

import org.uc.entity.Player;
import org.uc.entity.Team;
import org.uc.entity.TeamUpdateDto;

import java.util.List;

public interface TeamService {
	void createTeam(Team team);
	void addPlayerToTeam(Team team, Player player);
	Team updateTeamName(TeamUpdateDto dto, Team teamToUpdate);
	Team getById(Long id);
	List<Team> getTeamList();
}
