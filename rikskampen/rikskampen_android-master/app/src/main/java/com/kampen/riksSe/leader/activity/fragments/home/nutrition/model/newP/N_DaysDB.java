package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class N_DaysDB extends RealmObject {


    @SerializedName("Day1")
    @Expose
    public N_DayDB Day1;


    @SerializedName("Day2")
    @Expose
    public N_DayDB Day2;


    @SerializedName("Day3")
    @Expose
    public N_DayDB Day3;

    @SerializedName("Day4")
    @Expose
    public N_DayDB Day4;

    @SerializedName("Day5")
    @Expose
    public N_DayDB Day5;

    @SerializedName("Day6")
    @Expose
    public N_DayDB Day6;

    @SerializedName("Day7")
    @Expose
    public N_DayDB Day7;


    public N_DayDB getDay1() {
        return Day1;
    }

    public void setDay1(N_DayDB day1) {
        Day1 = day1;
    }

    public N_DayDB getDay2() {
        return Day2;
    }

    public void setDay2(N_DayDB day2) {
        Day2 = day2;
    }

    public N_DayDB getDay3() {
        return Day3;
    }

    public void setDay3(N_DayDB day3) {
        Day3 = day3;
    }

    public N_DayDB getDay4() {
        return Day4;
    }

    public void setDay4(N_DayDB day4) {
        Day4 = day4;
    }

    public N_DayDB getDay5() {
        return Day5;
    }

    public void setDay5(N_DayDB day5) {
        Day5 = day5;
    }

    public N_DayDB getDay6() {
        return Day6;
    }

    public void setDay6(N_DayDB day6) {
        Day6 = day6;
    }

    public N_DayDB getDay7() {
        return Day7;
    }

    public void setDay7(N_DayDB day7) {
        Day7 = day7;
    }
}
