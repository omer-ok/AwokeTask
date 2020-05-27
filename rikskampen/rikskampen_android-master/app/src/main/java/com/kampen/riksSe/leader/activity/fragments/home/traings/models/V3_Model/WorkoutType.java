
package com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

@SuppressWarnings("unused")
public class WorkoutType extends RealmObject {

    @SerializedName("en")
    private String mEn;
    @SerializedName("id")
    private int mId;
    @SerializedName("sv")
    private String mSv;
    @SerializedName("type")
    private String mType;

    public String getEn() {
        return mEn;
    }

    public void setEn(String en) {
        mEn = en;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getSv() {
        return mSv;
    }

    public void setSv(String sv) {
        mSv = sv;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
