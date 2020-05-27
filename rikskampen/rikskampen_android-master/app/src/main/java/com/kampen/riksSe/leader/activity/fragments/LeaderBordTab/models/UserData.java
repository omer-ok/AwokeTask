package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class UserData extends RealmObject {


    @SerializedName("user")
    @Expose
    private ContestantUser user;

    public ContestantUser getUser() {
        return user;
    }

    public void setUser(ContestantUser user) {
        this.user = user;
    }

}
