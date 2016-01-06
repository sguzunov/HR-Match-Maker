package controllers;

import javax.ws.rs.core.Response;

import common.Authentication;
import controllers.contracts.DeleteController;
import controllers.contracts.PostController;
import controllers.contracts.PutController;
import enums.StatusCode;
import http.HttpRequest;
import http.HttpResponseProvider;
import http.ResponseProviderFactory;
import models.User;
import parsers.contracts.ModelsParser;
import persistence.contracts.UserPersistence;

public class UsersController implements PostController, PutController, DeleteController {
	private UserPersistence persister;
	private ModelsParser parser;
	private ResponseProviderFactory responseProviderFactory;
	private Authentication authentication;

	public UsersController(UserPersistence persister, ModelsParser parser,
			ResponseProviderFactory responseProviderFactory, Authentication authentication) {
		this.parser = parser;
		this.responseProviderFactory = responseProviderFactory;
		this.authentication = authentication;
	}

	// Log in user.
	@Override
	public Response put(HttpRequest request) {
		String modelAsJsonString = request.getBody();
		User user = this.parser.parseStringToModel(modelAsJsonString, User.class);

		HttpResponseProvider httpResponseProvider = null;
		try {
			String username = user.getUserName();
			String password = user.getPassword();
			String token = this.authentication.authenticateUser(this.persister, username, password);

			// Make new user with authToken;
			User authenticatedUser = new User(user.getUserName(), token);
			String authenticatedUserAsJsonString = parser.parseModelToString(authenticatedUser);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
			httpResponseProvider.setResponseBody(authenticatedUserAsJsonString);
		} catch (Exception e) {
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
		}

		return httpResponseProvider.getResponse();
	}

	// User registration.
	@Override
	public Response post(HttpRequest request) {
		String modelAsJsonString = request.getBody();
		User user = this.parser.parseStringToModel(modelAsJsonString, User.class);

		HttpResponseProvider httpResponseProvider = null;
		try {
			this.persister.create(user);

			String username = user.getUserName();
			String password = user.getPassword();
			String token = this.authentication.authenticateUser(this.persister, username, password);

			// Make new user with authToken;
			User authenticatedUser = new User(user.getUserName(), token);
			String authenticatedUserAsJsonString = parser.parseModelToString(authenticatedUser);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
			httpResponseProvider.setResponseBody(authenticatedUserAsJsonString);
		} catch (Exception e) {
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
		}

		return httpResponseProvider.getResponse();
	}

	// Log out user.
	@Override
	public Response delete(HttpRequest request) {
		String modelAsJsonString = request.getBody();
		User user = this.parser.parseStringToModel(modelAsJsonString, User.class);

		HttpResponseProvider httpResponseProvider = null;
		try {
			String username = user.getUserName();
			this.authentication.logOutUser(username);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
		} catch (Exception e) {
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
		}

		return httpResponseProvider.getResponse();
	}
}
