
package com.kampen.riksSe.api.remote_api.V2_api_model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

@SuppressWarnings("unused")
public class Competition extends RealmObject {

    @SerializedName("id")
    private int mId;
    @SerializedName("startDate")
    private String mStartDate;
    @SerializedName("endDate")
    private String mEndDate;
    @SerializedName("title")
    private String mName;
    @SerializedName("CompetitionStatus")
    private Boolean mCompetitionStatus;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    public Boolean getmCompetitionStatus() {
        return mCompetitionStatus;
    }

    public void setmCompetitionStatus(Boolean mCompetitionStatus) {
        this.mCompetitionStatus = mCompetitionStatus;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

}
