package models;

import java.util.Collection;

import enums.CarrerField;
import enums.Education;
import enums.WorkPosition;
import enums.WorkType;

public class JobCV extends JobAccount {
	private Collection<String> knownLanguages;
	private int age;

	public JobCV(int id, User createBy, Location location, Education requiredEducation, WorkPosition workPosition,
			WorkType workType, Boolean requiredExperience, int age, Collection<CarrerField> carrerFields,
			Collection<String> knownLanguages) {
		super(id, createBy, location, requiredEducation, workPosition, workType, requiredExperience, carrerFields);
		this.setAge(age);
		this.knownLanguages = knownLanguages;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Collection<String> getKnownLanguages() {
		return this.knownLanguages;
	}

	public void setKnownLanguages(Collection<String> knowLanguages) {
		this.knownLanguages = knowLanguages;
	}
}
