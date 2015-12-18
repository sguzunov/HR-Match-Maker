package models;

public class Location {
	private String city;
	private String country;

	public Location(String city, String country) {
		this.setCity(city);
		this.setCountry(country);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
