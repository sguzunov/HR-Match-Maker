package persistence.contracts;

public interface Updateble {
	public abstract <E> void update(final E data);
}
