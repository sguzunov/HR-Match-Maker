package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import common.EnumUtils;
import enums.UserType;
import helpers.DatabaseHelper;
import models.User;

public class UserDao extends Dao {
	private static final String CREATE_USER_SQL_QUERY = "INSERT INTO Users" + "(username,password,fk_usertype_id) "
			+ "VALUES(?,?,(SELECT usertype_id FROM usertypes WHERE usertype=?));";
	private static final String RETRIEVE_ALL_USERS_SQL_QUERY = "SELECT username,usertype " + "FROM users "
			+ "INNER JOIN usertypes ON " + "users.fk_usertype_id=usertype_id";
	private static final String UPDATE_USER_PASSWORD_SQL_QUERY = "UPDATE users " + "SET password=? "
			+ "WHERE username=?;";

	public UserDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public <E> void create(E data) {
		User newUser = (User) data;
		try {
			super.openConnection();
			super.defineStatement(CREATE_USER_SQL_QUERY);

			// Database requires strings for values.
			String usertypeAsString = EnumUtils.ConvertEnumValueToString(newUser.getUserType());
			super.preparedStatement.setString(1, newUser.getUserName());
			super.preparedStatement.setString(2, newUser.getPassword());
			super.preparedStatement.setString(3, usertypeAsString);

			// Add the required user type to database.
			DatabaseHelper.insertUserTypeIntoDatabase(connection, usertypeAsString);

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
	public <E> Collection<E> retrieve() throws ClassNotFoundException, SQLException {
		Collection<E> queryReult = null;
		try {
			super.openConnection();
			super.defineStatement(RETRIEVE_ALL_USERS_SQL_QUERY);

			super.resultSet = super.preparedStatement.executeQuery();
			queryReult = new ArrayList<E>();
			while (super.resultSet.next()) {
				String userName = super.resultSet.getString("username");
				String userTypeAsString = super.resultSet.getString("usertype");

				UserType userType = EnumUtils.ConvertStringToEnumValue(userTypeAsString, UserType.class);
				User user = new User(userName, userType);
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

	@Override
	public <E> void update(E data) {
		User user = (User) data;

		try {
			super.openConnection();
			super.defineStatement(UPDATE_USER_PASSWORD_SQL_QUERY);

			super.preparedStatement.setString(1, user.getPassword());
			super.preparedStatement.setString(2, user.getUserName());
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
