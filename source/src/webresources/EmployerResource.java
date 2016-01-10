package webresources;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import controllers.EmployerProfileController;
import controllers.ProfilesController;
import http.ResponseProviderFactory;
import persistence.EmployerDao;
import persistence.contracts.UserProfilePersistence;
import persistence.sources.DataSource;
import persistence.sources.MySQLSource;
import transformers.JSONModelsTransformer;
import transformers.contracts.ModelsTransformer;

@Path("/employers/id")
public class EmployerResource {

	@GET
	@Produces("application/json")
	public Response getJSON(@PathParam("id") int id) {
		DataSource dataSource = new MySQLSource();
		UserProfilePersistence employerDao = new EmployerDao(dataSource);
		ModelsTransformer modelsTransformer = new JSONModelsTransformer();
		ResponseProviderFactory responseProviderFactory = new ResponseProviderFactory();
		ProfilesController profilesController = new EmployerProfileController(employerDao, modelsTransformer,
				responseProviderFactory);
		Response response = profilesController.get();

		return response;
	}

	@POST
	@Produces("application/json")
	public Response update(@Context HttpServletRequest request) {
		return null;
	}
}
