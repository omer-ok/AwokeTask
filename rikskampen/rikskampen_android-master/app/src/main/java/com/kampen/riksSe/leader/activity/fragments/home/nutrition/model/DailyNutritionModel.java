package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model;



import io.realm.RealmObject;

public class DailyNutritionModel extends RealmObject {




    private String id;

    private String week_id;

    private String user_id;

    private String dayName;

    private String dateTime;

    private Meal_Model breakfast;

    private Meal_Model lunch;

    private Meal_Model dinner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeek_id() {
        return week_id;
    }

    public void setWeek_id(String week_id) {
        this.week_id = week_id;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Meal_Model getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Meal_Model breakfast) {
        this.breakfast = breakfast;
    }

    public Meal_Model getLunch() {
        return lunch;
    }

    public void setLunch(Meal_Model lunch) {
        this.lunch = lunch;
    }

    public Meal_Model getDinner() {
        return dinner;
    }

    public void setDinner(Meal_Model dinner) {
        this.dinner = dinner;
    }
}
