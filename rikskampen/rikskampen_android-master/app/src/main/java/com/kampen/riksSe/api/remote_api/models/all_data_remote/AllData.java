package com.kampen.riksSe.api.remote_api.models.all_data_remote;

import com.kampen.riksSe.api.remote_api.models.all_data_remote.home.HomeData;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllData {




    @SerializedName("Home")
    @Expose
    private
    HomeData homeData;



    public static HomeData  createHomeDataJson()
    {

        HomeData homeData=new HomeData();

        return homeData;
    }


    public  String  toJson()
    {
        Gson gson = new Gson();

        String jsonData = gson.toJson(this);

        return  jsonData;
    }



    public HomeData getHomeData() {
        return homeData;
    }

    public void setHomeData(HomeData homeData) {
        this.homeData = homeData;
    }
}
