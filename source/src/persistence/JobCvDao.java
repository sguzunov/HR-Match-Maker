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
import models.Location;
import models.User;
import persistence.contracts.JobAccountPersistence;
import persistence.sources.DataSource;

public class JobCvDao extends Dao implements JobAccountPersistence {
	protected static final String WORKPOSITION_COLUMN = "workposition";
	protected static final String WORKTYPE_COLUMN = "worktype";
	protected static final String REQUIREDEXPERIENCE_COLUMN = "requiredexperience";
	private static final String JOBCV_ID_COLUMN = "jobcv_id";

	public JobCvDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public <E> E selectBy(String identifier) {
		JobCV jobCV = null;
		int id = Integer.parseInt(identifier);
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.RETRIEVE_JOBCV_BY_ID_SQL_QUERY);

			super.preparedStatement.setInt(1, id);
			super.resultSet = super.preparedStatement.executeQuery();
			while (super.resultSet.next()) {
				String userName = super.resultSet.getString(USERNAME_COLUMN);
				String userTypeAsString = super.resultSet.getString(USERTYPE_COLUMN);
				String city = super.resultSet.getString(CITY_COLUMN);
				String country = super.resultSet.getString(COUNTRY_COLUMN);
				String educationAsString = super.resultSet.getString(EDUCATION_COLUMN);
				String workPositionAsString = super.resultSet.getString(WORKPOSITION_COLUMN);
				String worktypeAsString = super.resultSet.getString(WORKTYPE_COLUMN);
				boolean requiredExperience = super.resultSet.getBoolean(REQUIREDEXPERIENCE_COLUMN);
				int age = super.resultSet.getInt(AGE_COLUMN);

				UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
				Education requiredEducation = EnumUtils.ConvertStringToEnumValue(educationAsString, Education.class);
				WorkPosition workPosition = EnumUtils.ConvertStringToEnumValue(workPositionAsString,
						WorkPosition.class);
				WorkType workType = EnumUtils.ConvertStringToEnumValue(worktypeAsString, WorkType.class);

				User user = new User(userName, userType);
				Location location = new Location(city, country);
				Collection<CarrerField> carrerFields = PersistenceHelper.retrieveAllCarrerFields(super.connection,
						SqlQueries.RETRIEVE_ALL_CARRERFIELDS_BY_JOBCVS_SQL_QUERY, id);
				Collection<String> knownLanguages = PersistenceHelper.retrieveAllLanguages(super.connection,
						SqlQueries.RETRIEVE_ALL_LANGUAGES_SQL_QUERY, id);
				jobCV = new JobCV(id, user, location, requiredEducation, workPosition, workType, requiredExperience,
						age, carrerFields, knownLanguages);
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

		return (E) jobCV;
	}

	@Override
	public <E> void create(E data) {
		JobCV newJobCV = (JobCV) data;
		try {
			super.connection = super.dataSource.getConnection();
			super.preparedStatement = connection.prepareStatement(SqlQueries.CREATE_JOBCV_SQL_QUERY);

			// Database requires strings for values.
			String educationAsString = EnumUtils.ConvertEnumValueToString(newJobCV.getRequiredEducation());
			String workPositionAsString = EnumUtils.ConvertEnumValueToString(newJobCV.getWorkPostion());
			String workTypeAsString = EnumUtils.ConvertEnumValueToString(newJobCV.getWorkType());
			super.preparedStatement.setString(1, newJobCV.getCreatedBy().getUserName());
			super.preparedStatement.setString(2, newJobCV.getLocation().getCity());
			super.preparedStatement.setString(3, newJobCV.getLocation().getCountry());
			super.preparedStatement.setString(4, educationAsString);
			super.preparedStatement.setString(5, workPositionAsString);
			super.preparedStatement.setString(6, workTypeAsString);
			super.preparedStatement.setBoolean(7, newJobCV.isRequiredExperience());
			super.preparedStatement.setInt(8, newJobCV.getAge());

			// Add required location to the database.
			PersistenceHelper.insertLocationIntoDatabase(super.connection, newJobCV.getLocation().getCity(),
					newJobCV.getLocation().getCountry());

			// Add required education to the database.
			PersistenceHelper.insertEducationIntoDatabase(super.connection, educationAsString);

			// Add required work position to the database.
			PersistenceHelper.insertWorkPositionIntoDatabase(super.connection, workPositionAsString);

			// Add required work type to the database.
			PersistenceHelper.insertWorkTypeIntoDatabase(super.connection, workTypeAsString);

			super.preparedStatement.executeUpdate();

			int jobcvLastInsertId = PersistenceHelper.getLastInsertedId(super.connection);
			PersistenceHelper.establishConnectionWithCarrerFields(super.connection,
					SqlQueries.JOBCVS_CARRERFIELDS_CONNECTION_SQL_QUERY, newJobCV.getCarrerField(), jobcvLastInsertId);
			PersistenceHelper.establishConnectionWithLanguages(super.connection,
					SqlQueries.JOBCVS_LANGUAGES_CONNECTION_SQL_QUERY, newJobCV.getKnownLanguages(), jobcvLastInsertId);
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
			super.defineStatement(SqlQueries.RETRIEVE_ALL_JOBCVS_SQL_QUERY);

			super.resultSet = super.preparedStatement.executeQuery();
			queryReult = new ArrayList<E>();
			while (super.resultSet.next()) {
				int jobCvId = super.resultSet.getInt(JOBCV_ID_COLUMN);
				String userName = super.resultSet.getString(USERNAME_COLUMN);
				String userTypeAsString = super.resultSet.getString(USERTYPE_COLUMN);
				String city = super.resultSet.getString(CITY_COLUMN);
				String country = super.resultSet.getString(COUNTRY_COLUMN);
				String educationAsString = super.resultSet.getString(EDUCATION_COLUMN);
				String workPositionAsString = super.resultSet.getString(WORKPOSITION_COLUMN);
				String worktypeAsString = super.resultSet.getString(WORKTYPE_COLUMN);
				boolean requiredExperience = super.resultSet.getBoolean(REQUIREDEXPERIENCE_COLUMN);
				int age = super.resultSet.getInt(AGE_COLUMN);

				UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
				Education requiredEducation = EnumUtils.ConvertStringToEnumValue(educationAsString, Education.class);
				WorkPosition workPosition = EnumUtils.ConvertStringToEnumValue(workPositionAsString,
						WorkPosition.class);
				WorkType workType = EnumUtils.ConvertStringToEnumValue(worktypeAsString, WorkType.class);
				User user = new User(userName, userType);
				Location location = new Location(city, country);
				Collection<CarrerField> carrerFields = PersistenceHelper.retrieveAllCarrerFields(super.connection,
						SqlQueries.RETRIEVE_ALL_CARRERFIELDS_BY_JOBCVS_SQL_QUERY, jobCvId);
				Collection<String> knownLanguages = PersistenceHelper.retrieveAllLanguages(super.connection,
						SqlQueries.RETRIEVE_ALL_LANGUAGES_SQL_QUERY, jobCvId);
				JobCV jobCV = new JobCV(jobCvId, user, location, requiredEducation, workPosition, workType,
						requiredExperience, age, carrerFields, knownLanguages);

				queryReult.add((E) jobCV);
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
		JobCV jobCV = (JobCV) data;
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.UPDATE_JOBCV_SQL_QUERY);

			String educationAsString = EnumUtils.ConvertEnumValueToString(jobCV.getRequiredEducation());
			String workPositionAsString = EnumUtils.ConvertEnumValueToString(jobCV.getWorkPostion());
			String workTypeAsString = EnumUtils.ConvertEnumValueToString(jobCV.getWorkType());

			super.preparedStatement.setBoolean(1, jobCV.isRequiredExperience());
			super.preparedStatement.setInt(2, jobCV.getAge());
			super.preparedStatement.setString(3, jobCV.getLocation().getCity());
			super.preparedStatement.setString(4, jobCV.getLocation().getCountry());
			super.preparedStatement.setString(5, educationAsString);
			super.preparedStatement.setString(6, workPositionAsString);
			super.preparedStatement.setString(7, workTypeAsString);

			super.preparedStatement.executeUpdate();

			// TODO: End logic for updating languages and carrer fields.

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
