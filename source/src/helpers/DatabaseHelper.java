package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {
	private static final String INSERT_LOCATION_SQL_QUERY = "INSERT IGNORE INTO " + "locations(city,country) "
			+ "VALUES(?,?)";
	private static final String INSERT_USERTYPE_SQL_QUERY = "INSERT IGNORE INTO " + "usertypes(usertype) "
			+ "VALUES(?)";
	private static final String INSERT_EDUCATION_SQL_QUERY = "INSERT IGNORE INTO " + "educations(education) "
			+ "VALUES(?)";
	private static final String INSERT_WORKPOSITION_SQL_QUERY = "INSERT IGNORE INTO " + "workpositions(workposition) "
			+ "VALUES(?)";
	private static final String INSERT_WORKTYPE_SQL_QUERY = "INSERT IGNORE INTO " + "worktypes(worktype) "
			+ "VALUES(?)";
	private static final String INSERT_CARRERFIELD_SQL_QUERY = "INSERT IGNORE INTO " + "carrerfields(carrerfield) "
			+ "VALUES(?)";
	private static final String INSERT_LANGUAGE_SQL_QUERY = "INSERT IGNORE INTO " + "languages(language) "
			+ "VALUES(?)";

	public static void insertLocationIntoDatabase(Connection connection, String city, String country)
			throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOCATION_SQL_QUERY);
		preparedStatement.setString(1, city);
		preparedStatement.setString(2, country);
		preparedStatement.executeUpdate();
	}

	public static void insertUserTypeIntoDatabase(Connection connection, String userType) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERTYPE_SQL_QUERY);
		preparedStatement.setString(1, userType);
		preparedStatement.executeUpdate();
	}

	public static void insertEducationIntoDatabase(Connection connection, String education) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EDUCATION_SQL_QUERY);
		preparedStatement.setString(1, education);
		preparedStatement.executeUpdate();
	}

	public static void insertWorkPositionIntoDatabase(Connection connection, String workPosition) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WORKPOSITION_SQL_QUERY);
		preparedStatement.setString(1, workPosition);
		preparedStatement.executeUpdate();
	}

	public static void insertWorkTypeIntoDatabase(Connection connection, String workType) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WORKTYPE_SQL_QUERY);
		preparedStatement.setString(1, workType);
		preparedStatement.executeUpdate();
	}

	public static void insertCarrerFieldIntoDatabase(Connection connection, String carrerField) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARRERFIELD_SQL_QUERY);
		preparedStatement.setString(1, carrerField);
		preparedStatement.executeUpdate();
	}

	public static void insertLanguageIntoDatabase(Connection connection, String language) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LANGUAGE_SQL_QUERY);
		preparedStatement.setString(1, language);
		preparedStatement.executeUpdate();
	}
	
	public static void insertIntoConnectionTable(Connection connection, String tableName, int firstId, int secondId) throws SQLException {
//		String insertConnectionSqlQuery = "INSERT INTO " + tableName + "()";
//		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LANGUAGE_SQL_QUERY);
//		preparedStatement.setString(1, language);
//		preparedStatement.executeUpdate();
	}

	public static int getLastInsertedId(Connection connection) throws SQLException {
		PreparedStatement getLastInsertId = connection.prepareStatement("SELECT LAST_INSERT_ID()");
		ResultSet rs = getLastInsertId.executeQuery();
		int last_insert_Id = 0;
		if (rs.next()) {
			last_insert_Id = rs.getInt("last_insert_id()");
		}

		return last_insert_Id;
	}
}
