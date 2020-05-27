package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.trainings;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class T_Day {


    @SerializedName("dayName")
    @Expose
    private String  dayName;

    @SerializedName("dayID")
    @Expose
    private int  dayID;


    @SerializedName("dayactivitesList")
    @Expose
    private List<DayTaining> dayactivitesList;


}
