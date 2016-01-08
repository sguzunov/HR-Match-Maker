package common;

import java.util.UUID;

public class RandomProvider {

	public static String generaterandomUUIDFromString() {
		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}
}
