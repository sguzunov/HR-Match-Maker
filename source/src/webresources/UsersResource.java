package webresources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import controllers.AuthenticationController;
import controllers.UsersController;
import http.HttpRequest;
import http.ResponseProviderFactory;
import persistence.UserDao;
import persistence.sources.DataSource;
import persistence.sources.MySQLSource;
import transformers.JSONModelsTransformer;
import transformers.contracts.ModelsTransformer;

@Path("/users")
public class UsersResource {

	@POST
	@Produces("application/json")
	public Response create(@Context HttpServletRequest request) {
		DataSource dataSource = new MySQLSource();
		UserDao userDao = new UserDao(dataSource);
		ModelsTransformer modelsTransformer = new JSONModelsTransformer();
		ResponseProviderFactory factory = new ResponseProviderFactory();
		HttpRequest httpRequest = new HttpRequest(request);

		UsersController controller = new UsersController(userDao, modelsTransformer, factory);
		Response response = controller.post(httpRequest);

		return response;
	}

	@PUT
	@Path("/auth")
	@Produces("application/json")
	public Response authenticateUser(@Context HttpServletRequest request) {
		DataSource dataSource = new MySQLSource();
		UserDao userDao = new UserDao(dataSource);
		ModelsTransformer modelsTransformer = new JSONModelsTransformer();
		ResponseProviderFactory factory = new ResponseProviderFactory();
		HttpRequest httpRequest = new HttpRequest(request);

		AuthenticationController controller = new AuthenticationController(userDao, modelsTransformer, factory);
		Response response = controller.put(httpRequest);

		return response;
	}

	@DELETE
	@Produces("application/json")
	public Response logOutUser(@Context HttpServletRequest request) {
		DataSource dataSource = new MySQLSource();
		UserDao userDao = new UserDao(dataSource);
		ModelsTransformer modelsTransformer = new JSONModelsTransformer();
		ResponseProviderFactory factory = new ResponseProviderFactory();
		HttpRequest httpRequest = new HttpRequest(request);

		AuthenticationController controller = new AuthenticationController(userDao, modelsTransformer, factory);
		Response response = controller.delete(httpRequest);

		return response;
	}
}
