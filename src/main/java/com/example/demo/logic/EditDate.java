package com.example.demo.logic;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import lombok.AllArgsConstructor;

/**
 * 日付加工.
 */
@AllArgsConstructor
public class EditDate {
    /** MM-dd-yyyy. */
    private static final String PATTERN_MDY = "MM-dd-yyyy";
    /** yyyyMMdd. */
    private static final String PATTERN_YMD = "yyyyMMdd";
    /** yyyy. */
    private static final String PATTERN_Y = "yyyy";
    /** MM. */
    private static final String PATTERN_M = "MM";
    /** 前期. */
    private static final int FIRST_HALF = 1;
    /** 後期. */
    private static final int SECOND_HALF = 2;
    
    /** LocalDate型日付. */
    private final LocalDate localDate;
    
    /**
     * 生成.
     * @param date String
     * @return EditDate
     */
    public static EditDate create(String date) {
        return new EditDate(LocalDate.parse(date, DateTimeFormatter.ofPattern(PATTERN_MDY)));
    }

    /**
     * 暦年
     * calenderYear.
     * 
     * @return year
     */
    public String getCalenderYear() {
        return localDate.format(DateTimeFormatter.ofPattern(PATTERN_Y));
    }

    /**
     * 月.
     * 
     * @return month
     */
    public String getMonth() {
        return localDate.format(DateTimeFormatter.ofPattern(PATTERN_M));
    }

    /**
     * 年月.
     * 
     * @return yearMonth
     */
    public String getYearMonth() {
        return localDate.format(DateTimeFormatter.ofPattern(PATTERN_Y+PATTERN_M));
    }

    /**
     * 年度.
     * 
     * @return fiscalYear
     */
    public  String getFiscalYear() {
        int month = localDate.getMonthValue();
        if (Month.APRIL.getValue() <= month && month <= Month.DECEMBER.getValue()) {
            return localDate.format(DateTimeFormatter.ofPattern(PATTERN_Y));
        }
        return localDate.minusYears(1).format(DateTimeFormatter.ofPattern(PATTERN_Y));
    }

    /**
     * 半期区分.
     * 
     * @return halfDiv
     */
    public int getHalfDiv() {
        // (4月～9月 前期:1 10月～３月 後期：２)
        int month = localDate.getMonthValue();
        if (Month.APRIL.getValue() <= month && month <= Month.SEPTEMBER.getValue()) {
            return FIRST_HALF;
        }
        return SECOND_HALF;
    }

    /**
     * 当期開始日.
     * 
     * @return startPeriod
     */
    public String getStartPeriod() {
        int halfDiv = getHalfDiv();
        if (halfDiv == FIRST_HALF) {
            return localDate.withMonth(Month.APRIL.getValue()).with(TemporalAdjusters.firstDayOfMonth())
                    .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
        }
        int month = localDate.getMonthValue();
        if (Month.JANUARY.getValue() <= month && month <= Month.MARCH.getValue()) {
            return localDate.minusYears(1).withMonth(Month.OCTOBER.getValue()).with(TemporalAdjusters.firstDayOfMonth())
                    .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
        }
        return localDate.withMonth(Month.OCTOBER.getValue()).with(TemporalAdjusters.firstDayOfMonth())
                .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
    }

    /**
     * 当期終了日.
     * @return startPeriod
     */
    public String getEndPeriod() {
        int halfDiv = getHalfDiv();
        if (halfDiv == FIRST_HALF) {
            return localDate.withMonth(Month.SEPTEMBER.getValue()).with(TemporalAdjusters.lastDayOfMonth())
                    .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
        }
        int month = localDate.getMonthValue();
        if (Month.JANUARY.getValue() <= month && month <= Month.MARCH.getValue()) {
            return localDate.withMonth(Month.MARCH.getValue()).with(TemporalAdjusters.lastDayOfMonth())
                    .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
        }
        return localDate.plusYears(1).withMonth(Month.MARCH.getValue()).with(TemporalAdjusters.lastDayOfMonth())
                .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
    }

    /**
     * 前期開始日.
     * @return beforeHalf
     */
    public String getStartBeforeHalf() {
        int halfDiv = getHalfDiv();
        if (halfDiv == FIRST_HALF) {
            return localDate.withMonth(Month.OCTOBER.getValue()).with(TemporalAdjusters.firstDayOfMonth())
                    .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
        }
        int month = localDate.getMonthValue();
        if (Month.JANUARY.getValue() <= month && month <= Month.MARCH.getValue()) {
            return localDate.minusYears(1).withMonth(Month.APRIL.getValue()).with(TemporalAdjusters.firstDayOfMonth())
                    .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
        }
        return localDate.withMonth(Month.APRIL.getValue()).with(TemporalAdjusters.firstDayOfMonth())
                .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
    }

    /**
     * 前期終了日.
     * 
     * @return beforeHalf
     */
    public String getEndBeforeHalf() {
        int halfDiv = getHalfDiv();
        if (halfDiv == FIRST_HALF) {
            return localDate.withMonth(Month.MARCH.getValue()).with(TemporalAdjusters.lastDayOfMonth())
                    .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
        }
        int month = localDate.getMonthValue();
        if (Month.JANUARY.getValue() <= month && month <= Month.MARCH.getValue()) {
            return localDate.withMonth(Month.SEPTEMBER.getValue()).with(TemporalAdjusters.lastDayOfMonth())
                    .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
        }
        return localDate.minusYears(1).withMonth(Month.SEPTEMBER.getValue()).with(TemporalAdjusters.lastDayOfMonth())
                .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
    }

    /**
     * 前年度終了日.
     * 
     * @return endPreviousFiscalYear
     */
    public String getEndPreviousFiscalYear() {
        // 20230401 20230331
        // 20230301 20220331
        int month = localDate.getMonthValue();
        if (Month.JANUARY.getValue() <= month && month <= Month.MARCH.getValue()) {
            return localDate.minusYears(1).withMonth(Month.MARCH.getValue()).with(TemporalAdjusters.lastDayOfMonth())
                    .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
        }
        return localDate.withMonth(Month.MARCH.getValue()).with(TemporalAdjusters.lastDayOfMonth())
                .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
    }

    /**
     * 前月月末.
     * 
     * @return endOfLastMonth
     */
    public String getEndOfLastMonth() {
        return localDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth())
                .format(DateTimeFormatter.ofPattern(PATTERN_YMD));
    }

    /**
     * 月末営業日.
     * 
     * @param holidayList List
     * @return beforEndOfLastMonth
     */
    public String getLastBusinessDay(List<String> holidayList) {
        // 月末日を取得
        LocalDate localEndOfLastMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());

        while (holidayList.contains(localEndOfLastMonth.format(DateTimeFormatter.ofPattern(PATTERN_YMD)))) {
            // 前日
            localEndOfLastMonth = localEndOfLastMonth.minusDays(1);
        }
        return localEndOfLastMonth.format(DateTimeFormatter.ofPattern(PATTERN_YMD));
    }

    /**
     * 月末２営業日前.
     * 
     * @param holidayList List
     * @return befor2LastBusinessDay
     */
    public String getBefor2LastBusinessDay(List<String> holidayList) {
        // 月末日を取得
        LocalDate localEndOfLastMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());

        while (holidayList.contains(localEndOfLastMonth.format(DateTimeFormatter.ofPattern(PATTERN_YMD)))) {
            // 前日
            localEndOfLastMonth = localEndOfLastMonth.minusDays(1);
        }
        localEndOfLastMonth = localEndOfLastMonth.minusDays(1);
        while (holidayList.contains(localEndOfLastMonth.format(DateTimeFormatter.ofPattern(PATTERN_YMD)))) {
            // 前日
            localEndOfLastMonth = localEndOfLastMonth.minusDays(1);
        }
        return localEndOfLastMonth.format(DateTimeFormatter.ofPattern(PATTERN_YMD));
    }
}
