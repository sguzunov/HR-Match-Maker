package parsers;

import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import parsers.contracts.ModelsTransformer;

public class JSONModelsTransformer implements ModelsTransformer {
	private static final String JSON_ARRAY_OPEN_STRING = "{[";
	private static final String JSON_ARRAY_CLOSE_STRING = "]}";

	private Gson gson;

	public JSONModelsTransformer() {
		this.gson = new GsonBuilder().create();
	}

	@Override
	public <E> String transformModelToString(E model) {
		String modelAsJsonString = this.gson.toJson(model);

		return modelAsJsonString;
	}

	@Override
	public <E> E transformStringToModel(String modelAsString, Class<E> type) {
		E model = (E) this.gson.fromJson(modelAsString, type);

		return model;
	}

	public <E> String transformCollectionToString(Collection<E> models) {
		String jsonArrayAsString = JSON_ARRAY_OPEN_STRING;
		for (E model : models) {
			jsonArrayAsString += this.transformModelToString(model);
			jsonArrayAsString += ",";
		}

		jsonArrayAsString = jsonArrayAsString.substring(0, jsonArrayAsString.length() - 1);
		jsonArrayAsString += JSON_ARRAY_CLOSE_STRING;

		return jsonArrayAsString;
	}

}
