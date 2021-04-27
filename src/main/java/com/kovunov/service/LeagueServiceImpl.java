package com.kovunov.service;

import com.kovunov.entity.League;
import com.kovunov.entity.Team;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	}
}
