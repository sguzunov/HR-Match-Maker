package models;

public class Credentials {
	private String username;
	private String authKey;

	public Credentials(String username, String authKey) {
		this.username = username;
		this.authKey = authKey;
	}

	public String getUserName() {
		return this.username;
	}

	public String getAuthKey() {
		return this.authKey;
	}
}
