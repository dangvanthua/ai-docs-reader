package com.viai.ai_docs_reader.util;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneUtils {

    public static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

    public static ZonedDateTime getCurrentTimeInZone(String timeZone) {
        return ZonedDateTime.now(ZoneId.of(timeZone));
    }

    public static ZonedDateTime convertUtcToZone(OffsetDateTime utcTime, String timeZone) {
        return utcTime.atZoneSameInstant(ZoneId.of(timeZone));
    }

    public static String formatDateTime(ZonedDateTime time, DateTimeFormatter formatter) {
        return time.format(formatter);
    }

    public static String formatDateTimeIso(ZonedDateTime time) {
        return time.format(ISO_FORMATTER);
    }
}
