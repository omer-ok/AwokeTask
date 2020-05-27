package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PastActiviesDailyModel {

    @SerializedName("title")
    @Expose
    private Object title;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("mediaImage")
    @Expose
    private String mediaImage;
    @SerializedName("locationAddress")
    @Expose
    private Object locationAddress;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time_zone")
    @Expose
    private String timeZone;
    @SerializedName("steps")
    @Expose
    private int steps;
    @SerializedName("stars")
    @Expose
    private int stars;
    @SerializedName("calories")
    @Expose
    private float calories;
    @SerializedName("weight")
    @Expose
    private double weight;
    @SerializedName("distance")
    @Expose
    private double distance;
    @SerializedName("waist")
    @Expose
    private int waist;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("week")
    @Expose
    private int week;

    @SerializedName("day")
    @Expose
    private int day;

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMediaImage() {
        return mediaImage;
    }

    public void setMediaImage(String mediaImage) {
        this.mediaImage = mediaImage;
    }

    public Object getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(Object locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
