package models;

import java.util.List;

import enums.Education;
import enums.CarrerField;
import enums.WorkPosition;
import enums.WorkType;
import models.contracts.ComparableJobAccount;

public abstract class JobAccount implements ComparableJobAccount {
	private User createdBy;
	private Location location;
	private Education requiredEducation;
	private WorkPosition workPosition;
	private WorkType workType;
	private Boolean requiredExperience;
	private List<CarrerField> carrerFields;

	public JobAccount(User createBy, Location location, Education requiredEducation, WorkPosition workPosition,
			WorkType workType, Boolean requiredExperience, List<CarrerField> carrerFields) {
		this.setCreatedBy(createBy);
		this.setLocation(location);
		this.setRequiredEducation(requiredEducation);
		this.setWorkPosition(workPosition);
		this.setWorkType(workType);
		this.requiredExperience = requiredExperience;
		this.carrerFields = carrerFields;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public Location getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public Education getRequiredEducation() {
		return this.requiredEducation;
	}

	@Override
	public void setRequiredEducation(Education requiredEducation) {
		this.requiredEducation = requiredEducation;
	}

	@Override
	public WorkPosition getWorkPostion() {
		return this.workPosition;
	}

	@Override
	public void setWorkPosition(WorkPosition workPosition) {
		this.workPosition = workPosition;
	}

	@Override
	public WorkType getWorkType() {
		return this.workType;
	}

	@Override
	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	@Override
	public Boolean isRequiredExperience() {
		return this.requiredExperience;
	}

	@Override
	public List<CarrerField> getCarrerField() {
		return this.carrerFields;
	}

	@Override
	public void setCarrerField(List<CarrerField> carrerField) {
		this.carrerFields = carrerField;
	}
}
