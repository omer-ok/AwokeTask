package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models;

import android.content.Intent;

import io.realm.RealmObject;

public class TopContestant extends RealmObject {


    private  String  id;
    private  String  steps;
    private  String  calories;
    private  String  stars;
    private  String  name;
    private  String  profileImage;
    private  int  position;
    public   int tempResId;
    private String stepsRank;
    private String starRank;
    private String caloriesRank;



    public String getStepsRank() {
        return stepsRank;
    }

    public void setStepsRank(String stepsRank) {
        this.stepsRank = stepsRank;
    }

    public String getStarRank() {
        return starRank;
    }

    public void setStarRank(String starRank) {
        this.starRank = starRank;
    }




    public TopContestant() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public void setCaloriesRank(String caloriesRank) {
        this.caloriesRank = caloriesRank;
    }

    public String getCaloriesRank() {
        return caloriesRank;
    }

}
