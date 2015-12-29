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
import persistence.sources.DataSource;

public class JobCvDao extends JobAccountDao {
	public JobCvDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public <E> E getById(int id) {
		JobCV jobCV = null;
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.RETRIEVE_JOBCV_BY_ID_SQL_QUERY);

			super.preparedStatement.setInt(1, id);
			super.resultSet = super.preparedStatement.executeQuery();
			while (super.resultSet.next()) {
				String userName = super.resultSet.getString("username");
				String userTypeAsString = super.resultSet.getString("usertype");
				String city = super.resultSet.getString("city");
				String country = super.resultSet.getString("country");
				String educationAsString = super.resultSet.getString("education");
				String workPositionAsString = super.resultSet.getString("workposition");
				String worktypeAsString = super.resultSet.getString("worktype");
				boolean requiredExperience = super.resultSet.getBoolean("requiredexperience");
				int age = super.resultSet.getInt("age");

				UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
				Education requiredEducation = EnumUtils.ConvertStringToEnumValue(educationAsString, Education.class);
				WorkPosition workPosition = EnumUtils.ConvertStringToEnumValue(workPositionAsString,
						WorkPosition.class);
				WorkType workType = EnumUtils.ConvertStringToEnumValue(worktypeAsString, WorkType.class);
				User user = new User(userName, userType);
				Location location = new Location(city, country);
				Collection<CarrerField> carrerFields = retrieveAllCarrerFields(id);
				Collection<String> knownLanguages = retrieveAllLanguages(id);
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

			int jobcv_last_insert_id = PersistenceHelper.getLastInsertedId(super.connection);
			this.establishConnectionWithCarrerFields(newJobCV.getCarrerField(), jobcv_last_insert_id);
			this.establishConnectionWithLanguages(newJobCV.getKnownLanguages(), jobcv_last_insert_id);
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
	public <E> Collection<E> retrieve() throws ClassNotFoundException, SQLException {
		Collection<E> queryReult = null;
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.RETRIEVE_ALL_JOBCVS_SQL_QUERY);

			super.resultSet = super.preparedStatement.executeQuery();
			queryReult = new ArrayList<E>();
			while (super.resultSet.next()) {
				int jobCvId = super.resultSet.getInt("jobcv_id");
				String userName = super.resultSet.getString("username");
				String userTypeAsString = super.resultSet.getString("usertype");
				String city = super.resultSet.getString("city");
				String country = super.resultSet.getString("country");
				String educationAsString = super.resultSet.getString("education");
				String workPositionAsString = super.resultSet.getString("workposition");
				String worktypeAsString = super.resultSet.getString("worktype");
				boolean requiredExperience = super.resultSet.getBoolean("requiredexperience");
				int age = super.resultSet.getInt("age");

				UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
				Education requiredEducation = EnumUtils.ConvertStringToEnumValue(educationAsString, Education.class);
				WorkPosition workPosition = EnumUtils.ConvertStringToEnumValue(workPositionAsString,
						WorkPosition.class);
				WorkType workType = EnumUtils.ConvertStringToEnumValue(worktypeAsString, WorkType.class);
				User user = new User(userName, userType);
				Location location = new Location(city, country);
				Collection<CarrerField> carrerFields = retrieveAllCarrerFields(jobCvId);
				Collection<String> knownLanguages = retrieveAllLanguages(jobCvId);
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
			super.defineStatement(SqlQueries.UPDATE_JOBCV_SQL_QUERY_SQL_QUERY);

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

	private Collection<CarrerField> retrieveAllCarrerFields(int jobCvId) throws SQLException {
		Collection<String> carrerFieldsAsString = PersistenceHelper.retrieveDataFromConnectionTable(super.connection,
				SqlQueries.RETRIEVE_ALL_CARRERFIELDS_SQL_QUERY, "carrerfield", jobCvId);
		Collection<CarrerField> carrerFields = new ArrayList<CarrerField>();
		for (String carrerFieldAsString : carrerFieldsAsString) {
			CarrerField carrerField = EnumUtils.ConvertStringToEnumValue(carrerFieldAsString, CarrerField.class);
			carrerFields.add(carrerField);
		}

		return carrerFields;
	}

	private Collection<String> retrieveAllLanguages(int jobCvId) throws SQLException {
		Collection<String> languages = PersistenceHelper.retrieveDataFromConnectionTable(super.connection,
				SqlQueries.RETRIEVE_ALL_LANGUAGES_SQL_QUERY, "language", jobCvId);

		return languages;
	}

	// Makes the connection between "jobcvs" table and "carrerfields" table in
	// many-to-many relationship
	private void establishConnectionWithCarrerFields(Collection<CarrerField> carrerFields, int jobcv_id)
			throws SQLException {
		for (CarrerField carrerField : carrerFields) {
			String carrerFieldAsString = EnumUtils.ConvertEnumValueToString(carrerField);
			PersistenceHelper.insertCarrerFieldIntoDatabase(super.connection, carrerFieldAsString);
			int carrerField_last_insert_id = PersistenceHelper.getLastInsertedId(super.connection);
			PersistenceHelper.insertIntoConnectionTable(super.connection,
					SqlQueries.JOBCVS_CARRERFIELDS_CONNECTION_SQL_QUERY, jobcv_id, carrerField_last_insert_id);
		}
	}

	// Makes the connection between "jobcvs" table and "languages" table in
	// many-to-many relationship
	private void establishConnectionWithLanguages(Collection<String> languages, int jobcv_last_insert_id)
			throws SQLException {
		for (String language : languages) {
			PersistenceHelper.insertLanguageIntoDatabase(super.connection, language);
			int language_last_insert_id = PersistenceHelper.getLastInsertedId(super.connection);
			PersistenceHelper.insertIntoConnectionTable(super.connection,
					SqlQueries.JOBCVS_CARRERFIELDS_CONNECTION_SQL_QUERY, jobcv_last_insert_id, language_last_insert_id);
		}
	}
}
