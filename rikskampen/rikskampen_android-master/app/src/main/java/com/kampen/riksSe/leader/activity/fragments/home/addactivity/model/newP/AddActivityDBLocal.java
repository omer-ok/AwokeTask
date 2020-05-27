package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP;

import io.realm.RealmObject;

public class AddActivityDBLocal extends RealmObject {

    private String activity_title;
    private String activity_type_id;
    private String steps_count;
    private String day_name;
    private String stars_count;
    private String calories;
    private String user_activity_time;
    private String activity_image;
    private String activity_lat;
    private String activity_long;
    private String activity_location;
    private String activity_weight;

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

    public String getActivity_weight() {
        return activity_weight;
    }

    public void setActivity_weight(String activity_weight) {
        this.activity_weight = activity_weight;
    }



}
