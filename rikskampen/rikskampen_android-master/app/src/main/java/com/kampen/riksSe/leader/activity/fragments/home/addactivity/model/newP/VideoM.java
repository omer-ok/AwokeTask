package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class VideoM extends RealmObject {

    @SerializedName("mediaUrl")
    @Expose
    private String mediaUrl;
    @SerializedName("mediaThumb")
    @Expose
    private String mediaThumb;
    @SerializedName("mimeType")
    @Expose
    private String mimeType;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("postedDate")
    @Expose
    private String postedDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("size")
    @Expose
    private String size;

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaThumb() {
        return mediaThumb;
    }

    public void setMediaThumb(String mediaThumb) {
        this.mediaThumb = mediaThumb;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


}
