package models.contracts;

import java.util.List;

import enums.CarrerField;
import enums.Education;
import enums.WorkPosition;
import enums.WorkType;
import models.Location;

public interface ComparableJobAccount {
	public Location getLocation();

	public void setLocation(Location location);

	public Education getRequiredEducation();

	public void setRequiredEducation(Education requiredEducation);

	public WorkPosition getWorkPostion();

	public void setWorkPosition(WorkPosition workPosition);

	public WorkType getWorkType();

	public void setWorkType(WorkType workType);

	public Boolean isRequiredExperience();

	public List<CarrerField> getCarrerField();

	public void setCarrerField(List<CarrerField> carrerField);
}
