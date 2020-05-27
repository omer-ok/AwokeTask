package com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class T_DayDB extends RealmObject {


    @SerializedName("dayName")
    @Expose
    private String  dayName;

    @SerializedName("dayID")
    @Expose
    private int  dayID;

    @SerializedName("currentDay")
    @Expose
    public String  currentDay;

    @SerializedName("imagePath")
    @Expose
    public String  imagePath;

    @SerializedName("startVideo")
    @Expose
    public VideoStartEnd  startVideo;


    @SerializedName("endVideo")
    @Expose
    public VideoStartEnd  endVideo;



    @SerializedName("dayDescription")
    @Expose
    public String  dayDescription;

    @SerializedName("dayactivitesList")
    @Expose
    private RealmList<DayTainingDB> dayactivitesList;


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

    public List<DayTainingDB> getDayactivitesList() {
        return dayactivitesList;
    }

    public void setDayactivitesList(RealmList<DayTainingDB> dayactivitesList) {
        this.dayactivitesList = dayactivitesList;
    }

    public String getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(String currentDay) {
        this.currentDay = currentDay;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public VideoStartEnd getStartVideo() {
        return startVideo;
    }

    public void setStartVideo(VideoStartEnd startVideo) {
        this.startVideo = startVideo;
    }

    public VideoStartEnd getEndVideo() {
        return endVideo;
    }

    public void setEndVideo(VideoStartEnd endVideo) {
        this.endVideo = endVideo;
    }

    public String getDayDescription() {
        return dayDescription;
    }

    public void setDayDescription(String dayDescription) {
        this.dayDescription = dayDescription;
    }

}
