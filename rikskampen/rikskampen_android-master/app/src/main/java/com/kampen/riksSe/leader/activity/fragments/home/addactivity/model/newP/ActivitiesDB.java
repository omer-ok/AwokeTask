package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ActivitiesDB extends RealmObject {





    @SerializedName("userID")
    @Expose
    private  String userID;

    @SerializedName("weeklyActivities")
    @Expose
    private
    A_weeklyActivitiesDB weeklyActivities;

    public A_weeklyActivitiesDB getWeeklyActivities() {
        return weeklyActivities;
    }

    public void setWeeklyActivities(A_weeklyActivitiesDB weeklyActivities) {
        this.weeklyActivities = weeklyActivities;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}

