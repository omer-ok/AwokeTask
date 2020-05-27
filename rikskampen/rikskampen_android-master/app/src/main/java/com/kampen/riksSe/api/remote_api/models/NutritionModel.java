package com.kampen.riksSe.api.remote_api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NutritionModel  {


    @SerializedName("Nutritions")
    @Expose
    private NutritionListModel[] Nutritions;


    public NutritionListModel[] getNutritions() {
        return Nutritions;
    }

    public void setNutritions(NutritionListModel[] nutritions) {
        Nutritions = nutritions;
    }














}
