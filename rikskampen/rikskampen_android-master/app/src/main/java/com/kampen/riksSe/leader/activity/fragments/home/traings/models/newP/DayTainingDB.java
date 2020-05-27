package com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class DayTainingDB extends RealmObject {



   @SerializedName("title")
   @Expose
   private String title;


   @SerializedName("description")
   @Expose
   private String description;


   @SerializedName("reps")
   @Expose
   private String reps;


   @SerializedName("sets")
   @Expose
   private String sets;

   @SerializedName("mediaVideo")
   @Expose
   private String mediaVideo;

   @SerializedName("mediaImage")
   @Expose
   private String  mediaImage;


   @SerializedName("tagline")
   @Expose
   private String  tagline;

   @SerializedName("videoType")
   @Expose
   private String  videoType;

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




   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
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

   public String getMediaVideo() {
      return mediaVideo;
   }

   public void setMediaVideo(String mediaVideo) {
      this.mediaVideo = mediaVideo;
   }

   public String getMediaImage() {
      return mediaImage;
   }

   public void setMediaImage(String mediaImage) {
      this.mediaImage = mediaImage;
   }
}
