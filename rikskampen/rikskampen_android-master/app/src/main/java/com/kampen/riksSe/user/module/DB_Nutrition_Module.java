package com.kampen.riksSe.user.module;


import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.DailyNutritionModel;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.Dish_Model;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.Meal_Model;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.WeekNutritionModel;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {WeekNutritionModel.class,DailyNutritionModel.class,Meal_Model.class,Dish_Model.class})
public class DB_Nutrition_Module {

}
