package controllers;

import java.util.Collection;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import common.security.Authentication;
import controllers.contracts.DeleteController;
import controllers.contracts.PutController;
import enums.StatusCode;
import exceptions.InvalidUserException;
import http.HttpRequest;
import http.HttpResponseProvider;
import http.ResponseProviderFactory;
import models.Credentials;
import models.User;
import persistence.contracts.UserPersistence;
import transformers.contracts.ModelsTransformer;

@Path("/users/auth")
public class AuthenticationController implements PutController, DeleteController {
	private UserPersistence persistence;
	private ModelsTransformer modelsTransformer;
	private ResponseProviderFactory responseProviderFactory;
	private Authentication authentication;

	public AuthenticationController(UserPersistence persistence, ModelsTransformer modelsTransformer,
			ResponseProviderFactory responseProviderFactory, Authentication authentication) {
		this.persistence = persistence;
		this.modelsTransformer = modelsTransformer;
		this.responseProviderFactory = responseProviderFactory;
		this.authentication = authentication;
	}

	// logIn
	@Override
	public Response put(HttpRequest request) {
		String modelAsJsonString = request.getBody();
		HttpResponseProvider httpResponseProvider = null;
		try {
			User user = this.modelsTransformer.transformStringToModel(modelAsJsonString, User.class);
			String username = user.getUserName();
			String password = user.getPassword();
			Collection<User> users = this.persistence.retrieve();

			this.authentication.authenticate(username, password, users);

			String authToken = this.authentication.issueToken();
			Credentials userCredentials = new Credentials(username, authToken);
			this.authentication.saveCredentials(userCredentials);

			String userCredentialsAsString = this.modelsTransformer.transformModelToString(userCredentials);
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
			httpResponseProvider.setResponseBody(userCredentialsAsString);
		} catch (Exception e) {
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
			e.printStackTrace();
		}

		return httpResponseProvider.getResponse();
	}

	// Log out user.
	@Override
	public Response delete(HttpRequest request) {
		String modelAsJsonString = request.getBody();
		HttpResponseProvider httpResponseProvider = null;
		try {

			Credentials credentials = this.modelsTransformer.transformStringToModel(modelAsJsonString,
					Credentials.class);

			if (!this.authentication.isUserLogged(credentials)) {
				throw new InvalidUserException();
			}

			this.authentication.removeCredentials(credentials);
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
		} catch (Exception e) {
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
			e.printStackTrace();
		}

		return httpResponseProvider.getResponse();
	}

}
