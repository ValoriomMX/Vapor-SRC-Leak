package dev.astro.net.utils.chat;

import java.util.concurrent.atomic.*;
import org.apache.commons.lang.time.*;
import java.text.*;
import java.time.*;
import dev.astro.net.*;
import com.google.common.base.*;
import java.util.*;

public class Formats
{
    private static AtomicBoolean loaded;
    public static FastDateFormat DAY_MTH_HR_MIN_SECS;
    public static FastDateFormat DAY_MTH_YR_HR_MIN_AMPM;
    public static FastDateFormat DAY_MTH_HR_MIN_AMPM;
    public static FastDateFormat HR_MIN_AMPM;
    public static FastDateFormat MNT_DAY_HR_MIN_AMPH;
    public static FastDateFormat HR_MIN_AMPM_TIMEZONE;
    public static FastDateFormat HR_MIN;
    public static FastDateFormat KOTH_FORMAT;
    public static ThreadLocal<DecimalFormat> SECONDS;
    public static ThreadLocal<DecimalFormat> REMAINING_SECONDS;
    public static ThreadLocal<DecimalFormat> REMAINING_SECONDS_TRAILING;
    public static TimeZone SERVER_TIME_ZONE;
    public static ZoneId SERVER_ZONE_ID;
    
    static {
        Formats.loaded = new AtomicBoolean(false);
        Formats.SECONDS = (ThreadLocal<DecimalFormat>)new ThreadLocal() {
            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat("0");
            }
        };
        Formats.REMAINING_SECONDS = new ThreadLocal<DecimalFormat>() {
            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat("0.#");
            }
        };
        Formats.REMAINING_SECONDS_TRAILING = new ThreadLocal<DecimalFormat>() {
            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat("0.0");
            }
        };
        Formats.SERVER_TIME_ZONE = TimeZone.getTimeZone(Vapor.getInstance().getConfig().getString("TIMEZONE"));
        Formats.SERVER_ZONE_ID = Formats.SERVER_TIME_ZONE.toZoneId();
    }
    
    private Formats() {
    }
    
    public static void reload(final TimeZone timeZone) throws IllegalStateException {
        Preconditions.checkArgument(!Formats.loaded.getAndSet(true), (Object)"Already loaded");
        Formats.DAY_MTH_HR_MIN_SECS = FastDateFormat.getInstance("dd/MM HH:mm:ss", timeZone, Locale.US);
        Formats.MNT_DAY_HR_MIN_AMPH = FastDateFormat.getInstance("MM/dd HH:mm:ss", timeZone, Locale.US);
        Formats.DAY_MTH_YR_HR_MIN_AMPM = FastDateFormat.getInstance("dd/MM hh:mma", timeZone, Locale.US);
        Formats.DAY_MTH_HR_MIN_AMPM = FastDateFormat.getInstance("dd/MM hh:mma", timeZone, Locale.US);
        Formats.HR_MIN_AMPM = FastDateFormat.getInstance("hh:mma", timeZone, Locale.US);
        Formats.HR_MIN_AMPM_TIMEZONE = FastDateFormat.getInstance("hh:mma z", timeZone, Locale.US);
        Formats.HR_MIN = FastDateFormat.getInstance("hh:mm", timeZone, Locale.US);
        Formats.KOTH_FORMAT = FastDateFormat.getInstance("m:ss", timeZone, Locale.US);
    }
}
