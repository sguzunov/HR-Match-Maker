package controllers.contracts;

import javax.ws.rs.core.Response;

public interface SelectController {
	public Response select(int identifier);
}
