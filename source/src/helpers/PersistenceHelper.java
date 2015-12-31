package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.glassfish.jersey.internal.util.collection.StringIgnoreCaseKeyComparator;

import common.EnumUtils;
import common.SqlQueries;
import enums.CarrerField;

public class PersistenceHelper {
	private static final String CARRERFIELDS_COLUMN_NAME = "carrerfield";
	private static final String LANGUAGE_COLUMN_NAME = "language";

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

	// Selects the last inserted primary key from database
	public static int getLastInsertedId(Connection connection) throws SQLException {
		PreparedStatement getLastInsertId = connection.prepareStatement("SELECT LAST_INSERT_ID()");
		ResultSet rs = getLastInsertId.executeQuery();
		int last_insert_Id = 0;
		if (rs.next()) {
			last_insert_Id = rs.getInt("last_insert_id()");
		}

		return last_insert_Id;
	}

	public static Collection<CarrerField> retrieveAllCarrerFields(Connection connection, String sqlQuery, int id)
			throws SQLException {
		Collection<String> carrerFieldsAsString = PersistenceHelper.retrieveDataFromConnectionTable(connection,
				sqlQuery, CARRERFIELDS_COLUMN_NAME, id);
		Collection<CarrerField> carrerFields = new ArrayList<CarrerField>();
		for (String carrerFieldAsString : carrerFieldsAsString) {
			CarrerField carrerField = EnumUtils.ConvertStringToEnumValue(carrerFieldAsString, CarrerField.class);
			carrerFields.add(carrerField);
		}

		return carrerFields;
	}

	// Get all languages correspondenting from attribute table 'languages'.
	public static Collection<String> retrieveAllLanguages(Connection connection, String sqlQuery, int id)
			throws SQLException {
		Collection<String> languages = PersistenceHelper.retrieveDataFromConnectionTable(connection, sqlQuery,
				LANGUAGE_COLUMN_NAME, id);

		return languages;
	}

	// Makes the connection between related tables in
	// many-to-many relationship.
	public static void establishConnectionWithCarrerFields(Connection connection, String sqlQuery,
			Collection<CarrerField> carrerFields, int id) throws SQLException {
		for (CarrerField carrerField : carrerFields) {

			// Inserting the required value if missing from database.
			String carrerFieldAsString = EnumUtils.ConvertEnumValueToString(carrerField);
			PersistenceHelper.insertCarrerFieldIntoDatabase(connection, carrerFieldAsString);

			// Makes the connection between the two tables.
			int carrerFieldLastInsertId = PersistenceHelper.getLastInsertedId(connection);
			PersistenceHelper.insertIntoConnectionTable(connection, sqlQuery, id, carrerFieldLastInsertId);
		}
	}

	// Makes the connection between related tables in
	// many-to-many relationship.
	public static void establishConnectionWithLanguages(Connection connection, String sqlQuery,
			Collection<String> languages, int id) throws SQLException {
		for (String language : languages) {

			// Insert the value if missing
			PersistenceHelper.insertLanguageIntoDatabase(connection, language);

			// Makes the connection between the two tables.
			int languageLastInsertId = PersistenceHelper.getLastInsertedId(connection);
			PersistenceHelper.insertIntoConnectionTable(connection, sqlQuery, id, languageLastInsertId);
		}
	}
}
