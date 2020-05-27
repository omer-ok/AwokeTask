
package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

@SuppressWarnings("unused")
public class QuestionResponse extends RealmObject {

    @SerializedName("id")
    private int mQuestionId;
    @SerializedName("response")
    private boolean mResponse;

    public int getId() {
        return mQuestionId;
    }

    public void setId(int id) {
        mQuestionId = id;
    }

    public boolean getResponse() {
        return mResponse;
    }

    public void setResponse(boolean response) {
        mResponse = response;
    }

}
