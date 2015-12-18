package models;

public abstract class UserProfile {
	private String firstName;
	private String lastName;
	private Location location;
	private String webSite;

	public UserProfile(String firstName, String lastName, Location location, String webSite) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setLocation(location);
		this.setWebSite(webSite);
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
