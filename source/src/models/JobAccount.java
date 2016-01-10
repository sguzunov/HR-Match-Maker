package models;

import java.util.Collection;

import enums.Education;
import enums.CarrerField;
import enums.WorkPosition;
import enums.WorkType;
import models.contracts.ComparableJobAccount;

public abstract class JobAccount implements ComparableJobAccount {
	private int id;
	private User user;
	private Location location;
	private Education requiredEducation;
	private WorkPosition workPosition;
	private WorkType workType;
	private Boolean requiredExperience;
	private Collection<CarrerField> carrerFields;

	public JobAccount(int id, User user, Location location, Education requiredEducation, WorkPosition workPosition,
			WorkType workType, Boolean requiredExperience, Collection<CarrerField> carrerFields) {
		this.id = id;
		this.setUser(user);
		this.setLocation(location);
		this.setRequiredEducation(requiredEducation);
		this.setWorkPosition(workPosition);
		this.setWorkType(workType);
		this.requiredExperience = requiredExperience;
		this.carrerFields = carrerFields;
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
	public Collection<CarrerField> getCarrerField() {
		return this.carrerFields;
	}

	@Override
	public void setCarrerField(Collection<CarrerField> carrerField) {
		this.carrerFields = carrerField;
	}
}
