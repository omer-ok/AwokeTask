
package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

@SuppressWarnings("unused")
public class NutritionPlans extends RealmObject {

    @SerializedName("meal_types")
    private RealmList<MealType> mMealTypes;
    @SerializedName("plans")
    private RealmList<N_Plans> mPlans;

    public RealmList<MealType> getMealTypes() {
        return mMealTypes;
    }

    public void setMealTypes(RealmList<MealType> mealTypes) {
        mMealTypes = mealTypes;
    }
    public RealmList<N_Plans> getPlans() {
        return mPlans;
    }

    public void setPlans(RealmList<N_Plans> plans) {
        mPlans = plans;
    }

}
