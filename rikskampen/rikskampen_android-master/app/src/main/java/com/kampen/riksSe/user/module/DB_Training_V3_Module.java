package com.kampen.riksSe.user.module;



import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.TrainingPlans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Day;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Video;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.WorkoutType;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.DayTainingDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_weeklyActivitiesDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.TrainingsDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.VideoStartEnd;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {TrainingPlans.class, WorkoutType.class, W_Video.class, W_Plans.class, W_Day.class})
public class DB_Training_V3_Module {

}
