package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class AlergyResultData extends RealmObject {

    @SerializedName("Alergies")
    @Expose
    private RealmList<Alergy> alergies = null;
    @SerializedName("userAlergies")
    @Expose
    private RealmList<UserAlergies> userAlergies = null;

    public RealmList<Alergy> getAlergies() {
        return alergies;
    }

    public void setAlergies(RealmList<Alergy> alergies) {
        this.alergies = alergies;
    }

    public RealmList<UserAlergies> getUserAlergies() {
        return userAlergies;
    }

    public void setUserAlergies(RealmList<UserAlergies> userAlergies) {
        this.userAlergies = userAlergies;
    }

}
