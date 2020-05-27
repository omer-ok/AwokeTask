package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.nutritions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class N_Day {


    @SerializedName("dayName")
    @Expose
    private String  dayName;

    @SerializedName("dayID")
    @Expose
    private int  dayID;


    @SerializedName("dayactivitesList")
    @Expose
    private List<DayNutrition> dayactivitesList;


    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public List<DayNutrition> getDayactivitesList() {
        return dayactivitesList;
    }

    public void setDayactivitesList(List<DayNutrition> dayactivitesList) {
        this.dayactivitesList = dayactivitesList;
    }
}
