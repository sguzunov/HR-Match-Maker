package models;

import java.util.List;

import enums.CarrerField;
import enums.Education;
import enums.WorkPosition;
import enums.WorkType;

public class JobCV extends JobAccount {
	private static long id;
	private int age;

	public JobCV(User createBy, Location location, Education requiredEducation, WorkPosition workPosition,
			WorkType workType, Boolean requiredExperience, int age, List<CarrerField> carrerFields) {
		super(createBy, location, requiredEducation, workPosition, workType, requiredExperience, carrerFields);
		this.setAge(age);
		JobCV.id++;
	}

	public long getId() {
		return this.id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
