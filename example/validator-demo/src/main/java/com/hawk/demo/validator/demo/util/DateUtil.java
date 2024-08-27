package com.hawk.demo.validator.demo.util;

import lombok.SneakyThrows;

import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

public class DateUtil {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_FORMAT_CN = "yyyy年MM月dd日";
    public static final String PARSE_DATETIME_IS_NULL = "parse date time is null";
    private static final String EMPTY_STR = "";

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Date 转时间
     */
    public static String toString(Date date, String formatter) {
        return new SimpleDateFormat(formatter).format(date);
    }

    /**
     * LocalDateTime ==> String
     * <br/>
     * <p>如果 LocalDateTime 为 null, 则返回空字符串</p>
     */
    public static String toString(LocalDateTime localDateTime, String formatter) {
        if (Objects.isNull(localDateTime)) {
            return EMPTY_STR;
        }
        return DateTimeFormatter.ofPattern(formatter).format(localDateTime);
    }

    /**
     * LocalDate ==> String
     * <br/>
     * <p>如果 LocalDate 为 null, 则返回空字符串</p>
     */
    public static String toString(LocalDate localDate, String formatter) {
        if (Objects.isNull(localDate)) {
            return EMPTY_STR;
        }
        return DateTimeFormatter.ofPattern(formatter).format(localDate);
    }

    public static LocalDate toLocalDate(String localDate, String formatter) {
        Objects.requireNonNull(localDate, PARSE_DATETIME_IS_NULL);
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .appendPattern(formatter)
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .toFormatter();
        return LocalDate.parse(localDate, df);
    }

    public static LocalDate toLocalDate(long milliSecond, ZoneId zoneId) {
        return Instant.ofEpochMilli(milliSecond).atZone(zoneId).toLocalDate();
    }

    public static LocalDate toLocalDate(long milliSecond) {
        return toLocalDate(milliSecond, ZoneId.systemDefault());
    }

    /**
     * 时间字符转Date
     * 此方法 可能抛出异常
     */
    @SneakyThrows
    public static Date toDate(String time, String formatter) {
        return new SimpleDateFormat(formatter).parse(time);
    }

    public static Date toDate(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return Date.from(Timestamp.valueOf(localDate.atTime(LocalTime.MIDNIGHT)).toInstant());
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return toDate(localDateTime, ZoneId.systemDefault());
    }

    public static Date toDate(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    public static Date toDate(long milliSecond) {
        return new Date(milliSecond);
    }

    public static LocalDateTime toLocalDateTime(String localDateTime, String formatter) {
        Objects.requireNonNull(localDateTime, PARSE_DATETIME_IS_NULL);
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .appendPattern(formatter)
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .toFormatter();
        return LocalDateTime.parse(localDateTime, df);
    }

    public static LocalDateTime toLocalDateTime(Date date, ZoneId zoneId) {
        return date == null ? null : date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return toLocalDateTime(date, ZoneId.systemDefault());
    }

    public static LocalDateTime toLocalDateTime(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        Instant instant = Timestamp.valueOf(localDate.atTime(LocalTime.MIDNIGHT)).toInstant();
        return toLocalDateTime(Date.from(instant));
    }

    public static LocalDateTime toLocalDateTime(long millSecond, ZoneId zoneId) {
        return  LocalDateTime.ofInstant(Instant.ofEpochMilli(millSecond), zoneId);
     }

    public static LocalDateTime toLocalDateTime(long millSecond) {
        return  LocalDateTime.ofInstant(Instant.ofEpochMilli(millSecond), ZoneId.systemDefault());
    }
    /**
     * 获取几天后的最大时间
     * 示例：
     * <p>入参为 2024-01-01 23:40:04, 3</p>
     * </p>则返回 2024-01-04 23:59:59</p>
     */
    public static LocalDateTime getMaxTimeOfDatePlusDay(LocalDateTime localDateTime, long addDay) {
        Objects.requireNonNull(localDateTime);
        return getMaxTimeOfDate(localDateTime.plusDays(addDay));
    }
    /**
     * 获取几天后的最小时间
     * 示例：
     * <p>入参为 2024-01-04 23:40:04, 3</p>
     * </p>则返回 2024-01-01 00:00:00</p>
     */
    public static LocalDateTime getMaxTimeOfDateMinusDay(LocalDateTime localDateTime, long minusDay) {
        Objects.requireNonNull(localDateTime);
        return getMinTimeOfDate(localDateTime.minusDays(minusDay));
    }

    public static LocalDateTime getMaxTimeOfDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime);
        return localDateTime.withHour(23).withMinute(59).withSecond(59);
    }

    public static LocalDateTime getMinTimeOfDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime);
        return localDateTime.withHour(0).withMinute(0).withSecond(0);
    }

    /**
     * 计算两个时间的差值，不足设定单位的部分会被舍弃
     * <p>示例：</p>
     * <p>入参：2024-01-08, 2024-01-05, ChronoUnit.DAYS</p>
     * <p>返回：-3</p>
     */
    public static long getDistance(LocalDate beginDate, LocalDate endDate, ChronoUnit chronoUnit) {
        Objects.requireNonNull(beginDate, "beginDate is null");
        Objects.requireNonNull(endDate, "endDate is null");
        Objects.requireNonNull(chronoUnit, "chronoUnit is null");

        return chronoUnit.between(beginDate, endDate);
    }

    /**
     * 计算两个时间的差值，不足设定单位的部分会被舍弃
     * <p>示例：</p>
     * <p>入参：2024-01-05 19:30:08, 2024-01-08 12:23:50, ChronoUnit.DAYS</p>
     * <p>返回：3</p>
     */
    public static long getDistance(LocalDateTime beginDate, LocalDateTime endDate, ChronoUnit chronoUnit) {
        Objects.requireNonNull(beginDate, "beginDate is null");
        Objects.requireNonNull(endDate, "endDate is null");
        Objects.requireNonNull(chronoUnit, "chronoUnit is null");

        return chronoUnit.between(beginDate, endDate);
    }

    /**
     * 计算两个时间的差值，不足设定单位的部分会被舍弃
     */
    public static long getDistance(Date beginDate, Date endDate, ChronoUnit chronoUnit) {
        Objects.requireNonNull(beginDate, "beginDate is null");
        Objects.requireNonNull(endDate, "endDate is null");
        Objects.requireNonNull(chronoUnit, "chronoUnit is null");

        return chronoUnit.between(beginDate.toInstant(), endDate.toInstant());
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

}
