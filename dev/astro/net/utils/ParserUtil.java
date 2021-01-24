package dev.astro.net.utils;

import java.util.concurrent.*;
import com.google.common.base.*;
import java.math.*;

public class ParserUtil
{
    private static CharMatcher CHAR_MATCHER_ASCII;
    
    static {
        ParserUtil.CHAR_MATCHER_ASCII = CharMatcher.inRange('0', '9').or(CharMatcher.inRange('a', 'z')).or(CharMatcher.inRange('A', 'Z')).or(CharMatcher.WHITESPACE).precomputed();
    }
    
    public static boolean isAlphanumeric(final String string) {
        return ParserUtil.CHAR_MATCHER_ASCII.matchesAllOf((CharSequence)string);
    }
    
    public static Integer tryParseInt(final String string) {
        try {
            return Integer.parseInt(string);
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }
    
    public static Long tryParseLong(final String string) {
        try {
            return Long.parseLong(string);
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    public static Double tryParseDouble(final String string) {
        try {
            return Double.parseDouble(string);
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }
    
    public static Float tryParseFloat(final String string) {
        try {
            return Float.parseFloat(string);
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    public static Short tryParseShort(final String string) {
        try {
            return Short.parseShort(string);
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    public static boolean isInteger(final String string) {
        try {
            Integer.parseInt(string);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public static boolean isLong(final String string) {
        try {
            Long.parseLong(string);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public static boolean isDouble(final String string) {
        try {
            Double.parseDouble(string);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public static boolean isFloat(final String string) {
        try {
            Float.parseFloat(string);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public static boolean isShort(final String string) {
        try {
            Short.parseShort(string);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    private static long convert(final int value, final char unit) {
        switch (unit) {
            case 'y': {
                return value * TimeUnit.DAYS.toMillis(365L);
            }
            case 'M': {
                return value * TimeUnit.DAYS.toMillis(30L);
            }
            case 'd': {
                return value * TimeUnit.DAYS.toMillis(1L);
            }
            case 'h': {
                return value * TimeUnit.HOURS.toMillis(1L);
            }
            case 'm': {
                return value * TimeUnit.MINUTES.toMillis(1L);
            }
            case 's': {
                return value * TimeUnit.SECONDS.toMillis(1L);
            }
            default: {
                return -1L;
            }
        }
    }
    
    public static long parse(final String input) {
        if (input == null || input.isEmpty()) {
            return -1L;
        }
        long result = 0L;
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < input.length(); ++i) {
            final char c = input.charAt(i);
            if (Character.isDigit(c)) {
                number.append(c);
            }
            else {
                final String str;
                if (Character.isLetter(c) && !(str = number.toString()).isEmpty()) {
                    result += convert(Integer.parseInt(str), c);
                    number = new StringBuilder();
                }
            }
        }
        return result;
    }
    
    public static String format(final Number number) {
        return format(number, 5);
    }
    
    public static String format(final Number number, final int decimalPlaces) {
        return format(number, decimalPlaces, RoundingMode.HALF_DOWN);
    }
    
    public static String format(final Number number, final int decimalPlaces, final RoundingMode roundingMode) {
        Preconditions.checkNotNull((Object)number, (Object)"The number cannot be null");
        return new BigDecimal(number.toString()).setScale(decimalPlaces, roundingMode).stripTrailingZeros().toPlainString();
    }
}
