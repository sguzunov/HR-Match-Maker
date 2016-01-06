package http;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class HttpResponseProvider {
	private static final String INITIAL_BODY_CONTENT = "";

	private Response response;
	private Status status;
	private String body;

	public HttpResponseProvider(Status status) {
		this.status = status;
		this.body = INITIAL_BODY_CONTENT;
	}

	public void setResponseBody(String body) {
		this.body = body;
	}

	public Response getResponse() {
		this.generateResponse();
		return this.response;
	}

	private void generateResponse() {
		this.response = Response.status(this.status).entity(this.body).build();
	}
}
