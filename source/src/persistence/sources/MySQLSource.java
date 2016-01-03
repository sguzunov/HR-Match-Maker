package persistence.sources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLSource implements DataSource {
	private static final String DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/hrmatchmakerdb";
	private static final String DB_USER = "owner";
	private static final String DB_PASSWORD = "ownerpw";

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DB_DRIVER_NAME);
		Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		return connection;
	}

	@Override
	public Boolean disconnect(Connection connection) throws SQLException {
		if (connection == null) {
			return false;
		} else {
			connection.close();
			return true;
		}
	}

}
