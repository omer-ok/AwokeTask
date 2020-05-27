
package com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Meal;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


@SuppressWarnings("unused")
public class W_Day extends RealmObject implements Comparable<W_Day>{

    @SerializedName("day")

    private int mDay;
    @SerializedName("instructions")
    private String mInstructions;
    @SerializedName("plan_id")
    @PrimaryKey
    private int mPlanId;
    @SerializedName("videos")
    private RealmList<W_Video> mWVideos;
    @SerializedName("week")
    private int mWeek;
    @SerializedName("week_image")
    private String mWeekImage;
    @SerializedName("status")
    private String status;


    public int getmDay() {
        return mDay;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }

    public String getmInstructions() {
        return mInstructions;
    }

    public void setmInstructions(String mInstructions) {
        this.mInstructions = mInstructions;
    }

    public int getmPlanId() {
        return mPlanId;
    }

    public void setmPlanId(int mPlanId) {
        this.mPlanId = mPlanId;
    }

    public RealmList<W_Video> getmWVideos() {
        return mWVideos;
    }

    public void setmWVideos(RealmList<W_Video> mWVideos) {
        this.mWVideos = mWVideos;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(W_Day w_day) {
        if (getmDay() == 0 || w_day.getmDay() == 0)
            return 0;
        return Integer.compare(getmDay(),w_day.getmDay());
    }
}
