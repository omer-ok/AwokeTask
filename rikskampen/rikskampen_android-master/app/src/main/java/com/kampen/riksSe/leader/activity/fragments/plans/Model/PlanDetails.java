package com.kampen.riksSe.leader.activity.fragments.plans.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class PlanDetails extends RealmObject {

    @SerializedName("idSingle")
    @Expose
    private String idSingle;
    @SerializedName("idDuo")
    @Expose
    private String idDuo;
    @SerializedName("titleSingle")
    @Expose
    private String titleSingle;
    @SerializedName("titleDuo")
    @Expose
    private String titleDuo;
    @SerializedName("costSingle")
    @Expose
    private String costSingle;
    @SerializedName("costDuo")
    @Expose
    private String costDuo;
    @SerializedName("installmentsPlanSingle")
    @Expose
    private String installmentsPlanSingle;
    @SerializedName("installmentsPlanDuo")
    @Expose
    private String installmentsPlanDuo;
    @SerializedName("adminFeeSingle")
    @Expose
    private String adminFeeSingle;
    @SerializedName("adminFeeDuo")
    @Expose
    private String adminFeeDuo;
    @SerializedName("featuresSingle")
    @Expose
    private String featuresSingle;
    @SerializedName("featuresDuo")
    @Expose
    private String featuresDuo;
    @SerializedName("imageDuo")
    @Expose
    private String imageDuo;
    @SerializedName("imageSingle")
    @Expose
    private String imageSingle;
    @SerializedName("prize")
    @Expose
    private String prize;

    public String getIdSingle() {
        return idSingle;
    }

    public void setIdSingle(String idSingle) {
        this.idSingle = idSingle;
    }

    public String getIdDuo() {
        return idDuo;
    }

    public void setIdDuo(String idDuo) {
        this.idDuo = idDuo;
    }

    public String getTitleSingle() {
        return titleSingle;
    }

    public void setTitleSingle(String titleSingle) {
        this.titleSingle = titleSingle;
    }

    public String getTitleDuo() {
        return titleDuo;
    }

    public void setTitleDuo(String titleDuo) {
        this.titleDuo = titleDuo;
    }

    public String getCostSingle() {
        return costSingle;
    }

    public void setCostSingle(String costSingle) {
        this.costSingle = costSingle;
    }

    public String getCostDuo() {
        return costDuo;
    }

    public void setCostDuo(String costDuo) {
        this.costDuo = costDuo;
    }

    public String getInstallmentsPlanSingle() {
        return installmentsPlanSingle;
    }

    public void setInstallmentsPlanSingle(String installmentsPlanSingle) {
        this.installmentsPlanSingle = installmentsPlanSingle;
    }

    public String getInstallmentsPlanDuo() {
        return installmentsPlanDuo;
    }

    public void setInstallmentsPlanDuo(String installmentsPlanDuo) {
        this.installmentsPlanDuo = installmentsPlanDuo;
    }

    public String getAdminFeeSingle() {
        return adminFeeSingle;
    }

    public void setAdminFeeSingle(String adminFeeSingle) {
        this.adminFeeSingle = adminFeeSingle;
    }

    public String getAdminFeeDuo() {
        return adminFeeDuo;
    }

    public void setAdminFeeDuo(String adminFeeDuo) {
        this.adminFeeDuo = adminFeeDuo;
    }

    public String getFeaturesSingle() {
        return featuresSingle;
    }

    public void setFeaturesSingle(String featuresSingle) {
        this.featuresSingle = featuresSingle;
    }

    public String getFeaturesDuo() {
        return featuresDuo;
    }

    public void setFeaturesDuo(String featuresDuo) {
        this.featuresDuo = featuresDuo;
    }

    public String getImageDuo() {
        return imageDuo;
    }

    public void setImageDuo(String imageDuo) {
        this.imageDuo = imageDuo;
    }

    public String getImageSingle() {
        return imageSingle;
    }

    public void setImageSingle(String imageSingle) {
        this.imageSingle = imageSingle;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

}
