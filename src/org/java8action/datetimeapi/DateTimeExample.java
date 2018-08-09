package org.java8action.datetimeapi;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.TimeZone;

import static java.time.temporal.TemporalAdjusters.*;

import java.time.DayOfWeek;

import javax.print.attribute.standard.Chromaticity;

public class DateTimeExample {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.of(2018, 07, 25);
        System.out.println(ld.getYear());
        System.out.println(ld.get(ChronoField.DAY_OF_MONTH));
        System.out.println(ld.isLeapYear());
        System.out.println(ld.lengthOfMonth());
        System.out.println(ld.lengthOfYear());
        System.out.println(ld.now());
        LocalDate ld1 = LocalDate.parse("2018-07-25");
        System.out.println("Parsed Year ==" + ld1.getYear());
        System.out.println("Parsed Day of the Month ==" + ld1.getDayOfMonth());
        System.out.println("Parsed Month ==" + ld1.getMonthValue());

        LocalTime lt = LocalTime.now();
        System.out.println(lt.getHour());
        System.out.println(lt.getMinute());
        System.out.println(lt.getSecond());
        System.out.println(lt.getNano());

        // 2014-03-18T13:45:20
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(ld, lt);
        LocalDateTime dt3 = ld.atTime(13, 45, 20);
        LocalDateTime dt4 = ld.atTime(lt);
        LocalDateTime dt5 = lt.atDate(ld);

        System.out.println(dt1);
        System.out.println(dt2);
        System.out.println(dt3);
        System.out.println(dt4);
        System.out.println(dt5);

        LocalDate ld2 = dt1.toLocalDate();
        LocalTime lt2 = dt1.toLocalTime();

        System.out.println(ld2);
        System.out.println(lt2);
        System.out.println(Instant.now());

        Duration d = Duration.between(LocalTime.now(), LocalTime.of(18, 0, 0));
        Duration d1 = Duration.between(LocalDateTime.now(), LocalDateTime.of(LocalDate.of(2018, 07, 24), LocalTime.of(18, 30)));
        System.out.println("Duration between LocalTimes==" + d.toHours());
        System.out.println("Duration between LocalDateTime===" + d1.toHours());
        System.out.println(Duration.ofDays(35)
                .toDays());

        Period p1 = Period.between(LocalDate.now(), LocalDate.of(2018, 8, 24));
        System.out.println("Period in Days ==" + p1.getDays());

        LocalDate ldd = LocalDate.now();
        LocalDate ldd2 = ldd.with(ChronoField.DAY_OF_MONTH, 10); // absolute way
        LocalDate ldd3 = ldd.plus(12, ChronoUnit.DAYS);
        System.out.println(ldd2);
        System.out.println(ldd3);
        LocalDate lld = LocalDate.of(2018, 07, 28);
        LocalDate lld1 = ld.with(dayOfWeekInMonth(4, DayOfWeek.MONDAY));
        LocalDate lld2 = ld.with(firstDayOfMonth());
        LocalDate lld3 = ld.with(firstDayOfNextMonth());
        LocalDate lld4 = lld.with(new NextWorkingDay());
        LocalDateTime llt = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 00));
        System.out.println(llt);
        LocalDateTime lld5 = llt.with(new NextWorkingHour());
        System.out.println(lld1);
        System.out.println(lld2);
        System.out.println(lld3);
        System.out.println(lld4);
        System.out.println(lld5.getHour());

        // lambda for TemporalAdjuster Functional interface

        LocalDate lld8 = ldd.with(temporal -> {
            // TODO Auto-generated method stub
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int daytoAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                daytoAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                daytoAdd = 2;
            }

            return temporal.plus(daytoAdd, ChronoUnit.DAYS);
        });

        System.out.println(lld8);

        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(temporal -> {
            // TODO Auto-generated method stub
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int daytoAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                daytoAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                daytoAdd = 2;
            }

            return temporal.plus(daytoAdd, ChronoUnit.DAYS);
        });

        System.out.println(ldd.with(nextWorkingDay));

        // Date Time Formatter and Parsing

        String basicIsoDate = ldd.format(DateTimeFormatter.BASIC_ISO_DATE);
        String localIsoDate = ldd.format(DateTimeFormatter.ISO_DATE);

        System.out.println("basicISODate == " + basicIsoDate);
        System.out.println("localIsoDate == " + localIsoDate);

        LocalDate basicIsoDateParse = LocalDate.parse("20180731", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate localISODoateParse = LocalDate.parse("2018-07-30", DateTimeFormatter.ISO_DATE);

        System.out.println("basicIsoDateParse== " + basicIsoDateParse);

        System.out.println("localIlddSODoateParse== " + localISODoateParse);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = ldd.format(formatter);
        System.out.println(formattedDate);
        LocalDate ldddd = LocalDate.parse(formattedDate, formatter);
        System.out.println(ldddd);

        DateTimeFormatter formatter_eng = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        String formattedDate_eng = ldd.format(formatter_eng);
        System.out.println(formattedDate_eng);
        LocalDate ldddd_eng = LocalDate.parse(formattedDate_eng, formatter_eng);
        System.out.println(ldddd_eng);

        ZoneId zoneId = TimeZone.getDefault()
                .toZoneId();
        System.out.println(ldd.atStartOfDay(zoneId));

        System.out.println(dt1.atZone(zoneId));

    }

}
