package com.kampen.riksSe.api.remote_api.models.all_data_remote.home;

import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.ActivitiesDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.MotivationVideos;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.NutritiousDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.TrainingsDB;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeData {



    @SerializedName("Activities")
    @Expose
    private ActivitiesDB activities;

    @SerializedName("Nutritions")
    @Expose
    private NutritiousDB nutritious;

    @SerializedName("Trainings")
    @Expose
    private TrainingsDB trainings;

    @SerializedName("MotivationVideos")
    @Expose
    private MotivationVideos motivationVideos;



    public ActivitiesDB getActivities() {
        return activities;
    }

    public void setActivities(ActivitiesDB activities) {
        this.activities = activities;
    }


    public NutritiousDB getNutritious() {
        return nutritious;
    }

    public void setNutritious(NutritiousDB nutritious) {
        this.nutritious = nutritious;
    }

    public TrainingsDB getTrainings() {
        return trainings;
    }

    public void setTrainings(TrainingsDB trainings) {
        this.trainings = trainings;
    }

    public MotivationVideos getMotivationVideos() {
        return motivationVideos;
    }

    public void setMotivationVideos(MotivationVideos motivationVideos) {
        this.motivationVideos = motivationVideos;
    }

}
