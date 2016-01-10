package persistence.contracts;

public interface Selectable {
	public <E> E selectBy(int identifier);
}
