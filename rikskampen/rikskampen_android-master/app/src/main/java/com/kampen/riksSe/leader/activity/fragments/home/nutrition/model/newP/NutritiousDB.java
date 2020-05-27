package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class NutritiousDB extends RealmObject {


    @SerializedName("userID")
    @Expose
    private  String userID;

    @SerializedName("weeklyNutrition")
    @Expose
    private
    N_weeklyActivitiesDB weekNutrition;

    @SerializedName("recipeSchedule")
    @Expose
    private  String recipeSchedule;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public N_weeklyActivitiesDB getWeekNutrition() {
        return weekNutrition;
    }

    public void setWeekNutrition(N_weeklyActivitiesDB weekNutrition) {
        this.weekNutrition = weekNutrition;
    }

    public String getRecipeSchedule() {
        return recipeSchedule;
    }

    public void setRecipeSchedule(String recipeSchedule) {
        this.recipeSchedule = recipeSchedule;
    }
}
