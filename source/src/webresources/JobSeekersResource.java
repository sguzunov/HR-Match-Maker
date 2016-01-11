package webresources;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import common.security.Secured;
import controllers.JobSeekerProfilesController;
import controllers.ProfilesController;
import http.HttpRequest;
import http.ResponseProviderFactory;
import persistence.contracts.UserProfilePersistence;
import persistence.factories.DaoFactory;
import transformers.contracts.ModelsTransformer;

@Path("/jobseekers")
public class JobSeekersResource {

	@Inject
	private ModelsTransformer modelsTransformer;

	@Inject
	private ResponseProviderFactory responseProviderFactory;

	@Inject
	private DaoFactory daoFactory;

	@GET
	@Produces("application/json")
	public Response getJSON() {
		UserProfilePersistence jobSeekersDao = this.daoFactory.getJobSeekerDao();
		ProfilesController profilesController = new JobSeekerProfilesController(jobSeekersDao, modelsTransformer,
				responseProviderFactory);
		Response response = profilesController.get();

		return response;
	}

	@Secured
	@POST
	@Produces("application/json")
	public Response create(@Context HttpServletRequest request) {
		UserProfilePersistence jobSeekerDao = this.daoFactory.getJobSeekerDao();
		ProfilesController profilesController = new JobSeekerProfilesController(jobSeekerDao, modelsTransformer,
				responseProviderFactory);
		HttpRequest httpRequest = new HttpRequest(request);
		Response response = profilesController.post(httpRequest);

		return response;
	}

	@GET
	@Produces("application/json")
	@Path("/{id}")
	public Response getJSON(@PathParam("id") Integer id) {
		UserProfilePersistence jobSeekersDao = this.daoFactory.getJobSeekerDao();
		ProfilesController profilesController = new JobSeekerProfilesController(jobSeekersDao, modelsTransformer,
				responseProviderFactory);
		Response response = profilesController.select(id);

		return response;
	}
}
