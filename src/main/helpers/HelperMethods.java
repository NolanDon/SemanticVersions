package main.helpers;

import main.classes.VersionRequest;

public class HelperMethods {

	/** SPLIT STRING INTO ELEMENTS & SCAN ILLEGAL CHARS
	 *
	 * @param `String`
	 *
	 * @return CompletableFuture<VersionRequest>
	 * */
	public static boolean scanIllegalArguments(String str) throws IllegalArgumentException {

		for (int i = 0; i < str.length(); i ++) {
			if (!(str.charAt(i) >= '0') && str.charAt(i) <= '9' || str.charAt(i) == '.') {
				throw new IllegalArgumentException();
			}
		}

		return true;
	}

	/** ADDS DECIMALS TO FINAL VERSION BASED ON FORMAT
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<VersionRequest>
	 * */
	public static String setDecimals(VersionRequest request) throws IllegalArgumentException {

		String currentVersion = request.getNewVersion();
		int format = request.getFormat();
		String result = "";

		for (int i = 0; i < currentVersion.length(); i ++) {
			String decimal = ".";
			if (i <= 1) {
				decimal = (i > 0 + format) ? "." : "";
			}
			result += decimal + currentVersion.charAt(i);
		}

		return result;
	}
}
