package controllers;

import java.util.Collection;

import javax.ws.rs.core.Response;

import controllers.contracts.GetController;
import controllers.contracts.PostController;
import controllers.contracts.SelectController;
import enums.StatusCode;
import http.HttpRequest;
import http.HttpResponseProvider;
import http.ResponseProviderFactory;
import models.UserProfile;
import persistence.contracts.UserProfilePersistence;
import transformers.contracts.ModelsTransformer;

public abstract class ProfilesController implements GetController, PostController, SelectController {
	protected UserProfilePersistence persistence;
	protected ModelsTransformer modelsTransformer;
	protected ResponseProviderFactory responseProviderFactory;

	public ProfilesController(UserProfilePersistence persistence, ModelsTransformer modelsTransformer,
			ResponseProviderFactory responseProviderFactory) {
		this.persistence = persistence;
		this.modelsTransformer = modelsTransformer;
		this.responseProviderFactory = responseProviderFactory;
	}

	@Override
	public Response select(int identifier) {
		HttpResponseProvider httpResponseProvider = null;
		try {
			UserProfile profile = this.persistence.selectBy(identifier);
			String modelsAsJsonString = this.modelsTransformer.transformModelToString(profile);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
			httpResponseProvider.setResponseBody(modelsAsJsonString);
		} catch (Exception e) {
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
			e.printStackTrace();
		}

		return httpResponseProvider.getResponse();
	}

	@Override
	public Response get() {
		HttpResponseProvider httpResponseProvider = null;
		try {
			Collection<UserProfile> profiles = this.persistence.retrieve();
			String modelsAsJsonString = this.modelsTransformer.transformCollectionToString(profiles);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
			httpResponseProvider.setResponseBody(modelsAsJsonString);
		} catch (Exception e) {
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
			e.printStackTrace();
		}

		return httpResponseProvider.getResponse();
	}

	public abstract Response post(HttpRequest request);
}
