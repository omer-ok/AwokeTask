package com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail;


import android.content.Context;

import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Meal;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeVideoPresenter implements RecipeVideoContract.Presenter {

    private RecipeVideoContract.View mView;

    private Context mContext;


    public RecipeVideoPresenter(RecipeVideoContract.View _view, Context context) {

        this.mView = _view;
        this.mContext = context;

    }

    public List<Recipe> getRecipeMealDB(int MealID,int MealIngrediantID,int RecipeID){
        List<Meal> mealDB = Repository.getNutritionMealTypeV3(MealID);

        List<Recipe> recipeList = new ArrayList();
        for(int j=0;j<mealDB.size();j++){
            for(int i=0;i<mealDB.get(j).getMainIngredient().size();i++){
                if(mealDB.get(j).getMainIngredient().get(i).getId()==MealIngrediantID){
                    recipeList.addAll(mealDB.get(j).getMainIngredient().get(i).getRecipes());
                }
            }
        }

        return recipeList;
    }


}
