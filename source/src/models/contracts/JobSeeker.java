package models.contracts;

import enums.Education;

public interface JobSeeker {
	public int getAge();

	public void setAge(int age);

	public Education getEducation();

	public void setEducation(Education education);
}
