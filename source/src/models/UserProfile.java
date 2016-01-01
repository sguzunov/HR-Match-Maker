package models;

public abstract class UserProfile {
	private int id;
	private User user;
	private String firstName;
	private String lastName;
	private Location location;
	private String webSite;

	public UserProfile(int id, User user,String firstName, String lastName, Location location, String webSite) {
		this.id = id;
		this.setUser(user);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setLocation(location);
		this.setWebSite(webSite);
	}

	public int getId() {
		return this.id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
}
