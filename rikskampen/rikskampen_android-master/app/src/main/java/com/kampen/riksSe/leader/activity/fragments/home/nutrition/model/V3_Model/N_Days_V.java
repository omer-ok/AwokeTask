package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class N_Days_V  extends RealmObject {
    @SerializedName("plan_id")
    @PrimaryKey
    private int mPlanId;
    @SerializedName("status")
    private String status;
    @SerializedName("week")
    private int mWeek;
    @SerializedName("week_image")
    private String mWeekImage;
    @SerializedName("day")
    private int mDay;
    @SerializedName("meals")
    private RealmList<Meal> mMeals;

    public int getmPlanId() {
        return mPlanId;
    }

    public void setmPlanId(int mPlanId) {
        this.mPlanId = mPlanId;
    }

    public int getmWeek() {
        return mWeek;
    }

    public void setmWeek(int mWeek) {
        this.mWeek = mWeek;
    }

    public String getmWeekImage() {
        return mWeekImage;
    }

    public void setmWeekImage(String mWeekImage) {
        this.mWeekImage = mWeekImage;
    }

    public int getmDay() {
        return mDay;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }

    public List<Meal> getmMeals() {
        return mMeals;
    }

    public void setmMeals(RealmList<Meal> mMeals) {
        this.mMeals = mMeals;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
