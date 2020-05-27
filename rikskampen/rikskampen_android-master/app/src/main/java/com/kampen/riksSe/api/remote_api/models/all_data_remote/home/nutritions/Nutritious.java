package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.nutritions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nutritious {


    @SerializedName("userID")
    @Expose
    private  String userID;

    @SerializedName("weeklyActivities")
    @Expose
    private
    N_weeklyActivities weekNutrition;


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public N_weeklyActivities getWeekNutrition() {
        return weekNutrition;
    }

    public void setWeekNutrition(N_weeklyActivities weekNutrition) {
        this.weekNutrition = weekNutrition;
    }
}
