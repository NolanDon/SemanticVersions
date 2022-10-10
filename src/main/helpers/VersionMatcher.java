package main.helpers;

import java.util.concurrent.CompletableFuture;
import main.classes.VersionRequest;
import java.util.regex.Pattern;

public class VersionMatcher {

	/** FORMATS VERSION REQUEST IN FIVE STAGES
	 *
	 * 1 = {determineFormat} DETERMINE THE FORMAT OF THE VERSION, eg: (1.0, 10.0, 1.2.3.4.5.6,)
	 * 2 = {determineParts} SPLITS THE VERSION STRING INTO ELEMENTS PARTS, eg: ("1.2.3.4" = ["1", "2", "3", "4"])
	 * 3 = {determineNextVersion} COMPUTES THE STRING INTO AN INT AND INCREMENTS TO NEXT VERSION
	 * 4 = {assembleParts} ASSEMBLES PARTS, MAJOR, MINOR, PATCH, ADDITIONAL INTO ONE STRING
	 * 5 = {determineResult} COMPUTES FINAL RESULT
	 * */

	public enum Format
	{
		FORMAT_DOUBLE_SHORT,
		FORMAT_DOUBLE_LONG,
		FORMAT_SINGLE_LONG,
		FORMAT_SINGLE_SHORT,
		FORMAT_SINGLE_VALUE,
		FORMAT_SINGLE_FAR,
		ERROR;
	}

	private static Pattern REGEX_DOUBLE_SHORT = Pattern.compile("^(([0-9])[0-9]).([0-9])");
	private static Pattern REGEX_DOUBLE_LONG = Pattern.compile("^(([0-9])[0-9]).([0-9]).([0-9])");
	private static Pattern REGEX_SINGLE_LONG = Pattern.compile("^(([0-9]).([0-9]).([0-9]))");
	private static Pattern REGEX_SINGLE_SHORT = Pattern.compile("^(([0-9]).([0-9]))");
	private static Pattern REGEX_SINGLE_VALUE = Pattern.compile("^(([0-9]{1}))");
	private static Pattern REGEX_SINGLE_FAR = Pattern.compile("^(([0-9]|.){7,20})");

	public static boolean scanIllegalArguments(String[] array) throws IllegalArgumentException {

		boolean result = false;

		for (int i = 0; i < array.length; i ++) {
			if (!array[i].matches("^[0-9]|.")) {
				throw new IllegalArgumentException();
			}
		}

		return result;
	}

	/** SPLIT STRING INTO ELEMENTS & SCAN ILLEGAL CHARS
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<VersionRequest>
	 * */
	public CompletableFuture<VersionRequest> determineParts(VersionRequest request)
	{
		String currentVersion = request.getCurrentVersion();
		String[] array = currentVersion.split(".");

		try {
			scanIllegalArguments(array);
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return CompletableFuture.completedFuture(request.failed());
		}


		request.setParts(array);
		return CompletableFuture.completedFuture(request);
	}

	/** ASSEMBLE THE STRING BACK AFTER INCREMENTATION
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<VersionRequest>
	 * */
	public CompletableFuture<VersionRequest> assembleParts(VersionRequest request)
	{
		return CompletableFuture.completedFuture(request);
	}

	/** COMPUTES FINAL RESULT
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<VersionRequest>
	 * */
	public CompletableFuture<VersionRequest> determineResult(VersionRequest request)
	{
		return CompletableFuture.completedFuture(request.success());
	}

	/** DETERMINE FORMAT OF THE VERSION
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<VersionRequest>
	 * */
	public CompletableFuture<VersionRequest> determineFormat(VersionRequest request)
	{

		String version = request.getCurrentVersion();

		if (REGEX_DOUBLE_SHORT.matcher(version).matches()) {

			request.setFormat(Format.FORMAT_DOUBLE_SHORT);
			return CompletableFuture.completedFuture(request);
		}

		if (REGEX_SINGLE_FAR.matcher(version).matches()) {

			request.setFormat(Format.FORMAT_SINGLE_FAR);
			return CompletableFuture.completedFuture(request);
		}

		if (REGEX_DOUBLE_LONG.matcher(version).matches()) {

			request.setFormat(Format.FORMAT_DOUBLE_LONG);
			return CompletableFuture.completedFuture(request);
		}

		if (REGEX_SINGLE_LONG.matcher(version).matches()) {

			request.setFormat(Format.FORMAT_SINGLE_LONG);
			return CompletableFuture.completedFuture(request);
		}

		if (REGEX_SINGLE_SHORT.matcher(version).matches()) {

			request.setFormat(Format.FORMAT_SINGLE_SHORT);
			return CompletableFuture.completedFuture(request);
		}

		if (REGEX_SINGLE_VALUE.matcher(version).matches()) {

			request.setFormat(Format.FORMAT_SINGLE_VALUE);
			return CompletableFuture.completedFuture(request);
		}

		request.setFormat(Format.ERROR);
		return CompletableFuture.completedFuture(request);
	}

//	/** DETERMINE FORMAT OF THE VERSION
//	 *
//	 * @param `VersionRequest`
//	 *
//	 * @return CompletableFuture<VersionRequest>
//	 * */
//	public CompletableFuture<VersionRequest> determineNextVersion(VersionRequest request)
//	{
//		return CompletableFuture.completedFuture(request);
//	}

}
