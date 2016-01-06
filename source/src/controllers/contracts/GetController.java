package controllers.contracts;

import javax.ws.rs.core.Response;

import http.HttpRequest;

public interface GetController {
	public Response get(HttpRequest request);
}
