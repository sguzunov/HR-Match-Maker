package persistence;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import common.EnumUtils;
import common.SqlQueries;
import enums.UserType;
import helpers.PersistenceHelper;
import models.User;
import persistence.contracts.UserPersistence;
import persistence.sources.DataSource;

public class UserDao extends Dao implements UserPersistence {
	private static final String USERNAME_COLUMN = "username";
	private static final String PASSWORD_COLUMN = "password";
	private static final String USERTYPE_COLUMN = "usertype";

	public UserDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public <E> void create(E data) {
		User newUser = (User) data;
		try {
			super.openConnection();
			super.defineStatement(SqlQueries.CREATE_USER_SQL_QUERY);

			// Database requires strings for values.
			String usertypeAsString = EnumUtils.ConvertEnumValueToString(newUser.getUserType());
			super.preparedStatement.setString(1, newUser.getUserName());
			super.preparedStatement.setString(2, newUser.getPassword());
			super.preparedStatement.setString(3, usertypeAsString);

			// Add the required user type to database.
			PersistenceHelper.insertUserTypeIntoDatabase(connection, usertypeAsString);

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
			super.defineStatement(SqlQueries.RETRIEVE_ALL_USERS_SQL_QUERY);

			super.resultSet = super.preparedStatement.executeQuery();
			queryReult = new ArrayList<E>();
			while (super.resultSet.next()) {
				String userName = super.resultSet.getString(USERNAME_COLUMN);
				String password = super.resultSet.getString(PASSWORD_COLUMN);
				String userTypeAsString = super.resultSet.getString(USERTYPE_COLUMN);

				UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
				User user = new User(userName, password, userType);
				queryReult.add((E) user);
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
}
