package org.uc.service;

import org.uc.entity.League;
import org.uc.entity.Team;

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

	@Override
	public void addTeamToLeague(League league, Team team) {
		team.setLeague(league);
		em.merge(team);
	}

	@Override
	public League getById(Long id) {
		return em.find(League.class, id);
	}

	@Override
	public List<League> getLeagueList() {
		return em.createNamedQuery("League.findAll", League.class)
				.getResultList();
	}
}