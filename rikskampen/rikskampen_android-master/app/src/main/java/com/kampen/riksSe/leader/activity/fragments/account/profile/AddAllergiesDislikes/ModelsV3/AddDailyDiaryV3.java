
package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AddDailyDiaryV3 {

    @SerializedName("diaries")
    private List<diaries> mDiary;

    public List<diaries> getDiary() {
        return mDiary;
    }

    public void setDiary(List<diaries> diary) {
        mDiary = diary;
    }

}
