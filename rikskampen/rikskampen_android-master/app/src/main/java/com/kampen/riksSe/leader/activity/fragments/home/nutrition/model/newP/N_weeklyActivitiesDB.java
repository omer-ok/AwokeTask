package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class N_weeklyActivitiesDB extends RealmObject {





    @SerializedName("Week1")
    @Expose
    private N_WeekDB week1;


    @SerializedName("Week2")
    @Expose
    private N_WeekDB week2;


    @SerializedName("Week3")
    @Expose
    private N_WeekDB week3;


    @SerializedName("Week4")
    @Expose
    private N_WeekDB week4;


    @SerializedName("Week5")
    @Expose
    private N_WeekDB week5;

    @SerializedName("Week6")
    @Expose
    private N_WeekDB week6;


    @SerializedName("Week7")
    @Expose
    private N_WeekDB week7;

    @SerializedName("Week8")
    @Expose
    private N_WeekDB week8;

    @SerializedName("Week9")
    @Expose
    private N_WeekDB week9;

    @SerializedName("Week10")
    @Expose
    private N_WeekDB week10;


    public N_WeekDB getWeek1() {
        return week1;
    }

    public void setWeek1(N_WeekDB week1) {
        this.week1 = week1;
    }

    public N_WeekDB getWeek2() {
        return week2;
    }

    public void setWeek2(N_WeekDB week2) {
        this.week2 = week2;
    }

    public N_WeekDB getWeek3() {
        return week3;
    }

    public void setWeek3(N_WeekDB week3) {
        this.week3 = week3;
    }

    public N_WeekDB getWeek4() {
        return week4;
    }

    public void setWeek4(N_WeekDB week4) {
        this.week4 = week4;
    }

    public N_WeekDB getWeek5() {
        return week5;
    }

    public void setWeek5(N_WeekDB week5) {
        this.week5 = week5;
    }

    public N_WeekDB getWeek6() {
        return week6;
    }

    public void setWeek6(N_WeekDB week6) {
        this.week6 = week6;
    }

    public N_WeekDB getWeek7() {
        return week7;
    }

    public void setWeek7(N_WeekDB week7) {
        this.week7 = week7;
    }

    public N_WeekDB getWeek8() {
        return week8;
    }

    public void setWeek8(N_WeekDB week8) {
        this.week8 = week8;
    }

    public N_WeekDB getWeek9() {
        return week9;
    }

    public void setWeek9(N_WeekDB week9) {
        this.week9 = week9;
    }

    public N_WeekDB getWeek10() {
        return week10;
    }

    public void setWeek10(N_WeekDB week10) {
        this.week10 = week10;
    }
}
