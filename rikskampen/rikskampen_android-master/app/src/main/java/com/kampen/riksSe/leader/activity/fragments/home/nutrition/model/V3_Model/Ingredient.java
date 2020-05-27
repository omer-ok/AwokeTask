
package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model;

import org.parceler.Generated;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


@SuppressWarnings("unused")
public class Ingredient  extends RealmObject {

    @SerializedName("calories")
    private float mCalories;
    @SerializedName("fats")
    private float mFats;
    @SerializedName("fats_unit")
    private String mFats_Unit;
    @SerializedName("fats_unit_sv")
    private String mFats_Unit_Sv;
    @SerializedName("id")
    //@PrimaryKey
    private int mId;
    @SerializedName("ingredient_name")
    private String mIngredientName;
    @SerializedName("ingredient_name_sv")
    private String mIngredientNameSv;
    @SerializedName("protein")
    private float mProtein;
    @SerializedName("protein_unit")
    private String mProtein_Unit;
    @SerializedName("protein_unit_sv")
    private String mProtein_Unit_Sv;
    @SerializedName("quantity")
    private float mQuantity;
    @SerializedName("unit")
    private String mUnit;
    @SerializedName("unit_sv")
    private String mUnit_Sv;
    @SerializedName("carbs")
    private float mCarbs;
    @SerializedName("carbs_unit")
    private String mCarbs_Unit;
    @SerializedName("carbs_unit_sv")
    private String mCarbs_Unit_Sv;
    public float getmCalories() {
        return mCalories;
    }

    public void setmCalories(float mCalories) {
        this.mCalories = mCalories;
    }

    public float getmFats() {
        return mFats;
    }

    public void setmFats(float mFats) {
        this.mFats = mFats;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmIngredientName() {
        return mIngredientName;
    }

    public void setmIngredientName(String mIngredientName) {
        this.mIngredientName = mIngredientName;
    }

    public String getmIngredientNameSv() {
        return mIngredientNameSv;
    }

    public void setmIngredientNameSv(String mIngredientNameSv) {
        this.mIngredientNameSv = mIngredientNameSv;
    }

    public float getmProtein() {
        return mProtein;
    }

    public void setmProtein(float mProtein) {
        this.mProtein = mProtein;
    }

    public float getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(float mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public float getmCarbs() {
        return mCarbs;
    }

    public void setmCarbs(float mCarbs) {
        this.mCarbs = mCarbs;
    }

    public String getmFats_Unit() {
        return mFats_Unit;
    }

    public void setmFats_Unit(String mFats_Unit) {
        this.mFats_Unit = mFats_Unit;
    }

    public String getmProtein_Unit() {
        return mProtein_Unit;
    }

    public void setmProtein_Unit(String mProtein_Unit) {
        this.mProtein_Unit = mProtein_Unit;
    }

    public String getmCarbs_Unit() {
        return mCarbs_Unit;
    }

    public void setmCarbs_Unit(String mCarbs_Unit) {
        this.mCarbs_Unit = mCarbs_Unit;
    }

    public String getmFats_Unit_Sv() {
        return mFats_Unit_Sv;
    }

    public void setmFats_Unit_Sv(String mFats_Unit_Sv) {
        this.mFats_Unit_Sv = mFats_Unit_Sv;
    }

    public String getmProtein_Unit_Sv() {
        return mProtein_Unit_Sv;
    }

    public void setmProtein_Unit_Sv(String mProtein_Unit_Sv) {
        this.mProtein_Unit_Sv = mProtein_Unit_Sv;
    }

    public String getmUnit_Sv() {
        return mUnit_Sv;
    }

    public void setmUnit_Sv(String mUnit_Sv) {
        this.mUnit_Sv = mUnit_Sv;
    }

    public String getmCarbs_Unit_Sv() {
        return mCarbs_Unit_Sv;
    }

    public void setmCarbs_Unit_Sv(String mCarbs_Unit_Sv) {
        this.mCarbs_Unit_Sv = mCarbs_Unit_Sv;
    }
}
