package controllers.contracts;

import javax.ws.rs.core.Response;

import http.HttpRequest;

public interface PutController {
	public Response put(HttpRequest request);
}
