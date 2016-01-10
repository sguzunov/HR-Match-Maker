package webresources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import common.security.Secured;
import controllers.JobAccountController;
import controllers.JobCVsController;
import http.HttpRequest;
import http.ResponseProviderFactory;
import persistence.JobCvDao;
import persistence.contracts.JobAccountPersistence;
import persistence.sources.DataSource;
import persistence.sources.MySQLSource;
import transformers.JSONModelsTransformer;
import transformers.contracts.ModelsTransformer;

@Path("/jobcvs")
public class JobCVsResource {

	@GET
	@Produces("application/json")
	public Response getJSON() {
		DataSource dataSource = new MySQLSource();
		JobAccountPersistence jobAccountPersistence = new JobCvDao(dataSource);
		ModelsTransformer modelsTransformer = new JSONModelsTransformer();
		ResponseProviderFactory responseProviderFactory = new ResponseProviderFactory();
		JobAccountController profilesController = new JobCVsController(jobAccountPersistence, modelsTransformer,
				responseProviderFactory);
		Response response = profilesController.get();

		return response;
	}

	@Secured
	@POST
	@Produces("application/json")
	public Response create(@Context HttpServletRequest request) {
		DataSource dataSource = new MySQLSource();
		JobAccountPersistence jobAccountPersistence = new JobCvDao(dataSource);
		ModelsTransformer modelsTransformer = new JSONModelsTransformer();
		ResponseProviderFactory responseProviderFactory = new ResponseProviderFactory();
		JobAccountController profilesController = new JobCVsController(jobAccountPersistence, modelsTransformer,
				responseProviderFactory);
		HttpRequest httpRequest = new HttpRequest(request);
		Response response = profilesController.post(httpRequest);

		return response;
	}
}
