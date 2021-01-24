package dev.astro.net.utils;

public class NumberUtils
{
    public static boolean isInteger(final String value) {
        try {
            Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
