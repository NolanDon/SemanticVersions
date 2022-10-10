package main.helpers;

import static main.helpers.HelperMethods.scanIllegalArguments;
import static main.helpers.enums.Format.*;
import static main.helpers.enums.Regex.*;
import java.util.concurrent.CompletableFuture;
import main.classes.VersionRequest;

public class VersionMatcher {

	/** FORMATS VERSION REQUEST IN FIVE STAGES
	 *
	 * 1 = {determineFormat} DETERMINE THE FORMAT OF THE VERSION, eg: (1.0, 10.0, 1.2.3.4.5.6,)
	 * 2 = {determineParts} SPLITS THE VERSION STRING INTO ELEMENTS PARTS, eg: ("1.2.3.4" = ["1", "2", "3", "4"])
	 * 3 = {determineNextVersion} COMPUTES THE STRING INTO AN INT AND INCREMENTS TO NEXT VERSION
	 * 4 = {assembleParts} ASSEMBLES PARTS, MAJOR, MINOR, PATCH, ADDITIONAL INTO ONE STRING
	 * 5 = {determineResult} COMPUTES FINAL RESULT
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


	/** SPLIT STRING INTO ELEMENTS & SCAN ILLEGAL CHARS
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<VersionRequest>
	 * */
	public CompletableFuture<VersionRequest> determineParts(VersionRequest request)
	{
		String currentVersion = request.getCurrentVersion();
		String splitString = currentVersion.replaceAll("[.]", "");

		try {
			scanIllegalArguments(splitString);
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return CompletableFuture.completedFuture(request.failed());
		}


		request.setSplitVersion(splitString);
		return CompletableFuture.completedFuture(request);
	}

	/** ASSEMBLE THE STRING BACK AFTER INCREMENTATION
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<VersionRequest>
	 * */
	public CompletableFuture<VersionRequest> determineNextVersion(VersionRequest request)
	{
		String version = request.getSplitVersion();
		int incrementedVersion = Integer.parseInt(version) + 1;

		request.setNewVersion(incrementedVersion);
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
		boolean skip = request.getFormat() == FORMAT_SINGLE_VALUE;

		// SKIP ADDING DECIMALS TO SINGLE VALUES
		String cleanVersion = skip ? request.getNewVersion() : setDecimals(request);

		request.setFinalVersion(cleanVersion);
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
			request.setFormat(FORMAT_DOUBLE_SHORT);
			return CompletableFuture.completedFuture(request);
		}
		if (REGEX_SINGLE_FAR.matcher(version).matches()) {
			request.setFormat(FORMAT_SINGLE_FAR);
			return CompletableFuture.completedFuture(request);
		}
		if (REGEX_DOUBLE_LONG.matcher(version).matches()) {
			request.setFormat(FORMAT_DOUBLE_LONG);
			return CompletableFuture.completedFuture(request);
		}
		if (REGEX_SINGLE_LONG.matcher(version).matches()) {
			request.setFormat(FORMAT_SINGLE_LONG);
			return CompletableFuture.completedFuture(request);
		}
		if (REGEX_SINGLE_SHORT.matcher(version).matches()) {
			request.setFormat(FORMAT_DOUBLE_SHORT);
			return CompletableFuture.completedFuture(request);
		}
		if (REGEX_SINGLE_VALUE.matcher(version).matches()) {
			request.setFormat(FORMAT_SINGLE_VALUE);
			return CompletableFuture.completedFuture(request);
		}

		if (REGEX_SINGLE_DOUBLE.matcher(version).matches()) {
			request.setFormat(FORMAT_SINGLE_VALUE);
			return CompletableFuture.completedFuture(request);
		}

		request.setFormat(ERROR);

		return CompletableFuture.completedFuture(request.failed());
	}

}
