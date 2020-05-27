package com.kampen.riksSe.leader.activity.fragments.home.traings.models;


import io.realm.RealmList;
import io.realm.RealmObject;

public class TrainingModel extends RealmObject {



    private String id;

    private String weekId;

    private String userId;

    private String dayName;

    private String description;

    private String dateTime;

    private String title;

    private String  imagePath;

    private RealmList<Workout_Model> workoutList;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeekId() {
        return weekId;
    }

    public void setWeekId(String weekId) {
        this.weekId = weekId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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


    public RealmList<Workout_Model> getWorkoutList() {
        return workoutList;
    }

    public void setWorkoutList(RealmList<Workout_Model> workoutList) {
        this.workoutList = workoutList;
    }
}
