package webresources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/users")
public class UsersResource {

	@POST
	@Produces("application/json")
	public Response createUser(@Context HttpServletRequest request) {
		return null;
	}

	@PUT
	@Produces("application/json")
	public Response logInUser(@Context HttpServletRequest request) {
		return null;
	}

	@DELETE
	public Response logOutUser(@Context HttpServletRequest request) {
		return null;
	}
}
