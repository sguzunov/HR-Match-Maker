package parsers;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import parsers.contracts.ModelParser;

public class JSONObjectParser implements ModelParser {
	private ObjectMapper mapper;

	public JSONObjectParser() {
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
	public <E> E parseStringToModel(String modelAsString, E type) {
		E objectFromJsonString = null;
		try {
			objectFromJsonString = (E) mapper.readValue(modelAsString, type.getClass());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return objectFromJsonString;
	}

}
