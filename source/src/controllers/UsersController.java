package controllers;

import javax.ws.rs.core.Response;

import controllers.contracts.PostController;
import enums.StatusCode;
import http.HttpRequest;
import http.HttpResponseProvider;
import http.ResponseProviderFactory;
import models.User;
import parsers.contracts.ModelsTransformer;
import persistence.contracts.UserPersistence;

public class UsersController implements PostController {
	private UserPersistence persistence;
	private ModelsTransformer modelsTransformer;
	private ResponseProviderFactory responseProviderFactory;

	public UsersController(UserPersistence persistence, ModelsTransformer modelsTransformer,
			ResponseProviderFactory responseProviderFactory) {
		this.persistence = persistence;
		this.modelsTransformer = modelsTransformer;
		this.responseProviderFactory = responseProviderFactory;
	}

	// User registration.
	@Override
	public Response post(HttpRequest request) {
		String modelAsJsonString = request.getBody();
		HttpResponseProvider httpResponseProvider = null;
		try {
			User user = this.modelsTransformer.transformStringToModel(modelAsJsonString, User.class);
			this.persistence.create(user);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
		} catch (Exception e) {
			e.printStackTrace();
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
		}

		return httpResponseProvider.getResponse();
	}
}
