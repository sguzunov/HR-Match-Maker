package webresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/jobaccounts/matched")
public class MatchedAccountsResource {

	@GET
	@Produces("application/json")
	public Response getJSON() {
		return null;
	}
}
