package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserCalories;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserData;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserStars;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserSteps;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class LeaderBoardAllDataV3 extends RealmObject {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("competition")
    private RealmList<Competition> mCompetition;
    @SerializedName("userData")
    @Expose
    private UserDataV3 userData;
    @SerializedName("stepsData")
    @Expose
    private RealmList<StepsDataV3> stepsData;
    @SerializedName("starsData")
    @Expose
    private RealmList<StarsDataV3> starsData;
    @SerializedName("caloriesData")
    @Expose
    private RealmList<CaloriesDataV3> caloriesData;


    public RealmList<Competition> getmCompetition() {
        return mCompetition;
    }

    public void setmCompetition(RealmList<Competition> mCompetition) {
        this.mCompetition = mCompetition;
    }
    public RealmList<StepsDataV3> getStepsData() {
        return stepsData;
    }

    public void setStepsData(RealmList<StepsDataV3> stepsData) {
        this.stepsData = stepsData;
    }

    public RealmList<StarsDataV3> getStarsData() {
        return starsData;
    }

    public void setStarsData(RealmList<StarsDataV3> starsData) {
        this.starsData = starsData;
    }

    public RealmList<CaloriesDataV3> getCaloriesData() {
        return caloriesData;
    }

    public void setCaloriesData(RealmList<CaloriesDataV3> caloriesData) {
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

    public UserDataV3 getUserData() {
        return userData;
    }

    public void setUserData(UserDataV3 userData) {
        this.userData = userData;
    }



}
