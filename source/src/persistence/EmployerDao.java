package persistence;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import common.EnumUtils;
import common.SqlQueries;
import enums.CarrerField;

import enums.UserType;
import helpers.PersistenceHelper;
import models.EmployerProfile;
import models.Location;
import models.User;
import persistence.contracts.UserProfilePersistence;
import persistence.sources.DataSource;

public class EmployerDao extends Dao implements UserProfilePersistence {
	private final static String FIRSTNAME_COLUMN = "firstname";
	private final static String LASTNAME_COLUMN = "lastname";
	private final static String WEBSITE_COLUMN = "website";
	private static final String PROFILE_ID_COLUMN = "profile_id";
	private static final String COMPANYAME_COLUMN = "companyname";

	public EmployerDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public <E> E selectBy(String identifier) {
		EmployerProfile employerProfile = null;
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.RETRIEVE_EMPLOYERPROFILE_BY_NAME_SQL_QUERY);

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
			String companyName = super.resultSet.getString(COMPANYAME_COLUMN);

			Collection<CarrerField> carrerFields = PersistenceHelper.retrieveAllCarrerFields(super.connection,
					SqlQueries.RETRIEVE_EMPLOYERPROFILE_BY_NAME_SQL_QUERY, id);
			UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
			User user = new User(userName, userType);
			Location location = new Location(city, country);

			employerProfile = new EmployerProfile(id, user, firstName, lastName, location, webSite, companyName,
					carrerFields);
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

		return (E) employerProfile;
	}

	@Override
	public <E> void create(E data) {
		EmployerProfile employerProfile = (EmployerProfile) data;
		try {
			super.connection = super.dataSource.getConnection();
			super.preparedStatement = connection.prepareStatement(SqlQueries.CREATE_EMPLOYERPROFILE_SQL_QUERY);

			String city = employerProfile.getLocation().getCity();
			String country = employerProfile.getLocation().getCountry();
			super.preparedStatement.setString(1, employerProfile.getUser().getUserName());
			super.preparedStatement.setString(2, employerProfile.getFirstName());
			super.preparedStatement.setString(3, employerProfile.getLastName());
			super.preparedStatement.setString(4, city);
			super.preparedStatement.setString(5, country);
			super.preparedStatement.setString(6, employerProfile.getWebSite());
			super.preparedStatement.setString(7, employerProfile.getCompanyName());

			// Add required location in the database
			PersistenceHelper.insertLocationIntoDatabase(super.connection, city, country);

			super.preparedStatement.executeUpdate();

			int employerProfileLastId = PersistenceHelper.getLastInsertedId(super.connection);

			// Create the connection between all required carrer fields and the
			// new employer profile.
			PersistenceHelper.establishConnectionWithCarrerFields(super.connection,
					SqlQueries.EMPLOYERPROFILES_CARRERFIELDS_CONNECTION_SQL_QUERY, employerProfile.getCarrerFields(),
					employerProfileLastId);
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
			super.defineStatement(SqlQueries.RETRIEVE_ALL_EMPLOYERPROFILES_SQL_QUERY);

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
				String companyName = super.resultSet.getString(COMPANYAME_COLUMN);

				UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
				User user = new User(userName, userType);
				Location location = new Location(city, country);
				Collection<CarrerField> carrerFields = PersistenceHelper.retrieveAllCarrerFields(super.connection,
						SqlQueries.RETRIEVE_ALL_CARRERFIELDS_BY_EMPLOYERPROFILES_SQL_QUERY, id);

				EmployerProfile employerProfile = new EmployerProfile(id, user, firstName, lastName, location, webSite,
						companyName, carrerFields);

				queryReult.add((E) employerProfile);
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
		EmployerProfile employerProfile = (EmployerProfile) data;
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.UPDATE_EMPLOYERPROFILE_SQL_QUERY);

			super.preparedStatement.setString(1, employerProfile.getFirstName());
			super.preparedStatement.setString(2, employerProfile.getLastName());
			super.preparedStatement.setString(3, employerProfile.getWebSite());
			super.preparedStatement.setString(4, employerProfile.getCompanyName());
			super.preparedStatement.setString(5, employerProfile.getLocation().getCity());
			super.preparedStatement.setString(6, employerProfile.getLocation().getCountry());

			super.preparedStatement.executeUpdate();

			// TODO: End logic for updating carrer fields.

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
