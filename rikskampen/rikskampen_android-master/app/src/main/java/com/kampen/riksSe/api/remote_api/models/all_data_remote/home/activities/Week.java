package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.activities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Week {

    @SerializedName("Week")
    @Expose
    private weeklyActivities Week;


    public weeklyActivities getWeek() {
        return Week;
    }

    public void setWeek(weeklyActivities week) {
        Week = week;
    }









}
