package com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.adapterListModel;

import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.DailyDiaryQuestionModel;

import java.util.List;

public class activityAdapterListModel implements Comparable<activityAdapterListModel>{


    private float mCalories;

    private String mDate;

    private long mDay;

    private double mDistance;

    private int mId;

    private String mLocationAddress;

    private String mMediaImage;

    private int mStars;

    private String mStatus;

    private int mSteps;

    private String mTitle;

    private int mType;

    private int mWaist;

    private long mWeek;

    private double mWeight;

    public String distanceUnit;

    public String caloriesUnit;

    public String DayName;

    public String CurrentWeek;

    public DailyDiaryQuestionModel dailyDiaryQuestionModel;

    public String getCurrentWeek() {
        return CurrentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        CurrentWeek = currentWeek;
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

    public String getDayName() {
        return DayName;
    }

    public void setDayName(String dayName) {
        DayName = dayName;
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

    public long getmDay() {
        return mDay;
    }

    public void setmDay(long mDay) {
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

    public long getmWeek() {
        return mWeek;
    }

    public void setmWeek(long mWeek) {
        this.mWeek = mWeek;
    }

    public double getmWeight() {
        return mWeight;
    }

    public void setmWeight(double mWeight) {
        this.mWeight = mWeight;
    }

    public DailyDiaryQuestionModel getDailyDiaryQuestionModel() {
        return dailyDiaryQuestionModel;
    }

    public void setDailyDiaryQuestionModel(DailyDiaryQuestionModel dailyDiaryQuestionModel) {
        this.dailyDiaryQuestionModel = dailyDiaryQuestionModel;
    }


    @Override
    public int compareTo(activityAdapterListModel activityAdapterListModel) {
        if (getmDate() == null || activityAdapterListModel.getmDate() == null)
            return 0;
        return getmDate().compareTo(activityAdapterListModel.getmDate());
    }
}
