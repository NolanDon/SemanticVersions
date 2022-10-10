package main.helpers;

import java.util.concurrent.CompletableFuture;
import main.classes.VersionRequest;
import java.util.regex.Pattern;

public class VersionMatcher {

	/** FORMATS VERSION REQUEST IN 4 STAGES
	 *
	 * 1 = {determineFormat} DETERMINE THE FORMAT OF THE VERSION, eg: (1.0, 10.0, 1.2.3.4.5.6,)
	 * 2 = {determineParts} SPLITS THE VERSION STRING INTO ELEMENTS PARTS, eg: ("1.2.3.4" = ["1", "2", "3", "4"])
	 * 3 = {determineNextVersion} COMPUTES THE STRING INTO AN INT AND INCREMENTS TO NEXT VERSION
	 * 4 = {assembleParts} ASSEMBLES PARTS, MAJOR, MINOR, PATCH, ADDITIONAL INTO ONE STRING
	 * 5 = {determineResult} COMPUTES FINAL RESULT
	 *
	 * */

	public enum Format
	{
		FORMAT_MAJOR_LEADING_DOUBLE,
		FORMAT_MAJOR_LEADING_SINGLE_SHORT,
		FORMAT_MAJOR_LEADING_SINGLE_LONG,
		FORMAT_MAJOR_SINGLE_VALUE,

		ERROR;
	}

	private static Pattern REGEX_PATTERN_DOUBLE = Pattern.compile("^(([0-9])[0-9]).([0-9]).([0-9])");
	private static Pattern REGEX_PATTERN_SINGLE = Pattern.compile("^(([0-9])[0-9]).([0-9]).([0-9])");
	private static Pattern REGEX_SINGLE_VALUE = Pattern.compile("^(([0-9])[0-9]).([0-9]).([0-9])");

	/** SPLIT STRING INTO ELEMENTS & SCAN ILLEGAL CHARS
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<Format>
	 * */
	public CompletableFuture<Format> determineParts(VersionRequest request)
	{
		return CompletableFuture.completedFuture(Format.FORMAT_MAJOR_SINGLE_VALUE);
	}

	/** ASSEMBLE THE STRING BACK AFTER INCREMENTATION
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<Format>
	 * */
	public CompletableFuture<Format> assembleParts(VersionRequest request)
	{
		return CompletableFuture.completedFuture(Format.FORMAT_MAJOR_SINGLE_VALUE);
	}

	/** COMPUTES FINAL RESULT
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<Format>
	 * */
	public CompletableFuture<Format> determineResult(VersionRequest request)
	{
		return CompletableFuture.completedFuture(Format.FORMAT_MAJOR_SINGLE_VALUE);
	}

	/** DETERMINE FORMAT OF THE VERSION
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<Format>
	 * */
	public CompletableFuture<Format> determineFormat(VersionRequest request)
	{

		String version = request.getCurrentVersion();

		if (REGEX_PATTERN_DOUBLE.matcher(version).matches())
			return CompletableFuture.completedFuture(Format.FORMAT_MAJOR_LEADING_DOUBLE);

		if (REGEX_PATTERN_SINGLE.matcher(version).matches())
			return CompletableFuture.completedFuture(Format.FORMAT_MAJOR_LEADING_SINGLE_SHORT);

		if (REGEX_SINGLE_VALUE.matcher(version).matches())
			return CompletableFuture.completedFuture(Format.FORMAT_MAJOR_SINGLE_VALUE);

		return CompletableFuture.completedFuture(Format.ERROR);
	}

	/** DETERMINE FORMAT OF THE VERSION
	 *
	 * @param `VersionRequest`
	 *
	 * @return CompletableFuture<Format>
	 * */
	public CompletableFuture<Format> determineNextVersion(VersionRequest request)
	{
		return CompletableFuture.completedFuture(Format.FORMAT_MAJOR_SINGLE_VALUE);
	}

}
