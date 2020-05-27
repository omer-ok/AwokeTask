package com.kampen.riksSe.user.module;


import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.DailyActivityModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.Stars_Model;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.MotivationalVideo;
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

@RealmModule(classes = {Competition.class,ActivityDaily.class, MotivationalVideo.class})
public class DB_Activity_V3_Module {

}
