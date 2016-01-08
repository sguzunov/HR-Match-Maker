package webresources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/profiles")
public class EmployersResource {

	@GET
	@Produces("application/json")
	public Response getJSON() {
		return null;
	}

	@POST
	@Consumes("application/json")
	public Response createProfile(String representation) {
		return null;
	}
}
