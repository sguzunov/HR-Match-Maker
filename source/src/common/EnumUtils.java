package common;

public class EnumUtils {
	public static <E extends Enum<E>> E ConvertStringToEnumValue(String userTypeAsString, Class<E> type) {
		E userType = Enum.valueOf(type, userTypeAsString);

		return userType;
	}

	public static <E extends Enum<E>> String ConvertEnumValueToString(E enumValue) {
		String enumValueAsString = enumValue.toString();

		return enumValueAsString;
	}
}
