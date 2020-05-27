package com.kampen.riksSe.api.remote_api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kampen.riksSe.login.ModelsV3.RemoteUser;

public class RemoteUserResult  {

    @SerializedName("token")
    @Expose
    private  String   token;



    @SerializedName("user")
    @Expose
    private RemoteUser user;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RemoteUser getUser() {
        return user;
    }

    public void setUser(RemoteUser user) {
        this.user = user;
    }
}
