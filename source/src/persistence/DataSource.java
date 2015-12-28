package persistence;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {
	public Connection getConnection() throws ClassNotFoundException, SQLException;

	public Boolean disconnect(Connection connection) throws SQLException;
}
