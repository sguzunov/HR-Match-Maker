package models;

import java.io.Serializable;

public class Credential implements Serializable {
	private String username;
	private String password;
	public Location location;

	public Credential(String username, String password, Location location) {
		this.username = username;
		this.password = password;
		this.location = location;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
