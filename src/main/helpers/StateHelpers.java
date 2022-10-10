package main.helpers;

import java.util.regex.Pattern;

public class StateHelpers {
    public static boolean containsNonNumericals(String binaryValue) throws IllegalArgumentException {
        return binaryValue.matches("^[0-9]+$");
    }

}

