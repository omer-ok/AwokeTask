package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.trainings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DayTaining {



   @SerializedName("title")
   @Expose
   private String title;


   @SerializedName("description")
   @Expose
   private String description;


   @SerializedName("mediaVideo")
   @Expose
   private String mediaVideo;

   @SerializedName("mediaImage")
   @Expose
   private String  mediaImage;



}
