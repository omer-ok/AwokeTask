package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class AddAllergiesData extends RealmObject {


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
    private RealmList<AddAllergiesDetails> AllergiesList = null;

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


    public RealmList<AddAllergiesDetails> getAllergiesList() {
        return AllergiesList;
    }

    public void setAllergiesList(RealmList<AddAllergiesDetails> allergiesList) {
        AllergiesList = allergiesList;
    }



}
