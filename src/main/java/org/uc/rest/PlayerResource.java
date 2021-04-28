package org.uc.rest;

import org.uc.entity.Player;
import org.uc.entity.PlayerUpdateDto;
import org.uc.entity.Team;
import org.uc.service.PlayerService;
import org.uc.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/players")
public class PlayerResource {

    @EJB
    private PlayerService playerService;

    @EJB
    private TeamService teamService;

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
        //no valid team id is indicated
        if (team.getId() == null || team.getId() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"Error\":\"Please provide a valid team id\"}")
                    .build();
        }

        //team doesn't exist
        Team dbTeam =  teamService.getById(team.getId());
        if (dbTeam == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"Error\":\"No team with id " + team.getId() + " exists\"}")
                    .build();
        }

        return Response.ok()
                .entity(playerService.getPlayerListByTeam(team))
                .build();
    }

    @GET
    @Produces({APPLICATION_JSON})
    public Response getAllPlayers() {
        return Response.ok().entity(playerService.getPlayerList()).build();
    }

    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updatePlayer(PlayerUpdateDto updateDto) {
        if (updateDto.getId() == null || updateDto.getId() == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"Please provide correct id\"\n" +
                            "}").build();
        }
        Player playerToUpdate = playerService.getById(updateDto.getId());
        if (playerToUpdate == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"No such player\"\n" +
                            "}").build();
        }
        return Response.ok().entity(playerService.updatePlayer(updateDto, playerToUpdate)).build();
    }

    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createPlayer(Player player) {
        playerService.addToList(player);
        return Response.status(Response.Status.CREATED).entity(player).build();
    }
}
