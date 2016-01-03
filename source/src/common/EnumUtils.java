package common;

public class EnumUtils {
	public static <E extends Enum<E>> E ConvertStringToEnumValue(String enumAsString, Class<E> type) {
		E userType = Enum.valueOf(type, enumAsString.toUpperCase());

		return userType;
	}

	public static <E extends Enum<E>> String ConvertEnumValueToString(E enumValue) {
		String enumValueAsString = enumValue.toString();

		return enumValueAsString.toLowerCase();
	}
}
