package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;




public class N_WeekDB extends RealmObject  {


    @SerializedName("weekID")
    @Expose
    public int  weekID;

    @SerializedName("weekName")
    @Expose
    public int  weekName;


    @SerializedName("imagePath")
    @Expose
    public String  imagePath;

    @SerializedName("weeklyIngredientsList")
    @Expose
    public RealmList<WeeklyIngredients> weeklyIngredientsList;

    @SerializedName("days")
    @Expose
    public N_DaysDB days;

    @SerializedName("currentWeek")
    @Expose
    public String currentWeek;


    public int getWeekID() {
        return weekID;
    }

    public void setWeekID(int weekID) {
        this.weekID = weekID;
    }

    public int getWeekName() {
        return weekName;
    }

    public void setWeekName(int weekName) {
        this.weekName = weekName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public RealmList<WeeklyIngredients> getWeeklyIngredientsList() {
        return weeklyIngredientsList;
    }

    public void setWeeklyIngredientsList(RealmList<WeeklyIngredients> weeklyIngredientsList) {
        this.weeklyIngredientsList = weeklyIngredientsList;
    }

    public N_DaysDB getDays() {
        return days;
    }

    public void setDays(N_DaysDB days) {
        this.days = days;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }


}
