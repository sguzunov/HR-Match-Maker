package common.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import controllers.AuthenticationController;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("WORKS");
		// Get the HTTP Authorization header from the request
		String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// A null authorization header is incorrect
		if (token == null) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}

		try {
			validateToken(token);

		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			e.printStackTrace();
		}
	}

	private void validateToken(String token) throws Exception {
		boolean isAutorized = AuthenticationController.tokens.containsValue(token);
		if (!isAutorized) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}
	}
}