package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class CaloriesData extends RealmObject {


    @SerializedName("user")
    @Expose
    private UserCalories userCalories;


    public UserCalories getUserCalories() {
        return userCalories;
    }

    public void setUserCalories(UserCalories userCalories) {
        this.userCalories = userCalories;
    }


}
