package org.uc.rest;

import javax.ws.rs.core.Response;

public class ResponseFactory {
	public static Response badRequest(Object o) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"Error\":\"" + o + "\"}")
				.build();
	}

	public static Response ok(Object o) {
		return Response.ok().entity(o).build();
	}

	public static Response created(Object o) {
		return Response.status(Response.Status.CREATED).entity(o).build();
	}
}
