package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.activities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Activities  {





    @SerializedName("userID")
    @Expose
    private  String userID;

    @SerializedName("weeklyActivities")
    @Expose
    private
    ArrayList<weeklyActivities>    weeklyActivities;



    public ArrayList<weeklyActivities> getWeekActivities() {
        return weeklyActivities;
    }

    public void setWeekActivities(ArrayList<weeklyActivities> weekActivities) {
        this.weeklyActivities = weekActivities;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
