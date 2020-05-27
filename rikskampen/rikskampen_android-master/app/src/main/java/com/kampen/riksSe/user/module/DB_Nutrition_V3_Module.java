package com.kampen.riksSe.user.module;



import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Ingredient;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MainIngredient;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Meal;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Days_V;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.NutritionPlans;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Recipe;
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

@RealmModule(classes = {NutritionPlans.class, N_Plans.class, N_Days_V.class, MealType.class, Recipe.class, Meal.class, MainIngredient.class, Ingredient.class})
public class DB_Nutrition_V3_Module {

}
