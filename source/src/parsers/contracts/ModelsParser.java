package parsers.contracts;

import java.util.Collection;

public interface ModelsParser {

	public <E> String parseModelToString(E model);

	public <E> String parseCollectionToString(Collection<E> models);

	public <E> E parseStringToModel(String modelAsString, Class<E> type);
}
