package com.kampen.riksSe.user.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserActivityData  extends RealmObject {

    private String locatStarCount;
    private String locatStepCount;
    private String locatCalCount;
    private String Distance;
    private String activity_weight;
    private String user_activity_time;
    private Boolean SyncedWithServer;



    public String getLocatStarCount() {
        return locatStarCount;
    }

    public void setLocatStarCount(String locatStarCount) {
        this.locatStarCount = locatStarCount;
    }

    public String getLocatStepCount() {
        return locatStepCount;
    }

    public void setLocatStepCount(String locatStepCount) {
        this.locatStepCount = locatStepCount;
    }

    public String getLocatCalCount() {
        return locatCalCount;
    }

    public void setLocatCalCount(String locatCalCount) {
        this.locatCalCount = locatCalCount;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getActivity_weight() {
        return activity_weight;
    }

    public void setActivity_weight(String activity_weight) {
        this.activity_weight = activity_weight;
    }


    public String getUser_activity_time() {
        return user_activity_time;
    }

    public void setUser_activity_time(String user_activity_time) {
        this.user_activity_time = user_activity_time;
    }



    public Boolean getSyncedWithServer() {
        return SyncedWithServer;
    }

    public void setSyncedWithServer(Boolean syncedWithServer) {
        SyncedWithServer = syncedWithServer;
    }








}
