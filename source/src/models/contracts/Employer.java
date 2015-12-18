package models.contracts;

import enums.CarrerField;

public interface Employer {
	public CarrerField getCarrerField();

	public void setCarrerField(CarrerField carrerField);

	public String getCompanyName();

	public void setCompanyname(String companyName);
}
