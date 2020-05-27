package com.kampen.riksSe.leader.activity.fragments.home.traings.workout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.adapter.DayOneWorkoutAdapter;


public class WeekWorkOutActivity extends AppCompatActivity implements WeekWorkOutContract.View {



    RecyclerView workoutWeekRV,workoutWeekRV1,workoutWeekRV2,workoutWeekRV3,workoutWeekRV4,workoutWeekRV5,workoutWeekRV6;

    DayOneWorkoutAdapter mDayOneWorkoutAdapter,mDayOneWorkoutAdapter1,mDayOneWorkoutAdapter2,mDayOneWorkoutAdapter3,mDayOneWorkoutAdapter4,mDayOneWorkoutAdapter5,mDayOneWorkoutAdapter6;

    WeekWorkOutPresenter weekWorkOutPresenter;

    TextView TrainingMsg1,TrainingMsg2,TrainingMsg3,TrainingMsg4,TrainingMsg5,TrainingMsg6,TrainingMsg7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_week_work_out);

        int weekId=getIntent().getIntExtra("selected_week",1);

        weekWorkOutPresenter = new WeekWorkOutPresenter(WeekWorkOutActivity.this,getApplicationContext());



        workoutWeekRV=findViewById(R.id.workoutWeekRVDayOne);
        workoutWeekRV1=findViewById(R.id.workoutWeekRVDayTwo);
        workoutWeekRV2=findViewById(R.id.workoutWeekRVDayThre);
        workoutWeekRV3=findViewById(R.id.workoutWeekRVDayFour);
        workoutWeekRV4=findViewById(R.id.workoutWeekRVDayFive);
        workoutWeekRV5=findViewById(R.id.workoutWeekRVDaySix);
        workoutWeekRV6=findViewById(R.id.workoutWeekRVDaySeven);


        TrainingMsg1 = findViewById(R.id.trainingMsg1);
        TrainingMsg2 = findViewById(R.id.trainingMsg2);
        TrainingMsg3 = findViewById(R.id.trainingMsg3);
        TrainingMsg4 = findViewById(R.id.trainingMsg4);
        TrainingMsg5 = findViewById(R.id.trainingMsg5);
        TrainingMsg6 = findViewById(R.id.trainingMsg6);
        TrainingMsg7 = findViewById(R.id.trainingMsg7);


        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this);
        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        LinearLayoutManager mLayoutManager3 = new LinearLayoutManager(this);
        LinearLayoutManager mLayoutManager4 = new LinearLayoutManager(this);
        LinearLayoutManager mLayoutManager5 = new LinearLayoutManager(this);
        LinearLayoutManager mLayoutManager6 = new LinearLayoutManager(this);
        LinearLayoutManager mLayoutManager7 = new LinearLayoutManager(this);




        try {

            T_WeekDB t_weekDB = weekWorkOutPresenter.getTraingData(getApplicationContext(), weekId);

            mDayOneWorkoutAdapter = new DayOneWorkoutAdapter(WeekWorkOutActivity.this, t_weekDB.getDays().getDay1(), weekId);
            mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
            workoutWeekRV.setLayoutManager(mLayoutManager1);
            workoutWeekRV.setAdapter(mDayOneWorkoutAdapter);


            mDayOneWorkoutAdapter1 = new DayOneWorkoutAdapter(WeekWorkOutActivity.this, t_weekDB.getDays().getDay2(), weekId);
            mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            workoutWeekRV1.setLayoutManager(mLayoutManager2);
            workoutWeekRV1.setAdapter(mDayOneWorkoutAdapter1);


            mDayOneWorkoutAdapter2 = new DayOneWorkoutAdapter(WeekWorkOutActivity.this, t_weekDB.getDays().getDay3(), weekId);
            mLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
            workoutWeekRV2.setLayoutManager(mLayoutManager3);
            workoutWeekRV2.setAdapter(mDayOneWorkoutAdapter2);


            mDayOneWorkoutAdapter3 = new DayOneWorkoutAdapter(WeekWorkOutActivity.this, t_weekDB.getDays().getDay4(), weekId);
            mLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
            workoutWeekRV3.setLayoutManager(mLayoutManager4);
            workoutWeekRV3.setAdapter(mDayOneWorkoutAdapter3);

            mDayOneWorkoutAdapter4 = new DayOneWorkoutAdapter(WeekWorkOutActivity.this, t_weekDB.getDays().getDay5(), weekId);
            mLayoutManager5.setOrientation(LinearLayoutManager.HORIZONTAL);
            workoutWeekRV4.setLayoutManager(mLayoutManager5);
            workoutWeekRV4.setAdapter(mDayOneWorkoutAdapter4);


            mDayOneWorkoutAdapter5 = new DayOneWorkoutAdapter(WeekWorkOutActivity.this, t_weekDB.getDays().getDay6(), weekId);
            mLayoutManager6.setOrientation(LinearLayoutManager.HORIZONTAL);
            workoutWeekRV5.setLayoutManager(mLayoutManager6);
            workoutWeekRV5.setAdapter(mDayOneWorkoutAdapter5);


            mDayOneWorkoutAdapter6 = new DayOneWorkoutAdapter(WeekWorkOutActivity.this, t_weekDB.getDays().getDay7(), weekId);
            mLayoutManager7.setOrientation(LinearLayoutManager.HORIZONTAL);
            workoutWeekRV6.setLayoutManager(mLayoutManager7);
            workoutWeekRV6.setAdapter(mDayOneWorkoutAdapter6);

            if(t_weekDB.getDays().getDay1().getDayactivitesList().size() == 0){

                workoutWeekRV.setVisibility(View.GONE);
                TrainingMsg1.setVisibility(View.VISIBLE);

            }
            if(t_weekDB.getDays().getDay2().getDayactivitesList().size() == 0){

                workoutWeekRV1.setVisibility(View.GONE);
                TrainingMsg2.setVisibility(View.VISIBLE);

            }
            if(t_weekDB.getDays().getDay3().getDayactivitesList().size() == 0){

                workoutWeekRV2.setVisibility(View.GONE);
                TrainingMsg3.setVisibility(View.VISIBLE);

            }
            if(t_weekDB.getDays().getDay4().getDayactivitesList().size() == 0){

                workoutWeekRV3.setVisibility(View.GONE);
                TrainingMsg4.setVisibility(View.VISIBLE);

            }
            if(t_weekDB.getDays().getDay5().getDayactivitesList().size() == 0){

                workoutWeekRV4.setVisibility(View.GONE);
                TrainingMsg5.setVisibility(View.VISIBLE);

            }
            if(t_weekDB.getDays().getDay6().getDayactivitesList().size() == 0){

                workoutWeekRV5.setVisibility(View.GONE);
                TrainingMsg6.setVisibility(View.VISIBLE);

            }
            if(t_weekDB.getDays().getDay7().getDayactivitesList().size() == 0){

                workoutWeekRV6.setVisibility(View.GONE);
                TrainingMsg7.setVisibility(View.VISIBLE);

            }


        }
        catch (Exception ex){

        }


    }

    public void onBackClick(View view) {

        finish();

    }

    @Override
    public void setPresenter(WeekWorkOutPresenter mPresenter) {

    }

    @Override
    public void setStartUpData(String message) {

    }

    @Override
    public void setStartUpDataFailed(String message) {

    }
}
