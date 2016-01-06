package webresources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/users/{username}")
public class UserResource {

	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response getJSON(@PathParam("username") String username, @Context HttpServletRequest request) {
		return null;
	}

	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateUser(@PathParam("username") String username, String representation) {
		return null;
	}
}
