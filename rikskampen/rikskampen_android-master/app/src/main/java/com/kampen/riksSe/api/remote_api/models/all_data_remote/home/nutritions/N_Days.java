package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.nutritions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class N_Days {


    @SerializedName("Day1")
    @Expose
    private N_Day  Day1;


    @SerializedName("Day2")
    @Expose
    private N_Day  Day2;


    @SerializedName("Day3")
    @Expose
    private N_Day  Day3;

    @SerializedName("Day4")
    @Expose
    private N_Day  Day4;

    @SerializedName("Day5")
    @Expose
    private N_Day  Day5;

    @SerializedName("Day6")
    @Expose
    private N_Day  Day6;

    @SerializedName("Day7")
    @Expose
    private N_Day  Day7;


    public N_Day getDay1() {
        return Day1;
    }

    public void setDay1(N_Day day1) {
        Day1 = day1;
    }

    public N_Day getDay2() {
        return Day2;
    }

    public void setDay2(N_Day day2) {
        Day2 = day2;
    }

    public N_Day getDay3() {
        return Day3;
    }

    public void setDay3(N_Day day3) {
        Day3 = day3;
    }

    public N_Day getDay4() {
        return Day4;
    }

    public void setDay4(N_Day day4) {
        Day4 = day4;
    }

    public N_Day getDay5() {
        return Day5;
    }

    public void setDay5(N_Day day5) {
        Day5 = day5;
    }

    public N_Day getDay6() {
        return Day6;
    }

    public void setDay6(N_Day day6) {
        Day6 = day6;
    }

    public N_Day getDay7() {
        return Day7;
    }

    public void setDay7(N_Day day7) {
        Day7 = day7;
    }
}
