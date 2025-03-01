package com.viai.ai_docs_reader.util;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TimeZoneUtils {

    public static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    public static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
    private static final Set<String> VALID_ZONE_IDS = ZoneId.getAvailableZoneIds();

    public static ZonedDateTime getCurrentTimeInZone(String timeZone) {
        ZoneId zoneId = parseZoneId(timeZone);
        return ZonedDateTime.now(zoneId);
    }

    public static ZonedDateTime convertUtcToZone(OffsetDateTime utcTime, String timeZone) {
        if (utcTime == null) return null;
        ZoneId zoneId = parseZoneId(timeZone);
        return utcTime.atZoneSameInstant(zoneId);
    }

    public static String formatDateTime(ZonedDateTime time, DateTimeFormatter formatter) {
        if (time == null || formatter == null) return null;
        return time.format(formatter);
    }

    public static String formatDateTimeIso(ZonedDateTime time) {
        return formatDateTime(time, ISO_FORMATTER);
    }

    private static ZoneId parseZoneId(String timeZone) {
        if (timeZone == null || timeZone.isBlank() || !VALID_ZONE_IDS.contains(timeZone)) {
            return ZoneId.of("UTC");
        }
        try {
            return ZoneId.of(timeZone);
        } catch (Exception e) {
            return ZoneId.of("UTC");
        }
    }
}