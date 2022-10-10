package main.helpers;

import static main.helpers.HelperMethods.scanIllegalArguments;
import static main.helpers.HelperMethods.setDecimals;
import static main.enums.Format.*;
import static main.enums.Regex.*;
import java.util.concurrent.CompletableFuture;
import main.classes.VersionRequest;

public class VersionMatcher {


	/** SPLIT THE CURRENT VERSION AND SCAN FOR ILLEGAL CHARACTERS
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
