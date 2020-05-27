package com.kampen.riksSe.user.module;


import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.Contestant;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.Trainer;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.TrainingModel;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {ScheduledLiveVideoCall.class,TrainingModel.class, Trainer.class, Contestant.class})
public class DB_TrainerSchdule_Module {

}
