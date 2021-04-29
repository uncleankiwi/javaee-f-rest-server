package org.uc.service;

import org.uc.entity.League;
import org.uc.entity.Team;
import org.uc.exception.InvalidLeagueIdException;
import org.uc.exception.LeagueNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class LeagueServiceImpl implements LeagueService{

	@PersistenceContext
	private EntityManager em;

	@Override
	public void createLeague(League league) {
		em.persist(league);
	}

	//assumes that league and team both exist
	@Override
	public void addTeamToLeague(League league, Team team) {
		team.setLeague(league);
		em.merge(team);
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
