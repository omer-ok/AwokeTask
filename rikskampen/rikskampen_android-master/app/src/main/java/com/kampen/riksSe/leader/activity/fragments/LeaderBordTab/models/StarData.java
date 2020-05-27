package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class StarData extends RealmObject {


    @SerializedName("user")
    @Expose
    private UserStars userStars;

    public UserStars getUserStars() {
        return userStars;
    }

    public void setUserStars(UserStars userStars) {
        this.userStars = userStars;
    }

}
