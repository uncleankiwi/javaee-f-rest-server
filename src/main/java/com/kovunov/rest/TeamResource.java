package com.kovunov.rest;

import com.kovunov.entity.Player;
import com.kovunov.entity.PlayerUpdateDto;
import com.kovunov.entity.Team;
import com.kovunov.entity.TeamUpdateDto;
import com.kovunov.service.PlayerService;
import com.kovunov.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

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
		List<Team> teams = teamService.getTeamList();
		for (Team t : teams) {
			System.out.println(t.getName() + ":size " + t.getPlayerList().size());	//TODO rm
		}

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
				.entity("Added player " + playerToAdd + " to team " + team)
				.build();
	}

	@PUT
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	public Response updateTeamName(TeamUpdateDto dto) {
		if (dto.getId() == null || dto.getId() == 0) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\n" +
							"\t\"error\": \"Please provide correct id\"\n" +
							"}").build();
		}
		Team teamToUpdate = teamService.getById(dto.getId());
		if (teamToUpdate == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\n" +
							"\t\"error\": \"No such team\"\n" +
							"}").build();
		}
		return Response.ok().entity(teamService.updateTeamName(dto, teamToUpdate)).build();
	}
}
