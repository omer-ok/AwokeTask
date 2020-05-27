package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllergyResult {

    @SerializedName("name")
    @Expose
    private String title;
    @SerializedName("id")
    @Expose
    private int allergyId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(int allergyId) {
        this.allergyId = allergyId;
    }
}
