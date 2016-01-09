package webresources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import common.security.Secured;
import controllers.EmployerProfileController;
import controllers.JobSeekerProfileController;
import controllers.ProfilesController;
import http.HttpRequest;
import http.ResponseProviderFactory;
import persistence.JobSeekerDao;
import persistence.sources.DataSource;
import persistence.sources.MySQLSource;
import transformers.JSONModelsTransformer;
import transformers.contracts.ModelsTransformer;

@Path("/jobseekers")
public class JobSeekersResource {

	@GET
	@Produces("application/json")
	public Response getJSON() {
		DataSource dataSource = new MySQLSource();
		JobSeekerDao jobSeekersDao = new JobSeekerDao(dataSource);
		ModelsTransformer modelsTransformer = new JSONModelsTransformer();
		ResponseProviderFactory responseProviderFactory = new ResponseProviderFactory();
		ProfilesController profilesController = new JobSeekerProfileController(jobSeekersDao, modelsTransformer,
				responseProviderFactory);
		Response response = profilesController.get();

		return response;
	}

	@Secured
	@POST
	@Produces("application/json")
	public Response create(@Context HttpServletRequest request) {
		DataSource dataSource = new MySQLSource();
		JobSeekerDao jobSeekerDao = new JobSeekerDao(dataSource);
		ModelsTransformer modelsTransformer = new JSONModelsTransformer();
		ResponseProviderFactory responseProviderFactory = new ResponseProviderFactory();
		ProfilesController profilesController = new EmployerProfileController(jobSeekerDao, modelsTransformer,
				responseProviderFactory);
		HttpRequest httpRequest = new HttpRequest(request);

		Response response = profilesController.post(httpRequest);

		return response;
	}
}
