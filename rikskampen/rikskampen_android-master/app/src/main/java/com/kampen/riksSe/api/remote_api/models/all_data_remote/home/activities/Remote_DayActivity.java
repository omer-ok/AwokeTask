package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.activities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Remote_DayActivity {



   @SerializedName("id")
   @Expose
   private   String   id;

   @SerializedName("user_id")
   @Expose
   private   String   user_id;

   @SerializedName("dayName")
   @Expose
   private   String   dayName;

   @SerializedName("imagePath")
   @Expose
   private   String   imagePath;

   @SerializedName("dateTime")
   @Expose
   private   String   dateTime;

   @SerializedName("steps")
   @Expose
   private    int     steps;

   @SerializedName("stars")
   @Expose
   private ArrayList<Remote_Star>  stars;

   @SerializedName("locationName")
   @Expose
   private    String  locationName;

   @SerializedName("lat")
   @Expose
   private    String  lat;

   @SerializedName("lng")
   @Expose
   private    String  lng;

   @SerializedName("calories")
   @Expose
   private    String  calories;


   private   byte[] imageData;


   public String getDayName() {
      return dayName;
   }

   public void setDayName(String dayName) {
      this.dayName = dayName;
   }

   public String getImagePath() {
      return imagePath;
   }

   public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
   }

   public String getDateTime() {
      return dateTime;
   }

   public void setDateTime(String dateTime) {
      this.dateTime = dateTime;
   }

   public int getSteps() {
      return steps;
   }

   public void setSteps(int steps) {
      this.steps = steps;
   }

   public ArrayList<Remote_Star> getStars() {
      return stars;
   }

   public void setStars(ArrayList<Remote_Star> stars) {
      this.stars = stars;
   }

   public String getLocationName() {
      return locationName;
   }

   public void setLocationName(String locationName) {
      this.locationName = locationName;
   }

   public String getLat() {
      return lat;
   }

   public void setLat(String lat) {
      this.lat = lat;
   }


   public String getLng() {
      return lng;
   }

   public void setLng(String lng) {
      this.lng = lng;
   }

   public String getUser_id() {
      return user_id;
   }

   public void setUser_id(String user_id) {
      this.user_id = user_id;
   }

   public String getCalories() {
      return calories;
   }

   public void setCalories(String calories) {
      this.calories = calories;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public byte[] getImageData() {
      return imageData;
   }

   public void setImageData(byte[] imageData) {
      this.imageData = imageData;
   }
}
