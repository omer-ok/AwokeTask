package com.kampen.riksSe.utils;

public class ContestWeekDayTimeModel {


    private long Weeks;
    private long Days;
    private long Hours;
    private long Miniutes;
    private long Seconds;
    private long StratTimeInMilli;
    private long EndTimeInMilli;

    public long getWeeks() {
        return Weeks;
    }

    public void setWeeks(long weeks) {
        Weeks = weeks;
    }
    public long getDays() {
        return Days;
    }

    public void setDays(long days) {
        Days = days;
    }

    public long getHours() {
        return Hours;
    }

    public void setHours(long hours) {
        Hours = hours;
    }

    public long getMiniutes() {
        return Miniutes;
    }

    public void setMiniutes(long miniutes) {
        Miniutes = miniutes;
    }

    public long getSeconds() {
        return Seconds;
    }

    public void setSeconds(long seconds) {
        Seconds = seconds;
    }

    public long getStratTimeInMilli() {
        return StratTimeInMilli;
    }

    public void setStratTimeInMilli(long stratTimeInMilli) {
        StratTimeInMilli = stratTimeInMilli;
    }

    public long getEndTimeInMilli() {
        return EndTimeInMilli;
    }

    public void setEndTimeInMilli(long endTimeInMilli) {
        EndTimeInMilli = endTimeInMilli;
    }
}
