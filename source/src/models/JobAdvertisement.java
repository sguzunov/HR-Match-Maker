package models;

import java.util.List;

import enums.CarrerField;
import enums.Education;
import enums.WorkPosition;
import enums.WorkType;

public class JobAdvertisement extends JobAccount {
	private static long id;
	private String title;
	private String resume;

	public JobAdvertisement(User createBy, Location location, Education requiredEducation, WorkPosition workPosition,
			WorkType workType, Boolean requiredExperience, String title, String resume,
			List<CarrerField> carrerFields) {
		super(createBy, location, requiredEducation, workPosition, workType, requiredExperience, carrerFields);
		this.setTitle(title);
		this.setResume(resume);
		JobAdvertisement.id++;
	}

	public long getId(){
		return JobAdvertisement.id;
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
