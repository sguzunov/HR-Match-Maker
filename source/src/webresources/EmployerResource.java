package webresources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import common.security.Secured;

@Path("/profiles/my-profile")
public class EmployerResource {

	@Secured
	@GET
	@Produces("application/json")
	public Response getJSON(@PathParam("profileid") String profileid) {
		return Response.ok().entity("{\"luck\":\"yes\"}").build();
	}

	@Secured
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateProfile(@PathParam("profileid") String profileid, String representation) {
		return null;
	}
}
