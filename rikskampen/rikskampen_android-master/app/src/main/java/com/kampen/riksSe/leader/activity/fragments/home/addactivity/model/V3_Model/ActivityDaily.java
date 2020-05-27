
package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model;

import org.parceler.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


@SuppressWarnings("unused")
public class ActivityDaily extends RealmObject implements Comparable<ActivityDaily> {

    @SerializedName("calories")
    //@Expose
    private float mCalories;
    @SerializedName("date")
    private String mDate;
    @SerializedName("day")
    private int mDay;
    @SerializedName("distance")
    private double mDistance;

    @SerializedName("id")
    //@PrimaryKey
    private int mId;
    @SerializedName("locationAddress")
    private String mLocationAddress;
    @SerializedName("mediaImage")
    private String mMediaImage;
    @SerializedName("stars")
    private int mStars;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("steps")
    private int mSteps;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("type")
    private int mType;
    @SerializedName("waist")
    private int mWaist;
    @SerializedName("week")
    private int mWeek;
    @SerializedName("weight")
    private double mWeight;

    @SerializedName("distanceUnit")
    //@Expose
    public String distanceUnit;

    @SerializedName("caloriesUnit")
    //@Expose
    public String caloriesUnit;

    @SerializedName("latitude")
    private double mLatitude;

    @SerializedName("longitude")
    private double mLongitude;

    private   boolean isSyncedWithServer;


    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public boolean isSyncedWithServer() {
        return isSyncedWithServer;
    }

    public void setSyncedWithServer(boolean syncedWithServer) {
        isSyncedWithServer = syncedWithServer;
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

    public float getmCalories() {
        return mCalories;
    }

    public void setmCalories(float mCalories) {
        this.mCalories = mCalories;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public int getmDay() {
        return mDay;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }

    public double getmDistance() {
        return mDistance;
    }

    public void setmDistance(double mDistance) {
        this.mDistance = mDistance;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmLocationAddress() {
        return mLocationAddress;
    }

    public void setmLocationAddress(String mLocationAddress) { this.mLocationAddress = mLocationAddress; }

    public String getmMediaImage() {
        return mMediaImage;
    }

    public void setmMediaImage(String mMediaImage) {
        this.mMediaImage = mMediaImage;
    }

    public int getmStars() {
        return mStars;
    }

    public void setmStars(int mStars) {
        this.mStars = mStars;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public int getmSteps() {
        return mSteps;
    }

    public void setmSteps(int mSteps) {
        this.mSteps = mSteps;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public int getmWaist() {
        return mWaist;
    }

    public void setmWaist(int mWaist) {
        this.mWaist = mWaist;
    }

    public int getmWeek() {
        return mWeek;
    }

    public void setmWeek(int mWeek) {
        this.mWeek = mWeek;
    }

    public double getmWeight() {
        return mWeight;
    }

    public void setmWeight(double mWeight) {
        this.mWeight = mWeight;
    }

    @Override
    public int compareTo(ActivityDaily activityDaily) {

        if (getmDate() == null || activityDaily.getmDate() == null)
            return 0;
        return getmDate().compareTo(activityDaily.getmDate());
    }
}
