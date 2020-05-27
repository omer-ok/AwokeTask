package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
public class IngredientsDB extends RealmObject {


    @SerializedName("ingredient_name")
    @Expose
    public String  ingredientName;

    @SerializedName("quantity")
    @Expose
    public String  quantity;

    @SerializedName("unit")
    @Expose
    public String  unit;

    @SerializedName("fats")
    @Expose
    public String  fats;

    @SerializedName("calories")
    @Expose
    public String  calories;

    @SerializedName("protein")
    @Expose
    public String  protein;

    @SerializedName("carbs")
    @Expose
    public String  carbs;


    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }
}
