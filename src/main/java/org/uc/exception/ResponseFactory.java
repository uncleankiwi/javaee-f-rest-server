package org.uc.exception;

import javax.ws.rs.core.Response;

public class ResponseFactory {
	public static final String INVALID_TEAM_ID = "{\"Error\":\"Please provide a valid team id\"}";

	public static Response invalidTeamId(Long teamId) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"Error\":\"No team with id " + teamId + " exists\"}")
				.build();
	}

	public static Response badRequest(Object o) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"Error\":\"" + o +"}")
				.build();
	}

	public static Response ok(Object o) {
		return Response.ok().entity(o).build();
	}
}
