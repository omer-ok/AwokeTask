package com.kampen.riksSe.user.module;


import com.kampen.riksSe.ForgotPassword.Model.ForgotPasswordData;
import com.kampen.riksSe.ForgotPassword.Model.Result;
import com.kampen.riksSe.ForgotPassword.Model.User;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.Model.AddAllergiesData;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.Model.AddAllergiesDetails;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {ForgotPasswordData.class, Result.class, User.class})
public class DB_ForgotPass_Module {

}
