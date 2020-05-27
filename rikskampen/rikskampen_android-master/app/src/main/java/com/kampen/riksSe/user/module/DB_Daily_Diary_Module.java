package com.kampen.riksSe.user.module;


import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.QuestionResponse;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.diaries;
import com.kampen.riksSe.leader.activity.modelV3.Question;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {diaries.class, QuestionResponse.class, Question.class})
public class DB_Daily_Diary_Module {

}
