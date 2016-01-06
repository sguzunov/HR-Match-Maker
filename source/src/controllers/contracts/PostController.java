package controllers.contracts;

import javax.ws.rs.core.Response;

import http.HttpRequest;

public interface PostController {
	public Response post(HttpRequest request);
}
