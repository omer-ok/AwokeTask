package com.kampen.riksSe.api.remote_api.V2_api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TruncateId {
    @SerializedName("activities")
    @Expose
    private List<Integer> activities = null;
    @SerializedName("workout_plans")
    @Expose
    private List<Integer> workoutPlans = null;
    @SerializedName("nutrition_plans")
    @Expose
    private List<Integer> nutritionPlans = null;
    @SerializedName("motivational_videos")
    @Expose
    private List<Integer> motivationalVideos = null;

    public List<Integer> getActivities() {
        return activities;
    }

    public void setActivities(List<Integer> activities) {
        this.activities = activities;
    }

    public List<Integer> getWorkoutPlans() {
        return workoutPlans;
    }

    public void setWorkoutPlans(List<Integer> workoutPlans) {
        this.workoutPlans = workoutPlans;
    }

    public List<Integer> getNutritionPlans() {
        return nutritionPlans;
    }

    public void setNutritionPlans(List<Integer> nutritionPlans) {
        this.nutritionPlans = nutritionPlans;
    }

    public List<Integer> getMotivationalVideos() {
        return motivationalVideos;
    }

    public void setMotivationalVideos(List<Integer> motivationalVideos) {
        this.motivationalVideos = motivationalVideos;
    }
}
