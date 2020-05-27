package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.trainings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class T_Days {


    @SerializedName("Day1")
    @Expose
    private T_Day  Day1;


    @SerializedName("Day2")
    @Expose
    private T_Day  Day2;


    @SerializedName("Day3")
    @Expose
    private T_Day  Day3;

    @SerializedName("Day4")
    @Expose
    private T_Day  Day4;

    @SerializedName("Day5")
    @Expose
    private T_Day  Day5;

    @SerializedName("Day6")
    @Expose
    private T_Day  Day6;

    @SerializedName("Day7")
    @Expose
    private T_Day  Day7;


    public T_Day getDay1() {
        return Day1;
    }

    public void setDay1(T_Day day1) {
        Day1 = day1;
    }

    public T_Day getDay2() {
        return Day2;
    }

    public void setDay2(T_Day day2) {
        Day2 = day2;
    }

    public T_Day getDay3() {
        return Day3;
    }

    public void setDay3(T_Day day3) {
        Day3 = day3;
    }

    public T_Day getDay4() {
        return Day4;
    }

    public void setDay4(T_Day day4) {
        Day4 = day4;
    }

    public T_Day getDay5() {
        return Day5;
    }

    public void setDay5(T_Day day5) {
        Day5 = day5;
    }

    public T_Day getDay6() {
        return Day6;
    }

    public void setDay6(T_Day day6) {
        Day6 = day6;
    }

    public T_Day getDay7() {
        return Day7;
    }

    public void setDay7(T_Day day7) {
        Day7 = day7;
    }
}
