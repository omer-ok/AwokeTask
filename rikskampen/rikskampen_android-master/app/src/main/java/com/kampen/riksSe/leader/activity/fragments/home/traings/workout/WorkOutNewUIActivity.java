/*
package com.kampen.riksSe.leader.activity.fragments.home.traings.workout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.adapter.SectionedGridRecyclerViewAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.adapter.WorkOutNewUIAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.WorkoutMotivationVideo;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class WorkOutNewUIActivity extends AppCompatActivity implements WeekWorkOutContract.View{


    TextView WeekTab1,WeekTab2,WeekTab3,WeekTab4,WeekTab5,WeekTab6,WeekTab7,WeekTab8,WeekTab9,WeekTab10;
    TextView MondayTab,TuesdayTab,WednesdayTab,ThursdayTab,FridayTab,SaturdayTab,SundayTab;
    TextView descriptionTitle,description,startTitle,endTitle;
    ImageView startImage,endImage;
    View StartVideoView,EndVideoView;
    NestedScrollView scrollView;

    WeekWorkOutPresenter weekWorkOutPresenter;
    View noDataAssiened;
    T_WeekDB t_weekDB;
    T_DayDB t_dayDB;
    int weekId,weekIDScroll;
    View PreviousWeekTab1,PreviousWeekTab2,PreviousWeekTab3,PreviousWeekTab4,PreviousWeekTab5,PreviousWeekTab6,PreviousWeekTab7,PreviousWeekTab8,PreviousWeekTab9,PreviousWeekTab10;
    View StartVideoBtn,EndVideoBtn,DecriptionView,daysVideoLayout;
    int dayNum;

    HorizontalScrollView weekScroll;

    LinearLayoutManager mLayoutManager1;

    private WorkOutNewUIAdapter mDayTrainingAdapter;

    private RecyclerView mTrainingRecyclerView;


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        try{
            //weekIDScroll= weekId;
            weekIDScroll=mDayTrainingAdapter.weekId;
        }catch (Exception e){
            weekIDScroll= weekId;
        }





        switch (weekIDScroll) {
            case 1: {
                int x = WeekTab1.getLeft();
                int y = WeekTab1.getTop();
                onFocusScroll(x,y);
                //noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setText("VECKA 2");
                WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setText("VECKA 3");
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                WeekTab4.setText("VECKA 4");
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                WeekTab5.setText("VECKA 5");
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                WeekTab6.setText("VECKA 6");
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                WeekTab7.setText("VECKA 7");
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                WeekTab8.setText("VECKA 8");
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                WeekTab9.setText("VECKA 9");
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                WeekTab10.setText("VECKA 10");
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setTextColor(Color.parseColor("#BB9767"));
MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);





                break;
            }
            case 2: {
                int x = WeekTab2.getLeft();
                int y = WeekTab2.getTop();
                onFocusScroll(x,y);
                //noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab2.setText("VECKA 2");
                WeekTab2.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setText("VECKA 3");
                WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setText("VECKA 4");
                WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setText("VECKA 5");
                WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setText("VECKA 6");
                WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setText("VECKA 7");
                WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setText("VECKA 8");
                WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setText("VECKA 9");
                WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setText("VECKA 10");
                WeekTab10.setTextColor(Color.parseColor("#BB9767"));

 MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);



                break;
            }

            case 3: {
                int x = WeekTab3.getLeft();
                int y = WeekTab3.getTop();
                onFocusScroll(x,y);
                //noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab3.setText("VECKA 3");
                WeekTab3.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setText("VECKA 2");
                WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);





                break;
            }

            case 4: {
                int x = WeekTab4.getLeft();
                int y = WeekTab4.getTop();
                onFocusScroll(x,y);
                //noDataAssiened.setVisibility(View.INVISIBLE);
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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);




                break;
            }

            case 5: {

                int x = WeekTab5.getLeft();
                int y = WeekTab5.getTop();
                onFocusScroll(x,y);
                //noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab5.setText("VECKA 5");
                WeekTab5.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setText("VECKA 2");
                WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setText("VECKA 3");
                WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setText("VECKA 4");
                WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);





                break;
            }

            case 6: {
                int x = WeekTab6.getLeft();
                int y = WeekTab6.getTop();
                onFocusScroll(x,y);
                //noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab6.setText("VECKA 6");
                WeekTab6.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_selected);
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
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);




                break;
            }

            case 7: {
                int x = WeekTab7.getLeft();
                int y = WeekTab7.getTop();
                onFocusScroll(x,y);
                //noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab7.setText("VECKA 7");
                WeekTab7.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_selected);
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
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setText("VECKA 8");
                WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setText("VECKA 9");
                WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setText("VECKA 10");
                WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

 MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);





                break;
            }

            case 8: {
                int x = WeekTab8.getLeft();
                int y = WeekTab8.getTop();
                onFocusScroll(x,y);
                //noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab8.setText("VECKA 8");
                WeekTab8.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_selected);
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
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setText("VECKA 9");
                WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setText("VECKA 10");
                WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);


                break;
            }

            case 9: {
                int x = WeekTab9.getLeft();
                int y = WeekTab9.getTop();
                onFocusScroll(x,y);
                //noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab9.setText("VECKA 9");
                WeekTab9.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_selected);
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
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setText("VECKA 10");
                WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);


                break;
            }

            case 10: {
                int x = WeekTab10.getLeft();
                int y = WeekTab10.getTop();
                onFocusScroll(x,y);
                //noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab10.setText("VECKA 10");
                WeekTab10.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_selected);
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
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#BB9767"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_unselected);

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);


                break;
            }

        }
        mTrainingRecyclerView=findViewById(R.id.workoutWeekRV);
mLayoutManager1 = new GridLayoutManager(this, 2);
        mLayoutManager1.setOrientation(GridLayoutManager.VERTICAL);



        t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,weekId);
        t_dayDB = t_weekDB.getDays().getDay1();


        if(t_dayDB.getDayactivitesList().isEmpty()){

            MondayTab.setTextSize(15);
            MondayTab.setTextColor(Color.parseColor("#000000"));
            MondayTab.setTypeface(null, Typeface.BOLD);
            noDataAssiened.setVisibility(View.VISIBLE);

        }else {
            mDayTrainingAdapter =new WorkOutNewUIAdapter(WorkOutNewUIActivity.this,t_dayDB,weekId);
            mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
            mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);

        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_new);

        weekId = (Integer)getIntent().getIntExtra("selected_week",1);
        //String NotificationString = getIntent().getStringExtra("From");

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


        PreviousWeekTab1 =findViewById(R.id.previousWeek1);
        PreviousWeekTab2 =findViewById(R.id.previousWeek2);
        PreviousWeekTab3 =findViewById(R.id.previousWeek3);
        PreviousWeekTab4 =findViewById(R.id.previousWeek4);
        PreviousWeekTab5 =findViewById(R.id.previousWeek5);
        PreviousWeekTab6 =findViewById(R.id.previousWeek6);
        PreviousWeekTab7 =findViewById(R.id.previousWeek7);
        PreviousWeekTab8 =findViewById(R.id.previousWeek8);
        PreviousWeekTab9 =findViewById(R.id.previousWeek9);
        PreviousWeekTab10 =findViewById(R.id.previousWeek10);


        MondayTab = findViewById(R.id.tab_monday);
        TuesdayTab = findViewById(R.id.tab_tuesday);
        WednesdayTab = findViewById(R.id.tab_wednesday);
        ThursdayTab = findViewById(R.id.tab_thursday);
        FridayTab = findViewById(R.id.tab_friday);
        SaturdayTab = findViewById(R.id.tab_saturday);
        SundayTab = findViewById(R.id.tab_sunday);
        weekScroll = findViewById(R.id.tabsContainerWeeks);

        descriptionTitle =findViewById(R.id.DecTitle);
        description =findViewById(R.id.descriptionVideo);

        startImage = findViewById(R.id.workoutStartImage);
        endImage = findViewById(R.id.workoutendImage);

        startTitle =findViewById(R.id.startTitle);
        endTitle =findViewById(R.id.endTile);

        scrollView =findViewById(R.id.topScroll);
        StartVideoBtn =findViewById(R.id.StartVideo);
        EndVideoBtn =findViewById(R.id.EndVideo);
        daysVideoLayout =findViewById(R.id.DaysVideo);
        DecriptionView =findViewById(R.id.DecriptionLayout);




        noDataAssiened = findViewById(R.id.NoDataView);

        weekWorkOutPresenter = new WeekWorkOutPresenter(WorkOutNewUIActivity.this,getApplicationContext());





        List<T_WeekDB> t_weekDBArrayList = weekWorkOutPresenter.getTrainingsData(WorkOutNewUIActivity.this);
 for(int i =1; i<=10;i++){

            T_WeekDB weekDB  = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,i);
            t_weekDBArrayList.add(weekDB);
        }



        for(int j=0; j<t_weekDBArrayList.size();j++){

            if(t_weekDBArrayList.get(j).getCurrentWeek().equals("past")){

                if(t_weekDBArrayList.get(j).getWeekID().equals("1")){
                    PreviousWeekTab1.setVisibility(View.VISIBLE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("2")){
                    PreviousWeekTab2.setVisibility(View.VISIBLE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("3")){
                    PreviousWeekTab3.setVisibility(View.VISIBLE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("4")){
                    PreviousWeekTab4.setVisibility(View.VISIBLE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("5")){
                    PreviousWeekTab5.setVisibility(View.VISIBLE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("6")){
                    PreviousWeekTab6.setVisibility(View.VISIBLE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("7")){
                    PreviousWeekTab7.setVisibility(View.VISIBLE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("8")){
                    PreviousWeekTab8.setVisibility(View.VISIBLE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("9")){
                    PreviousWeekTab9.setVisibility(View.VISIBLE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("10")){
                    PreviousWeekTab10.setVisibility(View.VISIBLE);
                    WeekTab10.setVisibility(View.GONE);
                }



            }else if(t_weekDBArrayList.get(j).getCurrentWeek().equals("present")){


                if(t_weekDBArrayList.get(j).getWeekID().equals("1")){
                    PreviousWeekTab1.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("2")){
                    PreviousWeekTab2.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("3")){
                    PreviousWeekTab3.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("4")){
                    PreviousWeekTab4.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("5")){
                    PreviousWeekTab5.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("6")){
                    PreviousWeekTab6.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("7")){
                    PreviousWeekTab7.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("8")){
                    PreviousWeekTab8.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("9")){
                    PreviousWeekTab9.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("10")){
                    PreviousWeekTab10.setVisibility(View.GONE);
                }
            }else if(t_weekDBArrayList.get(j).getDays()==null){


                if(t_weekDBArrayList.get(j).getWeekID().equals("1")){
                    WeekTab1.setVisibility(View.GONE);
                    PreviousWeekTab1.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("2")){
                    WeekTab2.setVisibility(View.GONE);
                    PreviousWeekTab2.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("3")){
                    WeekTab3.setVisibility(View.GONE);
                    PreviousWeekTab3.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("4")){
                    WeekTab4.setVisibility(View.GONE);
                    PreviousWeekTab4.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("5")){
                    WeekTab5.setVisibility(View.GONE);
                    PreviousWeekTab5.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("6")){
                    WeekTab6.setVisibility(View.GONE);
                    PreviousWeekTab6.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("7")){
                    WeekTab7.setVisibility(View.GONE);
                    PreviousWeekTab7.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("8")){
                    WeekTab8.setVisibility(View.GONE);
                    PreviousWeekTab8.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("9")){
                    WeekTab9.setVisibility(View.GONE);
                    PreviousWeekTab9.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("10")){
                    WeekTab10.setVisibility(View.GONE);
                    PreviousWeekTab10.setVisibility(View.GONE);
                }
            }
            else{

                if(t_weekDBArrayList.get(j).getWeekID().equals("1")){
                    PreviousWeekTab1.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("2")){
                    PreviousWeekTab2.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("3")){
                    PreviousWeekTab3.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("4")){
                    PreviousWeekTab4.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("5")){
                    PreviousWeekTab5.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("6")){
                    PreviousWeekTab6.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("7")){
                    PreviousWeekTab7.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("8")){
                    PreviousWeekTab8.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("9")){
                    PreviousWeekTab9.setVisibility(View.GONE);
                }else if(t_weekDBArrayList.get(j).getWeekID().equals("10")){
                    PreviousWeekTab10.setVisibility(View.GONE);
                }
            }
        }

 for(int i = 0; i<t_weekDBArrayList.size(); i++){
            if(t_weekDBArrayList.get(i).getDays()!=null){

                if(i==0) {
                    //HorizontalScrollView.LayoutParams params = new HorizontalScrollView.LayoutParams(HorizontalScrollView.LayoutParams.MATCH_PARENT, HorizontalScrollView.LayoutParams.WRAP_CONTENT);

                    if(t_weekDBArrayList.get(i).getCurrentWeek().equals("present")){
                        //WeekTab1.setLayoutParams(params);
                        WeekTab1.setWidth(HorizontalScrollView.LayoutParams.MATCH_PARENT);
                    }else{
                        PreviousWeekTab1.setMinimumWidth(HorizontalScrollView.LayoutParams.MATCH_PARENT);
                    }



                }else if(t_weekDBArrayList.size()==1){
                   // HorizontalScrollView.LayoutParams params = new HorizontalScrollView.LayoutParams(HorizontalScrollView.LayoutParams.MATCH_PARENT, HorizontalScrollView.LayoutParams.WRAP_CONTENT);

                    if(t_weekDBArrayList.get(i).getCurrentWeek().equals("present")){

WeekTab1.setLayoutParams(params);
                        WeekTab2.setLayoutParams(params);
                    }else{

  PreviousWeekTab1.setLayoutParams(params);
                        PreviousWeekTab2.setLayoutParams(params);
                    }
                }
            }
        }







        switch (weekId) {
            case 1: {

                noDataAssiened.setVisibility(View.INVISIBLE);
                WeekTab1.setText("VECKA 1");
                WeekTab1.setTextColor(Color.parseColor("#FFFFFF"));
                WeekTab1.setBackgroundResource(R.drawable.tab_middle_selected);
                WeekTab2.setText("VECKA 2");
                WeekTab2.setTextColor(Color.parseColor("#BB9767"));
                WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setText("VECKA 3");
                WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab3.setTextColor(Color.parseColor("#BB9767"));
                WeekTab4.setText("VECKA 4");
                WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab4.setTextColor(Color.parseColor("#BB9767"));
                WeekTab5.setText("VECKA 5");
                WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab5.setTextColor(Color.parseColor("#BB9767"));
                WeekTab6.setText("VECKA 6");
                WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab6.setTextColor(Color.parseColor("#BB9767"));
                WeekTab7.setText("VECKA 7");
                WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab7.setTextColor(Color.parseColor("#BB9767"));
                WeekTab8.setText("VECKA 8");
                WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab8.setTextColor(Color.parseColor("#BB9767"));
                WeekTab9.setText("VECKA 9");
                WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab9.setTextColor(Color.parseColor("#BB9767"));
                WeekTab10.setText("VECKA 10");
                WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);
                WeekTab10.setTextColor(Color.parseColor("#BB9767"));

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);





                break;
            }
            case 2: {
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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);



                break;
            }

            case 3: {

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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);





                break;
            }

            case 4: {

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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);




                break;
            }

            case 5: {


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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);





                break;
            }

            case 6: {
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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);




                break;
            }

            case 7: {

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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);





                break;
            }

            case 8: {

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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);




                break;
            }

            case 9: {

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

MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);





                break;
            }

            case 10: {
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

 MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);

                TuesdayTab.setTextSize(8);
                TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                TuesdayTab.setTypeface(null, Typeface.NORMAL);

                WednesdayTab.setTextSize(8);
                WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                WednesdayTab.setTypeface(null, Typeface.NORMAL);

                ThursdayTab.setTextSize(8);
                ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                ThursdayTab.setTypeface(null, Typeface.NORMAL);

                FridayTab.setTextSize(8);
                FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                FridayTab.setTypeface(null, Typeface.NORMAL);

                SaturdayTab.setTextSize(8);
                SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SaturdayTab.setTypeface(null, Typeface.NORMAL);

                SundayTab.setTextSize(8);
                SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                SundayTab.setTypeface(null, Typeface.NORMAL);




                break;
            }

        }
        mTrainingRecyclerView=findViewById(R.id.workoutWeekRV);
        mLayoutManager1 = new GridLayoutManager(this, 2);
        mLayoutManager1.setOrientation(GridLayoutManager.VERTICAL);
        description.setMovementMethod(new ScrollingMovementMethod());
        

        t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,weekId);
        //t_dayDB  = t_weekDB.getDays().getDay1();


        try{
            if(t_weekDB.getDays()!=null){
                if(t_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                    t_dayDB = t_weekDB.getDays().getDay1();
                    dayNum = 1;
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#776E6E"));
                    MondayTab.setTypeface(null, Typeface.BOLD);

                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);
                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);
                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);
                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);
                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);
                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);

                }else if(t_weekDB!=null && t_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                    t_dayDB = t_weekDB.getDays().getDay2();
                    dayNum = 2;
                    TuesdayTab.setTextSize(15);
                    TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                    TuesdayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);
                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);
                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);
                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);
                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);
                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);

                }else if(t_weekDB!=null && t_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                    t_dayDB = t_weekDB.getDays().getDay3();
                    dayNum = 3;
                    WednesdayTab.setTextSize(15);
                    WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                    WednesdayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);
                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);
                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);
                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);
                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);
                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);

                }else if(t_weekDB!=null && t_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                    t_dayDB = t_weekDB.getDays().getDay4();
                    dayNum = 4;
                    ThursdayTab.setTextSize(15);
                    ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                    ThursdayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);
                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);
                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);
                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);
                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);
                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);

                }else if(t_weekDB!=null && t_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                    t_dayDB = t_weekDB.getDays().getDay5();
                    dayNum = 5;
                    FridayTab.setTextSize(15);
                    FridayTab.setTextColor(Color.parseColor("#776E6E"));
                    FridayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);
                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);
                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);
                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);
                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);
                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);

                }else if(t_weekDB!=null && t_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                    t_dayDB = t_weekDB.getDays().getDay6();
                    dayNum = 6;
                    SaturdayTab.setTextSize(15);
                    SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                    SaturdayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);
                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);
                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);
                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);
                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);
                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);

                }else if(t_weekDB!=null && t_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                    t_dayDB = t_weekDB.getDays().getDay7();
                    dayNum = 7;
                    SundayTab.setTextSize(15);
                    SundayTab.setTextColor(Color.parseColor("#776E6E"));
                    SundayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);
                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);
                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);
                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);
                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);
                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);

                }else{
                    if(t_weekDB!=null ){
                        t_dayDB = t_weekDB.getDays().getDay1();
                        dayNum = 1;
                        MondayTab.setTextSize(15);
                        MondayTab.setTextColor(Color.parseColor("#776E6E"));
                        MondayTab.setTypeface(null, Typeface.BOLD);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);
                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);
                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);
                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);
                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);
                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);
                    }


                }
            }else{

                noDataAssiened.setVisibility(View.VISIBLE);
                StartVideoBtn.setVisibility(View.GONE);
                EndVideoBtn.setVisibility(View.GONE);
                daysVideoLayout.setVisibility(View.GONE);
                DecriptionView.setVisibility(View.GONE);
            }

        }catch (Exception e){
            noDataAssiened.setVisibility(View.VISIBLE);
            StartVideoBtn.setVisibility(View.GONE);
            EndVideoBtn.setVisibility(View.GONE);
            daysVideoLayout.setVisibility(View.GONE);
            DecriptionView.setVisibility(View.GONE);
        }


        try{
            if(t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription()==null &&t_dayDB.getStartVideo().getMediaVideo()==null && t_dayDB.getEndVideo().getMediaVideo()==null){

MondayTab.setTextSize(15);
            MondayTab.setTextColor(Color.parseColor("#000000"));
            MondayTab.setTypeface(null, Typeface.BOLD);

                noDataAssiened.setVisibility(View.VISIBLE);
                StartVideoBtn.setVisibility(View.GONE);
                EndVideoBtn.setVisibility(View.GONE);
                daysVideoLayout.setVisibility(View.GONE);
                DecriptionView.setVisibility(View.GONE);


            }else {


                noDataAssiened.setVisibility(View.GONE);
                StartVideoBtn.setVisibility(View.VISIBLE);
                EndVideoBtn.setVisibility(View.VISIBLE);
                daysVideoLayout.setVisibility(View.VISIBLE);
                DecriptionView.setVisibility(View.VISIBLE);

                if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                    StartVideoBtn.setVisibility(View.VISIBLE);
                }else{
                    StartVideoBtn.setVisibility(View.GONE);
                }
                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                    EndVideoBtn.setVisibility(View.VISIBLE);
                }else{
                    EndVideoBtn.setVisibility(View.GONE);
                }
                if (t_dayDB.getStartVideo().getImagePath() != null) {
                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                }
                if (t_dayDB.getEndVideo().getImagePath() != null) {

                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                }
                if (t_dayDB.getStartVideo().getTitle() != null) {
                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                }
                if (t_dayDB.getEndVideo().getTitle() != null) {
                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                }
                if(t_dayDB.getDayDescription()!=null){
                    DecriptionView.setVisibility(View.VISIBLE);
                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                }else{
                    DecriptionView.setVisibility(View.GONE);
                }



                mDayTrainingAdapter =new WorkOutNewUIAdapter(WorkOutNewUIActivity.this,t_dayDB,weekId);
                mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                //This is the code to provide a sectioned grid
                List<SectionedGridRecyclerViewAdapter.Section> sections =
                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

                //Sections

     for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                    if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("hemVideo")){
                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Hemmaträning"));
                        break;
                    }
                }

                for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                    if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("gymVideo")){
                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Gymträning"));
                        break;
                    }
                }

                for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                    if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("outVideo")){
                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Uteträning"));
                        break;
                    }
                }


for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                    if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("home")){
                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Hemmaträning"));
                        break;
                    }
                }

                for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                    if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("gym")){
                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Gymträning"));
                        break;
                    }
                }

                for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                    if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("outdoor")){
                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Uteträning"));
                        break;
                    }
                }




                List<String> setionNamelist = new ArrayList<String>();
                Set<String> setionName = new TreeSet<String>();
                for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                    setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                }
                setionNamelist.addAll(setionName);

                for(int j =0; j<setionNamelist.size(); j++){
                    for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                        if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))){
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i,t_dayDB.getDayactivitesList().get(i).getVideoType()));
                            break;
                        }
                    }
                }


                //Add your adapter to the sectionAdapter
                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                        SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this,R.layout.workout_headers,R.id.Header,mTrainingRecyclerView,mDayTrainingAdapter);
                mSectionedAdapter.setSections(sections.toArray(dummy));

                //Apply this adapter to the RecyclerView
                mTrainingRecyclerView.setAdapter(mSectionedAdapter);




        }

        }catch (Exception e){

            noDataAssiened.setVisibility(View.VISIBLE);
            StartVideoBtn.setVisibility(View.GONE);
            EndVideoBtn.setVisibility(View.GONE);
            daysVideoLayout.setVisibility(View.GONE);
            DecriptionView.setVisibility(View.GONE);
        }
        //scrollView.smoothScrollTo(0, 0);


        scrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                description.getParent().requestDisallowInterceptTouchEvent(false);

                return false;
            }
        });


        description.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                description.getParent().requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });

        StartVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    Intent startVideo = new Intent(WorkOutNewUIActivity.this, WorkoutMotivationVideo.class);
                    startVideo.putExtra("VideoTitle",t_dayDB.getStartVideo().getTitle());
                    startVideo.putExtra("DecTxt",t_dayDB.getStartVideo().getDescription());
                    startVideo.putExtra("RapsTxt",t_dayDB.getStartVideo().getReps());
                    startVideo.putExtra("SetsTxt",t_dayDB.getStartVideo().getSets());
                    startVideo.putExtra("MusclePartTxt",t_dayDB.getStartVideo().getReps());
                    startVideo.putExtra("VideoUrl",t_dayDB.getStartVideo().getMediaVideo());
                    //startVideo.putExtra("VideoDec",t_dayDB.getStartVideo().getTitle());
                    startActivity(startVideo);

                }catch (Exception e){

                }

            }
        });

        EndVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent startVideo = new Intent(WorkOutNewUIActivity.this, WorkoutMotivationVideo.class);
                    startVideo.putExtra("VideoTitle",t_dayDB.getEndVideo().getTitle());
                    startVideo.putExtra("DecTxt",t_dayDB.getStartVideo().getDescription());
                    startVideo.putExtra("RapsTxt",t_dayDB.getStartVideo().getReps());
                    startVideo.putExtra("SetsTxt",t_dayDB.getStartVideo().getSets());
                    startVideo.putExtra("MusclePartTxt",t_dayDB.getStartVideo().getReps());
                    startVideo.putExtra("VideoUrl",t_dayDB.getEndVideo().getMediaVideo());
                    //startVideo.putExtra("VideoDec",t_dayDB.getStartVideo().getTitle());
                    startActivity(startVideo);
                }catch(Exception e){

                }

            }
        });

        WeekTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,1);
                    //t_dayDB = t_weekDB.getDays().getDay1();

                    if(t_weekDB.getDays()!=null){
                        if(t_weekDB!=null && t_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }else if(t_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                            t_dayDB = t_weekDB.getDays().getDay2();
                            dayNum = 2;
                            TuesdayTab.setTextSize(15);
                            TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            TuesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }else if(t_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                            t_dayDB = t_weekDB.getDays().getDay3();
                            dayNum = 3;
                            WednesdayTab.setTextSize(15);
                            WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            WednesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);
                        }else if(t_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                            t_dayDB = t_weekDB.getDays().getDay4();
                            dayNum = 4;
                            ThursdayTab.setTextSize(15);
                            ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                            ThursdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }else if(t_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                            t_dayDB = t_weekDB.getDays().getDay5();
                            dayNum = 5;
                            FridayTab.setTextSize(15);
                            FridayTab.setTextColor(Color.parseColor("#776E6E"));
                            FridayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }else if(t_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                            t_dayDB = t_weekDB.getDays().getDay6();
                            dayNum = 6;
                            SaturdayTab.setTextSize(15);
                            SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                            SaturdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }else if(t_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                            t_dayDB = t_weekDB.getDays().getDay7();
                            dayNum = 7;
                            SundayTab.setTextSize(15);
                            SundayTab.setTextColor(Color.parseColor("#776E6E"));
                            SundayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        }else{
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }

                    }else{
                        t_dayDB =null;
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }


                    if(t_dayDB!=null && t_dayDB.getDayactivitesList()!=null ) {

                        if (t_dayDB != null && t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

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
                            noDataAssiened.setVisibility(View.VISIBLE);
                            StartVideoBtn.setVisibility(View.GONE);
                            EndVideoBtn.setVisibility(View.GONE);
                            daysVideoLayout.setVisibility(View.GONE);
                            DecriptionView.setVisibility(View.GONE);

                        } else {

                            noDataAssiened.setVisibility(View.GONE);
                            StartVideoBtn.setVisibility(View.VISIBLE);
                            EndVideoBtn.setVisibility(View.VISIBLE);
                            daysVideoLayout.setVisibility(View.VISIBLE);
                            DecriptionView.setVisibility(View.VISIBLE);
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
                            WeekTab9.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);

                            WeekTab10.setText("VECKA 10");
                            WeekTab10.setTextColor(Color.parseColor("#BB9767"));
                            WeekTab10.setBackgroundResource(R.drawable.tab_middle_unselected);

                            try {
                                if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                    StartVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    StartVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                    EndVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    EndVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getStartVideo().getImagePath() != null) {
                                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                                }
                                if (t_dayDB.getEndVideo().getImagePath() != null) {

                                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                                }
                                if (t_dayDB.getStartVideo().getTitle() != null) {
                                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                                }
                                if (t_dayDB.getEndVideo().getTitle() != null) {
                                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                                }
                                if(t_dayDB.getDayDescription()!=null){
                                    DecriptionView.setVisibility(View.VISIBLE);
                                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                                }else{
                                    DecriptionView.setVisibility(View.GONE);
                                }

                                mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, 1);
                                mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                                //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                                //This is the code to provide a sectioned grid
                                List<SectionedGridRecyclerViewAdapter.Section> sections =
                                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();


                                List<String> setionNamelist = new ArrayList<String>();
                                Set<String> setionName = new TreeSet<String>();
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                                }
                                setionNamelist.addAll(setionName);

                                for (int j = 0; j < setionNamelist.size(); j++) {
                                    for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                            break;
                                        }
                                    }
                                }


                                //Add your adapter to the sectionAdapter
                                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                        SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                                mSectionedAdapter.setSections(sections.toArray(dummy));

                                //Apply this adapter to the RecyclerView
                                mTrainingRecyclerView.setAdapter(mSectionedAdapter);

                            } catch (Exception e) {

                            }

                        }
                    }else{
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
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }
                }catch (Exception e){
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
                    noDataAssiened.setVisibility(View.VISIBLE);
                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }

            }
        });



        WeekTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //t_weekDB=null;

                try{
                    t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,2);
                    //t_dayDB = t_weekDB.getDays().getDay1();
                    if(t_weekDB.getDays()!=null) {
                        if (t_weekDB.getDays().getDay1().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay2().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay2();
                            dayNum = 2;
                            TuesdayTab.setTextSize(15);
                            TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            TuesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay3().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay3();
                            dayNum = 3;
                            WednesdayTab.setTextSize(15);
                            WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            WednesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);
                        } else if (t_weekDB.getDays().getDay4().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay4();
                            dayNum = 4;
                            ThursdayTab.setTextSize(15);
                            ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                            ThursdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay5().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay5();
                            dayNum = 5;
                            FridayTab.setTextSize(15);
                            FridayTab.setTextColor(Color.parseColor("#776E6E"));
                            FridayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay6().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay6();
                            dayNum = 6;
                            SaturdayTab.setTextSize(15);
                            SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                            SaturdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay7().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay7();
                            dayNum = 7;
                            SundayTab.setTextSize(15);
                            SundayTab.setTextColor(Color.parseColor("#776E6E"));
                            SundayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        } else {
                            //t_dayDB = t_weekDB.getDays().getDay1();
                            //dayNum = 1;

                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }
                    }else{
                        t_dayDB =null;
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }

                    if(t_dayDB!=null && t_dayDB.getDayactivitesList()!=null ) {

                        if ( t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription().equals(null) && t_dayDB.getDayDescription().isEmpty() && t_dayDB.getStartVideo().getMediaVideo().equals(null) && t_dayDB.getEndVideo().getMediaVideo().equals(null) && t_dayDB.getStartVideo().getMediaVideo().isEmpty() && t_dayDB.getEndVideo().getMediaVideo().isEmpty()) {

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

                            noDataAssiened.setVisibility(View.VISIBLE);
                            StartVideoBtn.setVisibility(View.GONE);
                            EndVideoBtn.setVisibility(View.GONE);
                            daysVideoLayout.setVisibility(View.GONE);
                            DecriptionView.setVisibility(View.GONE);


                        } else {
                            noDataAssiened.setVisibility(View.GONE);
                            StartVideoBtn.setVisibility(View.VISIBLE);
                            EndVideoBtn.setVisibility(View.VISIBLE);
                            daysVideoLayout.setVisibility(View.VISIBLE);
                            DecriptionView.setVisibility(View.VISIBLE);

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


                            try {
                                if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                    StartVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    StartVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                    EndVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    EndVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getStartVideo().getImagePath() != null) {
                                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                                }
                                if (t_dayDB.getEndVideo().getImagePath() != null) {

                                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                                }
                                if (t_dayDB.getStartVideo().getTitle() != null) {
                                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                                }
                                if (t_dayDB.getEndVideo().getTitle() != null) {
                                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                                }
                                if(t_dayDB.getDayDescription()!=null){
                                    DecriptionView.setVisibility(View.VISIBLE);
                                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                                }else{
                                    DecriptionView.setVisibility(View.GONE);
                                }

                                mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, 2);
                                mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                                //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                                //This is the code to provide a sectioned grid
                                List<SectionedGridRecyclerViewAdapter.Section> sections =
                                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();




                                List<String> setionNamelist = new ArrayList<String>();
                                Set<String> setionName = new TreeSet<String>();
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                                }
                                setionNamelist.addAll(setionName);

                                for (int j = 0; j < setionNamelist.size(); j++) {
                                    for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                            break;
                                        }
                                    }
                                }


                                //Add your adapter to the sectionAdapter
                                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                        SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                                mSectionedAdapter.setSections(sections.toArray(dummy));

                                //Apply this adapter to the RecyclerView
                                mTrainingRecyclerView.setAdapter(mSectionedAdapter);

                            } catch (Exception e) {
                                noDataAssiened.setVisibility(View.VISIBLE);
                                StartVideoBtn.setVisibility(View.GONE);
                                EndVideoBtn.setVisibility(View.GONE);
                                daysVideoLayout.setVisibility(View.GONE);
                                DecriptionView.setVisibility(View.GONE);
                            }

                        }

                    }else{
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

                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }
                }catch (Exception e){
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

                    noDataAssiened.setVisibility(View.VISIBLE);
                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }

            }
        });

        WeekTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,3);

                    if(t_weekDB.getDays()!=null ) {
                        if (t_weekDB.getDays().getDay1().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay2().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay2();
                            dayNum = 2;
                            TuesdayTab.setTextSize(15);
                            TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            TuesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay3().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay3();
                            dayNum = 3;
                            WednesdayTab.setTextSize(15);
                            WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            WednesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);
                        } else if (t_weekDB.getDays().getDay4().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay4();
                            dayNum = 4;
                            ThursdayTab.setTextSize(15);
                            ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                            ThursdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay5().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay5();
                            dayNum = 5;
                            FridayTab.setTextSize(15);
                            FridayTab.setTextColor(Color.parseColor("#776E6E"));
                            FridayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay6().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay6();
                            dayNum = 6;
                            SaturdayTab.setTextSize(15);
                            SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                            SaturdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay7().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay7();
                            dayNum = 7;
                            SundayTab.setTextSize(15);
                            SundayTab.setTextColor(Color.parseColor("#776E6E"));
                            SundayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        } else {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }
                    }else{
                        t_dayDB =null;
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }

                    if(t_dayDB!=null && t_dayDB.getDayactivitesList()!=null) {

                        if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

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

                            noDataAssiened.setVisibility(View.VISIBLE);
                            StartVideoBtn.setVisibility(View.GONE);
                            EndVideoBtn.setVisibility(View.GONE);
                            daysVideoLayout.setVisibility(View.GONE);
                            DecriptionView.setVisibility(View.GONE);

                        } else {
                            noDataAssiened.setVisibility(View.GONE);
                            StartVideoBtn.setVisibility(View.VISIBLE);
                            EndVideoBtn.setVisibility(View.VISIBLE);
                            daysVideoLayout.setVisibility(View.VISIBLE);
                            DecriptionView.setVisibility(View.VISIBLE);
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


                            try {

                                if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                    StartVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    StartVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                    EndVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    EndVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getStartVideo().getImagePath() != null) {
                                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                                }
                                if (t_dayDB.getEndVideo().getImagePath() != null) {

                                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                                }
                                if (t_dayDB.getStartVideo().getTitle() != null) {
                                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                                }
                                if (t_dayDB.getEndVideo().getTitle() != null) {
                                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                                }
                                if(t_dayDB.getDayDescription()!=null){
                                    DecriptionView.setVisibility(View.VISIBLE);
                                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                                }else{
                                    DecriptionView.setVisibility(View.GONE);
                                }
                                mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, 3);
                                mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                                //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                                //This is the code to provide a sectioned grid
                                List<SectionedGridRecyclerViewAdapter.Section> sections =
                                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();


                                List<String> setionNamelist = new ArrayList<String>();
                                Set<String> setionName = new TreeSet<String>();
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                                }
                                setionNamelist.addAll(setionName);

                                for (int j = 0; j < setionNamelist.size(); j++) {
                                    for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                            break;
                                        }
                                    }
                                }


                                //Add your adapter to the sectionAdapter
                                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                        SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                                mSectionedAdapter.setSections(sections.toArray(dummy));

                                //Apply this adapter to the RecyclerView
                                mTrainingRecyclerView.setAdapter(mSectionedAdapter);

                            } catch (Exception e) {

                            }
                        }
                    }else{
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

                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }

                }catch (Exception e){
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

                    noDataAssiened.setVisibility(View.VISIBLE);
                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }


            }
        });


        WeekTab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,4);
                    //t_dayDB = t_weekDB.getDays().getDay1();
                    if(t_weekDB.getDays()!=null) {
                        if (t_weekDB.getDays().getDay1().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay2().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay2();
                            dayNum = 2;
                            TuesdayTab.setTextSize(15);
                            TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            TuesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay3().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay3();
                            dayNum = 3;
                            WednesdayTab.setTextSize(15);
                            WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            WednesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);
                        } else if (t_weekDB.getDays().getDay4().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay4();
                            dayNum = 4;
                            ThursdayTab.setTextSize(15);
                            ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                            ThursdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay5().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay5();
                            dayNum = 5;
                            FridayTab.setTextSize(15);
                            FridayTab.setTextColor(Color.parseColor("#776E6E"));
                            FridayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay6().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay6();
                            dayNum = 6;
                            SaturdayTab.setTextSize(15);
                            SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                            SaturdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay7().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay7();
                            dayNum = 7;
                            SundayTab.setTextSize(15);
                            SundayTab.setTextColor(Color.parseColor("#776E6E"));
                            SundayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        } else {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }
                    }else{
                        t_dayDB =null;
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }


                    if(t_dayDB!=null && t_dayDB.getDayactivitesList()!=null) {

                        if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

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

                            noDataAssiened.setVisibility(View.VISIBLE);
                            StartVideoBtn.setVisibility(View.GONE);
                            EndVideoBtn.setVisibility(View.GONE);
                            daysVideoLayout.setVisibility(View.GONE);
                            DecriptionView.setVisibility(View.GONE);

                        } else {
                            noDataAssiened.setVisibility(View.GONE);
                            StartVideoBtn.setVisibility(View.VISIBLE);
                            EndVideoBtn.setVisibility(View.VISIBLE);
                            daysVideoLayout.setVisibility(View.VISIBLE);
                            DecriptionView.setVisibility(View.VISIBLE);

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


                            try {
                                if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                    StartVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    StartVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                    EndVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    EndVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getStartVideo().getImagePath() != null) {
                                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                                }
                                if (t_dayDB.getEndVideo().getImagePath() != null) {

                                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                                }
                                if (t_dayDB.getStartVideo().getTitle() != null) {
                                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                                }
                                if (t_dayDB.getEndVideo().getTitle() != null) {
                                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                                }
                                if(t_dayDB.getDayDescription()!=null){
                                    DecriptionView.setVisibility(View.VISIBLE);
                                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                                }else{
                                    DecriptionView.setVisibility(View.GONE);
                                }
                                mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, 4);
                                mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                                //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                                //This is the code to provide a sectioned grid
                                List<SectionedGridRecyclerViewAdapter.Section> sections =
                                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();


                                List<String> setionNamelist = new ArrayList<String>();
                                Set<String> setionName = new TreeSet<String>();
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                                }
                                setionNamelist.addAll(setionName);

                                for (int j = 0; j < setionNamelist.size(); j++) {
                                    for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                            break;
                                        }
                                    }
                                }


                                //Add your adapter to the sectionAdapter
                                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                        SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                                mSectionedAdapter.setSections(sections.toArray(dummy));

                                //Apply this adapter to the RecyclerView
                                mTrainingRecyclerView.setAdapter(mSectionedAdapter);
                            } catch (Exception e) {

                            }

                        }
                    }else{
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

                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }


                }catch (Exception e){
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

                    noDataAssiened.setVisibility(View.VISIBLE);
                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }
            }
        });



        WeekTab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,5);
                    //t_dayDB = t_weekDB.getDays().getDay1();

                    if(t_weekDB.getDays()!=null) {
                        if (t_weekDB.getDays().getDay1().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay2().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay2();
                            dayNum = 2;
                            TuesdayTab.setTextSize(15);
                            TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            TuesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay3().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay3();
                            dayNum = 3;
                            WednesdayTab.setTextSize(15);
                            WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            WednesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);
                        } else if (t_weekDB.getDays().getDay4().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay4();
                            dayNum = 4;
                            ThursdayTab.setTextSize(15);
                            ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                            ThursdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay5().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay5();
                            dayNum = 5;
                            FridayTab.setTextSize(15);
                            FridayTab.setTextColor(Color.parseColor("#776E6E"));
                            FridayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay6().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay6();
                            dayNum = 6;
                            SaturdayTab.setTextSize(15);
                            SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                            SaturdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay7().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay7();
                            dayNum = 7;
                            SundayTab.setTextSize(15);
                            SundayTab.setTextColor(Color.parseColor("#776E6E"));
                            SundayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        } else {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }
                    }else{
                        t_dayDB =null;
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }

                    if(t_dayDB!=null && t_dayDB.getDayactivitesList()!=null ) {

                        if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

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


                            noDataAssiened.setVisibility(View.VISIBLE);
                            StartVideoBtn.setVisibility(View.GONE);
                            EndVideoBtn.setVisibility(View.GONE);
                            daysVideoLayout.setVisibility(View.GONE);
                            DecriptionView.setVisibility(View.GONE);

                        } else {
                            noDataAssiened.setVisibility(View.GONE);
                            StartVideoBtn.setVisibility(View.VISIBLE);
                            EndVideoBtn.setVisibility(View.VISIBLE);
                            daysVideoLayout.setVisibility(View.VISIBLE);
                            DecriptionView.setVisibility(View.VISIBLE);
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

                            try {
                                if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                    StartVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    StartVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                    EndVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    EndVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getStartVideo().getImagePath() != null) {
                                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                                }
                                if (t_dayDB.getEndVideo().getImagePath() != null) {

                                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                                }
                                if (t_dayDB.getStartVideo().getTitle() != null) {
                                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                                }
                                if (t_dayDB.getEndVideo().getTitle() != null) {
                                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                                }
                                if(t_dayDB.getDayDescription()!=null){
                                    DecriptionView.setVisibility(View.VISIBLE);
                                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                                }else{
                                    DecriptionView.setVisibility(View.GONE);
                                }
                                mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, 5);
                                mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                                //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                                //This is the code to provide a sectioned grid
                                List<SectionedGridRecyclerViewAdapter.Section> sections =
                                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

                                List<String> setionNamelist = new ArrayList<String>();
                                Set<String> setionName = new TreeSet<String>();
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                                }
                                setionNamelist.addAll(setionName);

                                for (int j = 0; j < setionNamelist.size(); j++) {
                                    for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                            break;
                                        }
                                    }
                                }

                                //Add your adapter to the sectionAdapter
                                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                        SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                                mSectionedAdapter.setSections(sections.toArray(dummy));

                                //Apply this adapter to the RecyclerView
                                mTrainingRecyclerView.setAdapter(mSectionedAdapter);
                            } catch (Exception e) {

                            }
                        }
                    }else{
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


                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }

                }catch (Exception e){
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


                    noDataAssiened.setVisibility(View.VISIBLE);
                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }


            }
        });


        WeekTab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,6);
                    //t_dayDB = t_weekDB.getDays().getDay1();

                    if(t_weekDB.getDays()!=null) {
                        if (t_weekDB.getDays().getDay1().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay2().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay2();
                            dayNum = 2;
                            TuesdayTab.setTextSize(15);
                            TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            TuesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay3().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay3();
                            dayNum = 3;
                            WednesdayTab.setTextSize(15);
                            WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            WednesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);
                        } else if (t_weekDB.getDays().getDay4().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay4();
                            dayNum = 4;
                            ThursdayTab.setTextSize(15);
                            ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                            ThursdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay5().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay5();
                            dayNum = 5;
                            FridayTab.setTextSize(15);
                            FridayTab.setTextColor(Color.parseColor("#776E6E"));
                            FridayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay6().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay6();
                            dayNum = 6;
                            SaturdayTab.setTextSize(15);
                            SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                            SaturdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay7().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay7();
                            dayNum = 7;
                            SundayTab.setTextSize(15);
                            SundayTab.setTextColor(Color.parseColor("#776E6E"));
                            SundayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        } else {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }
                    }else{
                        t_dayDB =null;
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }

                    if(t_dayDB!=null && t_dayDB.getDayactivitesList()!=null ) {

                        if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

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

                            noDataAssiened.setVisibility(View.VISIBLE);

                            StartVideoBtn.setVisibility(View.GONE);
                            EndVideoBtn.setVisibility(View.GONE);
                            daysVideoLayout.setVisibility(View.GONE);
                            DecriptionView.setVisibility(View.GONE);


                        } else {
                            noDataAssiened.setVisibility(View.GONE);
                            StartVideoBtn.setVisibility(View.VISIBLE);
                            EndVideoBtn.setVisibility(View.VISIBLE);
                            daysVideoLayout.setVisibility(View.VISIBLE);
                            DecriptionView.setVisibility(View.VISIBLE);

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

                            try {
                                if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                    StartVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    StartVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                    EndVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    EndVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getStartVideo().getImagePath() != null) {
                                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                                }
                                if (t_dayDB.getEndVideo().getImagePath() != null) {

                                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                                }
                                if (t_dayDB.getStartVideo().getTitle() != null) {
                                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                                }
                                if (t_dayDB.getEndVideo().getTitle() != null) {
                                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                                }
                                if(t_dayDB.getDayDescription()!=null){
                                    DecriptionView.setVisibility(View.VISIBLE);
                                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                                }else{
                                    DecriptionView.setVisibility(View.GONE);
                                }
                                mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, 6);
                                mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                                //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                                //This is the code to provide a sectioned grid
                                List<SectionedGridRecyclerViewAdapter.Section> sections =
                                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();


                                List<String> setionNamelist = new ArrayList<String>();
                                Set<String> setionName = new TreeSet<String>();
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                                }
                                setionNamelist.addAll(setionName);

                                for (int j = 0; j < setionNamelist.size(); j++) {
                                    for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                            break;
                                        }
                                    }
                                }

                                //Add your adapter to the sectionAdapter
                                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                        SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                                mSectionedAdapter.setSections(sections.toArray(dummy));

                                //Apply this adapter to the RecyclerView
                                mTrainingRecyclerView.setAdapter(mSectionedAdapter);
                            } catch (Exception e) {

                            }
                        }
                    }else{
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

                        noDataAssiened.setVisibility(View.VISIBLE);

                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }


                }catch (Exception e){
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

                    noDataAssiened.setVisibility(View.VISIBLE);

                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }
            }
        });



        WeekTab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,7);
                    //t_dayDB = t_weekDB.getDays().getDay1();
                    if(t_weekDB.getDays()!=null) {
                        if (t_weekDB.getDays().getDay1().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay2().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay2();
                            dayNum = 2;
                            TuesdayTab.setTextSize(15);
                            TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            TuesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay3().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay3();
                            dayNum = 3;
                            WednesdayTab.setTextSize(15);
                            WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            WednesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);
                        } else if (t_weekDB.getDays().getDay4().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay4();
                            dayNum = 4;
                            ThursdayTab.setTextSize(15);
                            ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                            ThursdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay5().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay5();
                            dayNum = 5;
                            FridayTab.setTextSize(15);
                            FridayTab.setTextColor(Color.parseColor("#776E6E"));
                            FridayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay6().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay6();
                            dayNum = 6;
                            SaturdayTab.setTextSize(15);
                            SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                            SaturdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay7().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay7();
                            dayNum = 7;
                            SundayTab.setTextSize(15);
                            SundayTab.setTextColor(Color.parseColor("#776E6E"));
                            SundayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        } else {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }
                    }else{
                        t_dayDB =null;
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }

                    if(t_dayDB!=null && t_dayDB.getDayactivitesList()!=null ){

                        if( t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription()==null &&t_dayDB.getStartVideo().getMediaVideo()==null && t_dayDB.getEndVideo().getMediaVideo()==null){

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

                            noDataAssiened.setVisibility(View.VISIBLE);

                            StartVideoBtn.setVisibility(View.GONE);
                            EndVideoBtn.setVisibility(View.GONE);
                            daysVideoLayout.setVisibility(View.GONE);
                            DecriptionView.setVisibility(View.GONE);

                        }else{
                            noDataAssiened.setVisibility(View.GONE);
                            StartVideoBtn.setVisibility(View.VISIBLE);
                            EndVideoBtn.setVisibility(View.VISIBLE);
                            daysVideoLayout.setVisibility(View.VISIBLE);
                            DecriptionView.setVisibility(View.VISIBLE);
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


                            try{
                                if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                    StartVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    StartVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                    EndVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    EndVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getStartVideo().getImagePath() != null) {
                                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                                }
                                if (t_dayDB.getEndVideo().getImagePath() != null) {

                                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                                }
                                if (t_dayDB.getStartVideo().getTitle() != null) {
                                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                                }
                                if (t_dayDB.getEndVideo().getTitle() != null) {
                                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                                }
                                if(t_dayDB.getDayDescription()!=null){
                                    DecriptionView.setVisibility(View.VISIBLE);
                                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                                }else{
                                    DecriptionView.setVisibility(View.GONE);
                                }
                                mDayTrainingAdapter =new WorkOutNewUIAdapter(WorkOutNewUIActivity.this,t_dayDB,7);
                                mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                                //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                                //This is the code to provide a sectioned grid
                                List<SectionedGridRecyclerViewAdapter.Section> sections =
                                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();



                                List<String> setionNamelist = new ArrayList<String>();
                                Set<String> setionName = new TreeSet<String>();
                                for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                                    setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                                }
                                setionNamelist.addAll(setionName);

                                for(int j =0; j<setionNamelist.size(); j++){
                                    for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                                        if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))){
                                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i,t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                            break;
                                        }
                                    }
                                }

                                //Add your adapter to the sectionAdapter
                                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                        SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this,R.layout.workout_headers,R.id.Header,mTrainingRecyclerView,mDayTrainingAdapter);
                                mSectionedAdapter.setSections(sections.toArray(dummy));

                                //Apply this adapter to the RecyclerView
                                mTrainingRecyclerView.setAdapter(mSectionedAdapter);

                            }catch (Exception e){

                            }

                        }
                    }else {
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

                        noDataAssiened.setVisibility(View.VISIBLE);

                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);

                    }


                }catch (Exception e){
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

                    noDataAssiened.setVisibility(View.VISIBLE);

                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);

                }
            }
        });



        WeekTab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,8);
                    //t_dayDB = t_weekDB.getDays().getDay1();
                    if(t_weekDB.getDays()!=null) {
                        if (t_weekDB.getDays().getDay1().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay2().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay2();
                            dayNum = 2;
                            TuesdayTab.setTextSize(15);
                            TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            TuesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay3().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay3();
                            dayNum = 3;
                            WednesdayTab.setTextSize(15);
                            WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            WednesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);
                        } else if (t_weekDB.getDays().getDay4().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay4();
                            dayNum = 4;
                            ThursdayTab.setTextSize(15);
                            ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                            ThursdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay5().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay5();
                            dayNum = 5;
                            FridayTab.setTextSize(15);
                            FridayTab.setTextColor(Color.parseColor("#776E6E"));
                            FridayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay6().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay6();
                            dayNum = 6;
                            SaturdayTab.setTextSize(15);
                            SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                            SaturdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay7().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay7();
                            dayNum = 7;
                            SundayTab.setTextSize(15);
                            SundayTab.setTextColor(Color.parseColor("#776E6E"));
                            SundayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        } else {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }
                    }else{
                        t_dayDB =null;
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }


                    if(t_dayDB!=null && t_dayDB.getDayactivitesList()!=null ) {

                        if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {
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


                            noDataAssiened.setVisibility(View.VISIBLE);
                            StartVideoBtn.setVisibility(View.GONE);
                            EndVideoBtn.setVisibility(View.GONE);
                            daysVideoLayout.setVisibility(View.GONE);
                            DecriptionView.setVisibility(View.GONE);

                        } else {
                            noDataAssiened.setVisibility(View.GONE);
                            StartVideoBtn.setVisibility(View.VISIBLE);
                            EndVideoBtn.setVisibility(View.VISIBLE);
                            daysVideoLayout.setVisibility(View.VISIBLE);
                            DecriptionView.setVisibility(View.VISIBLE);
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

                            try {
                                if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                    StartVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    StartVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                    EndVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    EndVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getStartVideo().getImagePath() != null) {
                                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                                }
                                if (t_dayDB.getEndVideo().getImagePath() != null) {

                                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                                }
                                if (t_dayDB.getStartVideo().getTitle() != null) {
                                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                                }
                                if (t_dayDB.getEndVideo().getTitle() != null) {
                                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                                }
                                if(t_dayDB.getDayDescription()!=null){
                                    DecriptionView.setVisibility(View.VISIBLE);
                                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                                }else{
                                    DecriptionView.setVisibility(View.GONE);
                                }
                                mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, 8);
                                mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                                //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                                //This is the code to provide a sectioned grid
                                List<SectionedGridRecyclerViewAdapter.Section> sections =
                                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();


                                List<String> setionNamelist = new ArrayList<String>();
                                Set<String> setionName = new TreeSet<String>();
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                                }
                                setionNamelist.addAll(setionName);

                                for (int j = 0; j < setionNamelist.size(); j++) {
                                    for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                            break;
                                        }
                                    }
                                }


                                //Add your adapter to the sectionAdapter
                                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                        SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                                mSectionedAdapter.setSections(sections.toArray(dummy));

                                //Apply this adapter to the RecyclerView
                                mTrainingRecyclerView.setAdapter(mSectionedAdapter);
                            } catch (Exception e) {

                            }
                        }
                    }else{
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


                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }


                }catch (Exception e){
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


                    noDataAssiened.setVisibility(View.VISIBLE);
                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }




            }
        });


        WeekTab9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,9);
                    //t_dayDB = t_weekDB.getDays().getDay1();
                    if(t_weekDB.getDays()!=null) {
                        if (t_weekDB.getDays().getDay1().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay2().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay2();
                            dayNum = 2;
                            TuesdayTab.setTextSize(15);
                            TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            TuesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay3().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay3();
                            dayNum = 3;
                            WednesdayTab.setTextSize(15);
                            WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            WednesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);
                        } else if (t_weekDB.getDays().getDay4().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay4();
                            dayNum = 4;
                            ThursdayTab.setTextSize(15);
                            ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                            ThursdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay5().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay5();
                            dayNum = 5;
                            FridayTab.setTextSize(15);
                            FridayTab.setTextColor(Color.parseColor("#776E6E"));
                            FridayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay6().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay6();
                            dayNum = 6;
                            SaturdayTab.setTextSize(15);
                            SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                            SaturdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay7().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay7();
                            dayNum = 7;
                            SundayTab.setTextSize(15);
                            SundayTab.setTextColor(Color.parseColor("#776E6E"));
                            SundayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        } else {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }
                    }else{
                        t_dayDB =null;
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }


                    if(t_dayDB!=null && t_dayDB.getDayactivitesList()!=null ) {

                        if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

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


                            noDataAssiened.setVisibility(View.VISIBLE);
                            StartVideoBtn.setVisibility(View.GONE);
                            EndVideoBtn.setVisibility(View.GONE);
                            daysVideoLayout.setVisibility(View.GONE);
                            DecriptionView.setVisibility(View.GONE);

                        } else {
                            noDataAssiened.setVisibility(View.GONE);
                            StartVideoBtn.setVisibility(View.VISIBLE);
                            EndVideoBtn.setVisibility(View.VISIBLE);
                            daysVideoLayout.setVisibility(View.VISIBLE);
                            DecriptionView.setVisibility(View.VISIBLE);
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


                            try {
                                if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                    StartVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    StartVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                    EndVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    EndVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getStartVideo().getImagePath() != null) {
                                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                                }
                                if (t_dayDB.getEndVideo().getImagePath() != null) {

                                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                                }
                                if (t_dayDB.getStartVideo().getTitle() != null) {
                                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                                }
                                if (t_dayDB.getEndVideo().getTitle() != null) {
                                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                                }
                                if(t_dayDB.getDayDescription()!=null){
                                    DecriptionView.setVisibility(View.VISIBLE);
                                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                                }else{
                                    DecriptionView.setVisibility(View.GONE);
                                }
                                mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, 9);
                                mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                                //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                                //This is the code to provide a sectioned grid
                                List<SectionedGridRecyclerViewAdapter.Section> sections =
                                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();


                                List<String> setionNamelist = new ArrayList<String>();
                                Set<String> setionName = new TreeSet<String>();
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                                }
                                setionNamelist.addAll(setionName);

                                for (int j = 0; j < setionNamelist.size(); j++) {
                                    for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                            break;
                                        }
                                    }
                                }


                                //Add your adapter to the sectionAdapter
                                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                        SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                                mSectionedAdapter.setSections(sections.toArray(dummy));

                                //Apply this adapter to the RecyclerView
                                mTrainingRecyclerView.setAdapter(mSectionedAdapter);

                            } catch (Exception e) {

                            }

                        }
                    }else{
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

                        t_dayDB =null;
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }


                }catch (Exception e){
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

                    t_dayDB =null;
                    noDataAssiened.setVisibility(View.VISIBLE);
                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }

            }
        });



        WeekTab10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,10);
                    //t_dayDB = t_weekDB.getDays().getDay1();

                    if(t_weekDB.getDays()!=null) {
                        if (t_weekDB.getDays().getDay1().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay2().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay2();
                            dayNum = 2;
                            TuesdayTab.setTextSize(15);
                            TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            TuesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay3().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay3();
                            dayNum = 3;
                            WednesdayTab.setTextSize(15);
                            WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                            WednesdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);
                        } else if (t_weekDB.getDays().getDay4().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay4();
                            dayNum = 4;
                            ThursdayTab.setTextSize(15);
                            ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                            ThursdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay5().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay5();
                            dayNum = 5;
                            FridayTab.setTextSize(15);
                            FridayTab.setTextColor(Color.parseColor("#776E6E"));
                            FridayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay6().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay6();
                            dayNum = 6;
                            SaturdayTab.setTextSize(15);
                            SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                            SaturdayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        } else if (t_weekDB.getDays().getDay7().getCurrentDay().equals("present")) {
                            t_dayDB = t_weekDB.getDays().getDay7();
                            dayNum = 7;
                            SundayTab.setTextSize(15);
                            SundayTab.setTextColor(Color.parseColor("#776E6E"));
                            SundayTab.setTypeface(null, Typeface.BOLD);

                            MondayTab.setTextSize(8);
                            MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            MondayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        } else {
                            t_dayDB = t_weekDB.getDays().getDay1();
                            dayNum = 1;
                            MondayTab.setTextSize(15);
                            MondayTab.setTextColor(Color.parseColor("#776E6E"));
                            MondayTab.setTypeface(null, Typeface.BOLD);

                            TuesdayTab.setTextSize(8);
                            TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            TuesdayTab.setTypeface(null, Typeface.NORMAL);
                            WednesdayTab.setTextSize(8);
                            WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            WednesdayTab.setTypeface(null, Typeface.NORMAL);
                            ThursdayTab.setTextSize(8);
                            ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            ThursdayTab.setTypeface(null, Typeface.NORMAL);
                            FridayTab.setTextSize(8);
                            FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            FridayTab.setTypeface(null, Typeface.NORMAL);
                            SaturdayTab.setTextSize(8);
                            SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SaturdayTab.setTypeface(null, Typeface.NORMAL);
                            SundayTab.setTextSize(8);
                            SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                            SundayTab.setTypeface(null, Typeface.NORMAL);

                        }
                    }else{
                        t_dayDB =null;
                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }





                    if(t_dayDB!=null && t_dayDB.getDayactivitesList()!=null) {

                        if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

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

                            noDataAssiened.setVisibility(View.VISIBLE);
                            StartVideoBtn.setVisibility(View.GONE);
                            EndVideoBtn.setVisibility(View.GONE);
                            daysVideoLayout.setVisibility(View.GONE);
                            DecriptionView.setVisibility(View.GONE);

                        } else {
                            noDataAssiened.setVisibility(View.GONE);
                            StartVideoBtn.setVisibility(View.VISIBLE);
                            EndVideoBtn.setVisibility(View.VISIBLE);
                            daysVideoLayout.setVisibility(View.VISIBLE);
                            DecriptionView.setVisibility(View.VISIBLE);
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


                            try {
                                if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                    StartVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    StartVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                    EndVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    EndVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getStartVideo().getImagePath() != null) {
                                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                                }
                                if (t_dayDB.getEndVideo().getImagePath() != null) {

                                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                                }
                                if (t_dayDB.getStartVideo().getTitle() != null) {
                                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                                }
                                if (t_dayDB.getEndVideo().getTitle() != null) {
                                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                                }
                                if(t_dayDB.getDayDescription()!=null){
                                    DecriptionView.setVisibility(View.VISIBLE);
                                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                                }else{
                                    DecriptionView.setVisibility(View.GONE);
                                }
                                mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, 10);
                                mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                                //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                                //This is the code to provide a sectioned grid
                                List<SectionedGridRecyclerViewAdapter.Section> sections =
                                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();


                                List<String> setionNamelist = new ArrayList<String>();
                                Set<String> setionName = new TreeSet<String>();
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                                }
                                setionNamelist.addAll(setionName);

                                for (int j = 0; j < setionNamelist.size(); j++) {
                                    for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                            break;
                                        }
                                    }
                                }


                                //Add your adapter to the sectionAdapter
                                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                        SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                                mSectionedAdapter.setSections(sections.toArray(dummy));

                                //Apply this adapter to the RecyclerView
                                mTrainingRecyclerView.setAdapter(mSectionedAdapter);
                            } catch (Exception e) {

                            }
                        }
                    }else{
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

                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);
                    }


                }catch (Exception e){
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

                    noDataAssiened.setVisibility(View.VISIBLE);
                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }

            }
        });

        MondayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(t_dayDB!=null) {

                    t_dayDB = t_weekDB.getDays().getDay1();
                    dayNum = 1;
                    if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

                        noDataAssiened.setVisibility(View.VISIBLE);
                        MondayTab.setTextSize(15);
                        MondayTab.setTextColor(Color.parseColor("#776E6E"));
                        MondayTab.setTypeface(null, Typeface.BOLD);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);


                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);

                    } else {
                        noDataAssiened.setVisibility(View.GONE);
                        StartVideoBtn.setVisibility(View.VISIBLE);
                        EndVideoBtn.setVisibility(View.VISIBLE);
                        daysVideoLayout.setVisibility(View.VISIBLE);
                        DecriptionView.setVisibility(View.VISIBLE);

                        MondayTab.setTextSize(15);
                        MondayTab.setTextColor(Color.parseColor("#776E6E"));
                        MondayTab.setTypeface(null, Typeface.BOLD);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);

                        try {
                            if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                StartVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                StartVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                EndVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                EndVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getStartVideo().getImagePath() != null) {
                                loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                            }
                            if (t_dayDB.getEndVideo().getImagePath() != null) {

                                loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                            }
                            if (t_dayDB.getStartVideo().getTitle() != null) {
                                startTitle.setText(t_dayDB.getStartVideo().getTitle());
                            }
                            if (t_dayDB.getEndVideo().getTitle() != null) {
                                endTitle.setText(t_dayDB.getEndVideo().getTitle());
                            }
                            if(t_dayDB.getDayDescription()!=null){
                                DecriptionView.setVisibility(View.VISIBLE);
                                description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                            }else{
                                DecriptionView.setVisibility(View.GONE);
                            }
                            mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, weekId);
                            mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                            //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                            //This is the code to provide a sectioned grid
                            List<SectionedGridRecyclerViewAdapter.Section> sections =
                                    new ArrayList<SectionedGridRecyclerViewAdapter.Section>();




                            List<String> setionNamelist = new ArrayList<String>();
                            Set<String> setionName = new TreeSet<String>();
                            for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                            }
                            setionNamelist.addAll(setionName);

                            for (int j = 0; j < setionNamelist.size(); j++) {
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                        break;
                                    }
                                }
                            }


                            //Add your adapter to the sectionAdapter
                            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                    SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                            mSectionedAdapter.setSections(sections.toArray(dummy));

                            //Apply this adapter to the RecyclerView
                            mTrainingRecyclerView.setAdapter(mSectionedAdapter);

                        } catch (Exception e) {
                            Log.i("Exception ", e.toString());
                        }
                    }

                }else{
                    noDataAssiened.setVisibility(View.VISIBLE);
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#776E6E"));
                    MondayTab.setTypeface(null, Typeface.BOLD);

                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);

                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);

                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);

                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);

                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);

                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);


                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }
            }
        });

        TuesdayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(t_dayDB!=null) {

                    t_dayDB = t_weekDB.getDays().getDay2();
                    dayNum = 2;
                    if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

                        noDataAssiened.setVisibility(View.VISIBLE);

                        TuesdayTab.setTextSize(15);
                        TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                        TuesdayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);

                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);

                    } else {
                        noDataAssiened.setVisibility(View.GONE);
                        StartVideoBtn.setVisibility(View.VISIBLE);
                        EndVideoBtn.setVisibility(View.VISIBLE);
                        daysVideoLayout.setVisibility(View.VISIBLE);
                        DecriptionView.setVisibility(View.VISIBLE);
                        TuesdayTab.setTextSize(15);
                        TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                        TuesdayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);

                        try {
                            if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                StartVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                StartVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                EndVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                EndVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getStartVideo().getImagePath() != null) {
                                loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                            }
                            if (t_dayDB.getEndVideo().getImagePath() != null) {

                                loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                            }
                            if (t_dayDB.getStartVideo().getTitle() != null) {
                                startTitle.setText(t_dayDB.getStartVideo().getTitle());
                            }
                            if (t_dayDB.getEndVideo().getTitle() != null) {
                                endTitle.setText(t_dayDB.getEndVideo().getTitle());
                            }
                            if(t_dayDB.getDayDescription()!=null){
                                DecriptionView.setVisibility(View.VISIBLE);
                                description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                            }else{
                                DecriptionView.setVisibility(View.GONE);
                            }
                            mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, weekId);
                            mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                            //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                            //This is the code to provide a sectioned grid
                            List<SectionedGridRecyclerViewAdapter.Section> sections =
                                    new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

                            //Sections
for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                        if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("hemVideo")){
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Hemmaträning"));
                            break;
                        }
                    }

                    for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                        if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("gymVideo")){
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Gymträning"));
                            break;
                        }
                    }

                    for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                        if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("outVideo")){
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Uteträning"));
                            break;
                        }
                    }



for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                            if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("home")){
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Hemmaträning"));
                                break;
                            }
                        }

                        for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                            if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("gym")){
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Gymträning"));
                                break;
                            }
                        }

                        for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                            if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("outdoor")){
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Uteträning"));
                                break;
                            }
                        }



                            List<String> setionNamelist = new ArrayList<String>();
                            Set<String> setionName = new TreeSet<String>();
                            for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                            }
                            setionNamelist.addAll(setionName);

                            for (int j = 0; j < setionNamelist.size(); j++) {
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                        break;
                                    }
                                }
                            }


                            //Add your adapter to the sectionAdapter
                            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                    SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                            mSectionedAdapter.setSections(sections.toArray(dummy));

                            //Apply this adapter to the RecyclerView
                            mTrainingRecyclerView.setAdapter(mSectionedAdapter);

                        } catch (Exception e) {

                        }
                    }
                }else{
                    noDataAssiened.setVisibility(View.VISIBLE);

                    TuesdayTab.setTextSize(15);
                    TuesdayTab.setTextColor(Color.parseColor("#776E6E"));
                    TuesdayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);

                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);

                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);

                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);

                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);

                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);

                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }
            }
        });


        WednesdayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(t_dayDB!=null) {


                    t_dayDB = t_weekDB.getDays().getDay3();
                    dayNum = 3;
                    if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

                        noDataAssiened.setVisibility(View.VISIBLE);

                        WednesdayTab.setTextSize(15);
                        WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                        WednesdayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);

                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);

                    } else {
                        noDataAssiened.setVisibility(View.GONE);
                        StartVideoBtn.setVisibility(View.VISIBLE);
                        EndVideoBtn.setVisibility(View.VISIBLE);
                        daysVideoLayout.setVisibility(View.VISIBLE);
                        DecriptionView.setVisibility(View.VISIBLE);

                        WednesdayTab.setTextSize(15);
                        WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                        WednesdayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);

                        try {
                            if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                StartVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                StartVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                EndVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                EndVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getStartVideo().getImagePath() != null) {
                                loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                            }
                            if (t_dayDB.getEndVideo().getImagePath() != null) {

                                loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                            }
                            if (t_dayDB.getStartVideo().getTitle() != null) {
                                startTitle.setText(t_dayDB.getStartVideo().getTitle());
                            }
                            if (t_dayDB.getEndVideo().getTitle() != null) {
                                endTitle.setText(t_dayDB.getEndVideo().getTitle());
                            }
                            if(t_dayDB.getDayDescription()!=null){
                                DecriptionView.setVisibility(View.VISIBLE);
                                description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                            }else{
                                DecriptionView.setVisibility(View.GONE);
                            }
                            mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, weekId);
                            mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                            //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                            //This is the code to provide a sectioned grid
                            List<SectionedGridRecyclerViewAdapter.Section> sections =
                                    new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

                            //Sections
for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                        if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("hemVideo")){
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Hemmaträning"));
                            break;
                        }
                    }

                    for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                        if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("gymVideo")){
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Gymträning"));
                            break;
                        }
                    }

                    for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                        if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("outVideo")){
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Uteträning"));
                            break;
                        }
                    }



 for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                            if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("home")){
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Hemmaträning"));
                                break;
                            }
                        }

                        for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                            if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("gym")){
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Gymträning"));
                                break;
                            }
                        }

                        for(int i =0; i<t_dayDB.getDayactivitesList().size();i++){
                            if(t_dayDB.getDayactivitesList().get(i).getVideoType().equals("outdoor")){
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i,"Uteträning"));
                                break;
                            }
                        }



                            List<String> setionNamelist = new ArrayList<String>();
                            Set<String> setionName = new TreeSet<String>();
                            for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                            }
                            setionNamelist.addAll(setionName);

                            for (int j = 0; j < setionNamelist.size(); j++) {
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                        break;
                                    }
                                }
                            }


                            //Add your adapter to the sectionAdapter
                            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                    SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                            mSectionedAdapter.setSections(sections.toArray(dummy));

                            //Apply this adapter to the RecyclerView
                            mTrainingRecyclerView.setAdapter(mSectionedAdapter);
                        } catch (Exception e) {

                        }

                    }
                }else{
                    noDataAssiened.setVisibility(View.VISIBLE);

                    WednesdayTab.setTextSize(15);
                    WednesdayTab.setTextColor(Color.parseColor("#776E6E"));
                    WednesdayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);

                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);

                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);

                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);

                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);

                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);

                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }
            }
        });


        ThursdayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(t_dayDB!=null) {

                    t_dayDB = t_weekDB.getDays().getDay4();
                    dayNum = 4;
                    if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

                        noDataAssiened.setVisibility(View.VISIBLE);
                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);

                        ThursdayTab.setTextSize(15);
                        ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                        ThursdayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);


                    } else {

                        noDataAssiened.setVisibility(View.GONE);
                        StartVideoBtn.setVisibility(View.VISIBLE);
                        EndVideoBtn.setVisibility(View.VISIBLE);
                        daysVideoLayout.setVisibility(View.VISIBLE);
                        DecriptionView.setVisibility(View.VISIBLE);

                        ThursdayTab.setTextSize(15);
                        ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                        ThursdayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);

                        try {
                            if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                StartVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                StartVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                EndVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                EndVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getStartVideo().getImagePath() != null) {
                                loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                            }
                            if (t_dayDB.getEndVideo().getImagePath() != null) {

                                loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                            }
                            if (t_dayDB.getStartVideo().getTitle() != null) {
                                startTitle.setText(t_dayDB.getStartVideo().getTitle());
                            }
                            if (t_dayDB.getEndVideo().getTitle() != null) {
                                endTitle.setText(t_dayDB.getEndVideo().getTitle());
                            }
                            if(t_dayDB.getDayDescription()!=null){
                                DecriptionView.setVisibility(View.VISIBLE);
                                description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                            }else{
                                DecriptionView.setVisibility(View.GONE);
                            }
                            mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, weekId);
                            mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                            //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                            //This is the code to provide a sectioned grid
                            List<SectionedGridRecyclerViewAdapter.Section> sections =
                                    new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

                            //Sections
for (int i = 0; i <t_dayDB.getDayactivitesList().size(); i++) {
                            if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("hemVideo")) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Hemmaträning"));
                                break;
                            }
                        }

                        for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                            if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("gymVideo")) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Gymträning"));
                                break;
                            }
                        }

                        for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                            if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("outVideo")) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Uteträning"));
                                break;
                            }
                        }



                            List<String> setionNamelist = new ArrayList<String>();
                            Set<String> setionName = new TreeSet<String>();
                            for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                            }
                            setionNamelist.addAll(setionName);

                            for (int j = 0; j < setionNamelist.size(); j++) {
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                        break;
                                    }
                                }
                            }


                            //Add your adapter to the sectionAdapter
                            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                    SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                            mSectionedAdapter.setSections(sections.toArray(dummy));

                            //Apply this adapter to the RecyclerView
                            mTrainingRecyclerView.setAdapter(mSectionedAdapter);
                        } catch (Exception e) {

                        }

                    }
                }else{
                    noDataAssiened.setVisibility(View.VISIBLE);
                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);

                    ThursdayTab.setTextSize(15);
                    ThursdayTab.setTextColor(Color.parseColor("#776E6E"));
                    ThursdayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);

                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);

                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);

                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);

                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);

                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);
                }
            }
        });



        FridayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(t_dayDB!=null) {

                    t_dayDB = t_weekDB.getDays().getDay5();
                    dayNum = 5;
                    if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {
                        noDataAssiened.setVisibility(View.VISIBLE);

                        FridayTab.setTextSize(15);
                        FridayTab.setTextColor(Color.parseColor("#776E6E"));
                        FridayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);


                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);


                    } else {

                        noDataAssiened.setVisibility(View.GONE);
                        StartVideoBtn.setVisibility(View.VISIBLE);
                        EndVideoBtn.setVisibility(View.VISIBLE);
                        daysVideoLayout.setVisibility(View.VISIBLE);
                        DecriptionView.setVisibility(View.VISIBLE);

                        FridayTab.setTextSize(15);
                        FridayTab.setTextColor(Color.parseColor("#776E6E"));
                        FridayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);

String descriptionBullets = String.valueOf(Html.fromHtml(t_dayDB.getDayDescription()));



                    int greenColorValue = Color.parseColor("#e3ab12");
                    SpannableString string = new SpannableString(descriptionBullets);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        string.setSpan(new BulletSpan(10, greenColorValue, 20), 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        description.setText(string);
                    }else{
                        description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                    }

//description.setText(Html.fromHtml("<ul><li  style=\"color:#e3ab12;\"><span style=\"color:#000;\">&nbsp;Demo W_Video point 1</span></li><li  style=\"color:#e3ab12;\"><span style=\"color:#000;\">&nbsp;Demo W_Video point 2</span></li><li  style=\"color:#e3ab12;\"><span style=\"color:#000;\">&nbsp;Demo W_Video point 3</span></li></ul>"));


                        try {
                            if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                StartVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                StartVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                EndVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                EndVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getStartVideo().getImagePath() != null) {
                                loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                            }
                            if (t_dayDB.getEndVideo().getImagePath() != null) {

                                loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                            }
                            if (t_dayDB.getStartVideo().getTitle() != null) {
                                startTitle.setText(t_dayDB.getStartVideo().getTitle());
                            }
                            if (t_dayDB.getEndVideo().getTitle() != null) {
                                endTitle.setText(t_dayDB.getEndVideo().getTitle());
                            }
                            if(t_dayDB.getDayDescription()!=null){
                                DecriptionView.setVisibility(View.VISIBLE);
                                description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                            }else{
                                DecriptionView.setVisibility(View.GONE);
                            }
                            mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, weekId);
                            mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                            //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                            //This is the code to provide a sectioned grid
                            List<SectionedGridRecyclerViewAdapter.Section> sections =
                                    new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

                            //Sections
for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                            if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("hemVideo")) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Hemmaträning"));
                                break;
                            }
                        }

                        for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                            if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("gymVideo")) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Gymträning"));
                                break;
                            }
                        }

                        for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                            if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("outVideo")) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Uteträning"));
                                break;
                            }
                        }



                            List<String> setionNamelist = new ArrayList<String>();
                            Set<String> setionName = new TreeSet<String>();
                            for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                            }
                            setionNamelist.addAll(setionName);

                            for (int j = 0; j < setionNamelist.size(); j++) {
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                        break;
                                    }
                                }
                            }


                            //Add your adapter to the sectionAdapter
                            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                    SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                            mSectionedAdapter.setSections(sections.toArray(dummy));

                            //Apply this adapter to the RecyclerView
                            mTrainingRecyclerView.setAdapter(mSectionedAdapter);

                        } catch (Exception e) {

                        }
                    }
                }else{
                    noDataAssiened.setVisibility(View.VISIBLE);

                    FridayTab.setTextSize(15);
                    FridayTab.setTextColor(Color.parseColor("#776E6E"));
                    FridayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);

                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);

                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);

                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);

                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);

                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);


                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }
            }
        });


        SaturdayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(t_dayDB!=null) {

                    t_dayDB = t_weekDB.getDays().getDay6();
                    dayNum = 6;
                    if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

                        noDataAssiened.setVisibility(View.VISIBLE);

                        SaturdayTab.setTextSize(15);
                        SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                        SaturdayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);

                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);


                    } else {

                        noDataAssiened.setVisibility(View.GONE);

                        StartVideoBtn.setVisibility(View.VISIBLE);
                        EndVideoBtn.setVisibility(View.VISIBLE);
                        daysVideoLayout.setVisibility(View.VISIBLE);
                        DecriptionView.setVisibility(View.VISIBLE);

                        SaturdayTab.setTextSize(15);
                        SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                        SaturdayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SundayTab.setTextSize(8);
                        SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SundayTab.setTypeface(null, Typeface.NORMAL);

                        try {
                            if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                StartVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                StartVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                EndVideoBtn.setVisibility(View.VISIBLE);
                            }else{
                                EndVideoBtn.setVisibility(View.GONE);
                            }
                            if (t_dayDB.getStartVideo().getImagePath() != null) {
                                loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                            }
                            if (t_dayDB.getEndVideo().getImagePath() != null) {

                                loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                            }
                            if (t_dayDB.getStartVideo().getTitle() != null) {
                                startTitle.setText(t_dayDB.getStartVideo().getTitle());
                            }
                            if (t_dayDB.getEndVideo().getTitle() != null) {
                                endTitle.setText(t_dayDB.getEndVideo().getTitle());
                            }
                            if(t_dayDB.getDayDescription()!=null){
                                DecriptionView.setVisibility(View.VISIBLE);
                                description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                            }else{
                                DecriptionView.setVisibility(View.GONE);
                            }
                            mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, weekId);
                            mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                            //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                            //This is the code to provide a sectioned grid
                            List<SectionedGridRecyclerViewAdapter.Section> sections =
                                    new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

                            //Sections
for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("hemVideo")) {
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Hemmaträning"));
                            break;
                        }
                    }

                    for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("gymVideo")) {
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Gymträning"));
                            break;
                        }
                    }

                    for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                        if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("outVideo")) {
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Uteträning"));
                            break;
                        }
                    }


                            List<String> setionNamelist = new ArrayList<String>();
                            Set<String> setionName = new TreeSet<String>();
                            for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                            }
                            setionNamelist.addAll(setionName);

                            for (int j = 0; j < setionNamelist.size(); j++) {
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                        break;
                                    }
                                }
                            }


                            //Add your adapter to the sectionAdapter
                            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                    SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                            mSectionedAdapter.setSections(sections.toArray(dummy));

                            //Apply this adapter to the RecyclerView
                            mTrainingRecyclerView.setAdapter(mSectionedAdapter);

                        } catch (Exception e) {

                        }
                    }
                }else{
                    noDataAssiened.setVisibility(View.VISIBLE);

                    SaturdayTab.setTextSize(15);
                    SaturdayTab.setTextColor(Color.parseColor("#776E6E"));
                    SaturdayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);

                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);

                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);

                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);

                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);

                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);

                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }
            }
        });


        SundayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(t_dayDB!=null) {

                    t_dayDB = t_weekDB.getDays().getDay7();
                    dayNum = 7;
                    if (t_dayDB.getDayactivitesList().isEmpty() && t_dayDB.getDayDescription() == null && t_dayDB.getStartVideo().getMediaVideo() == null && t_dayDB.getEndVideo().getMediaVideo() == null) {

                        noDataAssiened.setVisibility(View.VISIBLE);

                        SundayTab.setTextSize(15);
                        SundayTab.setTextColor(Color.parseColor("#776E6E"));
                        SundayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        StartVideoBtn.setVisibility(View.GONE);
                        EndVideoBtn.setVisibility(View.GONE);
                        daysVideoLayout.setVisibility(View.GONE);
                        DecriptionView.setVisibility(View.GONE);


                    } else {
                        noDataAssiened.setVisibility(View.GONE);
                        StartVideoBtn.setVisibility(View.VISIBLE);
                        EndVideoBtn.setVisibility(View.VISIBLE);
                        daysVideoLayout.setVisibility(View.VISIBLE);
                        DecriptionView.setVisibility(View.VISIBLE);

                        SundayTab.setTextSize(15);
                        SundayTab.setTextColor(Color.parseColor("#776E6E"));
                        SundayTab.setTypeface(null, Typeface.BOLD);

                        MondayTab.setTextSize(8);
                        MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        MondayTab.setTypeface(null, Typeface.NORMAL);

                        TuesdayTab.setTextSize(8);
                        TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        TuesdayTab.setTypeface(null, Typeface.NORMAL);

                        WednesdayTab.setTextSize(8);
                        WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        WednesdayTab.setTypeface(null, Typeface.NORMAL);

                        ThursdayTab.setTextSize(8);
                        ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        ThursdayTab.setTypeface(null, Typeface.NORMAL);

                        FridayTab.setTextSize(8);
                        FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        FridayTab.setTypeface(null, Typeface.NORMAL);

                        SaturdayTab.setTextSize(8);
                        SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                        SaturdayTab.setTypeface(null, Typeface.NORMAL);

                        try {
                             if (t_dayDB.getStartVideo().getMediaVideo() != null) {
                                    StartVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    StartVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getEndVideo().getMediaVideo() != null) {
                                    EndVideoBtn.setVisibility(View.VISIBLE);
                                }else{
                                    EndVideoBtn.setVisibility(View.GONE);
                                }
                                if (t_dayDB.getStartVideo().getImagePath() != null) {
                                    loadImage(startImage, t_dayDB.getStartVideo().getImagePath());
                                }
                                if (t_dayDB.getEndVideo().getImagePath() != null) {
                                   
                                    loadImage(endImage, t_dayDB.getEndVideo().getImagePath());
                                }
                                if (t_dayDB.getStartVideo().getTitle() != null) {
                                    startTitle.setText(t_dayDB.getStartVideo().getTitle());
                                }
                                if (t_dayDB.getEndVideo().getTitle() != null) {
                                    endTitle.setText(t_dayDB.getEndVideo().getTitle());
                                }
                                if(t_dayDB.getDayDescription()!=null){
                                    DecriptionView.setVisibility(View.VISIBLE);
                                    description.setText(Html.fromHtml(t_dayDB.getDayDescription()));
                                }else{
                                    DecriptionView.setVisibility(View.GONE);
                                }
                            mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIActivity.this, t_dayDB, weekId);
                            mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
                            //mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);


                            //This is the code to provide a sectioned grid
                            List<SectionedGridRecyclerViewAdapter.Section> sections =
                                    new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

                            //Sections
for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                            if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("hemVideo")) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Hemmaträning"));
                                break;
                            }
                        }

                        for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                            if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("gymVideo")) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Gymträning"));
                                break;
                            }
                        }

                        for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                            if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals("outVideo")) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, "Uteträning"));
                                break;
                            }
                        }



                            List<String> setionNamelist = new ArrayList<String>();
                            Set<String> setionName = new TreeSet<String>();
                            for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                setionName.add(t_dayDB.getDayactivitesList().get(i).getVideoType());
                            }
                            setionNamelist.addAll(setionName);

                            for (int j = 0; j < setionNamelist.size(); j++) {
                                for (int i = 0; i < t_dayDB.getDayactivitesList().size(); i++) {
                                    if (t_dayDB.getDayactivitesList().get(i).getVideoType().equals(setionNamelist.get(j))) {
                                        sections.add(new SectionedGridRecyclerViewAdapter.Section(i, t_dayDB.getDayactivitesList().get(i).getVideoType()));
                                        break;
                                    }
                                }
                            }


                            //Add your adapter to the sectionAdapter
                            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                                    SectionedGridRecyclerViewAdapter(WorkOutNewUIActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                            mSectionedAdapter.setSections(sections.toArray(dummy));

                            //Apply this adapter to the RecyclerView
                            mTrainingRecyclerView.setAdapter(mSectionedAdapter);
                        } catch (Exception e) {
                            //Toast.makeText(WorkOutNewUIActivity.this, "Wrok out Fail", Toast.LENGTH_SHORT).show();

                        }
                    }
                }else{
                    noDataAssiened.setVisibility(View.VISIBLE);

                    SundayTab.setTextSize(15);
                    SundayTab.setTextColor(Color.parseColor("#776E6E"));
                    SundayTab.setTypeface(null, Typeface.BOLD);

                    MondayTab.setTextSize(8);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    MondayTab.setTypeface(null, Typeface.NORMAL);

                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);

                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);

                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);

                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);

                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);

                    StartVideoBtn.setVisibility(View.GONE);
                    EndVideoBtn.setVisibility(View.GONE);
                    daysVideoLayout.setVisibility(View.GONE);
                    DecriptionView.setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onBackClick(View view) {

        finish();
    }


    @Override
    public void setPresenter(WeekWorkOutPresenter mPresenter) {

    }

    @Override
    public void setStartUpData(String message) {

        ProgressManager.hideProgress();
        mLayoutManager1 = new GridLayoutManager(this, 2);
        mLayoutManager1.setOrientation(GridLayoutManager.VERTICAL);



        t_weekDB = weekWorkOutPresenter.getTraingData(WorkOutNewUIActivity.this,weekId);
        t_dayDB = t_weekDB.getDays().getDay1();


        if(t_dayDB.getDayactivitesList().isEmpty()){

            MondayTab.setTextSize(15);
            MondayTab.setTextColor(Color.parseColor("#776E6E"));
            MondayTab.setTypeface(null, Typeface.BOLD);
            noDataAssiened.setVisibility(View.VISIBLE);

        }else {

            mDayTrainingAdapter =new WorkOutNewUIAdapter(WorkOutNewUIActivity.this,t_dayDB,weekId);
            mTrainingRecyclerView.setLayoutManager(mLayoutManager1);
            mTrainingRecyclerView.setAdapter(mDayTrainingAdapter);

        }

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
    public void setStartUpDataFailed(String message) {

        ProgressManager.hideProgress();
    }



    private void loadImage(final ImageView imageView, final String imageUrl) {

Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(10)
                .build();



        GlideApp
                .with(WorkOutNewUIActivity.this)
                .load(imageUrl)
                .centerInside()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")){
                            updatedImageUrl = imageUrl.replace("https", "http");
                        }else{
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);

      Picasso.get()
                .load(imageUrl)

 .transform(transformation)
                .resize(500, 500)
                .centerInside()
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")) {
                            updatedImageUrl = imageUrl.replace("https", "http");
                        } else {
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);
                    }
                });

    }
}
*/
