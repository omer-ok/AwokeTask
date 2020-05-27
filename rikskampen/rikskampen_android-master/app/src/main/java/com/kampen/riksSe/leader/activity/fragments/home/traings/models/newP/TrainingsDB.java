package com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class TrainingsDB extends RealmObject {





    @SerializedName("userID")
    @Expose
    private  String userID;

    @SerializedName("weeklyActivities")
    @Expose
    private
    T_weeklyActivitiesDB weeklyActivities;


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public T_weeklyActivitiesDB getWeeklyActivities() {
        return weeklyActivities;
    }

    public void setWeeklyActivities(T_weeklyActivitiesDB weeklyActivities) {
        this.weeklyActivities = weeklyActivities;
    }
}
