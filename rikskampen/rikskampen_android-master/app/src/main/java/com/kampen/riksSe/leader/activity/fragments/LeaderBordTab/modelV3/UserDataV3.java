package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class UserDataV3 extends RealmObject {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("thumbImage")
    @Expose
    private String thumbImage;
    @SerializedName("stepCount")
    @Expose
    private int stepCount;
    @SerializedName("starCount")
    @Expose
    private int starCount;
    @SerializedName("distanceSum")
    @Expose
    private double distanceSum;
    @SerializedName("caloriesCount")
    @Expose
    private double caloriesCount;
    @SerializedName("starsRank")
    @Expose
    private int starsRank;
    @SerializedName("stepsRank")
    @Expose
    private int stepsRank;
    @SerializedName("caloriesRank")
    @Expose
    private int caloriesRank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public double getDistanceSum() {
        return distanceSum;
    }

    public void setDistanceSum(double distanceSum) {
        this.distanceSum = distanceSum;
    }

    public double getCaloriesCount() {
        return caloriesCount;
    }

    public void setCaloriesCount(double caloriesCount) {
        this.caloriesCount = caloriesCount;
    }

    public int getStarsRank() {
        return starsRank;
    }

    public void setStarsRank(int starsRank) {
        this.starsRank = starsRank;
    }

    public int getStepsRank() {
        return stepsRank;
    }

    public void setStepsRank(int stepsRank) {
        this.stepsRank = stepsRank;
    }

    public int getCaloriesRank() {
        return caloriesRank;
    }

    public void setCaloriesRank(int caloriesRank) {
        this.caloriesRank = caloriesRank;
    }

}
