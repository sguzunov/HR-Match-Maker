package persistence;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.sources.DataSource;

public abstract class Dao {
	protected static final String USERNAME_COLUMN = "username";
	protected static final String USERTYPE_COLUMN = "usertype";
	protected static final String USERID_COLUMN = "user_id";
	protected static final String CITY_COLUMN = "city";
	protected static final String COUNTRY_COLUMN = "country";
	protected static final String EDUCATION_COLUMN = "education";
	protected static final String AGE_COLUMN = "age";

	protected Connection connection;
	protected PreparedStatement preparedStatement;
	protected ResultSet resultSet;
	protected DataSource dataSource;

	public Dao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected void openConnection() throws ClassNotFoundException, SQLException {
		this.connection = this.dataSource.getConnection();
	}

	protected void defineStatement(String sqlQuery) throws SQLException {
		this.preparedStatement = this.connection.prepareStatement(sqlQuery);
	}

	protected void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void closePreparedStatement() {
		try {
			this.preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void closeResultSet() {
		try {
			this.resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
