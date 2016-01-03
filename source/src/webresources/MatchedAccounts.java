package webresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

// Change methods return type from String to Response.

@Path("/matched")
public class MatchedAccounts {

	@GET
	@Produces("application/json")
	public String getJSON() {
		return null;
	}

	@GET
	@Produces("application/xml")
	public String getXML() {
		return null;
	}
}
