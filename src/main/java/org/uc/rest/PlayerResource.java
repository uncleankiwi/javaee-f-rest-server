package org.uc.rest;

import org.uc.entity.Player;
import org.uc.entity.PlayerUpdateDto;
import org.uc.entity.Team;
import org.uc.exception.*;
import org.uc.service.PlayerService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/players")
public class PlayerResource {

    @EJB
    private PlayerService playerService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Player service is working").build();
    }

    @DELETE
    @Path("{id}")
    @Produces(TEXT_PLAIN)
    public Response deletePlayer(@PathParam("id") long id) {
        playerService.removeFromList(playerService.getById(id));
        return Response.ok().entity("Player " + id + " deleted").build();
    }


    @GET
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response getAllPlayersByTeam(Team team) {
        List<Player> players;
        try {
            players = playerService.getPlayerListByTeam(team);
        } catch (InvalidTeamIdException | TeamNotFoundException e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
        return ResponseFactory.ok(players);
    }

    @GET
    @Produces({APPLICATION_JSON})
    public Response getAllPlayers() {
        return ResponseFactory.ok(playerService.getPlayerList());
    }

    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updatePlayer(PlayerUpdateDto updateDto) {
        Player player;
        try {
            player = playerService.updatePlayer(updateDto);
        }
        catch (InvalidPlayerIdException | PlayerNotFoundException e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
        return ResponseFactory.ok(player);
    }

    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createPlayer(Player player) {
        playerService.addToList(player);
        return ResponseFactory.created(player);
    }
}
