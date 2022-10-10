package main.helpers;

public class StateHelpers {
    public static boolean containsNonNumericals(String binaryValue) throws IllegalArgumentException {
        return binaryValue.matches("^[0-9]+$");
    }
}
