package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class LeaderBoardAllData extends RealmObject {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("userData")
    @Expose
    private UserData userData;
    @SerializedName("StepsData")
    @Expose
    private RealmList<UserSteps> stepsData;
    @SerializedName("StarsData")
    @Expose
    private RealmList<UserStars> starsData;

    @SerializedName("CaloriesData")
    @Expose
    private RealmList<UserCalories> caloriesData;

    public RealmList<UserSteps> getStepsData() {
        return stepsData;
    }

    public void setStepsData(RealmList<UserSteps> stepsData) {
        this.stepsData = stepsData;
    }

    public RealmList<UserStars> getStarsData() {
        return starsData;
    }

    public void setStarsData(RealmList<UserStars> starsData) {
        this.starsData = starsData;
    }

    public RealmList<UserCalories> getCaloriesData() {
        return caloriesData;
    }

    public void setCaloriesData(RealmList<UserCalories> caloriesData) {
        this.caloriesData = caloriesData;
    }




    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }



}
