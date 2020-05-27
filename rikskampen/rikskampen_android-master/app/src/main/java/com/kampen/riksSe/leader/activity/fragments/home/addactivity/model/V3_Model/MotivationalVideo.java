
package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


@SuppressWarnings("unused")
public class MotivationalVideo extends RealmObject  {

    @SerializedName("id")
    @PrimaryKey
    private int mId;
    @SerializedName("mediaThumb")
    private String mMediaThumb;
    @SerializedName("mediaUrl")
    private String mMediaUrl;
    @SerializedName("mimeType")
    private String mMimeType;
    @SerializedName("postedDate")
    private String mPostedDate;
    @SerializedName("size")
    private int mSize;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("status")
    private String status;


    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getMediaThumb() {
        return mMediaThumb;
    }

    public void setMediaThumb(String mediaThumb) {
        mMediaThumb = mediaThumb;
    }

    public String getMediaUrl() {
        return mMediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        mMediaUrl = mediaUrl;
    }

    public String getMimeType() {
        return mMimeType;
    }

    public void setMimeType(String mimeType) {
        mMimeType = mimeType;
    }

    public String getPostedDate() {
        return mPostedDate;
    }

    public void setPostedDate(String postedDate) {
        mPostedDate = postedDate;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
