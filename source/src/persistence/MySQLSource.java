package persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLSource implements DataSource {
	// private static final String dbName;
	private String tableName;
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public Connection connect() {
		// TODO Auto-generated method stub
		return null;
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
