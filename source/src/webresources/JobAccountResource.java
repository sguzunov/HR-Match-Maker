package webresources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/jobaccounts/{jobaccountid}")
public class JobAccountResource {

	@GET
	@Produces("application/json")
	public Response getJSON() {
		return null;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateJobAccount(String representation) {
		return null;
	}
}
