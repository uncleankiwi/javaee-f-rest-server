package org.uc.service;

import org.uc.entity.League;
import org.uc.entity.Team;
import org.uc.exception.InvalidLeagueIdException;
import org.uc.exception.InvalidTeamIdException;
import org.uc.exception.LeagueNotFoundException;
import org.uc.exception.TeamNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class LeagueServiceImpl implements LeagueService{

	@PersistenceContext
	private EntityManager em;

	@EJB
	private TeamService teamService;

	@Override
	public void createLeague(League league) {
		em.persist(league);
	}

	//assumes that league and team both exist
	@Override
	public void addTeamToLeague(Long leagueId, Team team)
			throws InvalidLeagueIdException, LeagueNotFoundException, InvalidTeamIdException, TeamNotFoundException {
		League league = getById(leagueId);
		Team teamToAdd = teamService.getById(team.getId());
		teamToAdd.setLeague(league);
		em.merge(teamToAdd);
	}

	@Override
	public League getById(Long id) throws InvalidLeagueIdException, LeagueNotFoundException {
		if (id == null || id <= 0) {
			throw new InvalidLeagueIdException(id);
		}

		//team doesn't exist
		League league =  em.find(League.class, id);
		if (league == null) {
			throw new LeagueNotFoundException(id);
		}
		return league;
	}

	@Override
	public List<League> getLeagueList() {
		return em.createNamedQuery("League.findAll", League.class)
				.getResultList();
	}
}
