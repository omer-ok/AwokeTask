package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class A_WeekDB extends RealmObject  {


    @SerializedName("weekID")
    @Expose
    public String  weekID;

    @SerializedName("weekName")
    @Expose
    public String  weekName;


    @SerializedName("imagePath")
    @Expose
    public String  imagePath;


    @SerializedName("days")
    @Expose
    public A_DaysDB days;


    @SerializedName("currentWeek")
    @Expose
    public String currentWeek;


    public A_DaysDB getDays() {
        return days;
    }

    public void setDays(A_DaysDB days) {
        this.days = days;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }

    public String getWeekID() {
        return weekID;
    }

    public void setWeekID(String weekID) {
        this.weekID = weekID;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
