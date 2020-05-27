package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.activities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class homeModel<A> {


    @SerializedName("Home")
    @Expose
    private
    String  Home;


    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }






}
