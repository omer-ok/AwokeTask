package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MealIngrediantsOptionsDB extends RealmObject {

    @SerializedName("mealName")
    @Expose
    public String mealName;



    @SerializedName("mealName_SV")
    @Expose
    public String mealName_SV;

    @SerializedName("MealOptionList")
    @Expose
    public RealmList<DayNutritionDB> MealOptionArrayList;

    public String getMealName_SV() {
        return mealName_SV;
    }

    public void setMealName_SV(String mealName_SV) {
        this.mealName_SV = mealName_SV;
    }
    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public RealmList<DayNutritionDB> getMealOptionArrayList() {
        return MealOptionArrayList;
    }

    public void setMealOptionArrayList(RealmList<DayNutritionDB> mealOptionArrayList) {
        MealOptionArrayList = mealOptionArrayList;
    }
}
