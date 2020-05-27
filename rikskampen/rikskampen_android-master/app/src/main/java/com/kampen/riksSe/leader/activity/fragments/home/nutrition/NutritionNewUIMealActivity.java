package com.kampen.riksSe.leader.activity.fragments.home.nutrition;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.kampen.riksSe.R;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.adapter.NutritionNewUIDaysAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.adapter.NutritionNewUIWeeksAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.adapter.PageControlerAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MainIngredient;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Meal;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Days_V;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Recipe;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionMealOptionsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.MealIngrediantsOptionsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class NutritionNewUIMealActivity extends AppCompatActivity implements WeekNutritionContract.View {


    List<String> mealName;
    List<String> mealOptionType;
    NutritionNewUIWeeksAdapter nutritionNewUIWeeksAdapter;
    NutritionNewUIDaysAdapter nutritionNewUIDaysAdapter;
    private RecyclerView  mNutritionMealsRV;
    private RecyclerView  mNutritionMealsOptionRV;
    LinearLayoutManager mLayoutManager1,mLayoutManager2;
    SpringDotsIndicator springDotsIndicator;
    ViewPager2 viewPager2;
    PageControlerAdapter adapter;
    Button weeklyIngrediantsBtn;
    int CurrentWeek,CurrentDay;
    int MealTypeListner;
    int mealNamePosition;
    WeekNutrition_Frag_Presenter weekNutritionFragPresenter;
    View nutritionView,noDataview;
    Competition CompitionDate;

    /*List<MealIngrediantsOptionsDB> MealIngrediantsOptionsList;
    List<DayNutritionMealOptionsDB> dayNutritionMealOptionsList;*/
    List<MealType> nutritionDataMealType;
    Meal nutritionDataMealTypeDetails;
    N_Days_V nutritionCurrentDayIngrdiantData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_new_ui_meal);

        int MealType = getIntent().getIntExtra("MealTypeID",1);

        mealName =new ArrayList<>();
        mealOptionType =new ArrayList<>();
        mNutritionMealsRV = findViewById(R.id.mealRV);
        nutritionView = findViewById(R.id.nutrionDataView);
        noDataview = findViewById(R.id.NoDataView);
        weeklyIngrediantsBtn = findViewById(R.id.weeklySummary);
        mNutritionMealsOptionRV = findViewById(R.id.mealOptionRV);
        mLayoutManager1 = new LinearLayoutManager(this);
        mLayoutManager2 = new LinearLayoutManager(this);
        weekNutritionFragPresenter = new WeekNutrition_Frag_Presenter(NutritionNewUIMealActivity.this,getApplicationContext());

        nutritionDataMealType = weekNutritionFragPresenter.getNutritionMealTypeDataV3(NutritionNewUIMealActivity.this);
        nutritionCurrentDayIngrdiantData = weekNutritionFragPresenter.getNutritionIngrdiantDataV3(NutritionNewUIMealActivity.this);

        try{

        for(int i=0; i<nutritionDataMealType.size();i++){
            if(MealType== nutritionDataMealType.get(i).getId()){
                mealNamePosition = i;
            }
        }
            for(int i=0;i<nutritionCurrentDayIngrdiantData.getmMeals().size();i++){
                if(MealType==nutritionCurrentDayIngrdiantData.getmMeals().get(i).getMealId()){
                    nutritionDataMealTypeDetails=nutritionCurrentDayIngrdiantData.getmMeals().get(i);
                }
            }

        mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
       // MealTypeListner = nutritionDataMealType.get(0).getId();
        MealTypeListner = MealType;
        nutritionNewUIWeeksAdapter = new NutritionNewUIWeeksAdapter(NutritionNewUIMealActivity.this,mealNamePosition,nutritionDataMealType);
        nutritionNewUIDaysAdapter = new NutritionNewUIDaysAdapter(NutritionNewUIMealActivity.this,nutritionDataMealTypeDetails.getMainIngredient());

        mNutritionMealsRV.setLayoutManager(mLayoutManager1);
        mNutritionMealsOptionRV.setLayoutManager(mLayoutManager2);

        mNutritionMealsRV.setAdapter(nutritionNewUIWeeksAdapter);
        mNutritionMealsRV.smoothScrollToPosition(mealNamePosition);
        mNutritionMealsOptionRV.setAdapter(nutritionNewUIDaysAdapter);

        springDotsIndicator = (SpringDotsIndicator) findViewById(R.id.spring_dots_indicator);
        viewPager2 = (ViewPager2) findViewById(R.id.view_pager2);
        adapter = new PageControlerAdapter(NutritionNewUIMealActivity.this,nutritionDataMealTypeDetails.getMealId(),nutritionDataMealTypeDetails.getMainIngredient().get(0).getId(),nutritionDataMealTypeDetails.getMainIngredient().get(0).getRecipes());
        viewPager2.setAdapter(adapter);
        springDotsIndicator.setViewPager2(viewPager2);

        }catch (Exception e){
            nutritionView.setVisibility(View.GONE);
            noDataview.setVisibility(View.VISIBLE);
        }

        weeklyIngrediantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent intent = new Intent(NutritionNewUIMealActivity.this, IngredientsWeeklySummary.class);
                intent.putExtra("WeekID",CurrentWeek);
                startActivity(intent);
*/
            }
        });

    }

    public void onBackClick(View view) {

        finish();

    }

   private final BroadcastReceiver NutritionViewBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            try {
                MealTypeListner = intent.getIntExtra("MealTypeID",1);


                for(int i=0; i<nutritionDataMealType.size();i++){
                    if(MealTypeListner == nutritionDataMealType.get(i).getId()){
                        mealNamePosition = i;
                    }
                }
                for(int i=0;i<nutritionCurrentDayIngrdiantData.getmMeals().size();i++){
                    if(MealTypeListner==nutritionCurrentDayIngrdiantData.getmMeals().get(i).getMealId()){
                        nutritionDataMealTypeDetails=nutritionCurrentDayIngrdiantData.getmMeals().get(i);
                    }
                }



                mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

                mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

                nutritionNewUIDaysAdapter = new NutritionNewUIDaysAdapter(NutritionNewUIMealActivity.this,nutritionDataMealTypeDetails.getMainIngredient());


                mNutritionMealsOptionRV.setLayoutManager(mLayoutManager2);

                mNutritionMealsOptionRV.setAdapter(nutritionNewUIDaysAdapter);

                PageControlerAdapter adapter = new PageControlerAdapter(NutritionNewUIMealActivity.this,nutritionDataMealTypeDetails.getMealId(),nutritionDataMealTypeDetails.getMainIngredient().get(0).getId(),nutritionDataMealTypeDetails.getMainIngredient().get(0).getRecipes());

                viewPager2.setAdapter(adapter);

                springDotsIndicator.setViewPager2(viewPager2);

            }catch (Exception e){

                nutritionView.setVisibility(View.GONE);

                noDataview.setVisibility(View.VISIBLE);
            }
        }
    };


    private final BroadcastReceiver NutritionViewMealIngrediandsBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            try{
            int MealIngrediantID = intent.getIntExtra("MealIngrediantID",1);
            List<Recipe> mainIngredientList = new ArrayList<>();

                for(int i=0;i<nutritionCurrentDayIngrdiantData.getmMeals().size();i++){
                    if(MealTypeListner==nutritionCurrentDayIngrdiantData.getmMeals().get(i).getMealId()){
                        for(int j=0;j<nutritionCurrentDayIngrdiantData.getmMeals().get(i).getMainIngredient().size();j++){
                            if(MealIngrediantID==nutritionCurrentDayIngrdiantData.getmMeals().get(i).getMainIngredient().get(j).getId()){
                                mainIngredientList.addAll(nutritionCurrentDayIngrdiantData.getmMeals().get(i).getMainIngredient().get(j).getRecipes());
                                //nutritionDataMealTypeDetails=nutritionCurrentDayIngrdiantData.getmMeals().get(i);
                            }
                        }
                    }
                }

                //PageControlerAdapter adapter = new PageControlerAdapter(NutritionNewUIMealActivity.this,mealName.get(mealNamePosition),mealOptionType.get(0),CurrentWeek,CurrentDay,nutritionDataMealTypeDetails.getMainIngredient().get(0).getRecipes());
                PageControlerAdapter adapter = new PageControlerAdapter(NutritionNewUIMealActivity.this,nutritionDataMealTypeDetails.getMealId(),MealIngrediantID,mainIngredientList);
            viewPager2.setAdapter(adapter);
            springDotsIndicator.setViewPager2(viewPager2);
            }catch (Exception e){
                nutritionView.setVisibility(View.GONE);
                noDataview.setVisibility(View.VISIBLE);
            }

        }
    };


    @Override
    public void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(NutritionNewUIMealActivity.this).registerReceiver(NutritionViewBroadcastReceiver,
                new IntentFilter("com.tutorialspoint.CUSTOM_INTENT_MEAL_NAME"));

        LocalBroadcastManager.getInstance(NutritionNewUIMealActivity.this).registerReceiver(NutritionViewMealIngrediandsBroadcastReceiver,
                new IntentFilter("com.tutorialspoint.CUSTOM_INTENT_MEAL_TYPE"));

    }



    @Override
    public void onStop() {

        super.onStop();

        LocalBroadcastManager.getInstance(NutritionNewUIMealActivity.this).unregisterReceiver(NutritionViewBroadcastReceiver);
        LocalBroadcastManager.getInstance(NutritionNewUIMealActivity.this).unregisterReceiver(NutritionViewMealIngrediandsBroadcastReceiver);

    }

    @Override
    public void setNutritions(List<WeekTrainingModel> list) {

    }

    @Override
    public void setStartUpData(String message) {

    }

    @Override
    public void setStartUpDataFailed(String message) {

    }

    @Override
    public void setPresenter(WeekNutritionContract.Presenter mPresenter) {

    }
}
