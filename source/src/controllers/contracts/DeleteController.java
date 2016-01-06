package controllers.contracts;

import javax.ws.rs.core.Response;

import http.HttpRequest;

public interface DeleteController {
	public Response delete(HttpRequest request);
}
