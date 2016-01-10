package models;

import java.util.Collection;

import enums.CarrerField;
import enums.Education;
import enums.WorkPosition;
import enums.WorkType;

public class JobAdvertisement extends JobAccount {
	private String title;
	private String resume;

	public JobAdvertisement(int id, User createBy, Location location, Education requiredEducation,
			WorkPosition workPosition, WorkType workType, Boolean requiredExperience, String title, String resume,
			Collection<CarrerField> carrerFields) {
		super(id, createBy, location, requiredEducation, workPosition, workType, requiredExperience, carrerFields);
		this.setTitle(title);
		this.setResume(resume);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
}
