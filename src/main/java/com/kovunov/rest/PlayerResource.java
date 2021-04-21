package com.kovunov.rest;

import com.kovunov.entity.Player;
import com.kovunov.service.PlayerService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/players") //usually plural
public class PlayerResource {

	@EJB
	PlayerService playerService;

	@GET
	@Path("/ping")
	public Response ping() {
		return Response.ok().entity("Service is working").build();
	}

	@GET//path not needed because this is the default with no id param
	@Produces({APPLICATION_JSON})
	public Response getAllPlayers() {
		return Response.ok().entity(playerService.getPlayerList()).build();
	}

	@POST
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	public Response createPlayer(Player player) {
		playerService.addToList(player);
		return Response.status(Response.Status.CREATED).entity(player).build();

				//or Response.status(201)
	}

	@DELETE
	@Consumes({APPLICATION_JSON})
	@Path("/{id}")
	public Response deletePlayer(Player player) {
		playerService.removeFromList(player);
		return Response.ok().entity("Player " + player.getId() + " deleted").build();
	}
}
