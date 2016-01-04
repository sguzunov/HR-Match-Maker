package webresources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/profiles/{profileid}")
public class ProfileResource {

	@GET
	@Produces("application/json")
	public Response getJSON(@PathParam("profileid") String profileid) {
		return null;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateProfile(@PathParam("profileid") String profileid, String representation) {
		return null;
	}
}
