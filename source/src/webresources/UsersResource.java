package webresources;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import common.security.Authentication;
import controllers.AuthenticationController;
import controllers.UsersController;
import http.HttpRequest;
import http.ResponseProviderFactory;
import persistence.contracts.UserPersistence;
import persistence.factories.DaoFactory;
import transformers.contracts.ModelsTransformer;

@Path("/users")
public class UsersResource {

	@Inject
	private ModelsTransformer modelsTransformer;

	@Inject
	private ResponseProviderFactory responseProviderFactory;

	@Inject
	private DaoFactory daoFactory;

	@Inject
	private Authentication authentication;

	@POST
	@Produces("application/json")
	public Response create(@Context HttpServletRequest servletRequest) {
		UserPersistence userDao = this.daoFactory.getUserDao();
		HttpRequest httpRequest = new HttpRequest(servletRequest);
		UsersController controller = new UsersController(userDao, modelsTransformer, responseProviderFactory);
		Response response = controller.post(httpRequest);

		return response;
	}

	@PUT
	@Path("/auth")
	@Produces("application/json")
	public Response authenticate(@Context HttpServletRequest servletRequest) {
		UserPersistence userDao = this.daoFactory.getUserDao();
		HttpRequest httpRequest = new HttpRequest(servletRequest);
		AuthenticationController controller = new AuthenticationController(userDao, modelsTransformer,
				this.responseProviderFactory, this.authentication);
		Response response = controller.put(httpRequest);

		return response;
	}

	@DELETE
	@Produces("application/json")
	public Response logOut(@Context HttpServletRequest servletRequest) {
		UserPersistence userDao = this.daoFactory.getUserDao();
		HttpRequest httpRequest = new HttpRequest(servletRequest);
		AuthenticationController controller = new AuthenticationController(userDao, modelsTransformer,
				this.responseProviderFactory, this.authentication);
		Response response = controller.delete(httpRequest);

		return response;
	}
}
