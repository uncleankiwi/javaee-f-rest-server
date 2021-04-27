package com.kovunov.rest;

import com.kovunov.entity.League;
import com.kovunov.service.LeagueService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/leagues")
public class LeagueResource {

	@EJB
	LeagueService leagueService;

	@POST
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	public Response createLeague(League league) {
		leagueService.createLeague(league);
		return Response.status(Response.Status.CREATED).entity(league).build();
	}
}
