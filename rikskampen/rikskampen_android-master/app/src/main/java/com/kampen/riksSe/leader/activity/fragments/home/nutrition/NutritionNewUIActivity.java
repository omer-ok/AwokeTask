/*
package com.kampen.riksSe.leader.activity.fragments.home.nutrition;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.MPPointF;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.adapter.NutritionNewUIDaysAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail.RecipeVideoActivity;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail.adapter.DetailNutritionAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.IngredientsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NutritionNewUIActivity extends AppCompatActivity  implements WeekNutritionContract.View{


    private NutritionNewUIDaysAdapter mNutritionAdapterDays;
    WeekNutrition_Frag_Presenter weekNutritionFragPresenter;

    protected Typeface tfRegular;
    protected Typeface tfLight;

    private Context mContext;
    private PieChart chart;
    float Calories ,Protine,Fats;

    TextView WeekTab1,WeekTab2,WeekTab3,WeekTab4,WeekTab5,WeekTab6,WeekTab7,WeekTab8,WeekTab9,WeekTab10;
    View PreviousWeekTab1,PreviousWeekTab2,PreviousWeekTab3,PreviousWeekTab4,PreviousWeekTab5,PreviousWeekTab6,PreviousWeekTab7,PreviousWeekTab8,PreviousWeekTab9,PreviousWeekTab10;
    TextView MondayTab,TuesdayTab,WednesdayTab,ThursdayTab,FridayTab,SaturdayTab,SundayTab;
    TextView BreakFastTab,SnackTab1,LunchTab,SnackTab2,DinnerTab,SnackTab3;
    ImageView NutritionImg;
    TextView RecipeTitle,Description;
    Button morebtn,ingredientsWeeklySummaryBtn;
    View noDataAssiened;
    N_WeekDB n_weekDB;
    public N_DayDB n_daysDB;

    int weekID,dayID,selectedMealID;
    String recipeTitle,recipeVideo,recipeDes;
    List<IngredientsDB> recipeIngredient;

    SwipeRefreshLayout pullToRefresh;
    HorizontalScrollView weekScroll;
    View WeekScrollView;
    int dataSize;
    LinearLayoutManager mLayoutManager1;
    private RecyclerView  mNutritionDetailRV;

    private DetailNutritionAdapter mDetailNutritionItem;

    int weekId,weekIDScroll;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        weekIDScroll=weekId;

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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



                break;
            }

            case 10: {
                //int nY_Pos = WeekTab9.getTop();
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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                break;
            }

        }

n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,weekId);
        n_daysDB = n_weekDB.getDays().getDay1();

        if(n_daysDB.getDayactivitesList().isEmpty()){


            MondayTab.setTextSize(15);
            MondayTab.setTextColor(Color.parseColor("#000000"));
            MondayTab.setTypeface(null, Typeface.BOLD);
            BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
            BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));
            noDataAssiened.setVisibility(View.VISIBLE);



        }else{
            noDataAssiened.setVisibility(View.INVISIBLE);
            MondayTab.setTextSize(15);
            MondayTab.setTextColor(Color.parseColor("#000000"));
            MondayTab.setTypeface(null, Typeface.BOLD);

            BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
            BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

            SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
            SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

            LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
            LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

            SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
            SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

            DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
            DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

            SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
            SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

            try{

                loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                Description.setMovementMethod(new ScrollingMovementMethod());

                recipeTitle = n_daysDB.getDayactivitesList().get(0).getMealType();
                recipeDes = n_daysDB.getDayactivitesList().get(0).getDescription();
                recipeVideo =n_daysDB.getDayactivitesList().get(0).getRecipesMedia();
                recipeIngredient= n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList();

                weekID = Integer.parseInt(n_weekDB.getWeekID());
                dayID =n_daysDB.getDayID();
                selectedMealID = 0;

                mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                mNutritionDetailRV.setAdapter(mDetailNutritionItem);


            }catch (Exception ex){

                MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);
                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));
                noDataAssiened.setVisibility(View.VISIBLE);
            }

        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_new);

        weekId = (Integer) getIntent().getIntExtra("selected_week",1);
        weekIDScroll=weekId;
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

        BreakFastTab = findViewById(R.id.tab_breakfast);
        SnackTab1 = findViewById(R.id.tab_snack);
        LunchTab = findViewById(R.id.tab_lunch);
        SnackTab2 = findViewById(R.id.tab_snack2);
        DinnerTab = findViewById(R.id.tab_dinner);
        SnackTab3 = findViewById(R.id.tab_snack3);

        NutritionImg = findViewById(R.id.nutrition_img);
        Description = findViewById(R.id.descriptionValue);
        RecipeTitle = findViewById(R.id.Description);
        noDataAssiened = findViewById(R.id.NoDataView);
        morebtn = findViewById(R.id.recipeMore);
        ingredientsWeeklySummaryBtn = findViewById(R.id.weeklySummary);
        weekScroll = findViewById(R.id.tabsContainerWeeks);
        WeekScrollView = findViewById(R.id.weekScrollView);
        mNutritionDetailRV=findViewById(R.id.IngrediantsList);
        chart = findViewById(R.id.chartNutritionDetail);
        mLayoutManager1 = new LinearLayoutManager(this);
        recipeIngredient = new ArrayList<IngredientsDB>();



        weekNutritionFragPresenter = new WeekNutrition_Frag_Presenter(NutritionNewUIActivity.this,getApplicationContext());
       // List<N_WeekDB> nutritionWeekData=weekNutritionFragPresenter.getNutritionWeekData(NutritionNewUIActivity.this);



        pullToRefresh = findViewById(R.id.pullToRefresh);
 pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


ProgressManager.showProgress(NutritionNewUIActivity.this,"Pulling data Please Wait...");

                MainLeaderActivity activity= new MainLeaderActivity();

                activity.getAllDataCallFromFragment();

                ProgressManager.showProgress(NutritionNewUIActivity.this,"Pulling Data...");
                weekNutritionFragPresenter.getAllStartUpData(NutritionNewUIActivity.this);




            }
        });


if(NotificationString.equals("nutrition")){

            ProgressManager.showProgress(NutritionNewUIActivity.this,"Pulling Data...");
            weekNutritionFragPresenter.getAllStartUpData(NutritionNewUIActivity.this);
        }


        ArrayList<N_WeekDB> n_weekDBArrayList = new ArrayList<>();
        for(int i =1; i<=10;i++){

            N_WeekDB weekDB =  weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,i);
            n_weekDBArrayList.add(weekDB);
        }




        for(int j=0; j<n_weekDBArrayList.size();j++){

            if(n_weekDBArrayList.get(j).getCurrentWeek().equals("past")){

                if(n_weekDBArrayList.get(j).getWeekID().equals("1")){
                    PreviousWeekTab1.setVisibility(View.VISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("2")){
                    PreviousWeekTab2.setVisibility(View.VISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("3")){
                    PreviousWeekTab3.setVisibility(View.VISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("4")){
                    PreviousWeekTab4.setVisibility(View.VISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("5")){
                    PreviousWeekTab5.setVisibility(View.VISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("6")){
                    PreviousWeekTab6.setVisibility(View.VISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("7")){
                    PreviousWeekTab7.setVisibility(View.VISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("8")){
                    PreviousWeekTab8.setVisibility(View.VISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("9")){
                    PreviousWeekTab9.setVisibility(View.VISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("10")){
                    PreviousWeekTab10.setVisibility(View.VISIBLE);
                }


            }else if(n_weekDBArrayList.get(j).getCurrentWeek().equals("present")){


                if(n_weekDBArrayList.get(j).getWeekID().equals("1")){
                    PreviousWeekTab1.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("2")){
                    PreviousWeekTab2.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("3")){
                    PreviousWeekTab3.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("4")){
                    PreviousWeekTab4.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("5")){
                    PreviousWeekTab5.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("6")){
                    PreviousWeekTab6.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("7")){
                    PreviousWeekTab7.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("8")){
                    PreviousWeekTab8.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("9")){
                    PreviousWeekTab9.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("10")){
                    PreviousWeekTab10.setVisibility(View.GONE);
                }
            }
            else{

                if(n_weekDBArrayList.get(j).getWeekID().equals("1")){
                    PreviousWeekTab1.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("2")){
                    PreviousWeekTab2.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("3")){
                    PreviousWeekTab3.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("4")){
                    PreviousWeekTab4.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("5")){
                    PreviousWeekTab5.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("6")){
                    PreviousWeekTab6.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("7")){
                    PreviousWeekTab7.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("8")){
                    PreviousWeekTab8.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("9")){
                    PreviousWeekTab9.setVisibility(View.INVISIBLE);
                }else if(n_weekDBArrayList.get(j).getWeekID().equals("10")){
                    PreviousWeekTab10.setVisibility(View.GONE);
                }
            }
        }


        switch (weekIDScroll) {
            case 1: {
                int x = WeekTab1.getLeft();
                int y = WeekTab1.getTop();
                onFocusScroll(x,y);

 if(n_weekDB.getCurrentWeek().equals("past")){

                    PreviousWeekTab1.setVisibility(View.VISIBLE);

                }else if(n_weekDB.getCurrentWeek().equals("present")){
                    PreviousWeekTab1.setVisibility(View.INVISIBLE);
                }
                else{

                    PreviousWeekTab1.setVisibility(View.INVISIBLE);
                }



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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



                break;
            }
            case 2: {

                int x = WeekTab2.getLeft();
                int y = WeekTab2.getTop();
                onFocusScroll(x,y);
if(n_weekDB.getCurrentWeek().equals("past")){

                    PreviousWeekTab2.setVisibility(View.VISIBLE);

                }else if(n_weekDB.getCurrentWeek().equals("present")){
                    PreviousWeekTab2.setVisibility(View.INVISIBLE);
                }
                else{
                    PreviousWeekTab2.setVisibility(View.INVISIBLE);


                }

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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                break;
            }

            case 3: {

                int x = WeekTab3.getLeft();
                int y = WeekTab3.getTop();
                onFocusScroll(x,y);

if(n_weekDB.getCurrentWeek().equals("past")){

                    PreviousWeekTab3.setVisibility(View.VISIBLE);

                }else if(n_weekDB.getCurrentWeek().equals("present")){
                    PreviousWeekTab3.setVisibility(View.INVISIBLE);
                }
                else{
                    PreviousWeekTab3.setVisibility(View.INVISIBLE);
                }


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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



                break;
            }

            case 4: {
                int x = WeekTab4.getLeft();
                int y = WeekTab4.getTop();
                onFocusScroll(x,y);
                if(n_weekDB.getCurrentWeek().equals("past")){


                    PreviousWeekTab4.setVisibility(View.VISIBLE);

                }else if(n_weekDB.getCurrentWeek().equals("present")){
                    PreviousWeekTab4.setVisibility(View.INVISIBLE);
                }
                else{

                    PreviousWeekTab4.setVisibility(View.INVISIBLE);

                }


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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



                break;
            }

            case 5: {

                int x = WeekTab5.getLeft();
                int y = WeekTab5.getTop();
                onFocusScroll(x,y);

 if(n_weekDB.getCurrentWeek().equals("past")){


                    PreviousWeekTab5.setVisibility(View.VISIBLE);

                }else if(n_weekDB.getCurrentWeek().equals("present")){
                    PreviousWeekTab5.setVisibility(View.INVISIBLE);
                }
                else{

                    PreviousWeekTab5.setVisibility(View.INVISIBLE);

                }


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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



                break;
            }

            case 6: {
                int x = WeekTab6.getLeft();
                int y = WeekTab6.getTop();
                onFocusScroll(x,y);

if(n_weekDB.getCurrentWeek().equals("past")){


                    PreviousWeekTab6.setVisibility(View.VISIBLE);

                }else if(n_weekDB.getCurrentWeek().equals("present")){
                    PreviousWeekTab6.setVisibility(View.INVISIBLE);
                }
                else{
                    PreviousWeekTab6.setVisibility(View.INVISIBLE);

                }


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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                break;
            }

            case 7: {
                int x = WeekTab7.getLeft();
                int y = WeekTab7.getTop();
                onFocusScroll(x,y);

if(n_weekDB.getCurrentWeek().equals("past")){


                    PreviousWeekTab7.setVisibility(View.VISIBLE);

                }else if(n_weekDB.getCurrentWeek().equals("present")){
                    PreviousWeekTab7.setVisibility(View.INVISIBLE);
                }
                else{
                    PreviousWeekTab7.setVisibility(View.INVISIBLE);

                }



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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



                break;
            }

            case 8: {
                int x = WeekTab8.getLeft();
                int y = WeekTab8.getTop();
                onFocusScroll(x,y);

if(n_weekDB.getCurrentWeek().equals("past")){


                    PreviousWeekTab8.setVisibility(View.VISIBLE);

                }else if(n_weekDB.getCurrentWeek().equals("present")){
                    PreviousWeekTab8.setVisibility(View.INVISIBLE);
                }
                else{
                    PreviousWeekTab8.setVisibility(View.INVISIBLE);

                }


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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



                break;
            }

            case 9: {
                int x = WeekTab9.getLeft();
                int y = WeekTab9.getTop();
                onFocusScroll(x,y);

if(n_weekDB.getCurrentWeek().equals("past")){

                    PreviousWeekTab9.setVisibility(View.VISIBLE);

                }else if(n_weekDB.getCurrentWeek().equals("present")){
                    PreviousWeekTab9.setVisibility(View.INVISIBLE);
                }
                else{
                    PreviousWeekTab9.setVisibility(View.INVISIBLE);

                }

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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));



                break;
            }

            case 10: {
                //int nY_Pos = WeekTab9.getTop();
                int x = WeekTab10.getLeft();
                int y = WeekTab10.getTop();
                onFocusScroll(x,y);


if(n_weekDB.getCurrentWeek().equals("past")){

                    PreviousWeekTab10.setVisibility(View.VISIBLE);

                }else if(n_weekDB.getCurrentWeek().equals("present")){
                    PreviousWeekTab10.setVisibility(View.INVISIBLE);
                }
                else{
                    PreviousWeekTab10.setVisibility(View.INVISIBLE);

                }


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

                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                break;
            }

        }




        n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,weekId);
        //n_daysDB = n_weekDB.getDays().getDay1();

        if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
            n_daysDB = n_weekDB.getDays().getDay1();
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

        }else if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
            n_daysDB = n_weekDB.getDays().getDay2();
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

        }else if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
            n_daysDB = n_weekDB.getDays().getDay3();
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
        }else if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
            n_daysDB = n_weekDB.getDays().getDay4();
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

        }else if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
            n_daysDB = n_weekDB.getDays().getDay5();
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

        }else if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
            n_daysDB = n_weekDB.getDays().getDay6();
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

        }else if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
            n_daysDB = n_weekDB.getDays().getDay7();
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
            n_daysDB = n_weekDB.getDays().getDay1();
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

        if(n_daysDB.getDayactivitesList().isEmpty()){


MondayTab.setTextSize(15);
            MondayTab.setTextColor(Color.parseColor("#000000"));
            MondayTab.setTypeface(null, Typeface.BOLD);

            BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
            BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));
            noDataAssiened.setVisibility(View.VISIBLE);



        }else{
            noDataAssiened.setVisibility(View.INVISIBLE);
MondayTab.setTextSize(15);
            MondayTab.setTextColor(Color.parseColor("#000000"));
            MondayTab.setTypeface(null, Typeface.BOLD);


            BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
            BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

            SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
            SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

            LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
            LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

            SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
            SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

            DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
            DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

            SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
            SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

            try{

                if(n_daysDB.getDayNutritionList().get(0).getRecipesMedia()==null)
                {
                    morebtn.setVisibility(View.INVISIBLE);
                }else{
                    morebtn.setVisibility(View.VISIBLE);
                }

                loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                Description.setMovementMethod(new ScrollingMovementMethod());

                recipeTitle = n_daysDB.getDayactivitesList().get(0).getMealType();
                recipeDes = n_daysDB.getDayactivitesList().get(0).getDescription();
                recipeVideo =n_daysDB.getDayactivitesList().get(0).getRecipesMedia();
                recipeIngredient= n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList();

                weekID = Integer.parseInt(n_weekDB.getWeekID());
                dayID =n_daysDB.getDayID();
                selectedMealID = 0;

                mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                mNutritionDetailRV.setAdapter(mDetailNutritionItem);


            }catch (Exception ex){

                MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#776E6E"));
                MondayTab.setTypeface(null, Typeface.BOLD);
                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));
                noDataAssiened.setVisibility(View.VISIBLE);
            }

        }

        WeekTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,1);
                //n_daysDB = n_weekDB.getDays().getDay1();

                if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay1();
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

                }else if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay2();
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

                }else if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay3();
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
                }else if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay4();
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

                }else if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay5();
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

                }else if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay6();
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

                }else if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay7();
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
                    n_daysDB = n_weekDB.getDays().getDay1();
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


                if(n_daysDB.getDayactivitesList().isEmpty()){

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    noDataAssiened.setVisibility(View.VISIBLE);

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
                    WeekTab9.setTextColor(Color.parseColor("#ffb3b3b3"));
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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());


                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }




            }
        });

        WeekTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,2);
                //n_daysDB = n_weekDB.getDays().getDay1();

                if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay1();
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

                }else if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay2();
                    TuesdayTab.setTextSize(15);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay3();
                    WednesdayTab.setTextSize(15);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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
                }else if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay4();
                    ThursdayTab.setTextSize(15);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay5();
                    FridayTab.setTextSize(15);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay6();
                    SaturdayTab.setTextSize(15);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay7();
                    SundayTab.setTextSize(15);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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
                    n_daysDB = n_weekDB.getDays().getDay1();
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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


                if(n_daysDB.getDayactivitesList().isEmpty()){

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    noDataAssiened.setVisibility(View.VISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

  Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }




            }
        });


        WeekTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,3);
                //n_daysDB = n_weekDB.getDays().getDay1();

                if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay1();
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

                }else if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay2();
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

                }else if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay3();
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
                }else if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay4();
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

                }else if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay5();
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

                }else if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay6();
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

                }else if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay7();
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
                    n_daysDB = n_weekDB.getDays().getDay1();
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


                if(n_daysDB.getDayactivitesList().isEmpty()){

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    noDataAssiened.setVisibility(View.VISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }


                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

    Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }




            }
        });

        WeekTab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,4);
                //n_daysDB = n_weekDB.getDays().getDay1();


                if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay1();
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

                }else if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay2();
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

                }else if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay3();
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
                }else if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay4();
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

                }else if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay5();
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

                }else if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay6();
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

                }else if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay7();
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
                    n_daysDB = n_weekDB.getDays().getDay1();
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




                if(n_daysDB.getDayactivitesList().isEmpty()){

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    noDataAssiened.setVisibility(View.VISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }




            }
        });


        WeekTab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,5);
                //n_daysDB = n_weekDB.getDays().getDay1();


                if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay1();
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

                }else if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay2();
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

                }else if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay3();
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
                }else if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay4();
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

                }else if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay5();
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

                }else if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay6();
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

                }else if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay7();
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
                    n_daysDB = n_weekDB.getDays().getDay1();
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

                if(n_daysDB.getDayactivitesList().isEmpty()){

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    noDataAssiened.setVisibility(View.VISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }
                
            }
        });


        WeekTab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,6);
                //n_daysDB = n_weekDB.getDays().getDay1();

                if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay1();
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

                }else if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay2();
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

                }else if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay3();
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
                }else if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay4();
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

                }else if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay5();
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

                }else if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay6();
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

                }else if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay7();
                    SundayTab.setTextSize(15);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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
                    n_daysDB = n_weekDB.getDays().getDay1();
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                if(n_daysDB.getDayactivitesList().isEmpty()){

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    noDataAssiened.setVisibility(View.VISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

 Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }




            }
        });


        WeekTab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,7);
                //n_daysDB = n_weekDB.getDays().getDay1();


                if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay1();
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay2();
                    TuesdayTab.setTextSize(15);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay3();
                    WednesdayTab.setTextSize(15);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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
                }else if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay4();
                    ThursdayTab.setTextSize(15);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay5();
                    FridayTab.setTextSize(15);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay6();
                    SaturdayTab.setTextSize(15);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay7();
                    SundayTab.setTextSize(15);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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
                    n_daysDB = n_weekDB.getDays().getDay1();
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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


                if(n_daysDB.getDayactivitesList().isEmpty()){

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    noDataAssiened.setVisibility(View.VISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }
                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

  Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }




            }
        });


        WeekTab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,8);
                //n_daysDB = n_weekDB.getDays().getDay1();


                if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay1();
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay2();
                    TuesdayTab.setTextSize(15);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay3();
                    WednesdayTab.setTextSize(15);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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
                }else if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay4();
                    ThursdayTab.setTextSize(15);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay5();
                    FridayTab.setTextSize(15);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay6();
                    SaturdayTab.setTextSize(15);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay7();
                    SundayTab.setTextSize(15);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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
                    n_daysDB = n_weekDB.getDays().getDay1();
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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



                if(n_daysDB.getDayactivitesList().isEmpty()){
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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    noDataAssiened.setVisibility(View.VISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

  Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }




            }
        });


        WeekTab9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,9);
                //n_daysDB = n_weekDB.getDays().getDay1();

                if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay1();
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay2();
                    TuesdayTab.setTextSize(15);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay3();
                    WednesdayTab.setTextSize(15);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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
                }else if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay4();
                    ThursdayTab.setTextSize(15);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay5();
                    FridayTab.setTextSize(15);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay6();
                    SaturdayTab.setTextSize(15);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay7();
                    SundayTab.setTextSize(15);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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
                    n_daysDB = n_weekDB.getDays().getDay1();
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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


                if(n_daysDB.getDayactivitesList().isEmpty()){

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

 MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#000000"));
                    MondayTab.setTypeface(null, Typeface.BOLD);

                    TuesdayTab.setTextSize(8);
                    TuesdayTab.setTextColor(Color.parseColor("#BB9767"));
                    TuesdayTab.setTypeface(null, Typeface.NORMAL);

                    WednesdayTab.setTextSize(8);
                    WednesdayTab.setTextColor(Color.parseColor("#BB9767"));
                    WednesdayTab.setTypeface(null, Typeface.NORMAL);

                    ThursdayTab.setTextSize(8);
                    ThursdayTab.setTextColor(Color.parseColor("#BB9767"));
                    ThursdayTab.setTypeface(null, Typeface.NORMAL);

                    FridayTab.setTextSize(8);
                    FridayTab.setTextColor(Color.parseColor("#BB9767"));
                    FridayTab.setTypeface(null, Typeface.NORMAL);

                    SaturdayTab.setTextSize(8);
                    SaturdayTab.setTextColor(Color.parseColor("#BB9767"));
                    SaturdayTab.setTypeface(null, Typeface.NORMAL);

                    SundayTab.setTextSize(8);
                    SundayTab.setTextColor(Color.parseColor("#BB9767"));
                    SundayTab.setTypeface(null, Typeface.NORMAL);


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    noDataAssiened.setVisibility(View.VISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

 Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }




            }
        });

        WeekTab10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,10);
                //n_daysDB = n_weekDB.getDays().getDay1();


                if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay1();
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay2();
                    TuesdayTab.setTextSize(15);
                    TuesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay3();
                    WednesdayTab.setTextSize(15);
                    WednesdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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
                }else if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay4();
                    ThursdayTab.setTextSize(15);
                    ThursdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay5();
                    FridayTab.setTextSize(15);
                    FridayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay6();
                    SaturdayTab.setTextSize(15);
                    SaturdayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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

                }else if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                    n_daysDB = n_weekDB.getDays().getDay7();
                    SundayTab.setTextSize(15);
                    SundayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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
                    n_daysDB = n_weekDB.getDays().getDay1();
                    MondayTab.setTextSize(15);
                    MondayTab.setTextColor(Color.parseColor("#ffb3b3b3"));
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


                if(n_daysDB.getDayactivitesList().isEmpty()){

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    noDataAssiened.setVisibility(View.VISIBLE);

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




                    WeekTab2.setBackgroundResource(R.drawable.tab_middle_unselected);
                    WeekTab3.setBackgroundResource(R.drawable.tab_middle_unselected);
                    WeekTab4.setBackgroundResource(R.drawable.tab_middle_unselected);
                    WeekTab5.setBackgroundResource(R.drawable.tab_middle_unselected);
                    WeekTab6.setBackgroundResource(R.drawable.tab_middle_unselected);
                    WeekTab7.setBackgroundResource(R.drawable.tab_middle_unselected);
                    WeekTab8.setBackgroundResource(R.drawable.tab_middle_unselected);
                    WeekTab9.setBackgroundResource(R.drawable.tab_middle_unselected);
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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

  Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }




            }
        });
        
        MondayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_daysDB = n_weekDB.getDays().getDay1();

                if(n_daysDB.getDayactivitesList().isEmpty()){

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

                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);

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

                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());
                    }

                    init();


                }

            }
        });


        TuesdayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_daysDB = n_weekDB.getDays().getDay2();

                if(n_daysDB.getDayactivitesList().isEmpty()){
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

                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);
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

                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

 Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();

                }

            }
        });


        WednesdayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_daysDB = n_weekDB.getDays().getDay3();

                if(n_daysDB.getDayactivitesList().isEmpty()){

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

                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

Calories=0;
                    Protine=0;
                    Fats=0;

                    for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                        Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }

            }
        });


        ThursdayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_daysDB = n_weekDB.getDays().getDay4();

                if(n_daysDB.getDayactivitesList().isEmpty()){

                    noDataAssiened.setVisibility(View.VISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                }else {

                    noDataAssiened.setVisibility(View.INVISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg, n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

 Calories=0;
                    Protine=0;
                    Fats=0;

                    for (int i = 0; i < n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size(); i++) {

                        Calories += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();

                }
            }
        });

        FridayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_daysDB = n_weekDB.getDays().getDay4();

                if(n_daysDB.getDayactivitesList().isEmpty()){
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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));




                }else {

                    noDataAssiened.setVisibility(View.INVISIBLE);
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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg, n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());


                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

Calories=0;
                    Protine=0;
                    Fats=0;

                    for (int i = 0; i < n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size(); i++) {

                        Calories += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();

                }
            }
        });

        SaturdayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n_daysDB = n_weekDB.getDays().getDay4();

                if(n_daysDB.getDayactivitesList().isEmpty()){

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                }else{

                    noDataAssiened.setVisibility(View.INVISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg, n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

Calories=0;
                    Protine=0;
                    Fats=0;

                    for (int i = 0; i < n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size(); i++) {

                        Calories += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();

                }
            }
        });


        SundayTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                n_daysDB = n_weekDB.getDays().getDay4();

                if(n_daysDB.getDayactivitesList().isEmpty()){
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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                }else {
                    noDataAssiened.setVisibility(View.INVISIBLE);

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


                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }

                    loadImage(NutritionImg, n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

for (int i = 0; i < n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size(); i++) {

                        Calories += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();

                }
            }
        });

        morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weekID = Integer.parseInt(n_weekDB.getWeekID());
                dayID =n_daysDB.getDayID();

                if(n_daysDB.getDayactivitesList().isEmpty()){

                    MyApplication.showSimpleSnackBar(NutritionNewUIActivity.this,"Recipe Details Not available");
                }
                else {

                    weekIDScroll=weekID;
                    Intent intent = new Intent(NutritionNewUIActivity.this, RecipeVideoActivity.class);
                    intent.putExtra("WeekID",weekID);
                    intent.putExtra("DayID",dayID);
                    intent.putExtra("MealID",selectedMealID);
                    //intent.putExtra("Ingrediants", (Serializable) recipeIngredient);
                    startActivity(intent);
                    finish();
                }
            }
        });

        ingredientsWeeklySummaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weekID = Integer.parseInt(n_weekDB.getWeekID());

                if(n_weekDB.getWeeklyIngredientsList().size()<=0){

                    MyApplication.showSimpleSnackBar(NutritionNewUIActivity.this,"Recipe Details Not available");
                }
                else {
                    weekIDScroll=weekID;
                    Intent intent = new Intent(NutritionNewUIActivity.this, IngredientsWeeklySummary.class);
                    intent.putExtra("WeekID",weekID);
                    startActivity(intent);
                    finish();
                }
    }
        });

        BreakFastTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(n_daysDB.getDayactivitesList().isEmpty()){

                    noDataAssiened.setVisibility(View.VISIBLE);

                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                }else{
                    noDataAssiened.setVisibility(View.INVISIBLE);
                    BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                    BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                    loadImage(NutritionImg, n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                    RecipeTitle.setText(n_daysDB.getDayactivitesList().get(0).getTitle());
                    Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                    Description.setMovementMethod(new ScrollingMovementMethod());

                    weekID = Integer.parseInt(n_weekDB.getWeekID());
                    dayID =n_daysDB.getDayID();
                    selectedMealID = 0;

                    if(n_daysDB.getDayactivitesList().get(0).getRecipesMedia()==null)
                    {
                        morebtn.setVisibility(View.INVISIBLE);
                    }else{
                        morebtn.setVisibility(View.VISIBLE);
                    }
                    recipeTitle = n_daysDB.getDayactivitesList().get(0).getMealType();
                    recipeDes = n_daysDB.getDayactivitesList().get(0).getDescription();
                    recipeVideo =n_daysDB.getDayactivitesList().get(0).getRecipesMedia();
                    recipeIngredient= n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList();



                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

 Calories=0;
                    Protine=0;
                    Fats=0;

                    for (int i = 0; i < n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size(); i++) {

                        Calories += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                        Protine += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                        Fats += Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                    }

                    init();


                }


            }
        });


        SnackTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    if(n_daysDB.getDayactivitesList().isEmpty() ){

                        noDataAssiened.setVisibility(View.VISIBLE);

                        SnackTab1.setBackgroundResource(R.drawable.tab_middle_selected);
                        SnackTab1.setTextColor(Color.parseColor("#FFFFFF"));

                        BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                        DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                    }else{

                        noDataAssiened.setVisibility(View.INVISIBLE);
                        SnackTab1.setBackgroundResource(R.drawable.tab_middle_selected);
                        SnackTab1.setTextColor(Color.parseColor("#FFFFFF"));

                        BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                        DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                        try{

                            if(n_daysDB.getDayactivitesList().get(1).getRecipesMedia()==null)
                            {
                                morebtn.setVisibility(View.INVISIBLE);
                            }else{
                                morebtn.setVisibility(View.VISIBLE);
                            }
                            recipeTitle = n_daysDB.getDayactivitesList().get(1).getMealType();
                            RecipeTitle.setText(n_daysDB.getDayactivitesList().get(1).getTitle());
                            recipeDes = n_daysDB.getDayactivitesList().get(1).getDescription();
                            recipeVideo =n_daysDB.getDayactivitesList().get(1).getRecipesMedia();
                            recipeIngredient= n_daysDB.getDayactivitesList().get(1).getIngredientsArrayList();

                            loadImage(NutritionImg, n_daysDB.getDayactivitesList().get(1).getMediaUrl());
                            Description.setText(n_daysDB.getDayactivitesList().get(1).getDescription());
                            Description.setMovementMethod(new ScrollingMovementMethod());

                            weekID = Integer.parseInt(n_weekDB.getWeekID());
                            dayID =n_daysDB.getDayID();
                            selectedMealID = 1;



                            mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                            mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(1).getIngredientsArrayList());

                            mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                            mNutritionDetailRV.setAdapter(mDetailNutritionItem);



Calories=0;
                            Protine=0;
                            Fats=0;

                            for (int i = 0; i < n_daysDB.getDayactivitesList().get(1).getIngredientsArrayList().size(); i++) {

                                Calories += Float.parseFloat(n_daysDB.getDayactivitesList().get(1).getIngredientsArrayList().get(i).getCalories());
                                Protine += Float.parseFloat(n_daysDB.getDayactivitesList().get(1).getIngredientsArrayList().get(i).getProtein());
                                Fats += Float.parseFloat(n_daysDB.getDayactivitesList().get(1).getIngredientsArrayList().get(i).getFats());

                            }

                            init();



                        }catch (Exception ex){

                            noDataAssiened.setVisibility(View.VISIBLE);

                            SnackTab1.setBackgroundResource(R.drawable.tab_middle_selected);
                            SnackTab1.setTextColor(Color.parseColor("#FFFFFF"));

                            BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                            LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                            SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                            DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                            SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                        }


                    }





            }
        });
        
        LunchTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    if (n_daysDB.getDayactivitesList().isEmpty()
|| n_daysDB.getDayactivitesList().get(2).getMealType() != "Lunch"
) {


                        noDataAssiened.setVisibility(View.VISIBLE);

                        LunchTab.setBackgroundResource(R.drawable.tab_middle_selected);
                        LunchTab.setTextColor(Color.parseColor("#FFFFFF"));

                        BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                        DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                    } else {

                        noDataAssiened.setVisibility(View.INVISIBLE);
                        LunchTab.setBackgroundResource(R.drawable.tab_middle_selected);
                        LunchTab.setTextColor(Color.parseColor("#FFFFFF"));

                        BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                        DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                        try {

                            if(n_daysDB.getDayactivitesList().get(2).getRecipesMedia()==null)
                            {
                                morebtn.setVisibility(View.INVISIBLE);
                            }else{
                                morebtn.setVisibility(View.VISIBLE);
                            }

                            recipeTitle = n_daysDB.getDayactivitesList().get(2).getMealType();
                            RecipeTitle.setText(n_daysDB.getDayactivitesList().get(2).getTitle());
                            recipeDes = n_daysDB.getDayactivitesList().get(2).getDescription();
                            recipeVideo = n_daysDB.getDayactivitesList().get(2).getRecipesMedia();
                            recipeIngredient = n_daysDB.getDayactivitesList().get(2).getIngredientsArrayList();

                            loadImage(NutritionImg, n_daysDB.getDayactivitesList().get(2).getMediaUrl());
                            Description.setText(n_daysDB.getDayactivitesList().get(2).getDescription());
                            Description.setMovementMethod(new ScrollingMovementMethod());

                            weekID = Integer.parseInt(n_weekDB.getWeekID());
                            dayID = n_daysDB.getDayID();
                            selectedMealID = 2;



                            mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                            mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(2).getIngredientsArrayList());

                            mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                            mNutritionDetailRV.setAdapter(mDetailNutritionItem);

 Calories=0;
                            Protine=0;
                            Fats=0;

                            for (int i = 0; i < n_daysDB.getDayactivitesList().get(2).getIngredientsArrayList().size(); i++) {

                                Calories += Float.parseFloat(n_daysDB.getDayactivitesList().get(2).getIngredientsArrayList().get(i).getCalories());
                                Protine += Float.parseFloat(n_daysDB.getDayactivitesList().get(2).getIngredientsArrayList().get(i).getProtein());
                                Fats += Float.parseFloat(n_daysDB.getDayactivitesList().get(2).getIngredientsArrayList().get(i).getFats());

                            }

                            init();



                        } catch (Exception ex) {

                            noDataAssiened.setVisibility(View.VISIBLE);

                            LunchTab.setBackgroundResource(R.drawable.tab_middle_selected);
                            LunchTab.setTextColor(Color.parseColor("#FFFFFF"));

                            BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                            SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                            SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                            DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                            SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));
                        }


                    }


                
                
            }
        });
        
        
        SnackTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    if (n_daysDB.getDayactivitesList().isEmpty()
|| n_daysDB.getDayactivitesList().get(3).getMealType() != "Snacks"
) {

                        noDataAssiened.setVisibility(View.VISIBLE);

                        SnackTab2.setBackgroundResource(R.drawable.tab_middle_selected);
                        SnackTab2.setTextColor(Color.parseColor("#FFFFFF"));

                        BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                        LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                    } else {

                        noDataAssiened.setVisibility(View.INVISIBLE);
                        SnackTab2.setBackgroundResource(R.drawable.tab_middle_selected);
                        SnackTab2.setTextColor(Color.parseColor("#FFFFFF"));

                        BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                        LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                        try {

                            if(n_daysDB.getDayactivitesList().get(3).getRecipesMedia()==null)
                            {
                                morebtn.setVisibility(View.INVISIBLE);
                            }else{
                                morebtn.setVisibility(View.VISIBLE);
                            }

                            recipeTitle = n_daysDB.getDayactivitesList().get(3).getMealType();
                            RecipeTitle.setText(n_daysDB.getDayactivitesList().get(3).getTitle());
                            recipeDes = n_daysDB.getDayactivitesList().get(3).getDescription();
                            recipeVideo = n_daysDB.getDayactivitesList().get(3).getRecipesMedia();
                            recipeIngredient = n_daysDB.getDayactivitesList().get(3).getIngredientsArrayList();

                            loadImage(NutritionImg, n_daysDB.getDayactivitesList().get(3).getMediaUrl());
                            Description.setText(n_daysDB.getDayactivitesList().get(3).getDescription());
                            Description.setMovementMethod(new ScrollingMovementMethod());

                            weekID = Integer.parseInt(n_weekDB.getWeekID());
                            dayID = n_daysDB.getDayID();
                            selectedMealID = 3;



                            mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                            mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(3).getIngredientsArrayList());

                            mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                            mNutritionDetailRV.setAdapter(mDetailNutritionItem);


   Calories=0;
                            Protine=0;
                            Fats=0;

                            for (int i = 0; i < n_daysDB.getDayactivitesList().get(3).getIngredientsArrayList().size(); i++) {

                                Calories += Float.parseFloat(n_daysDB.getDayactivitesList().get(3).getIngredientsArrayList().get(i).getCalories());
                                Protine += Float.parseFloat(n_daysDB.getDayactivitesList().get(3).getIngredientsArrayList().get(i).getProtein());
                                Fats += Float.parseFloat(n_daysDB.getDayactivitesList().get(3).getIngredientsArrayList().get(i).getFats());

                            }

                            init();


                        } catch (Exception ex) {

                            noDataAssiened.setVisibility(View.VISIBLE);

                            SnackTab2.setBackgroundResource(R.drawable.tab_middle_selected);
                            SnackTab2.setTextColor(Color.parseColor("#FFFFFF"));

                            BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                            SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                            LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                            DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                            SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                        }


                    }


                
            }
        });


        DinnerTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    if (n_daysDB.getDayactivitesList().isEmpty()
|| n_daysDB.getDayactivitesList().get(4).getMealType() != "Dinner"
) {

                        noDataAssiened.setVisibility(View.VISIBLE);

                        DinnerTab.setBackgroundResource(R.drawable.tab_middle_selected);
                        DinnerTab.setTextColor(Color.parseColor("#FFFFFF"));

                        BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                        LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));


                    } else {

                        noDataAssiened.setVisibility(View.INVISIBLE);
                        DinnerTab.setBackgroundResource(R.drawable.tab_middle_selected);
                        DinnerTab.setTextColor(Color.parseColor("#FFFFFF"));

                        BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                        LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                        try {

                            if(n_daysDB.getDayactivitesList().get(4).getRecipesMedia()==null)
                            {
                                morebtn.setVisibility(View.INVISIBLE);
                            }else{
                                morebtn.setVisibility(View.VISIBLE);
                            }

                            recipeTitle = n_daysDB.getDayactivitesList().get(4).getMealType();
                            RecipeTitle.setText(n_daysDB.getDayactivitesList().get(4).getTitle());
                            recipeDes = n_daysDB.getDayactivitesList().get(4).getDescription();
                            recipeVideo = n_daysDB.getDayactivitesList().get(4).getRecipesMedia();
                            recipeIngredient = n_daysDB.getDayactivitesList().get(4).getIngredientsArrayList();

                            loadImage(NutritionImg, n_daysDB.getDayactivitesList().get(4).getMediaUrl());
                            Description.setText(n_daysDB.getDayactivitesList().get(4).getDescription());
                            Description.setMovementMethod(new ScrollingMovementMethod());

                            weekID = Integer.parseInt(n_weekDB.getWeekID());
                            dayID = n_daysDB.getDayID();
                            selectedMealID = 4;



                            mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                            mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(4).getIngredientsArrayList());

                            mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                            mNutritionDetailRV.setAdapter(mDetailNutritionItem);
Calories=0;
                            Protine=0;
                            Fats=0;

                            for (int i = 0; i < n_daysDB.getDayactivitesList().get(4).getIngredientsArrayList().size(); i++) {

                                Calories += Float.parseFloat(n_daysDB.getDayactivitesList().get(4).getIngredientsArrayList().get(i).getCalories());
                                Protine += Float.parseFloat(n_daysDB.getDayactivitesList().get(4).getIngredientsArrayList().get(i).getProtein());
                                Fats += Float.parseFloat(n_daysDB.getDayactivitesList().get(4).getIngredientsArrayList().get(i).getFats());

                            }

                            init();


                        } catch (Exception ex) {

                            noDataAssiened.setVisibility(View.VISIBLE);

                            DinnerTab.setBackgroundResource(R.drawable.tab_middle_selected);
                            DinnerTab.setTextColor(Color.parseColor("#FFFFFF"));

                            BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                            SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                            LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                            SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                            SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
                            SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

                        }


                    }

                
            }
        });
        
        
        SnackTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(n_daysDB.getDayactivitesList().isEmpty()
|| n_daysDB.getDayactivitesList().get(5).getMealType()!="Snacks"
){

                    noDataAssiened.setVisibility(View.VISIBLE);

                    SnackTab3.setBackgroundResource(R.drawable.tab_middle_selected);
                    SnackTab3.setTextColor(Color.parseColor("#FFFFFF"));

                    BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));


                }else{

                    noDataAssiened.setVisibility(View.INVISIBLE);
                    SnackTab3.setBackgroundResource(R.drawable.tab_middle_selected);
                    SnackTab3.setTextColor(Color.parseColor("#FFFFFF"));

                    BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                    LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                    SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                    DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                    DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));


                    try{

                        if(n_daysDB.getDayactivitesList().get(5).getRecipesMedia()==null)
                        {
                            morebtn.setVisibility(View.INVISIBLE);
                        }else{
                            morebtn.setVisibility(View.VISIBLE);
                        }

                        loadImage(NutritionImg, n_daysDB.getDayactivitesList().get(5).getMediaUrl());
                        RecipeTitle.setText(n_daysDB.getDayactivitesList().get(5).getTitle());
                        Description.setText(n_daysDB.getDayactivitesList().get(5).getDescription());
                        Description.setMovementMethod(new ScrollingMovementMethod());

                        recipeTitle = n_daysDB.getDayactivitesList().get(5).getMealType();
                        recipeDes = n_daysDB.getDayactivitesList().get(5).getDescription();
                        recipeVideo = n_daysDB.getDayactivitesList().get(5).getRecipesMedia();
                        recipeIngredient = n_daysDB.getDayactivitesList().get(5).getIngredientsArrayList();


                        weekID = Integer.parseInt(n_weekDB.getWeekID());
                        dayID =n_daysDB.getDayID();
                        selectedMealID = 5;





                        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                        mDetailNutritionItem = new DetailNutritionAdapter(NutritionNewUIActivity.this,n_daysDB.getDayactivitesList().get(5).getIngredientsArrayList());

                        mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                        mNutritionDetailRV.setAdapter(mDetailNutritionItem);


                        Calories=0;
                        Protine=0;
                        Fats=0;

                        for (int i = 0; i < n_daysDB.getDayactivitesList().get(5).getIngredientsArrayList().size(); i++) {

                            Calories += Float.parseFloat(n_daysDB.getDayactivitesList().get(5).getIngredientsArrayList().get(i).getCalories());
                            Protine += Float.parseFloat(n_daysDB.getDayactivitesList().get(5).getIngredientsArrayList().get(i).getProtein());
                            Fats += Float.parseFloat(n_daysDB.getDayactivitesList().get(5).getIngredientsArrayList().get(i).getFats());

                        }

                        init();



                    }catch (Exception ex){

                        noDataAssiened.setVisibility(View.VISIBLE);

                        SnackTab3.setBackgroundResource(R.drawable.tab_middle_selected);
                        SnackTab3.setTextColor(Color.parseColor("#FFFFFF"));

                        BreakFastTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        BreakFastTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

                        LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

                        SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

                        DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
                        DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));
                    }


                }

            }
        });
        




    }


    private  void init()
    {

        mContext= NutritionNewUIActivity.this;

        chart.clear();
        tfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");


        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setCenterTextTypeface(tfLight);
       //chart.setBackgroundResource(R.drawable.chart_icon);
        //chart.setCenterText(generateCenterSpannableText());

        //chart.setBackgroundResource(R.drawable.ic);

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.parseColor("#F25022"));
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);


        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.BLACK);
        chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);
        chart.setEntryLabelTextSize(8f);


        chart.postDelayed(new Runnable() {
            @Override
            public void run() {

                setData(3, 10);
            }
        },200 );
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString(" ");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 10, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 13, s.length() - 5, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 13, s.length() - 5, 0);


        s.setSpan(new RelativeSizeSpan(1f), 10, s.length() - 5, 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 0 , s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#a7a7aa")), 0 , s.length(), 0);
        return s;
    }


    private void setData(int count, float range) {

        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
 for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5), getResources().getDrawable(R.drawable.ic_about)));
        }

        //entries.add(new PieEntry((float) ((Math.random() * range) + range / 5), getResources().getDrawable(R.drawable.ic_about)));



        PieEntry pieEntry=new PieEntry(1);


        //  pieEntry.setData();

        // entries.add(new PieEntry( (float) Float.valueOf(Calories),Float.valueOf(Protine),Float.valueOf(Fats)));
        entries.add(new PieEntry((float) Calories,"Kalorier"));
        entries.add(new PieEntry((float) Fats,"Fetter"));
        entries.add(new PieEntry((float) Protine,"Proteiner"));

        if(Calories !=0 && Fats != 0 && Protine != 0 ){



        }


        PieDataSet dataSet = new PieDataSet(entries, " ");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(6f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(8f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(Color.parseColor("#ffb3b3b3"));

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());


        colors.add(Color.parseColor("#BB9567"));
        colors.add(Color.parseColor("#FFDF93"));
        colors.add(Color.parseColor("#4D4D4D"));

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(tfLight);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();


    }







    private void loadImage(final ImageView imageView, final String imageUrl){
        Picasso.get()
                .load(imageUrl)
                .into(imageView , new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")){
                            updatedImageUrl = imageUrl.replace("https", "http");
                        }else{
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);
                    }
                });
    }

    public void onFocusScroll(int x,int y){
        weekScroll.post(new Runnable() {
            @Override
            public void run() {
                weekScroll.scrollTo(x,y);

            }
        });
    }

    public void onBackClick(View view) {

        finish();

    }


    @Override
    public void setNutritions(List<WeekTrainingModel> list) {

    }

    @Override
    public void setStartUpData(String message) {

        ProgressManager.hideProgress();
       // pullToRefresh.setRefreshing(false);
        n_weekDB = weekNutritionFragPresenter.getNutritionData(NutritionNewUIActivity.this,weekId);
        n_daysDB = n_weekDB.getDays().getDay1();


        if(n_daysDB.getDayactivitesList().isEmpty()){


            MondayTab.setTextSize(15);
            MondayTab.setTextColor(Color.parseColor("#000000"));
            MondayTab.setTypeface(null, Typeface.BOLD);
            BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
            BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));
            noDataAssiened.setVisibility(View.VISIBLE);



        }else{

            MondayTab.setTextSize(15);
            MondayTab.setTextColor(Color.parseColor("#000000"));
            MondayTab.setTypeface(null, Typeface.BOLD);

            BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
            BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));

            SnackTab1.setBackgroundResource(R.drawable.tab_left_unselected_days);
            SnackTab1.setTextColor(Color.parseColor("#ffb3b3b3"));

            LunchTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
            LunchTab.setTextColor(Color.parseColor("#ffb3b3b3"));

            SnackTab2.setBackgroundResource(R.drawable.tab_left_unselected_days);
            SnackTab2.setTextColor(Color.parseColor("#ffb3b3b3"));

            DinnerTab.setBackgroundResource(R.drawable.tab_left_unselected_days);
            DinnerTab.setTextColor(Color.parseColor("#ffb3b3b3"));

            SnackTab3.setBackgroundResource(R.drawable.tab_left_unselected_days);
            SnackTab3.setTextColor(Color.parseColor("#ffb3b3b3"));

            try{

                loadImage(NutritionImg,n_daysDB.getDayactivitesList().get(0).getMediaUrl());
                Description.setText(n_daysDB.getDayactivitesList().get(0).getDescription());
                Description.setMovementMethod(new ScrollingMovementMethod());

                recipeTitle = n_daysDB.getDayactivitesList().get(0).getMealType();
                recipeDes = n_daysDB.getDayactivitesList().get(0).getDescription();
                recipeVideo =n_daysDB.getDayactivitesList().get(0).getRecipesMedia();
                recipeIngredient= n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList();

                weekID = Integer.parseInt(n_weekDB.getWeekID());
                dayID =n_daysDB.getDayID();
                selectedMealID = 0;

                for(int i=0; i<n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().size();i++){

                    Calories+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getCalories());
                    Protine+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getProtein());
                    Fats+= Float.parseFloat(n_daysDB.getDayactivitesList().get(0).getIngredientsArrayList().get(i).getFats());

                }

                init();


            }catch (Exception ex){

                MondayTab.setTextSize(15);
                MondayTab.setTextColor(Color.parseColor("#000000"));
                MondayTab.setTypeface(null, Typeface.BOLD);
                BreakFastTab.setBackgroundResource(R.drawable.tab_middle_selected);
                BreakFastTab.setTextColor(Color.parseColor("#FFFFFF"));
                noDataAssiened.setVisibility(View.VISIBLE);
            }

        }

    }

    @Override
    public void setStartUpDataFailed(String message) {

        ProgressManager.hideProgress();

    }

    @Override
    public void setPresenter(WeekNutritionContract.Presenter mPresenter) {

    }

    public void updateNotify(){


    }
}
*/
