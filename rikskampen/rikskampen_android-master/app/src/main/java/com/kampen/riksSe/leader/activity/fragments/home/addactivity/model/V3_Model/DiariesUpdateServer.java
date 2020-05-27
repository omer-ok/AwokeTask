
package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.QuestionResponse;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@SuppressWarnings("unused")
public class DiariesUpdateServer {
    @SerializedName("user_id")
    @Expose
    private int mUserId;
   /* @SerializedName("id")
    @Expose
    private Integer mid;*/
    @SerializedName("day_description")
    @Expose
    @Nullable
    private String mDayDescription;
    @SerializedName("day_status")
    @Expose
    private Boolean mDayStatus;
    @SerializedName("questions")
    @Expose
    @Nullable
    private List<QuestionResponse> mQuestions;
    @SerializedName("diary_date")
    @Expose
    private String mDiary_Date;
  /*@SerializedName("updated_at")
    @Expose
    private String mUpdated_At;
    @SerializedName("created_at")
    @Expose
    private String mCreated_At;*/


    public int getmUserId() {
        return mUserId;
    }

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getDayDescription() {
        return mDayDescription;
    }

    public void setDayDescription(String dayDescription) {
        mDayDescription = dayDescription;
    }

    public Boolean getDayStatus() {
        return mDayStatus;
    }

    public void setDayStatus(Boolean dayStatus) {
        mDayStatus = dayStatus;
    }

    public List<QuestionResponse> getQuestions() {
        return mQuestions;
    }

    public void setQuestions(List<QuestionResponse> questions) {
        mQuestions = questions;
    }

   /* public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }*/

    public String getmDiary_Date() {
        return mDiary_Date;
    }

    public void setmDiary_Date(String mDiary_Date) {
        this.mDiary_Date = mDiary_Date;
    }

    /*public String getmUpdated_At() {
        return mUpdated_At;
    }

    public void setmUpdated_At(String mUpdated_At) {
        this.mUpdated_At = mUpdated_At;
    }

    public String getmCreated_At() {
        return mCreated_At;
    }

    public void setmCreated_At(String mCreated_At) {
        this.mCreated_At = mCreated_At;
    }*/
}
