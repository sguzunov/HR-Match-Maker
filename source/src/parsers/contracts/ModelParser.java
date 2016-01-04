package parsers.contracts;

public interface ModelParser {

	public <E> String parseModelToString(E model);

	public <E> E parseStringToModel(String modelAsString, E type);
}
