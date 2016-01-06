package common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import exceptions.InvalidUserException;
import models.User;
import persistence.contracts.Retrieveable;

public class Authentication {
	private static final int MIN_PASSWORD_LENGTH = 10;

	private static Map<String, String> authKeys = new HashMap<String, String>();

	public boolean isUserLoggedIn(String username, String token) {
		if (authKeys.containsKey(username) && (authKeys.get(username) == token)) {
			return true;
		}

		return false;
	}

	public void logOutUser(String username) throws InvalidUserException {
		if (authKeys.containsKey(username)) {
			authKeys.remove(username);
		} else {
			throw new InvalidUserException();
		}
	}

	public String authenticateUser(Retrieveable persister, String username, String password) throws Exception {
		Collection<User> users = persister.retrieve();

		// Authenticate the user using the credentials provided. If invalid
		// credentials exception is thrown
		authenticate(username, password, users);

		// Issue a token for the user
		String token = issueToken(username);
		authKeys.put(username, token);

		return token;

	}

	private void authenticate(String username, String password, Collection<User> users) throws Exception {
		if (password.length() < MIN_PASSWORD_LENGTH) {
			throw new InvalidUserException();
		}

		User existingUser = null;
		for (User user : users) {
			if (user.getUserName().equals(username)) {
				existingUser = user;
			}
		}

		if (existingUser == null) {
			throw new InvalidUserException();
		}

		if (existingUser.getPassword() != password) {
			throw new InvalidUserException();
		}
	}

	private String issueToken(String username) {
		String token = RandomProvider.generaterandomUUID(username);

		return token;
	}
}