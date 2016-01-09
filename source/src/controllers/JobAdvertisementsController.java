package controllers;

import javax.ws.rs.core.Response;

import enums.StatusCode;
import http.HttpRequest;
import http.HttpResponseProvider;
import http.ResponseProviderFactory;
import models.JobAdvertisement;
import persistence.contracts.JobAccountPersistence;
import transformers.contracts.ModelsTransformer;

public class JobAdvertisementsController extends JobAccountController {

	public JobAdvertisementsController(JobAccountPersistence persistence, ModelsTransformer modelsTransformer,
			ResponseProviderFactory responseProviderFactory) {
		super(persistence, modelsTransformer, responseProviderFactory);
	}

	@Override
	public Response post(HttpRequest request) {
		String modelAsJsonString = request.getBody();
		HttpResponseProvider httpResponseProvider = null;
		try {
			JobAdvertisement jobAdvertisement = this.modelsTransformer.transformStringToModel(modelAsJsonString,
					JobAdvertisement.class);
			this.persistence.create(jobAdvertisement);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
		} catch (Exception e) {
			e.printStackTrace();
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
		}

		return httpResponseProvider.getResponse();
	}
}
