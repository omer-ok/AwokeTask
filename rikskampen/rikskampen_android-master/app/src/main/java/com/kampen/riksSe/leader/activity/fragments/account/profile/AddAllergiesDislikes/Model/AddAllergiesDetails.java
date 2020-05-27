package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class AddAllergiesDetails extends RealmObject {



    @SerializedName("AllergyName")
    @Expose
    private String AllergiesTitle;
    @SerializedName("allergyId")
    @Expose
    private String allergyId;
    @SerializedName("selected")
    @Expose
    private boolean isSelected;

    public String getAllergiesTitle() {
        return AllergiesTitle;
    }

    public void setAllergiesTitle(String allergiesTitle) {
        AllergiesTitle = allergiesTitle;
    }

    public String getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(String allergyId) {
        this.allergyId = allergyId;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
