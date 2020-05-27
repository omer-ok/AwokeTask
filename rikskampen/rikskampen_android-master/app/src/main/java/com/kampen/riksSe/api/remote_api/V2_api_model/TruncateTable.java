package com.kampen.riksSe.api.remote_api.V2_api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TruncateTable {
    @SerializedName("activities")
    @Expose
    private boolean activities;
    @SerializedName("workout_plans")
    @Expose
    private boolean workoutPlans;
    @SerializedName("nutrition_plans")
    @Expose
    private boolean nutritionPlans;
    @SerializedName("motivational_videos")
    @Expose
    private boolean motivationalVideos;
    @SerializedName("recipes_books")
    @Expose
    private boolean recipesBooks;

    public boolean isRecipesBooks() {
        return recipesBooks;
    }

    public void setRecipesBooks(boolean recipesBooks) {
        this.recipesBooks = recipesBooks;
    }

    public boolean isActivities() {
        return activities;
    }

    public void setActivities(boolean activities) {
        this.activities = activities;
    }

    public boolean isWorkoutPlans() {
        return workoutPlans;
    }

    public void setWorkoutPlans(boolean workoutPlans) {
        this.workoutPlans = workoutPlans;
    }

    public boolean isNutritionPlans() {
        return nutritionPlans;
    }

    public void setNutritionPlans(boolean nutritionPlans) {
        this.nutritionPlans = nutritionPlans;
    }

    public boolean isMotivationalVideos() {
        return motivationalVideos;
    }

    public void setMotivationalVideos(boolean motivationalVideos) {
        this.motivationalVideos = motivationalVideos;
    }
}
