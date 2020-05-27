package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.nutritions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredients {


    @SerializedName("ingredientName")
    @Expose
    private String  ingredientName;

    @SerializedName("unit")
    @Expose
    private String  unit;

    @SerializedName("fats")
    @Expose
    private String  fats;

    @SerializedName("calories")
    @Expose
    private String  calories;

    @SerializedName("protein")
    @Expose
    private String  protein;


    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
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
