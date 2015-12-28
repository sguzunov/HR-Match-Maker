package models;

import enums.UserType;

public class User {
	private String userName;
	private String password;
	private UserType userType;

	public User(String userName, UserType userType) {
		this.userName = userName;
		this.userType = userType;
	}

	public User(String userName, String password, UserType userType) {
		this(userName, userType);
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}
}
