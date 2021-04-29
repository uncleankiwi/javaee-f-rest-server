package org.uc.rest;

import org.uc.entity.League;
import org.uc.entity.Team;
import org.uc.exception.*;
import org.uc.service.LeagueService;
import org.uc.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/leagues")
public class LeagueResource {

	@EJB
	LeagueService leagueService;

	@EJB
	TeamService teamService;

	@GET
	@Path("/ping")
	public Response ping() {
		return ResponseFactory.ok("League service is working");
	}

	@POST
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	public Response createLeague(League league) {
		leagueService.createLeague(league);
		return ResponseFactory.created(league);
	}

	@PUT
	@Path("{id}")
	@Consumes({APPLICATION_JSON})
	@Produces(TEXT_PLAIN)
	public Response addTeamToLeague(@PathParam("id") long id, Team team) {
		League league = null;
		Team teamToAdd;
		try {
			league = leagueService.getById(id);
			teamToAdd = teamService.getById(team.getId());
			leagueService.addTeamToLeague(league, teamToAdd);
		} catch (InvalidLeagueIdException | LeagueNotFoundException | InvalidTeamIdException | TeamNotFoundException e) {
			e.printStackTrace();
		}

		return ResponseFactory.ok("Added team " + team + " to " + league);
	}

	@GET
	@Produces({APPLICATION_JSON})
	public Response getAllLeagues() {
		return ResponseFactory.ok(leagueService.getLeagueList());
	}
}
