package persistence.contracts;

public interface Creatable {
	public <E> void create(final E data);
}
