package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model;

import io.realm.RealmObject;

public class Dish_Model extends RealmObject {



    private String    id;

    private String    nutrition_id;

    private String    meal_id;

    private String    title;

    private String    description;

    private int    qty;

    private String    takenTime;

    private String    calories;

    private String    fats;

    private String    protein;


    private String imagePath;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNutrition_id() {
        return nutrition_id;
    }

    public void setNutrition_id(String nutrition_id) {
        this.nutrition_id = nutrition_id;
    }

    public String getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(String meal_id) {
        this.meal_id = meal_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(String takenTime) {
        this.takenTime = takenTime;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
