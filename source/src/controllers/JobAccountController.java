package controllers;

import java.util.Collection;

import javax.ws.rs.core.Response;

import controllers.contracts.GetController;
import controllers.contracts.PostController;
import enums.StatusCode;
import http.HttpRequest;
import http.HttpResponseProvider;
import http.ResponseProviderFactory;
import models.JobAccount;
import persistence.contracts.JobAccountPersistence;
import transformers.contracts.ModelsTransformer;

public abstract class JobAccountController implements GetController, PostController {
	protected JobAccountPersistence persistence;
	protected ModelsTransformer modelsTransformer;
	protected ResponseProviderFactory responseProviderFactory;

	public JobAccountController(JobAccountPersistence persistence, ModelsTransformer modelsTransformer,
			ResponseProviderFactory responseProviderFactory) {
		this.persistence = persistence;
		this.modelsTransformer = modelsTransformer;
		this.responseProviderFactory = responseProviderFactory;
	}

	@Override
	public abstract Response post(HttpRequest request);

	@Override
	public Response get() {
		HttpResponseProvider httpResponseProvider = null;
		try {
			Collection<JobAccount> profiles = this.persistence.retrieve();
			String modelsAsJsonString = this.modelsTransformer.transformCollectionToString(profiles);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
			httpResponseProvider.setResponseBody(modelsAsJsonString);
		} catch (Exception e) {
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
			e.printStackTrace();
		}

		return httpResponseProvider.getResponse();
	}
}
