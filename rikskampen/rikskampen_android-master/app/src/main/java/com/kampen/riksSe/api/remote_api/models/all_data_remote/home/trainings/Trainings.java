package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.trainings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trainings {





    @SerializedName("userID")
    @Expose
    private  String userID;

    @SerializedName("weeklyActivities")
    @Expose
    private
    T_weeklyActivities  weeklyActivities;






    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public T_weeklyActivities getWeeklyActivities() {
        return weeklyActivities;
    }

    public void setWeeklyActivities(T_weeklyActivities weeklyActivities) {
        this.weeklyActivities = weeklyActivities;
    }
}
