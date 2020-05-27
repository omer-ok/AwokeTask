package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class StepsData extends RealmObject {

    @SerializedName("user")
    @Expose
    private UserSteps user;

    public UserSteps getUser() {
        return user;
    }

    public void setUser(UserSteps user) {
        this.user = user;
    }

}
