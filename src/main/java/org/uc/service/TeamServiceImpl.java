package org.uc.service;

import org.uc.entity.League;
import org.uc.entity.Player;
import org.uc.entity.Team;
import org.uc.entity.TeamUpdateDto;
import org.uc.exception.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TeamServiceImpl implements TeamService {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private LeagueService leagueService;

	@EJB
	private PlayerService playerService;

	@Override
	public void createTeam(Team team) throws InvalidLeagueIdException, LeagueNotFoundException {
		if (team.getLeague() != null) {
			League league = leagueService.getById(team.getLeague().getId());
			team.setLeague(league);
		}
		em.persist(team);
	}

	@Override
	public List<Team> getTeamList() {
		return em.createNamedQuery("Team.findAll", Team.class)
				.getResultList();
	}

	//assumes that team and player exist
	@Override
	public void addPlayerToTeam(Long teamId, Player player)
			throws InvalidTeamIdException, TeamNotFoundException, InvalidPlayerIdException, PlayerNotFoundException {
		Team team = getById(teamId);
		Player playerToAdd = playerService.getById(player.getId());
		playerToAdd.setTeam(team);
		em.merge(playerToAdd);
	}

	@Override
	public Team updateTeamName(TeamUpdateDto dto, Team teamToUpdate) {
		if (dto.getName() != null) {
			teamToUpdate.setName(dto.getName());
		}
		return em.merge(teamToUpdate);
	}

	@Override
	public Team getById(Long id) throws InvalidTeamIdException, TeamNotFoundException {
		if (id == null || id <= 0) {
			throw new InvalidTeamIdException(id);
		}

		//team doesn't exist
		Team team =  em.find(Team.class, id);
		if (team == null) {
			throw new TeamNotFoundException(id);
		}
		return team;
	}
}
