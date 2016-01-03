package webresources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/user")
public class UserResource {

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

	@PUT
	@Consumes("application/json")
	public void updateProfile() {

	}

	@POST
	@Consumes("application/json")
	public void createProfile() {

	}
}
