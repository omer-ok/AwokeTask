package com.kampen.riksSe.api.remote_api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NutritionListModel {


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
    private String  days;


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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }




}
