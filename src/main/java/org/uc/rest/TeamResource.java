package org.uc.rest;

import org.uc.entity.Player;
import org.uc.entity.Team;
import org.uc.entity.TeamUpdateDto;
import org.uc.exception.*;
import org.uc.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/teams")
public class TeamResource {
	@EJB
	private TeamService teamService;

	@GET
	@Path("/ping")
	public Response ping() {
		return ResponseFactory.ok("Team service is working");
	}

	@POST
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	public Response createTeam(Team team) {
		try {
			teamService.createTeam(team);
		} catch (InvalidLeagueIdException | LeagueNotFoundException e) {
			return ResponseFactory.badRequest(e.getMessage());
		}
		return ResponseFactory.created(team);
	}

	@GET
	@Produces({APPLICATION_JSON})
	public Response getAllTeams() {
		return ResponseFactory.ok(teamService.getTeamList());
	}

	@PUT
	@Path("{id}")
	@Consumes({APPLICATION_JSON})
	@Produces(TEXT_PLAIN)
	public Response addPlayerToTeam(@PathParam("id") Long id, Player player) {
		try {
			teamService.addPlayerToTeam(id, player);
		} catch (InvalidTeamIdException | TeamNotFoundException | InvalidPlayerIdException | PlayerNotFoundException e) {
			return ResponseFactory.badRequest(e.getMessage());
		}
		return ResponseFactory.ok("Added player id " + player.getId() + " to team id " + id);
	}

	@PUT
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	public Response updateTeamName(TeamUpdateDto dto) {
		Team teamToUpdate;
		try {
			teamToUpdate = teamService.getById(dto.getId());
		} catch (InvalidTeamIdException | TeamNotFoundException e) {
			return ResponseFactory.badRequest(e.getMessage());
		}
		return ResponseFactory.ok(teamService.updateTeamName(dto, teamToUpdate));
	}
}
