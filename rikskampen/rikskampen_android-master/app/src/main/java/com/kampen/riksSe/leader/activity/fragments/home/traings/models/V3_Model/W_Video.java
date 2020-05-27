
package com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

@SuppressWarnings("unused")
public class W_Video extends RealmObject implements Comparable<W_Video>{

    @SerializedName("bodyPart")
    private String mBodyPart;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("id")
    private int mId;
    @SerializedName("reps")
    private int mReps;
    @SerializedName("sets")
    private int mSets;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("type")
    private int mType;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("url_image")
    private String mUrlImage;

    public String getmBodyPart() {
        return mBodyPart;
    }

    public void setmBodyPart(String mBodyPart) {
        this.mBodyPart = mBodyPart;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmReps() {
        return mReps;
    }

    public void setmReps(int mReps) {
        this.mReps = mReps;
    }

    public int getmSets() {
        return mSets;
    }

    public void setmSets(int mSets) {
        this.mSets = mSets;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmUrlImage() {
        return mUrlImage;
    }

    public void setmUrlImage(String mUrlImage) {
        this.mUrlImage = mUrlImage;
    }

    @Override
    public int compareTo(W_Video wVideo) {
        if (getmType() == 0 || wVideo.getmType() == 0)
            return 0;
        return Integer.compare(getmType(),wVideo.getmType());
    }
}
