package com.kampen.riksSe.user.module;



import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.DayTainingDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_weeklyActivitiesDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.TrainingsDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.VideoStartEnd;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {DayTainingDB.class, T_DayDB.class, T_DaysDB.class, T_WeekDB.class, T_weeklyActivitiesDB.class, VideoStartEnd.class, TrainingsDB.class})
public class DB_Training_Module_New {

}
