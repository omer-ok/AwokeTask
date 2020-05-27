package com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class T_DaysDB extends RealmObject {


    @SerializedName("Day1")
    @Expose
    private T_DayDB Day1;


    @SerializedName("Day2")
    @Expose
    private T_DayDB Day2;


    @SerializedName("Day3")
    @Expose
    private T_DayDB Day3;

    @SerializedName("Day4")
    @Expose
    private T_DayDB Day4;

    @SerializedName("Day5")
    @Expose
    private T_DayDB Day5;

    @SerializedName("Day6")
    @Expose
    private T_DayDB Day6;

    @SerializedName("Day7")
    @Expose
    private T_DayDB Day7;


    public T_DayDB getDay1() {
        return Day1;
    }

    public void setDay1(T_DayDB day1) {
        Day1 = day1;
    }

    public T_DayDB getDay2() {
        return Day2;
    }

    public void setDay2(T_DayDB day2) {
        Day2 = day2;
    }

    public T_DayDB getDay3() {
        return Day3;
    }

    public void setDay3(T_DayDB day3) {
        Day3 = day3;
    }

    public T_DayDB getDay4() {
        return Day4;
    }

    public void setDay4(T_DayDB day4) {
        Day4 = day4;
    }

    public T_DayDB getDay5() {
        return Day5;
    }

    public void setDay5(T_DayDB day5) {
        Day5 = day5;
    }

    public T_DayDB getDay6() {
        return Day6;
    }

    public void setDay6(T_DayDB day6) {
        Day6 = day6;
    }

    public T_DayDB getDay7() {
        return Day7;
    }

    public void setDay7(T_DayDB day7) {
        Day7 = day7;
    }
}
