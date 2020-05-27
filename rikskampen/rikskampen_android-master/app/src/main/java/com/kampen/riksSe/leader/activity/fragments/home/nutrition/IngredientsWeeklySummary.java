package com.kampen.riksSe.leader.activity.fragments.home.nutrition;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail.adapter.WeeklyIngrediantsAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;

import java.util.List;

public class IngredientsWeeklySummary extends AppCompatActivity implements WeekNutritionContract.View{

    TextView WeekTab1,WeekTab2,WeekTab3,WeekTab4,WeekTab5,WeekTab6,WeekTab7,WeekTab8,WeekTab9,WeekTab10;
    WeekNutrition_Frag_Presenter weekNutritionFragPresenter;
    N_WeekDB n_weekDB;
    public N_DayDB n_daysDB;
    int weekId;
    View noDataAssiened;
    HorizontalScrollView weekScroll;
    LinearLayoutManager mLayoutManager1;
    private WeeklyIngrediantsAdapter weeklyIngrediantsAdapter;
    private RecyclerView mNutritionDetailRV;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


        switch (weekId) {
            case 1: {
                int x = WeekTab1.getLeft();
                int y = WeekTab1.getTop();
                onFocusScroll(x, y);
                noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                break;
            }
            case 2: {
                int x = WeekTab2.getLeft();
                int y = WeekTab2.getTop();
                onFocusScroll(x, y);
                noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab2.setText("VECKA 2");
                WeekTab2.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);



                break;
            }

            case 3: {
                int x = WeekTab3.getLeft();
                int y = WeekTab3.getTop();
                onFocusScroll(x, y);
                noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab3.setText("VECKA 3");
                WeekTab3.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                break;
            }

            case 4: {
                int x = WeekTab4.getLeft();
                int y = WeekTab4.getTop();
                onFocusScroll(x, y);
                noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab4.setText("VECKA 4");
                WeekTab4.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                break;
            }

            case 5: {

                int x = WeekTab5.getLeft();
                int y = WeekTab5.getTop();
                onFocusScroll(x, y);
                noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab5.setText("VECKA 5");
                WeekTab5.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                break;
            }

            case 6: {
                int x = WeekTab6.getLeft();
                int y = WeekTab6.getTop();
                onFocusScroll(x, y);
                noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab6.setText("VECKA 6");
                WeekTab6.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                break;
            }

            case 7: {
                int x = WeekTab7.getLeft();
                int y = WeekTab7.getTop();
                onFocusScroll(x, y);
                noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab7.setText("VECKA 7");
                WeekTab7.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                break;
            }

            case 8: {
                int x = WeekTab8.getLeft();
                int y = WeekTab8.getTop();
                onFocusScroll(x, y);
                noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab8.setText("VECKA 8");
                WeekTab8.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                break;
            }

            case 9: {
                int x = WeekTab9.getLeft();
                int y = WeekTab9.getTop();
                onFocusScroll(x, y);
                noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab9.setText("VECKA 9");
                WeekTab9.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                break;
            }

            case 10: {
                //int nY_Pos = WeekTab9.getTop();
                int x = WeekTab10.getLeft();
                int y = WeekTab10.getTop();
                onFocusScroll(x, y);
                noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab10.setText("VECKA 10");
                WeekTab10.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                break;
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_weekly_summary);



        WeekTab1 =findViewById(R.id.tab_week1);
        WeekTab2 =findViewById(R.id.tab_week2);
        WeekTab3 =findViewById(R.id.tab_week3);
        WeekTab4 =findViewById(R.id.tab_week4);
        WeekTab5 =findViewById(R.id.tab_week5);
        WeekTab6 =findViewById(R.id.tab_week6);
        WeekTab7 =findViewById(R.id.tab_week7);
        WeekTab8 =findViewById(R.id.tab_week8);
        WeekTab9 =findViewById(R.id.tab_week9);
        WeekTab10 =findViewById(R.id.tab_week10);
        noDataAssiened = findViewById(R.id.NoDataView);
        weekScroll = findViewById(R.id.tabsContainerWeeks);
        mLayoutManager1 = new LinearLayoutManager(this);
        mNutritionDetailRV=findViewById(R.id.IngrediantsWeekRV);
        weekId=(Integer)getIntent().getIntExtra("WeekID",1);

        try{
            weekNutritionFragPresenter = new WeekNutrition_Frag_Presenter(IngredientsWeeklySummary.this,getApplicationContext());
            N_WeekDB n_weekDB = weekNutritionFragPresenter.getNutritionData(IngredientsWeeklySummary.this,weekId);

            noDataAssiened.setVisibility(View.VISIBLE);

            mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

            weeklyIngrediantsAdapter = new WeeklyIngrediantsAdapter(IngredientsWeeklySummary.this,n_weekDB.getWeeklyIngredientsList());

            mNutritionDetailRV.setLayoutManager(mLayoutManager1);

            mNutritionDetailRV.setAdapter(weeklyIngrediantsAdapter);
        }catch (Exception e){

        }
        WeekTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N_WeekDB n_weekDB = weekNutritionFragPresenter.getNutritionData(IngredientsWeeklySummary.this,1);
                if(n_weekDB==null ||  n_weekDB.getWeeklyIngredientsList().size() <=0){
                    noDataAssiened.setVisibility(View.VISIBLE);
                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);
                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    weeklyIngrediantsAdapter = new WeeklyIngrediantsAdapter(IngredientsWeeklySummary.this,n_weekDB.getWeeklyIngredientsList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(weeklyIngrediantsAdapter);

                }


            }
        });

        WeekTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N_WeekDB n_weekDB = weekNutritionFragPresenter.getNutritionData(IngredientsWeeklySummary.this,2);

                if(n_weekDB==null || n_weekDB.getWeeklyIngredientsList().size()<=0){

                    noDataAssiened.setVisibility(View.VISIBLE);
                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);
                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    weeklyIngrediantsAdapter = new WeeklyIngrediantsAdapter(IngredientsWeeklySummary.this,n_weekDB.getWeeklyIngredientsList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(weeklyIngrediantsAdapter);
                }



            }
        });


        WeekTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N_WeekDB n_weekDB = weekNutritionFragPresenter.getNutritionData(IngredientsWeeklySummary.this,3);
                if(n_weekDB==null || n_weekDB.getWeeklyIngredientsList().size() <=0){

                    noDataAssiened.setVisibility(View.VISIBLE);
                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);
                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);


                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    weeklyIngrediantsAdapter = new WeeklyIngrediantsAdapter(IngredientsWeeklySummary.this,n_weekDB.getWeeklyIngredientsList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(weeklyIngrediantsAdapter);
                }




            }
        });

        WeekTab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N_WeekDB n_weekDB = weekNutritionFragPresenter.getNutritionData(IngredientsWeeklySummary.this,4);
                if(n_weekDB==null || n_weekDB.getWeeklyIngredientsList().size()<=0){

                    noDataAssiened.setVisibility(View.VISIBLE);
                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                }else{

                    noDataAssiened.setVisibility(View.INVISIBLE);
                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);


                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    weeklyIngrediantsAdapter = new WeeklyIngrediantsAdapter(IngredientsWeeklySummary.this,n_weekDB.getWeeklyIngredientsList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(weeklyIngrediantsAdapter);
                }




            }
        });

        WeekTab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N_WeekDB n_weekDB = weekNutritionFragPresenter.getNutritionData(IngredientsWeeklySummary.this,5);

                if(n_weekDB==null || n_weekDB.getWeeklyIngredientsList().size()<=0){
                    noDataAssiened.setVisibility(View.VISIBLE);
                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);
                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    weeklyIngrediantsAdapter = new WeeklyIngrediantsAdapter(IngredientsWeeklySummary.this,n_weekDB.getWeeklyIngredientsList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(weeklyIngrediantsAdapter);
                }



            }
        });

        WeekTab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N_WeekDB n_weekDB = weekNutritionFragPresenter.getNutritionData(IngredientsWeeklySummary.this,6);

                if(n_weekDB==null || n_weekDB.getWeeklyIngredientsList().size()<=0){
                    noDataAssiened.setVisibility(View.VISIBLE);
                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);
                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);
                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    weeklyIngrediantsAdapter = new WeeklyIngrediantsAdapter(IngredientsWeeklySummary.this,n_weekDB.getWeeklyIngredientsList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(weeklyIngrediantsAdapter);
                }




            }
        });

        WeekTab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N_WeekDB n_weekDB = weekNutritionFragPresenter.getNutritionData(IngredientsWeeklySummary.this,7);

                if(n_weekDB==null || n_weekDB.getWeeklyIngredientsList().size()<=0){

                    noDataAssiened.setVisibility(View.VISIBLE);
                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);
                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    weeklyIngrediantsAdapter = new WeeklyIngrediantsAdapter(IngredientsWeeklySummary.this,n_weekDB.getWeeklyIngredientsList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(weeklyIngrediantsAdapter);
                }





            }
        });

        WeekTab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N_WeekDB n_weekDB = weekNutritionFragPresenter.getNutritionData(IngredientsWeeklySummary.this,8);

                if(n_weekDB==null || n_weekDB.getWeeklyIngredientsList().size()<=0){
                    noDataAssiened.setVisibility(View.VISIBLE);
                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                }else{

                    noDataAssiened.setVisibility(View.INVISIBLE);
                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    weeklyIngrediantsAdapter = new WeeklyIngrediantsAdapter(IngredientsWeeklySummary.this,n_weekDB.getWeeklyIngredientsList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(weeklyIngrediantsAdapter);

                }




            }
        });

        WeekTab9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N_WeekDB n_weekDB = weekNutritionFragPresenter.getNutritionData(IngredientsWeeklySummary.this,9);
                if(n_weekDB==null || n_weekDB.getWeeklyIngredientsList().size()<=0){

                    noDataAssiened.setVisibility(View.VISIBLE);
                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);
                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    weeklyIngrediantsAdapter = new WeeklyIngrediantsAdapter(IngredientsWeeklySummary.this,n_weekDB.getWeeklyIngredientsList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(weeklyIngrediantsAdapter);
                }




            }
        });

        WeekTab10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                N_WeekDB n_weekDB = weekNutritionFragPresenter.getNutritionData(IngredientsWeeklySummary.this,10);

                if(n_weekDB==null || n_weekDB.getWeeklyIngredientsList().size()<=0){
                    noDataAssiened.setVisibility(View.VISIBLE);
                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);
                    WeekTab10.setText("VECKA 10");
                    WeekTab10.setTextColor(Color.parseColor("#FFFFFF"));
                    WeekTab10.setBackgroundResource(R.drawable.tab_middle_selected);

                    WeekTab1.setText("VECKA 1");
                    WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab2.setText("VECKA 2");
                    WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab3.setText("VECKA 3");
                    WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab4.setText("VECKA 4");
                    WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab5.setText("VECKA 5");
                    WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab6.setText("VECKA 6");
                    WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab7.setText("VECKA 7");
                    WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab8.setText("VECKA 8");
                    WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);

                    WeekTab9.setText("VECKA 9");
                    WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    weeklyIngrediantsAdapter = new WeeklyIngrediantsAdapter(IngredientsWeeklySummary.this,n_weekDB.getWeeklyIngredientsList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(weeklyIngrediantsAdapter);
                }





            }
        });


    }

    public void onBackClick(View view) {

/*        Intent intent =new Intent(IngredientsWeeklySummary.this,NutritionNewUIMealActivity.class);
        intent.putExtra("selected_week",weekId);
        startActivity(intent);*/
        finish();

    }

    public void onFocusScroll(int x,int y){
        weekScroll.post(new Runnable() {
            @Override
            public void run() {
                weekScroll.scrollTo(x,y);

            }
        });
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
