package models;

import java.util.Collection;

import enums.CarrerField;

public class EmployerProfile extends UserProfile {
	private String companyName;
	private Collection<CarrerField> carrerFields;

	public EmployerProfile(int id, User user, String firstName, String lastName, Location location, String webSite,
			String companyName, Collection<CarrerField> carrerFields) {
		super(id, user, firstName, lastName, location, webSite);
		this.setCompanyName(companyName);
		this.setCarrerFields(carrerFields);
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Collection<CarrerField> getCarrerFields() {
		return carrerFields;
	}

	public void setCarrerFields(Collection<CarrerField> carrerFields) {
		this.carrerFields = carrerFields;
	}
}
