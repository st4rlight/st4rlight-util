package cn.st4rlight.util.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 时间相关常量与转换
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-27
 */
public class DateTimeUtil {

    private DateTimeUtil() {
    }


    /**
     * 常用时间相关常量
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 常用时间转换模式
     */
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD_HHMMSS);
    /**
     * 存储常用变量加快转换
     */
    public static final ZoneId SYSTEM_ZONE_ID = ZoneId.systemDefault();


    /**
     * 时间戳 -- LocalDateTime 相互转换
     */
    public static long toTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(SYSTEM_ZONE_ID).toInstant().toEpochMilli();
    }
    public static LocalDateTime toLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, SYSTEM_ZONE_ID);
    }

    /**
     * 转成时间戳字符串
     */
    public static String toDateTimeString(long timestamp) {
        LocalDateTime localDateTime = toLocalDateTime(timestamp);
        return toDateTimeString(localDateTime);
    }
    public static String toDateTimeString(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * 字符串 --> 时间戳 / LocalDateTime
     */
    public static LocalDateTime strToDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
    }
    public static long strToTimestamp(String dateTimeStr) {
        LocalDateTime localDateTime = strToDateTime(dateTimeStr);
        return toTimestamp(localDateTime);
    }
}
