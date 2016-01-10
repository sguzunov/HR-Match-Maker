package webresources;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import common.security.Secured;
import controllers.JobAccountController;
import controllers.JobAdvertisementsController;
import http.HttpRequest;
import http.ResponseProviderFactory;
import persistence.JobAdvertisementDao;
import persistence.contracts.JobAccountPersistence;
import persistence.sources.DataSource;
import persistence.sources.MySQLSource;
import transformers.JSONModelsTransformer;
import transformers.contracts.ModelsTransformer;

public class JobAdvertisementsResource {
	@GET
	@Produces("application/json")
	public Response getJSON() {
		DataSource dataSource = new MySQLSource();
		JobAccountPersistence jobAccountPersistence = new JobAdvertisementDao(dataSource);
		ModelsTransformer modelsTransformer = new JSONModelsTransformer();
		ResponseProviderFactory responseProviderFactory = new ResponseProviderFactory();
		JobAccountController profilesController = new JobAdvertisementsController(jobAccountPersistence,
				modelsTransformer, responseProviderFactory);
		Response response = profilesController.get();

		return response;
	}

	@Secured
	@POST
	@Produces("application/json")
	public Response create(@Context HttpServletRequest request) {
		DataSource dataSource = new MySQLSource();
		JobAccountPersistence jobAccountPersistence = new JobAdvertisementDao(dataSource);
		ModelsTransformer modelsTransformer = new JSONModelsTransformer();
		ResponseProviderFactory responseProviderFactory = new ResponseProviderFactory();
		JobAccountController profilesController = new JobAdvertisementsController(jobAccountPersistence,
				modelsTransformer, responseProviderFactory);
		HttpRequest httpRequest = new HttpRequest(request);
		Response response = profilesController.post(httpRequest);

		return response;
	}

	@GET
	@Produces("application/json")
	@Path("/{id}")
	public Response getJSON(@PathParam("id") int id) {
		DataSource dataSource = new MySQLSource();
		JobAccountPersistence jobAccountPersistence = new JobAdvertisementDao(dataSource);
		ModelsTransformer modelsTransformer = new JSONModelsTransformer();
		ResponseProviderFactory responseProviderFactory = new ResponseProviderFactory();
		JobAccountController profilesController = new JobAdvertisementsController(jobAccountPersistence,
				modelsTransformer, responseProviderFactory);
		Response response = profilesController.select(id);

		return response;
	}
}
