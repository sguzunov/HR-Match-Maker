package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataSource {
	public Connection connect() throws ClassNotFoundException, SQLException;

	public ResultSet query();

	public Boolean update();

	public Boolean disconnect();
}
