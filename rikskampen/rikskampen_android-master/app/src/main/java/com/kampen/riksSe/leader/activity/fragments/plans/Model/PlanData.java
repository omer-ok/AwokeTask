package com.kampen.riksSe.leader.activity.fragments.plans.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class PlanData extends RealmObject {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("result")
    @Expose


    private RealmList<PlanDetails> planDetailsList = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public RealmList<PlanDetails> getPlanDetailsList() {
        return planDetailsList;
    }

    public void setPlanDetailsList(RealmList<PlanDetails> planDetailsList) {
        this.planDetailsList = planDetailsList;
    }

}
