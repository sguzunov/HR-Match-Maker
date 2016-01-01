package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import persistence.sources.DataSource;

public abstract class Dao {
	protected static final String USERNAME_COLUMN = "username";
	protected static final String USERTYPE_COLUMN = "usertype";
	protected static final String CITY_COLUMN = "city";
	protected static final String COUNTRY_COLUMN = "country";
	protected static final String EDUCATION_COLUMN = "education";
	protected static final String AGE_COLUMN = "age";

	protected DataSource dataSource;
	protected Connection connection;
	protected PreparedStatement preparedStatement;
	protected ResultSet resultSet;

	public Dao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public abstract <E> void create(final E data);

	public abstract <E> Collection<E> retrieve() throws ClassNotFoundException, SQLException;

	public abstract <E> void update(final E data);

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
