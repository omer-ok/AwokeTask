package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model;


import io.realm.RealmObject;

public class WeekActivityModel extends RealmObject {




    private String  userId;

    private String  weekID;

    private String  weekName;

    private String  imagePath;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWeekID() {
        return weekID;
    }

    public void setWeekID(String weekID) {
        this.weekID = weekID;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



}
