package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class AllergyV3 extends RealmObject {

    @SerializedName("name")
    @Expose
    private String title;
    @SerializedName("id")
    @Expose
    private int allergyId;

    private String Status;

    private Boolean isRecentlySelected = false;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

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

    public Boolean getRecentlySelected() {
        return isRecentlySelected;
    }

    public void setRecentlySelected(Boolean recentlySelected) {
        isRecentlySelected = recentlySelected;
    }
}
