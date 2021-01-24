package dev.astro.net.utils;

import java.util.concurrent.*;
import java.text.*;
import net.minecraft.util.org.apache.commons.lang3.time.*;

public class DurationFormatter
{
    private static long MINUTE;
    private static long HOUR;
    
    static {
        DurationFormatter.MINUTE = TimeUnit.MINUTES.toMillis(1L);
        DurationFormatter.HOUR = TimeUnit.HOURS.toMillis(1L);
    }
    
    public static String getRemaining(final long millis, final boolean milliseconds) {
        return getRemaining(millis, milliseconds, true);
    }
    
    public static String getRemaining(final long duration, final boolean milliseconds, final boolean trail) {
        if (milliseconds && duration < DurationFormatter.MINUTE) {
            return String.valueOf((trail ? DateTimeFormats.REMAINING_SECONDS_TRAILING : DateTimeFormats.REMAINING_SECONDS).get().format(duration * 0.001)) + 's';
        }
        return DurationFormatUtils.formatDuration(duration, String.valueOf((duration >= DurationFormatter.HOUR) ? "HH:" : "") + "mm:ss");
    }
}
