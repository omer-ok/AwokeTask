package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.nutritions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DayNutrition {



   @SerializedName("title")
   @Expose
   private String title;

   @SerializedName("id")
   @Expose
   private String id;


   @SerializedName("description")
   @Expose
   private String description;


   @SerializedName("mediaUrl")
   @Expose
   private String mediaUrl;

   @SerializedName("ingredients")
   @Expose
   private ArrayList<Ingredients>  ingredientsArrayList;


   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getMediaUrl() {
      return mediaUrl;
   }

   public void setMediaUrl(String mediaUrl) {
      this.mediaUrl = mediaUrl;
   }

   public ArrayList<Ingredients> getIngredientsArrayList() {
      return ingredientsArrayList;
   }

   public void setIngredientsArrayList(ArrayList<Ingredients> ingredientsArrayList) {
      this.ingredientsArrayList = ingredientsArrayList;
   }
}
