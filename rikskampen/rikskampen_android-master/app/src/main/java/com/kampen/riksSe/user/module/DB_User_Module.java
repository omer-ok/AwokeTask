package com.kampen.riksSe.user.module;


import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.ContestantUser;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.TopContestant;

import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.model.DailyVideo;
import com.kampen.riksSe.leader.activity.fragments.map.Modal.PlacesDetails_Modal;
import com.kampen.riksSe.leader.activity.fragments.order_history.fragments.model.Order;
import com.kampen.riksSe.login.ModelsV3.UserRoles;
import com.kampen.riksSe.user.model.Category;
import com.kampen.riksSe.user.model.ContestantDB;
import com.kampen.riksSe.user.model.DB_DailyFitnessPic;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.user.model.TestItem;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {Realm_User.class, UserRoles.class, ContestantDB.class, ContestantUser.class,DB_DailyFitnessPic.class,PlacesDetails_Modal.class,TopContestant.class,Order.class,DailyVideo.class,TestItem.class,Category.class})
public class DB_User_Module {

}
