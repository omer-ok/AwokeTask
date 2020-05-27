package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class AllergyData extends RealmObject {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("result")
    @Expose
    private AlergyResultData result;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public AlergyResultData getResult() {
        return result;
    }

    public void setResult(AlergyResultData result) {
        this.result = result;
    }


}
