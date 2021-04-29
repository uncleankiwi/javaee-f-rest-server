package org.uc.service;

import org.uc.entity.Player;
import org.uc.entity.Team;
import org.uc.entity.TeamUpdateDto;
import org.uc.exception.*;

import java.util.List;

public interface TeamService {
	void createTeam(Team team) throws InvalidLeagueIdException, LeagueNotFoundException;
	void addPlayerToTeam(Long id, Player player)
			throws InvalidTeamIdException, TeamNotFoundException, InvalidPlayerIdException, PlayerNotFoundException;
	Team updateTeamName(TeamUpdateDto dto, Team teamToUpdate);
	Team getById(Long id)  throws InvalidTeamIdException, TeamNotFoundException;
	List<Team> getTeamList();
}
