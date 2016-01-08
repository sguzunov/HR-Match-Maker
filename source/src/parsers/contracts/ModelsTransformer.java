package parsers.contracts;

import java.util.Collection;

public interface ModelsTransformer {

	public <E> String transformModelToString(E model);

	public <E> E transformStringToModel(String modelAsString, Class<E> type);

	public <E> String transformCollectionToString(Collection<E> models);
}
