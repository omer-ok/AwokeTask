
package com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


@SuppressWarnings("unused")
public class W_Plans extends RealmObject implements Comparable<W_Plans>{

    @SerializedName("days")
    private RealmList<W_Day> mWDays;
    @SerializedName("week")
    @PrimaryKey
    private int mWeek;
    @SerializedName("image")
    private String mWeekImage;


    public RealmList<W_Day> getmWDays() {
        return mWDays;
    }

    public void setmWDays(RealmList<W_Day> mWDays) {
        this.mWDays = mWDays;
    }

    public int getmWeek() {
        return mWeek;
    }

    public void setmWeek(int mWeek) {
        this.mWeek = mWeek;
    }

    public String getmWeekImage() {
        return mWeekImage;
    }

    public void setmWeekImage(String mWeekImage) {
        this.mWeekImage = mWeekImage;
    }
    @Override
    public int compareTo(W_Plans wPlans) {
        if (getmWeek() == 0 || wPlans.getmWeek() == 0)
            return 0;
        return Integer.compare(getmWeek(),wPlans.getmWeek());
    }
}
