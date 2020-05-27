package com.kampen.riksSe.user.module;


import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.CaloriesDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.StarsDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.StepsDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.UserDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.CaloriesData;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.ContestantUser;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.LeaderBoardAllData;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.StarData;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.StepsData;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.User;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserCalories;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserData;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserStars;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserSteps;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {LeaderBoardAllDataV3.class, UserDataV3.class, StepsDataV3.class, StarsDataV3.class, CaloriesDataV3.class,LeaderBoardAllData.class, ContestantUser.class, UserData.class, StepsData.class, UserSteps.class, StarData.class, UserStars.class, CaloriesData.class, UserCalories.class})
public class DB_LeaderBoard_Module {

}
