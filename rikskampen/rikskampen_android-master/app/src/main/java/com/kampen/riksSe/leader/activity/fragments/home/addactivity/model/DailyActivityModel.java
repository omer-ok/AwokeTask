package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model;



import io.realm.RealmList;
import io.realm.RealmObject;

public class DailyActivityModel extends RealmObject {



    private  String  id;

    private   String  user_id;

    private   String  weekID;

    private   String   dayName;

    private   String  imagePath;

    private   String   dateTime;

    private    int     steps;

    private   byte    []  picData;

    private    String  takenTimeDate;

    private    String   lat;

    private    String lng;

    private    String    placeName;

    private     String    caloriesBurned;

    private RealmList<Stars_Model> stars;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

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

    public byte[] getPicData() {
        return picData;
    }

    public void setPicData(byte[] picData) {
        this.picData = picData;
    }

    public String getTakenTimeDate() {
        return takenTimeDate;
    }

    public void setTakenTimeDate(String takenTimeDate) {
        this.takenTimeDate = takenTimeDate;
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

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(String caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public RealmList<Stars_Model> getStars() {
        return stars;
    }

    public void setStars(RealmList<Stars_Model> stars) {
        this.stars = stars;
    }

    public String getWeekID() {
        return weekID;
    }

    public void setWeekID(String weekID) {
        this.weekID = weekID;
    }
}
