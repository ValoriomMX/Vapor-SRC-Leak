package dev.astro.net.utils.chat;

import java.time.*;
import org.apache.commons.lang.time.*;
import java.text.*;
import dev.astro.net.*;
import java.util.*;

public class DateTimeFormats
{
    public static TimeZone SERVER_TIME_ZONE;
    public static ZoneId SERVER_ZONE_ID;
    public static FastDateFormat DAY_MTH_HR_MIN_SECS;
    public static FastDateFormat DAY_MTH_YR_HR_MIN_AMPM;
    public static FastDateFormat DAY_MTH_HR_MIN_AMPM;
    public static FastDateFormat HR_MIN_AMPM;
    public static FastDateFormat HR_MIN_AMPM_TIMEZONE;
    public static FastDateFormat HR_MIN;
    public static FastDateFormat MIN_SECS;
    public static FastDateFormat KOTH_FORMAT;
    public static ThreadLocal<DecimalFormat> REMAINING_SECONDS;
    public static ThreadLocal<DecimalFormat> REMAINING_SECONDS_TRAILING;
    
    static {
        DateTimeFormats.SERVER_TIME_ZONE = TimeZone.getTimeZone(Vapor.getInstance().getConfig().getString("TIMEZONE"));
        DateTimeFormats.SERVER_ZONE_ID = DateTimeFormats.SERVER_TIME_ZONE.toZoneId();
        DateTimeFormats.DAY_MTH_HR_MIN_SECS = FastDateFormat.getInstance("dd/MM HH:mm:ss", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        DateTimeFormats.DAY_MTH_YR_HR_MIN_AMPM = FastDateFormat.getInstance("dd/MM/yy hh:mma", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        DateTimeFormats.DAY_MTH_HR_MIN_AMPM = FastDateFormat.getInstance("dd/MM hh:mma", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        DateTimeFormats.HR_MIN_AMPM = FastDateFormat.getInstance("hh:mma", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        DateTimeFormats.HR_MIN_AMPM_TIMEZONE = FastDateFormat.getInstance("hh:mma z", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        DateTimeFormats.HR_MIN = FastDateFormat.getInstance("hh:mm", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        DateTimeFormats.MIN_SECS = FastDateFormat.getInstance("mm:ss", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        DateTimeFormats.KOTH_FORMAT = FastDateFormat.getInstance("m:ss", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        DateTimeFormats.REMAINING_SECONDS = new ThreadLocal<DecimalFormat>() {
            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat("0.#");
            }
        };
        DateTimeFormats.REMAINING_SECONDS_TRAILING = new ThreadLocal<DecimalFormat>() {
            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat("0.0");
            }
        };
    }
    
    private DateTimeFormats() {
    }
}
