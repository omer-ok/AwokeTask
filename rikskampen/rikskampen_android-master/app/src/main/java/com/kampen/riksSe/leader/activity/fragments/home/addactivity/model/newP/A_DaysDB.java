package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class A_DaysDB extends RealmObject {


    @SerializedName("Day1")
    @Expose
    public A_DayDB Day1;


    @SerializedName("Day2")
    @Expose
    public A_DayDB Day2;


    @SerializedName("Day3")
    @Expose
    public A_DayDB Day3;

    @SerializedName("Day4")
    @Expose
    public A_DayDB Day4;

    @SerializedName("Day5")
    @Expose
    public A_DayDB Day5;

    @SerializedName("Day6")
    @Expose
    public A_DayDB Day6;

    @SerializedName("Day7")
    @Expose
    public A_DayDB Day7;

    public A_DayDB getDay1() {
        return Day1;
    }

    public void setDay1(A_DayDB day1) {
        Day1 = day1;
    }

    public A_DayDB getDay2() {
        return Day2;
    }

    public void setDay2(A_DayDB day2) {
        Day2 = day2;
    }

    public A_DayDB getDay3() {
        return Day3;
    }

    public void setDay3(A_DayDB day3) {
        Day3 = day3;
    }

    public A_DayDB getDay4() {
        return Day4;
    }

    public void setDay4(A_DayDB day4) {
        Day4 = day4;
    }

    public A_DayDB getDay5() {
        return Day5;
    }

    public void setDay5(A_DayDB day5) {
        Day5 = day5;
    }

    public A_DayDB getDay6() {
        return Day6;
    }

    public void setDay6(A_DayDB day6) {
        Day6 = day6;
    }

    public A_DayDB getDay7() {
        return Day7;
    }

    public void setDay7(A_DayDB day7) {
        Day7 = day7;
    }





}
