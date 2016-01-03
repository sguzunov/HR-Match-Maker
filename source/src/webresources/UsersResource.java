package webresources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/users")
public class UsersResource {

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

	@POST
	@Consumes("application/json")
	public void createUser(String representation) {

	}
}
