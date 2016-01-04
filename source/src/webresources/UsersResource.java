package webresources;

import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/users")
public class UsersResource {

	@GET
	@Produces("application/json")
	public Response getJSON() {
		return null;
	}

	@POST
	@Consumes("application/xml")
	@Produces("application/json")
	public Response createUser(String representation) {
		return null;
	}
}
