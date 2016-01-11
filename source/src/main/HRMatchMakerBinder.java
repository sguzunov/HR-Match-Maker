package main;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import common.security.Authentication;
import http.ResponseProviderFactory;
import persistence.factories.DaoFactory;
import persistence.factories.MySQLDaoFactory;
import transformers.JSONModelsTransformer;
import transformers.contracts.ModelsTransformer;

public class HRMatchMakerBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(JSONModelsTransformer.class).to(ModelsTransformer.class);

		bind(ResponseProviderFactory.class).to(ResponseProviderFactory.class);

		bind(MySQLDaoFactory.class).to(DaoFactory.class);

		bind(Authentication.class).to(Authentication.class);

	}

}
