package models;

import enums.Education;

public class JobSeekerProfile extends UserProfile {
	private int age;
	private Education education;

	public JobSeekerProfile(int id, User user, String firstName, String lastName, Location location, String webSite,
			int age, Education education) {
		super(id, user, firstName, lastName, location, webSite);
		this.setAge(age);
		this.setEducation(education);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}
}
