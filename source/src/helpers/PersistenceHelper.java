package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import common.SqlQueries;

public class PersistenceHelper {
	public static void insertLocationIntoDatabase(Connection connection, String city, String country)
			throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_LOCATION_SQL_QUERY);
		preparedStatement.setString(1, city);
		preparedStatement.setString(2, country);
		preparedStatement.executeUpdate();
	}

	public static void insertUserTypeIntoDatabase(Connection connection, String userType) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_USERTYPE_SQL_QUERY);
		preparedStatement.setString(1, userType);
		preparedStatement.executeUpdate();
	}

	public static void insertEducationIntoDatabase(Connection connection, String education) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_EDUCATION_SQL_QUERY);
		preparedStatement.setString(1, education);
		preparedStatement.executeUpdate();
	}

	public static void insertWorkPositionIntoDatabase(Connection connection, String workPosition) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_WORKPOSITION_SQL_QUERY);
		preparedStatement.setString(1, workPosition);
		preparedStatement.executeUpdate();
	}

	public static void insertWorkTypeIntoDatabase(Connection connection, String workType) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_WORKTYPE_SQL_QUERY);
		preparedStatement.setString(1, workType);
		preparedStatement.executeUpdate();
	}

	public static void insertCarrerFieldIntoDatabase(Connection connection, String carrerField) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_CARRERFIELD_SQL_QUERY);
		preparedStatement.setString(1, carrerField);
		preparedStatement.executeUpdate();
	}

	public static void insertLanguageIntoDatabase(Connection connection, String language) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_LANGUAGE_SQL_QUERY);
		preparedStatement.setString(1, language);
		preparedStatement.executeUpdate();
	}

	public static void insertIntoConnectionTable(Connection connection, String connectionSqlQuery, int firstId,
			int secondId) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(connectionSqlQuery);
		preparedStatement.setInt(1, firstId);
		preparedStatement.setInt(2, secondId);
		preparedStatement.executeUpdate();
	}

	public static Collection<String> retrieveDataFromConnectionTable(Connection connection, String sqlQuery,
			String tableFieldName, int id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		Collection<String> queryResult = new ArrayList<String>();
		while (resultSet.next()) {
			String data = resultSet.getString(tableFieldName);
			queryResult.add(data);
		}

		return queryResult;
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