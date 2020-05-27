package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.model;

import java.io.Serializable;

public class WeeklyActivityModel implements Serializable {



    private String     date;
    private String     id;
    private String     activityPic;

    private String     lat;
    private String     lan;

    private String      calBurn;
    private String      steps;
    private String      stars;

    private String       activityType;

    private String       goalSteps;

    private String         activeTime;







    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityPic() {
        return activityPic;
    }

    public void setActivityPic(String activityPic) {
        this.activityPic = activityPic;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getCalBurn() {
        return calBurn;
    }

    public void setCalBurn(String calBurn) {
        this.calBurn = calBurn;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getGoalSteps() {
        return goalSteps;
    }

    public void setGoalSteps(String goalSteps) {
        this.goalSteps = goalSteps;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }





}
