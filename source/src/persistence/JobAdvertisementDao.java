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
import models.JobAdvertisement;
import models.Location;
import models.User;
import persistence.contracts.JobAccountPersistence;
import persistence.sources.DataSource;

public class JobAdvertisementDao extends Dao implements JobAccountPersistence {
	protected static final String WORKPOSITION_COLUMN = "workposition";
	protected static final String WORKTYPE_COLUMN = "worktype";
	protected static final String REQUIREDEXPERIENCE_COLUMN = "requiredexperience";
	private static final String JOBADVERTISEMENT_ID_COLUMN = "jobadvertisement_id";
	private static final String TITLE_COLUMN = "title";
	private static final String RESUME_COLUMN = "resume";

	public JobAdvertisementDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public <E> E selectBy(String identifier) {
		JobAdvertisement jobAd = null;
		int id = Integer.parseInt(identifier);
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.RETRIEVE_JOBADVERTISEMENT_BY_ID_SQL_QUERY);

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
				String title = super.resultSet.getString(TITLE_COLUMN);
				String resume = super.resultSet.getString(RESUME_COLUMN);

				UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
				Education requiredEducation = EnumUtils.ConvertStringToEnumValue(educationAsString, Education.class);
				WorkPosition workPosition = EnumUtils.ConvertStringToEnumValue(workPositionAsString,
						WorkPosition.class);
				WorkType workType = EnumUtils.ConvertStringToEnumValue(worktypeAsString, WorkType.class);
				User user = new User(userName, userType);
				Location location = new Location(city, country);
				Collection<CarrerField> carrerFields = PersistenceHelper.retrieveAllCarrerFields(super.connection,
						SqlQueries.RETRIEVE_ALL_CARRERFIELDS_BY_JOBADVERTISEMENTS_SQL_QUERY, id);
				jobAd = new JobAdvertisement(id, user, location, requiredEducation, workPosition, workType,
						requiredExperience, title, resume, carrerFields);
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

		return (E) jobAd;
	}

	@Override
	public <E> void create(E data) {
		JobAdvertisement newJobAd = (JobAdvertisement) data;
		try {
			super.connection = super.dataSource.getConnection();
			super.preparedStatement = connection.prepareStatement(SqlQueries.CREATE_JOBADVERTISEMENT_SQL_QUERY);

			// Database requires strings for values.
			String educationAsString = EnumUtils.ConvertEnumValueToString(newJobAd.getRequiredEducation());
			String workPositionAsString = EnumUtils.ConvertEnumValueToString(newJobAd.getWorkPostion());
			String workTypeAsString = EnumUtils.ConvertEnumValueToString(newJobAd.getWorkType());
			super.preparedStatement.setString(1, newJobAd.getCreatedBy().getUserName());
			super.preparedStatement.setString(2, newJobAd.getLocation().getCity());
			super.preparedStatement.setString(3, newJobAd.getLocation().getCountry());
			super.preparedStatement.setString(4, educationAsString);
			super.preparedStatement.setString(5, workPositionAsString);
			super.preparedStatement.setString(6, workTypeAsString);
			super.preparedStatement.setBoolean(7, newJobAd.isRequiredExperience());
			super.preparedStatement.setString(8, newJobAd.getTitle());
			super.preparedStatement.setString(9, newJobAd.getResume());

			// Add required location to the database.
			PersistenceHelper.insertLocationIntoDatabase(super.connection, newJobAd.getLocation().getCity(),
					newJobAd.getLocation().getCountry());

			// Add required education to the database.
			PersistenceHelper.insertEducationIntoDatabase(super.connection, educationAsString);

			// Add required work position to the database.
			PersistenceHelper.insertWorkPositionIntoDatabase(super.connection, workPositionAsString);

			// Add required work type to the database.
			PersistenceHelper.insertWorkTypeIntoDatabase(super.connection, workTypeAsString);

			super.preparedStatement.executeUpdate();

			int jobcvLastInsertId = PersistenceHelper.getLastInsertedId(super.connection);
			PersistenceHelper.establishConnectionWithCarrerFields(super.connection,
					SqlQueries.JOBADVERTISEMENTS_CARRERFIELDS_CONNECTION_SQL_QUERY, newJobAd.getCarrerField(),
					jobcvLastInsertId);
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
			super.defineStatement(SqlQueries.RETRIEVE_ALL_JOBADVERTISEMENTS_SQL_QUERY);

			super.resultSet = super.preparedStatement.executeQuery();
			queryReult = new ArrayList<E>();
			while (super.resultSet.next()) {
				int jobAdId = super.resultSet.getInt(JOBADVERTISEMENT_ID_COLUMN);
				String userName = super.resultSet.getString(USERNAME_COLUMN);
				String userTypeAsString = super.resultSet.getString(USERTYPE_COLUMN);
				String city = super.resultSet.getString(CITY_COLUMN);
				String country = super.resultSet.getString(COUNTRY_COLUMN);
				String educationAsString = super.resultSet.getString(EDUCATION_COLUMN);
				String workPositionAsString = super.resultSet.getString(WORKPOSITION_COLUMN);
				String worktypeAsString = super.resultSet.getString(WORKTYPE_COLUMN);
				boolean requiredExperience = super.resultSet.getBoolean(REQUIREDEXPERIENCE_COLUMN);
				String title = super.resultSet.getString(TITLE_COLUMN);
				String resume = super.resultSet.getString(RESUME_COLUMN);

				UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
				Education requiredEducation = EnumUtils.ConvertStringToEnumValue(educationAsString, Education.class);
				WorkPosition workPosition = EnumUtils.ConvertStringToEnumValue(workPositionAsString,
						WorkPosition.class);
				WorkType workType = EnumUtils.ConvertStringToEnumValue(worktypeAsString, WorkType.class);

				User user = new User(userName, userType);
				Location location = new Location(city, country);
				Collection<CarrerField> carrerFields = PersistenceHelper.retrieveAllCarrerFields(super.connection,
						SqlQueries.RETRIEVE_ALL_CARRERFIELDS_BY_JOBADVERTISEMENTS_SQL_QUERY, jobAdId);
				JobAdvertisement jobAd = new JobAdvertisement(jobAdId, user, location, requiredEducation, workPosition,
						workType, requiredExperience, title, resume, carrerFields);

				queryReult.add((E) jobAd);
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
		JobAdvertisement jobAd = (JobAdvertisement) data;
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.UPDATE_JOBADVERTISEMENT_SQL_QUERY);

			String educationAsString = EnumUtils.ConvertEnumValueToString(jobAd.getRequiredEducation());
			String workPositionAsString = EnumUtils.ConvertEnumValueToString(jobAd.getWorkPostion());
			String workTypeAsString = EnumUtils.ConvertEnumValueToString(jobAd.getWorkType());

			super.preparedStatement.setBoolean(1, jobAd.isRequiredExperience());
			super.preparedStatement.setString(2, jobAd.getTitle());
			super.preparedStatement.setString(3, jobAd.getResume());
			super.preparedStatement.setString(4, jobAd.getLocation().getCity());
			super.preparedStatement.setString(5, jobAd.getLocation().getCountry());
			super.preparedStatement.setString(6, educationAsString);
			super.preparedStatement.setString(7, workPositionAsString);
			super.preparedStatement.setString(8, workTypeAsString);

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
