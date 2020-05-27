package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model;

import com.google.gson.annotations.SerializedName;

public class QuestionResponceModel {

    private int mId;
    private boolean mResponse;
    String question;
    int order_id;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public boolean ismResponse() {
        return mResponse;
    }

    public void setmResponse(boolean mResponse) {
        this.mResponse = mResponse;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
