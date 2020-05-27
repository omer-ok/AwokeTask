package com.kampen.riksSe.user.module;


import com.kampen.riksSe.leader.activity.fragments.home.traings.models.MediaModel;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.PT_Model;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.TrainingModel;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.Workout_Model;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {WeekTrainingModel.class,TrainingModel.class,PT_Model.class,Workout_Model.class,MediaModel.class})
public class DB_Training_Module {

}
