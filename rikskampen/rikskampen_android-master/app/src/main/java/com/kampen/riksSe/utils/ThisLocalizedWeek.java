package com.kampen.riksSe.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ThisLocalizedWeek {
    // Try and always specify the time zone you're working with
    //private final static ZoneId TZ = ZoneId.of("Pacific/Auckland");

    private final Locale locale;
    private final DayOfWeek firstDayOfWeek;
    private final DayOfWeek lastDayOfWeek;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ThisLocalizedWeek(final Locale locale) {
        this.locale = locale;

            this.firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();

        this.lastDayOfWeek = DayOfWeek.of(((this.firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getFirstDay() {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        return LocalDate.now(ZoneId.of(tz.getID())).with(TemporalAdjusters.previousOrSame(this.firstDayOfWeek));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getLastDay() {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        return LocalDate.now(ZoneId.of(tz.getID())).with(TemporalAdjusters.nextOrSame(this.lastDayOfWeek));
    }

    @Override
    public String toString() {
        return String.format(   "The %s week starts on %s and ends on %s",
                this.locale.getDisplayName(),
                this.firstDayOfWeek,
                this.lastDayOfWeek);
    }
}
