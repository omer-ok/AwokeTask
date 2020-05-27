package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class DayNutritionDB extends RealmObject {



   @SerializedName("title")
   @Expose
   public String title;

   @SerializedName("mealName")
   @Expose
   public String mealName;

   @SerializedName("id")
   @Expose
   public String id;

   @SerializedName("description")
   @Expose
   public String description;

   @SerializedName("mediaUrl")
   @Expose
   public String mediaUrl;

   @SerializedName("recipesMedia")
   @Expose
   public String recipesMedia;

   @SerializedName("ingredients")
   @Expose
   public RealmList<IngredientsDB> ingredientsArrayList;


   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getMealName() {
      return mealName;
   }

   public void setMealName(String mealName) {
      this.mealName = mealName;
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

   public String getRecipesMedia() {
      return recipesMedia;
   }

   public void setRecipesMedia(String recipesMedia) {
      this.recipesMedia = recipesMedia;
   }

   public RealmList<IngredientsDB> getIngredientsArrayList() {
      return ingredientsArrayList;
   }

   public void setIngredientsArrayList(RealmList<IngredientsDB> ingredientsArrayList) {
      this.ingredientsArrayList = ingredientsArrayList;
   }
}
