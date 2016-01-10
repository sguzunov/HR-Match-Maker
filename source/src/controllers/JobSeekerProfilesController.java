package controllers;

import javax.ws.rs.core.Response;

import enums.StatusCode;
import http.HttpRequest;
import http.HttpResponseProvider;
import http.ResponseProviderFactory;
import models.JobSeekerProfile;
import persistence.contracts.UserProfilePersistence;
import transformers.contracts.ModelsTransformer;

public class JobSeekerProfilesController extends ProfilesController {
	public JobSeekerProfilesController(UserProfilePersistence persistence, ModelsTransformer modelsTransformer,
			ResponseProviderFactory responseProviderFactory) {
		super(persistence, modelsTransformer, responseProviderFactory);
	}

	@Override
	public Response post(HttpRequest request) {
		HttpResponseProvider httpResponseProvider = null;
		try {
			String modelAsJsonString = request.getBody();
			JobSeekerProfile jobSeekerProfile = this.modelsTransformer.transformStringToModel(modelAsJsonString,
					JobSeekerProfile.class);
			this.persistence.create(jobSeekerProfile);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
		} catch (Exception e) {
			e.printStackTrace();
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
		}

		return httpResponseProvider.getResponse();
	}
}
