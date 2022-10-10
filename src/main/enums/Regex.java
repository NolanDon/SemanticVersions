package main.enums;

import java.util.regex.Pattern;

public class Regex {
	public static Pattern REGEX_DOUBLE_SHORT = Pattern.compile("^(([0-9])[0-9]).([0-9])");
	public static Pattern REGEX_DOUBLE_LONG = Pattern.compile("^(([0-9])[0-9]).([0-9]).([0-9])");
	public static Pattern REGEX_SINGLE_LONG = Pattern.compile("^(([0-9]).([0-9]).([0-9]))");
	public static Pattern REGEX_SINGLE_SHORT = Pattern.compile("^(([0-9]).([0-9]))");
	public static Pattern REGEX_SINGLE_VALUE = Pattern.compile("^(([0-9]{1}))");
	public static Pattern REGEX_SINGLE_DOUBLE = Pattern.compile("^(([0-9]{2}))");
	public static Pattern REGEX_SINGLE_FAR = Pattern.compile("^(([0-9]|.){7,20})");
}
