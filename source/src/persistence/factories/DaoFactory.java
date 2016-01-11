package persistence.factories;

import persistence.contracts.JobAccountPersistence;
import persistence.contracts.UserPersistence;
import persistence.contracts.UserProfilePersistence;

public abstract class DaoFactory {
	public abstract UserPersistence getUserDao();

	public abstract UserProfilePersistence getJobSeekerDao();

	public abstract UserProfilePersistence getEmployerDao();

	public abstract JobAccountPersistence getJobCvDao();

	public abstract JobAccountPersistence getJobAdvertisementDao();
}
