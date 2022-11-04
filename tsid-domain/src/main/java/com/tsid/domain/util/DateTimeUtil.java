package com.tsid.domain.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

    public static final String DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final String YMD = "yyyyMMdd";
    public static final String YMD_ = "yyyy-MM-dd";
    public static final String YM_ = "yyyy-MM";
    public static final String YM = "yyyyMM";
    public static final String KST_ZONE = "Asia/Seoul";
    public static final String UTC_ZONE = "UTC";

    public static ZonedDateTime stringToZonedDateTime(String date){
        return stringToZonedDateTime(date, DEFAULT);
    }

    public static ZonedDateTime stringToZonedDateTime(String date, String format){
        if(date == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.of(KST_ZONE));
        return ZonedDateTime.parse(date, formatter);
    }

    public static String zonedDateTimeToString(ZonedDateTime time){
        return zonedDateTimeToString(time, DEFAULT);
    }

    public static String zonedDateTimeToString(ZonedDateTime time, String format){
        if(time == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return time.format(formatter);
    }

    public static ZonedDateTime addSeconds(long seconds){
        if(seconds == 0) return ZonedDateTime.now();
        return ZonedDateTime.now().plusSeconds(seconds);
    }

    public static ZonedDateTime addDays(long days){
        if(days == 0) return ZonedDateTime.now();
        return ZonedDateTime.now().plusDays(days);
    }

    public static ZonedDateTime addMonths(long months){
        if(months == 0) return ZonedDateTime.now();
        return ZonedDateTime.now().plusMonths(months);
    }

    public static String lastDayOfMonth(String date, boolean checkThisMonth){
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4));

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);

        if (checkThisMonth) {
            String thisTime = zonedDateTimeToString(ZonedDateTime.now(), YMD);
            if (thisTime.startsWith(date)) {
                return thisTime.substring(6);
            }
        }

        return String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    public static String todayMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat(YM);
        Calendar c = Calendar.getInstance();
        return sdf.format(c.getTime());
    }

    public static int dayOfWeek(String date) {
        String [] dates = date.split("-");

        LocalDate local = LocalDate.of(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
        DayOfWeek dow = local.getDayOfWeek();
        return dow.getValue();
    }

    public static int getWeekDay(){
        DayOfWeek dayOfWeek = ZonedDateTime.now().getDayOfWeek();
        return dayOfWeek.getValue();
    }

    public static ZonedDateTime viewMonthToEndZonedDateTime(String date){
        if(date==null || date.equals("")){
            return null;
        }

        String yearStr = date.substring(0, 4);
        String monthStr = date.substring(4, 6);

        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);

        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1,1);

        ZonedDateTime lastDateZonedDateTime = Year.of(year).atMonth(month).atDay(cal.getActualMaximum(Calendar.DAY_OF_MONTH)).atTime(0, 0).atZone(ZoneId.of("Asia/Seoul"));
        return lastDateZonedDateTime.plusDays(1L);
    }

    public static ZonedDateTime viewMonthToStartZonedDateTime(String date){
        if(date==null || date.equals("")){
            DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
            Date now =  new Date();
            date = dateFormat.format(now);
        }

        String yearStr = date.substring(0, 4);
        String monthStr = date.substring(4, 6);

        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);

        return  Year.of(year).atMonth(month).atDay(01).atTime(0, 0).atZone(ZoneId.of("Asia/Seoul"));
    }
}
