package com.kampen.riksSe.user.module;


import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.DailyActivityModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.Stars_Model;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.WeekActivityModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_DayActivityList;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_weeklyActivitiesDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.ActivitiesDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.AddActivityDBLocal;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.MotivationVideos;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.VideoM;
import com.kampen.riksSe.leader.activity.fragments.map.Modal.UserJourneyData;
import com.kampen.riksSe.user.model.UserActivityData;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {WeekActivityModel.class,DailyActivityModel.class,Stars_Model.class, ActivitiesDB.class, A_weeklyActivitiesDB.class, A_WeekDB.class, A_DaysDB.class, A_DayDB.class, A_DayActivityList.class, UserActivityData.class, AddActivityDBLocal.class, UserJourneyData.class, MotivationVideos.class, VideoM.class})
public class DB_Activity_Module {

}
