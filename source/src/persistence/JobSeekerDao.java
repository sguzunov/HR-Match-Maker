package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import common.EnumUtils;
import common.SqlQueries;
import enums.CarrerField;
import enums.Education;
import enums.UserType;
import enums.WorkPosition;
import enums.WorkType;
import helpers.PersistenceHelper;
import models.JobCV;
import models.JobSeekerProfile;
import models.Location;
import models.User;
import persistence.contracts.UserProfilePersistence;
import persistence.sources.DataSource;

public class JobSeekerDao extends Dao implements UserProfilePersistence {
	private final static String FIRSTNAME_COLUMN = "firstname";
	private final static String LASTNAME_COLUMN = "lastname";
	private final static String WEBSITE_COLUMN = "website";
	private static final String PROFILE_ID_COLUMN = "profile_id";

	public JobSeekerDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public <E> E selectBy(String identifier) {
		JobSeekerProfile jobSeekerProfile = null;
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.RETRIEVE_JOBSEEKERPROFILE_BY_NAME_SQL_QUERY);

			super.preparedStatement.setString(1, identifier);
			super.resultSet = super.preparedStatement.executeQuery();

			int id = super.resultSet.getInt(PROFILE_ID_COLUMN);
			String userName = super.resultSet.getString(USERNAME_COLUMN);
			String userTypeAsString = super.resultSet.getString(USERTYPE_COLUMN);
			String firstName = super.resultSet.getString(FIRSTNAME_COLUMN);
			String lastName = super.resultSet.getString(LASTNAME_COLUMN);
			String city = super.resultSet.getString(CITY_COLUMN);
			String country = super.resultSet.getString(COUNTRY_COLUMN);
			String webSite = super.resultSet.getString(WEBSITE_COLUMN);
			int age = super.resultSet.getInt(AGE_COLUMN);
			String educationAsString = super.resultSet.getString(EDUCATION_COLUMN);

			UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
			Education requiredEducation = EnumUtils.ConvertStringToEnumValue(educationAsString, Education.class);
			User user = new User(userName, userType);
			Location location = new Location(city, country);

			jobSeekerProfile = new JobSeekerProfile(id, user, firstName, lastName, location, webSite, age,
					requiredEducation);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (super.connection != null) {
				super.closeConnection();
			}
			if (super.preparedStatement != null) {
				super.closePreparedStatement();
			}
			if (super.resultSet != null) {
				super.closeResultSet();
			}
		}

		return (E) jobSeekerProfile;
	}

	@Override
	public <E> void create(E data) {
		JobSeekerProfile jobSeekerProfile = (JobSeekerProfile) data;
		try {
			super.connection = super.dataSource.getConnection();
			super.preparedStatement = connection.prepareStatement(SqlQueries.CREATE_JOBSEEKERPROFILE_SQL_QUERY);

			String educationAsString = EnumUtils.ConvertEnumValueToString(jobSeekerProfile.getEducation());
			String city = jobSeekerProfile.getLocation().getCity();
			String country = jobSeekerProfile.getLocation().getCountry();
			super.preparedStatement.setString(1, jobSeekerProfile.getUser().getUserName());
			super.preparedStatement.setString(2, jobSeekerProfile.getFirstName());
			super.preparedStatement.setString(3, jobSeekerProfile.getLastName());
			super.preparedStatement.setString(4, city);
			super.preparedStatement.setString(5, country);
			super.preparedStatement.setString(6, jobSeekerProfile.getWebSite());
			super.preparedStatement.setInt(7, jobSeekerProfile.getAge());
			super.preparedStatement.setString(8, educationAsString);

			// Add required location in the database
			PersistenceHelper.insertLocationIntoDatabase(super.connection, city, country);

			// Add required education to the database
			PersistenceHelper.insertEducationIntoDatabase(super.connection, educationAsString);

			super.preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (super.connection != null) {
				super.closeConnection();
			}
			if (super.preparedStatement != null) {
				super.closePreparedStatement();
			}
		}

	}

	@Override
	public <E> Collection<E> retrieve() {
		Collection<E> queryReult = null;
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.RETRIEVE_ALL_JOBSEEKERPROFILES_SQL_QUERY);

			super.resultSet = super.preparedStatement.executeQuery();
			queryReult = new ArrayList<E>();
			while (super.resultSet.next()) {
				int id = super.resultSet.getInt(PROFILE_ID_COLUMN);
				String userName = super.resultSet.getString(USERNAME_COLUMN);
				String userTypeAsString = super.resultSet.getString(USERTYPE_COLUMN);
				String firstName = super.resultSet.getString(FIRSTNAME_COLUMN);
				String lastName = super.resultSet.getString(LASTNAME_COLUMN);
				String city = super.resultSet.getString(CITY_COLUMN);
				String country = super.resultSet.getString(COUNTRY_COLUMN);
				String webSite = super.resultSet.getString(WEBSITE_COLUMN);
				int age = super.resultSet.getInt(AGE_COLUMN);
				String educationAsString = super.resultSet.getString(EDUCATION_COLUMN);

				UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
				Education requiredEducation = EnumUtils.ConvertStringToEnumValue(educationAsString, Education.class);
				User user = new User(userName, userType);
				Location location = new Location(city, country);
				JobSeekerProfile jobSeekerProfile = new JobSeekerProfile(id, user, firstName, lastName, location,
						webSite, age, requiredEducation);

				queryReult.add((E) jobSeekerProfile);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (super.connection != null) {
				super.closeConnection();
			}
			if (super.preparedStatement != null) {
				super.closePreparedStatement();
			}
			if (super.resultSet != null) {
				super.closeResultSet();
			}
		}

		return queryReult;
	}

	@Override
	public <E> void update(E data) {
		JobSeekerProfile jobSeekerProfile = (JobSeekerProfile) data;
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.UPDATE_JOBSEEKERPROFILE_SQL_QUERY);

			String educationAsString = EnumUtils.ConvertEnumValueToString(jobSeekerProfile.getEducation());

			super.preparedStatement.setString(1, jobSeekerProfile.getFirstName());
			super.preparedStatement.setString(2, jobSeekerProfile.getLastName());
			super.preparedStatement.setString(3, jobSeekerProfile.getWebSite());
			super.preparedStatement.setInt(4, jobSeekerProfile.getAge());
			super.preparedStatement.setString(5, jobSeekerProfile.getLocation().getCity());
			super.preparedStatement.setString(6, jobSeekerProfile.getLocation().getCountry());
			super.preparedStatement.setString(7, educationAsString);

			super.preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (super.connection != null) {
				super.closeConnection();
			}
			if (super.preparedStatement != null) {
				super.closePreparedStatement();
			}
		}
	}

}
