package controllers;

import javax.ws.rs.core.Response;

import enums.StatusCode;
import http.HttpRequest;
import http.HttpResponseProvider;
import http.ResponseProviderFactory;
import models.EmployerProfile;
import persistence.contracts.UserProfilePersistence;
import transformers.contracts.ModelsTransformer;

public class EmployerProfileController extends ProfilesController {

	public EmployerProfileController(UserProfilePersistence persistence, ModelsTransformer modelsTransformer,
			ResponseProviderFactory responseProviderFactory) {
		super(persistence, modelsTransformer, responseProviderFactory);
	}

	@Override
	public Response post(HttpRequest request) {
		String modelAsJsonString = request.getBody();
		HttpResponseProvider httpResponseProvider = null;
		try {
			EmployerProfile employerProfile = this.modelsTransformer.transformStringToModel(modelAsJsonString,
					EmployerProfile.class);
			this.persistence.create(employerProfile);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
		} catch (Exception e) {
			e.printStackTrace();
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
		}

		return httpResponseProvider.getResponse();
	}

}
