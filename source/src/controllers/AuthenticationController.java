package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import common.RandomProvider;
import controllers.contracts.DeleteController;
import controllers.contracts.PutController;
import enums.StatusCode;
import exceptions.InvalidUserException;
import http.HttpRequest;
import http.HttpResponseProvider;
import http.ResponseProviderFactory;
import models.Credentials;
import models.User;
import persistence.contracts.UserPersistence;
import transformers.contracts.ModelsTransformer;

@Path("/users/auth")
public class AuthenticationController implements PutController, DeleteController {
	private static final int USERNAME_MIN_LENGTH = 6;
	private static final int PASSWORD_MIN_LENGTH = 10;

	public static Map<String, String> tokens = new HashMap<String, String>();

	private UserPersistence persistence;
	private ModelsTransformer modelsTransformer;
	private ResponseProviderFactory responseProviderFactory;

	public AuthenticationController(UserPersistence persistence, ModelsTransformer modelsTransformer,
			ResponseProviderFactory responseProviderFactory) {
		this.persistence = persistence;
		this.modelsTransformer = modelsTransformer;
		this.responseProviderFactory = responseProviderFactory;
	}

	// logIn
	@Override
	public Response put(HttpRequest request) {
		String modelAsJsonString = request.getBody();
		User user = this.modelsTransformer.transformStringToModel(modelAsJsonString, User.class);

		HttpResponseProvider httpResponseProvider = null;
		try {
			String username = user.getUserName();
			String password = user.getPassword();
			this.authenticate(username, password);

			String authToken = this.issueToken();
			tokens.put(username, authToken);

			Credentials userCredentials = new Credentials(username, authToken);
			String userCredentialsAsString = this.modelsTransformer.transformModelToString(userCredentials);

			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
			httpResponseProvider.setResponseBody(userCredentialsAsString);
		} catch (Exception e) {
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
			e.printStackTrace();
		}

		return httpResponseProvider.getResponse();
	}

	// Log out user.
	@Override
	public Response delete(HttpRequest request) {
		String modelAsJsonString = request.getBody();
		Credentials credentials = this.modelsTransformer.transformStringToModel(modelAsJsonString, Credentials.class);

		HttpResponseProvider httpResponseProvider = null;
		try {
			String username = credentials.getUserName();
			String authKey = credentials.getAuthKey();
			if (!this.isUserLogged(username, authKey)) {
				throw new InvalidUserException();
			}

			tokens.remove(username);
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.OK);
		} catch (Exception e) {
			httpResponseProvider = this.responseProviderFactory.getResponseProvider(StatusCode.BADREQUEST);
			e.printStackTrace();
		}

		return httpResponseProvider.getResponse();
	}

	private boolean isUserLogged(String username, String authKey) {
		return tokens.containsKey(username);
	}

	private void authenticate(String username, String password) throws InvalidUserException {
		Collection<User> allRegisteredUsers = this.persistence.retrieve();
		if (password.length() < PASSWORD_MIN_LENGTH || username.length() < USERNAME_MIN_LENGTH) {
			throw new InvalidUserException();
		}

		boolean isUserRegistered = false;
		for (User user : allRegisteredUsers) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				isUserRegistered = true;
				break;
			}
		}

		if (!isUserRegistered) {
			throw new InvalidUserException();
		}
	}

	private String issueToken() {
		String token = RandomProvider.generaterandomUUIDFromString();

		return token;
	}

}
