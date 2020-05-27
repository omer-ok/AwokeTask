package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.trainings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class T_Week {


    @SerializedName("weekID")
    @Expose
    private String  weekID;

    @SerializedName("weekName")
    @Expose
    private String  weekName;


    @SerializedName("imagePath")
    @Expose
    private String  imagePath;

    @SerializedName("days")
    @Expose
    private T_Days days;



}
