package persistence.contracts;

import java.util.Collection;

public interface Retrieveable {
	public <E> Collection<E> retrieve();
}
