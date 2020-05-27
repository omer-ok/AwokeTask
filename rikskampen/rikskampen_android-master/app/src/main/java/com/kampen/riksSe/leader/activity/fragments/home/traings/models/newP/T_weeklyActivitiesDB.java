package com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class T_weeklyActivitiesDB extends RealmObject {



    @SerializedName("Week1")
    @Expose
    private T_WeekDB week1;


    @SerializedName("Week2")
    @Expose
    private T_WeekDB week2;


    @SerializedName("Week3")
    @Expose
    private T_WeekDB week3;


    @SerializedName("Week4")
    @Expose
    private T_WeekDB week4;


    @SerializedName("Week5")
    @Expose
    private T_WeekDB week5;

    @SerializedName("Week6")
    @Expose
    private T_WeekDB week6;


    @SerializedName("Week7")
    @Expose
    private T_WeekDB week7;

    @SerializedName("Week8")
    @Expose
    private T_WeekDB week8;

    @SerializedName("Week9")
    @Expose
    private T_WeekDB week9;

    @SerializedName("Week10")
    @Expose
    private T_WeekDB week10;


    public T_WeekDB getWeek1() {
        return week1;
    }

    public void setWeek1(T_WeekDB week1) {
        this.week1 = week1;
    }

    public T_WeekDB getWeek2() {
        return week2;
    }

    public void setWeek2(T_WeekDB week2) {
        this.week2 = week2;
    }

    public T_WeekDB getWeek3() {
        return week3;
    }

    public void setWeek3(T_WeekDB week3) {
        this.week3 = week3;
    }

    public T_WeekDB getWeek4() {
        return week4;
    }

    public void setWeek4(T_WeekDB week4) {
        this.week4 = week4;
    }

    public T_WeekDB getWeek5() {
        return week5;
    }

    public void setWeek5(T_WeekDB week5) {
        this.week5 = week5;
    }

    public T_WeekDB getWeek6() {
        return week6;
    }

    public void setWeek6(T_WeekDB week6) {
        this.week6 = week6;
    }

    public T_WeekDB getWeek7() {
        return week7;
    }

    public void setWeek7(T_WeekDB week7) {
        this.week7 = week7;
    }

    public T_WeekDB getWeek8() {
        return week8;
    }

    public void setWeek8(T_WeekDB week8) {
        this.week8 = week8;
    }

    public T_WeekDB getWeek9() {
        return week9;
    }

    public void setWeek9(T_WeekDB week9) {
        this.week9 = week9;
    }

    public T_WeekDB getWeek10() {
        return week10;
    }

    public void setWeek10(T_WeekDB week10) {
        this.week10 = week10;
    }
}
