package com.kampen.riksSe.leader.activity.fragments.account.profile.LiveVideoCall.ModelV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveVideoCallToken {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("room_name")
    @Expose
    private String roomName;
    @SerializedName("ttl")
    private String tokenSessionTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getTokenSessionTime() {
        return tokenSessionTime;
    }

    public void setTokenSessionTime(String tokenSessionTime) {
        this.tokenSessionTime = tokenSessionTime;
    }

}
