package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Add_Activity {


    @SerializedName("activity_title")
    @Expose
    String activity_title;
    @SerializedName("activity_type_id")
    @Expose
    String activity_type_id;
    @SerializedName("steps_count")
    @Expose
    String steps_count;
    @SerializedName("day_name")
    @Expose
    String day_name;
    @SerializedName("stars_count")
    @Expose
    String stars_count;
    @SerializedName("calories")
    @Expose
    String calories;
    @SerializedName("user_activity_time")
    @Expose
    String user_activity_time;
    @SerializedName("activity_image")
    @Expose
    String activity_image;
    @SerializedName("activity_lat")
    @Expose
    String activity_lat;
    @SerializedName("activity_long")
    @Expose
    String activity_long;
    @SerializedName("activity_location")
    @Expose
    String activity_location;

    @SerializedName("weight_loss")
    @Expose
    String weight_loss;
    @SerializedName("stepCount")
    @Expose
    String stepCount;
    @SerializedName("starCount")
    @Expose
    String starCount;
    @SerializedName("caloriesCount")
    @Expose
    String caloriesCount;



    public String getActivity_title() {
        return activity_title;
    }

    public void setActivity_title(String activity_title) {
        this.activity_title = activity_title;
    }

    public String getActivity_type_id() {
        return activity_type_id;
    }

    public void setActivity_type_id(String activity_type_id) {
        this.activity_type_id = activity_type_id;
    }

    public String getSteps_count() {
        return steps_count;
    }

    public void setSteps_count(String steps_count) {
        this.steps_count = steps_count;
    }

    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public String getStars_count() {
        return stars_count;
    }

    public void setStars_count(String stars_count) {
        this.stars_count = stars_count;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getUser_activity_time() {
        return user_activity_time;
    }

    public void setUser_activity_time(String user_activity_time) {
        this.user_activity_time = user_activity_time;
    }

    public String getActivity_image() {
        return activity_image;
    }

    public void setActivity_image(String activity_image) {
        this.activity_image = activity_image;
    }

    public String getActivity_lat() {
        return activity_lat;
    }

    public void setActivity_lat(String activity_lat) {
        this.activity_lat = activity_lat;
    }

    public String getActivity_long() {
        return activity_long;
    }

    public void setActivity_long(String activity_long) {
        this.activity_long = activity_long;
    }

    public String getActivity_location() {
        return activity_location;
    }

    public void setActivity_location(String activity_location) {
        this.activity_location = activity_location;
    }
    public String getWeight_loss() {
        return weight_loss;
    }

    public void setWeight_loss(String weight_loss) {
        this.weight_loss = weight_loss;
    }


    public String getStepCount() {
        return stepCount;
    }

    public void setStepCount(String stepCount) {
        this.stepCount = stepCount;
    }

    public String getStarCount() {
        return starCount;
    }

    public void setStarCount(String starCount) {
        this.starCount = starCount;
    }

    public String getCaloriesCount() {
        return caloriesCount;
    }

    public void setCaloriesCount(String caloriesCount) {
        this.caloriesCount = caloriesCount;
    }

}
