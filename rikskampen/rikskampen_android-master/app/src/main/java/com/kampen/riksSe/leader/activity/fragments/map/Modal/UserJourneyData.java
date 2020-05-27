package com.kampen.riksSe.leader.activity.fragments.map.Modal;

import io.realm.RealmObject;

public class UserJourneyData extends RealmObject {

    private int locatStarCount;
    private int locatStepCount;
    private double locatCalCount;
    private String localDistance;
    private String localTimer;
    private String localDate;
    private Boolean SyncedWithServer;


    public String getLocalDistance() {
        return localDistance;
    }

    public void setLocalDistance(String localDistance) {
        this.localDistance = localDistance;
    }

    public String getLocalTimer() {
        return localTimer;
    }

    public void setLocalTimer(String localTimer) {
        this.localTimer = localTimer;
    }
    public int getLocatStarCount() {
        return locatStarCount;
    }

    public void setLocatStarCount(int locatStarCount) {
        this.locatStarCount = locatStarCount;
    }

    public int getLocatStepCount() {
        return locatStepCount;
    }

    public void setLocatStepCount(int locatStepCount) {
        this.locatStepCount = locatStepCount;
    }

    public double getLocatCalCount() {
        return locatCalCount;
    }

    public void setLocatCalCount(double locatCalCount) {
        this.locatCalCount = locatCalCount;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public Boolean getSyncedWithServer() {
        return SyncedWithServer;
    }

    public void setSyncedWithServer(Boolean syncedWithServer) { SyncedWithServer = syncedWithServer; }

}
