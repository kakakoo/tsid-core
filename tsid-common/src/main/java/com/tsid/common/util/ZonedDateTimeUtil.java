package com.tsid.common.util;

import com.tsid.common.exception.InvalidException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ZonedDateTimeUtil {

    private final static int DEFAULT_SEARCH = 3;

    private final static int MAX_SEARCH = 6;

    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Seoul");

    public static ZonedDateTime convertLongToZonedDateTime(Long time){
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    public static ZonedDateTime stringToZonedDateTime(String date) {
        if(date==null){
            return null;
        }
        return ZonedDateTime.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(), ZONE_ID);
    }

    public static ZonedDateTime dateTimeToZoneDateTime(LocalDate date) {
        return ZonedDateTime.of(date.atStartOfDay(), ZONE_ID);
    }

    public static ZonedDateTime getStartTime(ZonedDateTime startTime) {
        if (startTime == null) {
            return ZonedDateTime.now().minusMonths(DEFAULT_SEARCH);
        }
        if (startTime.isBefore(ZonedDateTime.now().minusMonths(MAX_SEARCH))) {
            throw new InvalidException(String.format("검색 기간은 <%s>개월까지 제공합니다.", MAX_SEARCH));
        }
        return startTime;
    }

    public static ZonedDateTime getEndTime(ZonedDateTime endTime) {
        if (endTime == null) {
            return ZonedDateTime.now();
        }
        return endTime.plusDays(1);
    }

}
