package controllers;

import javax.ws.rs.core.Response;

import enums.StatusCode;
import http.HttpRequest;
import http.HttpResponseProvider;
import http.ResponseProviderFactory;
import models.JobCV;
import persistence.contracts.JobAccountPersistence;
import transformers.contracts.ModelsTransformer;

public class JobCVsController extends JobAccountController {

	public JobCVsController(JobAccountPersistence persistence, ModelsTransformer modelsTransformer,
			ResponseProviderFactory responseProviderFactory) {
		super(persistence, modelsTransformer, responseProviderFactory);
	}

	@Override
	public Response post(HttpRequest request) {
		String modelAsJsonString = request.getBody();
		HttpResponseProvider httpResponseProvider = null;
		try {
			JobCV jobCV = this.modelsTransformer.transformStringToModel(modelAsJsonString, JobCV.class);
			this.persistence.create(jobCV);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
		} catch (Exception e) {
			e.printStackTrace();
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
		}

		return httpResponseProvider.getResponse();
	}
}
