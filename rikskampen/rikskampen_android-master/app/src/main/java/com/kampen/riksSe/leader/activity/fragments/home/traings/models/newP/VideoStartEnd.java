package com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class VideoStartEnd extends RealmObject {

    @SerializedName("id")
    @Expose
    private String  id;


    @SerializedName("mediaVideo")
    @Expose
    private String  mediaVideo;


    @SerializedName("title")
    @Expose
    private String  title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("imagePath")
    @Expose
    private String  imagePath;

    @SerializedName("tagline")
    @Expose
    private String  tagline;

    @SerializedName("videoType")
    @Expose
    private String  videoType;

    @SerializedName("reps")
    @Expose
    private String reps;

    @SerializedName("sets")
    @Expose
    private String sets;



    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediaVideo() {
        return mediaVideo;
    }

    public void setMediaVideo(String mediaVideo) {
        this.mediaVideo = mediaVideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
