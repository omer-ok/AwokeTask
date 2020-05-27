package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class N_Plans extends RealmObject {

    @SerializedName("week")
    @PrimaryKey
    private int mWeek;
    @SerializedName("days")
    private RealmList<N_Days_V> mDays;

    public int getmWeek() {
        return mWeek;
    }

    public void setmWeek(int mWeek) {
        this.mWeek = mWeek;
    }

    public List<N_Days_V> getmDays() {
        return mDays;
    }

    public void setmDays(RealmList<N_Days_V> mDays) {
        this.mDays = mDays;
    }

}

