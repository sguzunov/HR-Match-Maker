package common.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	private static final String MISSING_AUTH_HEADER_MESSAGE = "Authorization header must be provided";

	@Inject
	private Authentication authentication;

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

	private void validateToken(String authToken) throws Exception {
		boolean isAutorized = this.authentication.isValidAuthToken(authToken);
		if (!isAutorized) {
			throw new NotAuthorizedException(MISSING_AUTH_HEADER_MESSAGE);
		}
	}
}