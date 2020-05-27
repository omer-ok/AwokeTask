package com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class T_WeekDB extends RealmObject {


    @SerializedName("weekID")
    @Expose
    private String  weekID;

    @SerializedName("weekName")
    @Expose
    private String  weekName;


    @SerializedName("imagePath")
    @Expose
    private String  imagePath;

    @SerializedName("days")
    @Expose
    private T_DaysDB days;

    @SerializedName("currentWeek")
    @Expose
    public String currentWeek;

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

    public T_DaysDB getDays() {
        return days;
    }

    public void setDays(T_DaysDB days) {
        this.days = days;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }

}
