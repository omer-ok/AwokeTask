package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class A_DayDB extends RealmObject {


    @SerializedName("dayName")
    @Expose
    public String  dayName;

    @SerializedName("dayID")
    @Expose
    public int  dayID;

    @SerializedName("dayactivitesList")
    @Expose
    public RealmList<A_DayActivityList> dayactivitesList;


    public RealmList<A_DayActivityList> getDayactivitesList() {
        return dayactivitesList;
    }

    public void setDayactivitesList(RealmList<A_DayActivityList> dayactivitesList) {
        this.dayactivitesList = dayactivitesList;
    }

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


}
