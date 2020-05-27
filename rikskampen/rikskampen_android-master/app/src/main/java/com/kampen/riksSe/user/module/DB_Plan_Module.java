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
import com.kampen.riksSe.leader.activity.fragments.plans.Model.PlanData;
import com.kampen.riksSe.leader.activity.fragments.plans.Model.PlanDetails;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {PlanData.class, PlanDetails.class})
public class DB_Plan_Module {

}
