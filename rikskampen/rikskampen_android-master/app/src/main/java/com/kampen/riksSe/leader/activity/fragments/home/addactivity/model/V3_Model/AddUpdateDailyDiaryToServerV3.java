
package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.diaries;

import java.util.List;

@SuppressWarnings("unused")
public class AddUpdateDailyDiaryToServerV3 {

    @SerializedName("diaries")
    @Expose
    private List<DiariesUpdateServer> mDiary;

    public List<DiariesUpdateServer> getDiary() {
        return mDiary;
    }

    public void setDiary(List<DiariesUpdateServer> diary) {
        mDiary = diary;
    }

}
