package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MotivationVideos extends RealmObject {

    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("videos")
    @Expose
    private RealmList<VideoM> videos = null;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public RealmList<VideoM> getVideos() {
        return videos;
    }

    public void setVideos(RealmList<VideoM> videos) {
        this.videos = videos;
    }

}
