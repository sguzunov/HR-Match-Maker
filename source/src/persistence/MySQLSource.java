package persistence;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLSource implements DataSource {
	private static final String DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ databaseName";
	private static final String DB_USER = "owner";
	private static final String DB_PASSWORD = "ownerbase";

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName(DB_DRIVER_NAME);
		Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		return connection;
	}

	@Override
	public ResultSet query() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean disconnect() {
		// TODO Auto-generated method stub
		return null;
	}

	private void createTable() {

	}
}
