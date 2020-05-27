package com.kampen.riksSe.api.remote_api.models.all_data_remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BadgeCountModel {

    @SerializedName("badge")
    @Expose
    private int badge;
    @SerializedName("chat")
    @Expose
    private int chat;
    @SerializedName("nutrition")
    @Expose
    private int nutrition;
    @SerializedName("workout")
    @Expose
    private int workout;

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public int getChat() {
        return chat;
    }

    public void setChat(int chat) {
        this.chat = chat;
    }

    public int getNutrition() {
        return nutrition;
    }

    public void setNutrition(int nutrition) {
        this.nutrition = nutrition;
    }

    public int getWorkout() {
        return workout;
    }

    public void setWorkout(int workout) {
        this.workout = workout;
    }



}
