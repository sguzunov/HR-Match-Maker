package webresources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/users/{username}")
public class UserResource {

	@GET
	@Produces("application/json")
	public Response getJSON() {
		return null;
	}

	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateUser() {
		return null;
	}
}
