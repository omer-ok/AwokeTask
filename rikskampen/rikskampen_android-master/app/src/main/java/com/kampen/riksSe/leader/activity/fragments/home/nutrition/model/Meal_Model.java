package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Meal_Model extends RealmObject {

    private String  id;

    private String  nutrition_id;

    private String   Title;

    private String imagePath;

    private RealmList<Dish_Model> foodList;

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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public RealmList<Dish_Model> getFoodList() {
        return foodList;
    }

    public void setFoodList(RealmList<Dish_Model> foodList) {
        this.foodList = foodList;
    }
}
