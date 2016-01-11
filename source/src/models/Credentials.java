package models;

public class Credentials {
	private String userName;
	private String authKey;

	public Credentials(String username, String authKey) {
		this.userName = username;
		this.authKey = authKey;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getAuthKey() {
		return this.authKey;
	}
}
