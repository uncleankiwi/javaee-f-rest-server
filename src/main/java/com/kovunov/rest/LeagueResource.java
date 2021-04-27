package com.kovunov.rest;

import com.kovunov.entity.League;
import com.kovunov.entity.Team;
import com.kovunov.service.LeagueService;
import com.kovunov.service.TeamService;

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

	@POST
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	public Response createLeague(League league) {
		leagueService.createLeague(league);
		return Response.status(Response.Status.CREATED).entity(league).build();
	}

	@PUT
	@Path("{id}")
	@Consumes({APPLICATION_JSON})
	@Produces(TEXT_PLAIN)
	public Response addTeamToLeague(@PathParam("id") long id, Team team) {
		League league = leagueService.getById(id);
		Team teamToAdd = teamService.getById(team.getId());
		leagueService.addTeamToLeague(league, teamToAdd);
		return Response.ok().entity("Added team " + team.getId() + " to " + league.getId()).build();
	}
}