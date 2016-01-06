package common;

import java.util.UUID;

public class RandomProvider {

	public static String generaterandomUUID(String data) {
		UUID uuid = UUID.fromString(data);

		return uuid.toString();
	}
}
