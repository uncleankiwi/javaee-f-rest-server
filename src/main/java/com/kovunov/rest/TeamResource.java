package com.kovunov.rest;

import com.kovunov.entity.Team;
import com.kovunov.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/teams")
public class TeamResource {
	@EJB
	private TeamService teamService;

	@GET
	@Path("/ping")
	public Response ping() {
		return Response.ok().entity("Service is working").build();
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
}
