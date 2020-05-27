package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.nutritions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class N_Week {


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
    private N_Days days;


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

    public N_Days getDays() {
        return days;
    }

    public void setDays(N_Days days) {
        this.days = days;
    }
}
