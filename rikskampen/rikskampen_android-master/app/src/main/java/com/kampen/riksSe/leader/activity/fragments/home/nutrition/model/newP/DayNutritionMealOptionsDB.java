package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class DayNutritionMealOptionsDB extends RealmObject {


    @SerializedName("MealImage")
    @Expose
    public String MealImage;

    @SerializedName("MealType")
    @Expose
    public String MealType;

    @SerializedName("MealTypeSV")
    @Expose
    public String MealTypeSV;

    @SerializedName("MealIngrediantsOptionList")
    @Expose
    public RealmList<MealIngrediantsOptionsDB> MealIngrediantsOptionsList;

    public String getMealImage() {
        return MealImage;
    }

    public void setMealImage(String mealImage) {
        MealImage = mealImage;
    }

    public String getMealType() {
        return MealType;
    }

    public String getMealTypeSV() {
        return MealTypeSV;
    }

    public void setMealTypeSV(String mealTypeSV) {
        MealTypeSV = mealTypeSV;
    }
    public void setMealType(String mealType) {
        MealType = mealType;
    }

    public RealmList<MealIngrediantsOptionsDB> getMealIngrediantsOptionsList() {
        return MealIngrediantsOptionsList;
    }

    public void setMealIngrediantsOptionsList(RealmList<MealIngrediantsOptionsDB> mealIngrediantsOptionsList) {
        MealIngrediantsOptionsList = mealIngrediantsOptionsList;
    }

}
