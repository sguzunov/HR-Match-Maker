package main;

import org.glassfish.jersey.server.ResourceConfig;

public class HRMatchMaker extends ResourceConfig {
	public HRMatchMaker() {
		register(new HRMatchMakerBinder());
	}
}
