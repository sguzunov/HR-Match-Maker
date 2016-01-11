package persistence.factories;

import persistence.EmployerDao;
import persistence.JobAdvertisementDao;
import persistence.JobCvDao;
import persistence.JobSeekerDao;
import persistence.UserDao;
import persistence.contracts.JobAccountPersistence;
import persistence.contracts.UserPersistence;
import persistence.contracts.UserProfilePersistence;
import persistence.sources.DataSource;
import persistence.sources.MySQLSource;

public class MySQLDaoFactory extends DaoFactory {
	private DataSource dataSource;

	public MySQLDaoFactory() {
		this.dataSource = new MySQLSource();
	}

	@Override
	public UserPersistence getUserDao() {
		return new UserDao(this.dataSource);
	}

	@Override
	public UserProfilePersistence getJobSeekerDao() {
		return new JobSeekerDao(this.dataSource);
	}

	@Override
	public UserProfilePersistence getEmployerDao() {
		return new EmployerDao(this.dataSource);
	}

	@Override
	public JobAccountPersistence getJobCvDao() {
		return new JobCvDao(this.dataSource);
	}

	@Override
	public JobAccountPersistence getJobAdvertisementDao() {
		return new JobAdvertisementDao(this.dataSource);
	}

}
