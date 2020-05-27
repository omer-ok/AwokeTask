package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class UserAlergies extends RealmObject {

    @SerializedName("allergyId")
    @Expose
    private Integer allergyId;
    @SerializedName("AllergyName")
    @Expose
    private String allergyName;
    @SerializedName("selected")
    @Expose
    private Boolean selected;

    private Boolean isRecentlySelected = false;


    public Boolean getRecentlySelected() {
        return isRecentlySelected;
    }

    public void setRecentlySelected(Boolean recentlySelected) {
        isRecentlySelected = recentlySelected;
    }

    public Integer getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(Integer allergyId) {
        this.allergyId = allergyId;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
