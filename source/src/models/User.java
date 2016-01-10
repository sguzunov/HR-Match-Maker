package models;

import enums.UserType;

public class User {
	private int id;
	private String userName;
	private String password;
	private UserType userType;

	public User(int id, String userName, UserType userType) {
		this.id = id;
		this.userName = userName;
		this.userType = userType;
	}

	public User(String userName, String password, UserType userType) {
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
