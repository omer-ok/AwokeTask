/*
package com.kampen.riksSe.leader.activity.fragments.home.nutrition;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.adapter.DailyNutritionAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;

import java.util.List;


public class WeekNutritionActivity extends AppCompatActivity implements WeekNutritionContract.View {


    TextView NutritionMsg1,NutritionMsg2,NutritionMsg3,NutritionMsg4,NutritionMsg5,NutritionMsg6,NutritionMsg7;


    RecyclerView nutritionWeekRVDayOne;
    DailyNutritionAdapter nutritionWeekDayOneAdapter;

    RecyclerView nutritionWeekRVDayTwo;
    DailyNutritionAdapter nutritionWeekDayTwoAdapter;

    RecyclerView nutritionWeekRVDayThree;
    DailyNutritionAdapter nutritionWeekDayThreeAdapter;

    RecyclerView nutritionWeekRVDayFour;
    DailyNutritionAdapter nutritionWeekDayFourAdapter;

    RecyclerView nutritionWeekRVDayFive;
    DailyNutritionAdapter nutritionWeekDayFiveAdapter;

    RecyclerView nutritionWeekRVDaySix;
    DailyNutritionAdapter nutritionWeekDaySixAdapter;

    RecyclerView nutritionWeekRVDaySeven;
    DailyNutritionAdapter nutritionWeekDaySevenAdapter;

    Context    mContext;


    N_WeekDB  mNweek;

    WeekNutrition_Frag_Presenter weekNutritionFragPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_week_nutrition);

        mContext=WeekNutritionActivity.this;


        NutritionMsg1 = findViewById(R.id.nutritionMsg1);
        NutritionMsg2 = findViewById(R.id.nutritionMsg2);
        NutritionMsg3 = findViewById(R.id.nutritionMsg3);
        NutritionMsg4 = findViewById(R.id.nutritionMsg4);
        NutritionMsg5 = findViewById(R.id.nutritionMsg5);
        NutritionMsg6 = findViewById(R.id.nutritionMsg6);
        NutritionMsg7 = findViewById(R.id.nutritionMsg7);

        weekNutritionFragPresenter = new WeekNutrition_Frag_Presenter(WeekNutritionActivity.this,getApplicationContext());

         int weekId=(Integer)getIntent().getIntExtra("selected_week",1);

        mNweek= weekNutritionFragPresenter.getNutritionData(mContext,weekId);




        nutritionWeekRVDayOne=findViewById(R.id.nutritionWeekRVDayOne);
        nutritionWeekDayOneAdapter=new DailyNutritionAdapter(mContext,mNweek.getDays().getDay1(),weekId);

        nutritionWeekRVDayTwo=findViewById(R.id.nutritionWeekRVDayTwo);
        nutritionWeekDayTwoAdapter=new DailyNutritionAdapter(mContext,mNweek.getDays().getDay2(),weekId);

        nutritionWeekRVDayThree=findViewById(R.id.nutritionWeekRVDayThre);
        nutritionWeekDayThreeAdapter=new DailyNutritionAdapter(mContext,mNweek.getDays().getDay3(),weekId);

        nutritionWeekRVDayFour=findViewById(R.id.nutritionWeekRVDayFour);
        nutritionWeekDayFourAdapter=new DailyNutritionAdapter(mContext,mNweek.getDays().getDay4(),weekId);

        nutritionWeekRVDayFive=findViewById(R.id.nutritionWeekRVDayFive);
        nutritionWeekDayFiveAdapter=new DailyNutritionAdapter(mContext,mNweek.getDays().getDay5(),weekId);

        nutritionWeekRVDaySix=findViewById(R.id.nutritionWeekRVDaySix);
        nutritionWeekDaySixAdapter=new DailyNutritionAdapter(mContext,mNweek.getDays().getDay6(),weekId);


        nutritionWeekRVDaySeven=findViewById(R.id.nutritionWeekRVDaySeven);
        nutritionWeekDaySevenAdapter=new DailyNutritionAdapter(mContext,mNweek.getDays().getDay7(),weekId);

        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this);
        mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);


        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager mLayoutManager3 = new LinearLayoutManager(this);
        mLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager mLayoutManager4 = new LinearLayoutManager(this);
        mLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager mLayoutManager5 = new LinearLayoutManager(this);
        mLayoutManager5.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager mLayoutManager6 = new LinearLayoutManager(this);
        mLayoutManager6.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager mLayoutManager7 = new LinearLayoutManager(this);
        mLayoutManager7.setOrientation(LinearLayoutManager.HORIZONTAL);


        nutritionWeekRVDayOne.setLayoutManager(mLayoutManager1);
        nutritionWeekRVDayOne.setAdapter(nutritionWeekDayOneAdapter);

        nutritionWeekRVDayTwo.setLayoutManager(mLayoutManager2);
        nutritionWeekRVDayTwo.setAdapter(nutritionWeekDayTwoAdapter);

        nutritionWeekRVDayThree.setLayoutManager(mLayoutManager3);
        nutritionWeekRVDayThree.setAdapter(nutritionWeekDayThreeAdapter);


        nutritionWeekRVDayFour.setLayoutManager(mLayoutManager4);
        nutritionWeekRVDayFour.setAdapter(nutritionWeekDayFourAdapter);


        nutritionWeekRVDayFive.setLayoutManager(mLayoutManager5);
        nutritionWeekRVDayFive.setAdapter(nutritionWeekDayFiveAdapter);

        nutritionWeekRVDaySix.setLayoutManager(mLayoutManager6);
        nutritionWeekRVDaySix.setAdapter(nutritionWeekDaySixAdapter);

        nutritionWeekRVDaySeven.setLayoutManager(mLayoutManager7);
        nutritionWeekRVDaySeven.setAdapter(nutritionWeekDaySevenAdapter);


        if(mNweek.getDays().getDay1().dayactivitesList.size() == 0){


            nutritionWeekRVDayOne.setVisibility(View.GONE);
            NutritionMsg1.setVisibility(View.VISIBLE);

        }
        if(mNweek.getDays().getDay2().dayactivitesList.size() == 0){


            nutritionWeekRVDayTwo.setVisibility(View.GONE);
            NutritionMsg2.setVisibility(View.VISIBLE);

        }
        if(mNweek.getDays().getDay3().dayactivitesList.size() == 0){


            nutritionWeekRVDayThree.setVisibility(View.GONE);
            NutritionMsg3.setVisibility(View.VISIBLE);

        }
        if(mNweek.getDays().getDay4().dayactivitesList.size() == 0){


            nutritionWeekRVDayFour.setVisibility(View.GONE);
            NutritionMsg4.setVisibility(View.VISIBLE);

        }
        if(mNweek.getDays().getDay5().dayactivitesList.size() == 0){


            nutritionWeekRVDayFive.setVisibility(View.GONE);
            NutritionMsg5.setVisibility(View.VISIBLE);

        }
        if(mNweek.getDays().getDay6().dayactivitesList.size() == 0){


            nutritionWeekRVDaySix.setVisibility(View.GONE);
            NutritionMsg6.setVisibility(View.VISIBLE);

        }
        if(mNweek.getDays().getDay7().dayactivitesList.size() == 0){


            nutritionWeekRVDaySeven.setVisibility(View.GONE);
            NutritionMsg7.setVisibility(View.VISIBLE);

        }




    }


    public void onBackClick(View view) {

        finish();

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
*/
