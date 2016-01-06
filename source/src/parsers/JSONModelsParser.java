package parsers;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import parsers.contracts.ModelsParser;

public class JSONModelsParser implements ModelsParser {
	private static final String JSON_ARRAY_NAME = "result";

	private ObjectMapper mapper;

	public JSONModelsParser() {
		this.mapper = new ObjectMapper();

		// All null values will be missed from the json string representation.
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	@Override
	public <E> String parseModelToString(E model) {
		String modelAsString = "";
		try {
			modelAsString = mapper.writeValueAsString(model);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return modelAsString;
	}

	@Override
	public <E> E parseStringToModel(String modelAsString, Class<E> type) {
		E objectFromJsonString = null;
		try {
			objectFromJsonString = (E) mapper.readValue(modelAsString, type);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return objectFromJsonString;
	}

	public <E> String parseCollectionToString(Collection<E> models) {
		String result = "{" + JSON_ARRAY_NAME + "{";
		for (E model : models) {
			result += this.parseModelToString(model);
			result += ",";
		}

		result = result.substring(0, result.length() - 1);
		result += "}]";

		return result;
	}

}
