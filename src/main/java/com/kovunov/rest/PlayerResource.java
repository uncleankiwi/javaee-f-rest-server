package com.kovunov.rest;

import com.kovunov.entity.Player;
import com.kovunov.entity.PlayerUpdateDto;
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

	//delete by played id
	@DELETE
	@Path("/{id}")
	public Response deletePlayer(@PathParam("id") long id) {
		playerService.removeFromList(playerService.getById(id));
		return Response.ok().entity("Player " + id + " deleted").build();
	}

	//delete by player object
	@DELETE
	@Consumes({APPLICATION_JSON})
	@Path("/{id}")
	public Response deletePlayer(Player player) {
		playerService.removeFromList(player);
		return Response.ok().entity("Player " + player.getId() + " deleted").build();
	}

	@PUT
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	public Response updatePlayer(PlayerUpdateDto updateDto) {
		if (updateDto.getId() == null || updateDto.getId() == 0) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\": \"Please provide " +
							"correct id\"}")
					.build();
		}
		Player playerToUpdate = playerService.getById(updateDto.getId());
		if (playerToUpdate == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("No such player")
					.build();
		}
		return Response.ok()
				.entity(playerService.updatePlayer(updateDto, playerToUpdate))
				.build();
	}
}
