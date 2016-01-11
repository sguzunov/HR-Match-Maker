package common.security;

import java.util.ArrayList;
import java.util.Collection;

import common.RandomProvider;
import exceptions.InvalidUserException;
import models.Credentials;
import models.User;

public class Authentication {
	private static final int USERNAME_MIN_LENGTH = 6;
	private static final int PASSWORD_MAX_LENGTH = 40;
	private static final int PASSWORD_MIN_LENGTH = 10;

	public static Collection<Credentials> credentialsCollection = new ArrayList<Credentials>();

	public boolean isUserLogged(Credentials checkedCredentials) {
		for (Credentials credentials : credentialsCollection) {
			if (credentials.getUserName().equals(checkedCredentials.getAuthKey())
					&& credentials.getAuthKey().equals(checkedCredentials.getAuthKey())) {
				return true;
			}
		}

		return false;
	}

	public void authenticate(String username, String password, Collection<User> users) throws InvalidUserException {
		if (username == null || password == null) {
			throw new NullPointerException();
		}

		if (password.length() < PASSWORD_MIN_LENGTH || PASSWORD_MAX_LENGTH < password.length()
				|| username.length() < USERNAME_MIN_LENGTH) {
			throw new InvalidUserException();
		}

		boolean isUserRegistered = false;
		for (User user : users) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				isUserRegistered = true;
				break;
			}
		}

		if (!isUserRegistered) {
			throw new InvalidUserException();
		}
	}

	public String issueToken() {
		String token = RandomProvider.generaterandomUUIDFromString();

		return token;
	}

	public void saveCredentials(Credentials credentials) {
		credentialsCollection.add(credentials);
	}

	public void removeCredentials(Credentials credentials) {
		credentialsCollection.remove(credentials);
	}

	public boolean isValidAuthToken(String authToken) {
		for (Credentials credentials : credentialsCollection) {
			if (credentials.getAuthKey().equals(authToken)) {
				return true;
			}
		}

		return false;
	}
}
