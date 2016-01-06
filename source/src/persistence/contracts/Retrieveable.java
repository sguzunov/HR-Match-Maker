package persistence.contracts;

import java.sql.SQLException;
import java.util.Collection;

public interface Retrieveable {
	public <E> Collection<E> retrieve() throws ClassNotFoundException, SQLException;
}
