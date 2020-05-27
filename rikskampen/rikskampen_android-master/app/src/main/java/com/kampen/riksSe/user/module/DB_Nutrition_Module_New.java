package com.kampen.riksSe.user.module;



import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionMealOptionsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.IngredientsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.MealIngrediantsOptionsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_weeklyActivitiesDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.NutritiousDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.WeeklyIngredients;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {DayNutritionDB.class, IngredientsDB.class, N_DayDB.class, N_DaysDB.class, N_WeekDB.class, WeeklyIngredients.class, N_weeklyActivitiesDB.class, NutritiousDB.class, DayNutritionMealOptionsDB.class, MealIngrediantsOptionsDB.class})
public class DB_Nutrition_Module_New {

}
