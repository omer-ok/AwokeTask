package com.kampen.riksSe.user.module;


import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.Alergy;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.AlergyResultData;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.AllergyData;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.UserAlergies;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AlergyResultDataV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AllergyV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.UserAllergyV3;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {AlergyResultDataV3.class, AllergyV3.class, UserAllergyV3.class,AllergyData.class, AlergyResultData.class, Alergy.class, UserAlergies.class,})
public class DB_Allergies_Module {

}
