package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model;

import java.util.List;

import io.realm.RealmList;

public class DailyDiaryQuestionModel {

    Integer id;
    private String mDayDescription;
    private Boolean mDayStatus;
    private String Date;
    private List<QuestionResponceModel> mQuestions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<QuestionResponceModel> getmQuestions() {
        return mQuestions;
    }

    public void setmQuestions(List<QuestionResponceModel> mQuestions) {
        this.mQuestions = mQuestions;
    }

    public String getmDayDescription() {
        return mDayDescription;
    }

    public void setmDayDescription(String mDayDescription) {
        this.mDayDescription = mDayDescription;
    }

    public Boolean ismDayStatus() {
        return mDayStatus;
    }

    public void setmDayStatus(Boolean mDayStatus) {
        this.mDayStatus = mDayStatus;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
