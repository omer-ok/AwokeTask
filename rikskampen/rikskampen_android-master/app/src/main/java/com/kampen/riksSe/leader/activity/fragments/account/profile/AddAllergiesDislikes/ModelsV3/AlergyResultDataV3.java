package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class AlergyResultDataV3 extends RealmObject {

    @SerializedName("allergies")
    @Expose
    private RealmList<AllergyV3> alergies;
    @SerializedName("userAllergies")
    @Expose
    private RealmList<UserAllergyV3> userAlergies;

    public RealmList<AllergyV3> getAlergies() {
        return alergies;
    }

    public void setAlergies(RealmList<AllergyV3> alergies) {
        this.alergies = alergies;
    }

    public RealmList<UserAllergyV3> getUserAlergies() {
        return userAlergies;
    }

    public void setUserAlergies(RealmList<UserAllergyV3> userAlergies) {
        this.userAlergies = userAlergies;
    }
}
