package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class A_DayActivityList extends RealmObject {

    @SerializedName("DailyImage")
    @Expose
    public String  DailyImage;
    @SerializedName("LocationAddress")
    @Expose
    public String  LocationAddress;
    @SerializedName("Date")
    @Expose
    public String  Date;

    @SerializedName("Steps")
    @Expose
    public String  Steps;

    @SerializedName("Stars")
    @Expose
    public String  Stars;


    @SerializedName("calories")
    @Expose
    public String calories;

    @SerializedName("weightLoss")
    @Expose
    public String weightLoss;

    @SerializedName("waist")
    @Expose
    public String waist;


    @SerializedName("distance")
    @Expose
    public String distance;

    @SerializedName("distanceUnit")
    @Expose
    public String distanceUnit;


    @SerializedName("caloriesUnit")
    @Expose
    public String caloriesUnit;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStars() {
        return Stars;
    }

    public void setStars(String stars) {
        Stars = stars;
    }

    public String getCalories() {
        return calories;
    }
    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public String getCaloriesUnit() {
        return caloriesUnit;
    }

    public void setCaloriesUnit(String caloriesUnit) {
        this.caloriesUnit = caloriesUnit;
    }


    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getDailyImage() {
        return DailyImage;
    }

    public void setDailyImage(String dailyImage) {
        DailyImage = dailyImage;
    }

    public String getLocationAddress() {
        return LocationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        LocationAddress = locationAddress;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSteps() {
        return Steps;
    }

    public void setSteps(String steps) {
        Steps = steps;
    }


    public String getWeightLoss() {
        return weightLoss;
    }

    public void setWeightLoss(String weightLoss) {
        this.weightLoss = weightLoss;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }
}
