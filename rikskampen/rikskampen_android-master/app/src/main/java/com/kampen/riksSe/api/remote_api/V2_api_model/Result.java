
package com.kampen.riksSe.api.remote_api.V2_api_model;

import androidx.annotation.Nullable;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.MotivationalVideo;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.NutritionPlans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.TrainingPlans;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmField;

@SuppressWarnings("unused")
public class Result{

    @SerializedName("truncate_table")
    @Expose
    @Nullable
    private TruncateTable truncateTable;
    @SerializedName("truncate_id")
    @Expose
    @Nullable
    private TruncateId truncateId;
    @SerializedName("activities")
    private List<ActivityDaily> mActivities;
    @SerializedName("competition")
    private List<Competition> mCompetition;
    @SerializedName("motivationalVideos")
    private List<MotivationalVideo> mMotivationalVideos;
    @SerializedName("nutritionPlanPDF")
    private String mNutritionPlanPDF;
    @SerializedName("nutritionPlans")
    private NutritionPlans mNutritionPlans;
    @SerializedName("trainingPlans")
    private TrainingPlans mTrainingPlans;


    public TruncateTable getTruncateTable() {
        return truncateTable;
    }

    public void setTruncateTable(TruncateTable truncateTable) {
        this.truncateTable = truncateTable;
    }

    public TruncateId getTruncateId() {
        return truncateId;
    }

    public void setTruncateId(TruncateId truncateId) {
        this.truncateId = truncateId;
    }
    public List<ActivityDaily> getActivities() {
        return mActivities;
    }

    public void setActivities(List<ActivityDaily> activities) {
        mActivities = activities;
    }

    public List<Competition> getCompetition() {
        return mCompetition;
    }

    public void setCompetition(List<Competition> competition) {
        mCompetition = competition;
    }

    public List<MotivationalVideo> getMotivationalVideos() {
        return mMotivationalVideos;
    }

    public void setMotivationalVideos(List<MotivationalVideo> motivationalVideos) {
        mMotivationalVideos = motivationalVideos;
    }

    public String getNutritionPlanPDF() {
        return mNutritionPlanPDF;
    }

    public void setNutritionPlanPDF(String nutritionPlanPDF) {
        mNutritionPlanPDF = nutritionPlanPDF;
    }

    public NutritionPlans getNutritionPlans() {
        return mNutritionPlans;
    }

    public void setNutritionPlans(NutritionPlans nutritionPlans) {
        mNutritionPlans = nutritionPlans;
    }

    public TrainingPlans getTrainingPlans() {
        return mTrainingPlans;
    }

    public void setTrainingPlans(TrainingPlans trainingPlans) {
        mTrainingPlans = trainingPlans;
    }

}
