package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class N_DayDB extends RealmObject {


    @SerializedName("dayName")
    @Expose
    public String  dayName;

    @SerializedName("dayID")
    @Expose
    public int  dayID;

    @SerializedName("currentDay")
    @Expose
    public String  currentDay;

    @SerializedName("imagePath")
    @Expose
    public String  imagePath;

    @SerializedName("dayNutritionList")
    @Expose
    public RealmList<DayNutritionMealOptionsDB> dayNutritionList;


    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public RealmList<DayNutritionMealOptionsDB> getDayNutritionList() {
        return dayNutritionList;
    }

    public void setDayNutritionList(RealmList<DayNutritionMealOptionsDB> dayNutritionList) {
        this.dayNutritionList = dayNutritionList;
    }

    public String getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(String currentDay) {
        this.currentDay = currentDay;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
