
package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Meal  extends RealmObject {
    @SerializedName("meal_id")
    private int mMealId;
    @SerializedName("main_ingredient")
    private RealmList<MainIngredient> mMainIngredient;

    public int getMealId() {
        return mMealId;
    }

    public void setMealId(int mealId) {
        mMealId = mealId;
    }

    public RealmList<MainIngredient> getMainIngredient() {
        return mMainIngredient;
    }

    public void setMainIngredient(RealmList<MainIngredient> mainIngredient) {
        mMainIngredient = mainIngredient;
    }

}
