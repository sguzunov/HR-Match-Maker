package http;

import javax.ws.rs.core.Response.Status;

import enums.StatusCode;

public class ResponseProviderFactory {

	public HttpResponseProvider getResponseProvider(StatusCode statusCode) {
		HttpResponseProvider httpResponseProvider = null;
		switch (statusCode) {
		case OK: {
			httpResponseProvider = new HttpResponseProvider(Status.OK);
		}
			break;

		case BADREQUEST: {
			httpResponseProvider = new HttpResponseProvider(Status.BAD_REQUEST);
		}
			break;
		case UNAUTHORIZED: {
			httpResponseProvider = new HttpResponseProvider(Status.UNAUTHORIZED);
		}
			break;

		default:
			break;
		}

		return httpResponseProvider;
	}
}
