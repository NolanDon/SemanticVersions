package main.helpers;

public class HelperMethods {
	public static boolean scanIllegalArguments(String str) throws IllegalArgumentException {

		boolean result = false;

		for (int i = 0; i < str.length(); i ++) {
			if (!(str.charAt(i) >= '0') && str.charAt(i) <= '9' || str.charAt(i) == '.') {
				throw new IllegalArgumentException();
			}
		}

		return result;
	}
}
