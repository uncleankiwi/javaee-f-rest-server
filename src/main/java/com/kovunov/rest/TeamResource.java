package com.kovunov.rest;

import com.kovunov.entity.Player;
import com.kovunov.entity.Team;
import com.kovunov.service.PlayerService;
import com.kovunov.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/teams")
public class TeamResource {
	@EJB
	private PlayerService playerService;

	@EJB
	private TeamService teamService;

	@GET
	@Path("/ping")
	public Response ping() {
		return Response.ok().entity("Team service is working").build();
	}

	@POST
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	public Response createTeam(Team team) {
		teamService.createTeam(team);
		return Response.status(Response.Status.CREATED)
				.entity(team)
				.build();
	}

	@GET
	@Produces({APPLICATION_JSON})
	public Response getAllTeams() {
		return Response.ok()
				.entity(teamService.getTeamList())
				.build();
	}

	@PUT
	@Path("{id}")
	@Consumes({APPLICATION_JSON})
	@Produces(TEXT_PLAIN)
	public Response addPlayerToTeam(@PathParam("id") long id, Player player) {
		Team team = teamService.getById(id);
		Player playerToAdd = playerService.getById(id);
		teamService.addPlayerToTeam(team, playerToAdd);
		return Response.ok()
				.entity("Added player " + playerToAdd.getId() + " to team #" + team.getId() + " " + team.getName())
				.build();
	}
}
