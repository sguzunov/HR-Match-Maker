package persistence;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DataSource {
	public Connection connect();

	public ResultSet query();

	public Boolean update();

	public Boolean disconnect();
}
