package com.kampen.riksSe.leader.activity.fragments.home.traings.workout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.traings.adapters.WorkOutNewUIDaysAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.traings.adapters.WorkOutNewUIWeeksAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Day;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Video;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.WorkoutType;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.adapter.SectionedGridRecyclerViewAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.adapter.WorkOutNewUIAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.VideoPlayActivity;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.WorkoutMotivationVideo;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;

import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;

public class WorkOutNewUIDyActivity extends AppCompatActivity implements WeekWorkOutContract.View{


    View noDataAssiened;
    ImageView startImage,endImage;
    View StartVideoView,EndVideoView;
    NestedScrollView scrollView;
    WeekWorkOutPresenter weekWorkOutPresenter;
    View StartVideoBtn,EndVideoBtn,DecriptionView,daysVideoLayout;
    HorizontalScrollView weekScroll;
    LinearLayoutManager mLayoutManagerWeeks,mLayoutManagerDays,mLayoutManagerVideos;
    TextView descriptionTitle,description,startTitle,endTitle;
    private WorkOutNewUIWeeksAdapter workOutNewUIWeeksAdapter;
    private WorkOutNewUIDaysAdapter workOutNewUIDaysAdapter;
    private WorkOutNewUIAdapter mDayTrainingAdapter;
    public RecyclerView weekRV,dayRV,mTrainingRecyclerView;
    int CurrentWeek,CurrentDay;
    List<W_Plans> traningDataV3;
    List<WorkoutType> workoutTypeList;
    Context mContext;
    int WeekIDChange;
    long currentDay;
    long currentWeek;
    long CurrentDayID;
    int startVideoPosition;
    int endVideoPosition;
    Competition CompitionDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out_new_uidy);

        mContext = WorkOutNewUIDyActivity.this;

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
        weekRV=findViewById(R.id.WeekRV);
        dayRV=findViewById(R.id.DaysRV);
        mTrainingRecyclerView=findViewById(R.id.workoutWeekRV);
        description.setMovementMethod(new ScrollingMovementMethod());
        int WeekID = getIntent().getIntExtra("WeekID",1);
        long DayID = getIntent().getLongExtra("CurrentDayID",1);

        weekWorkOutPresenter =new WeekWorkOutPresenter(WorkOutNewUIDyActivity.this,WorkOutNewUIDyActivity.this);

        traningDataV3 = weekWorkOutPresenter.getTrainingWeek();
        workoutTypeList = weekWorkOutPresenter.getTrainingWorkOutType();

        mLayoutManagerWeeks = new LinearLayoutManager(WorkOutNewUIDyActivity.this);
        mLayoutManagerWeeks.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLayoutManagerDays = new LinearLayoutManager(WorkOutNewUIDyActivity.this);
        mLayoutManagerDays.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLayoutManagerVideos = new GridLayoutManager(this, 2);
        mLayoutManagerVideos.setOrientation(GridLayoutManager.VERTICAL);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());
        Realm_User mUser  = weekWorkOutPresenter.provideUserLocal(WorkOutNewUIDyActivity.this);
        CompitionDate = Repository.getCompitionDate();
        if(CompitionDate.getStartDate()!=null) {
            ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(currentDateandTime, convertUTCToLocalTime(CompitionDate.getStartDate()));
            currentDay = contestWeekDayTimeModel.getDays() + 1;
            currentWeek = contestWeekDayTimeModel.getWeeks() + 1;
        }
        if(WeekID==currentWeek){
            CurrentDayID = currentDay;
            WeekIDChange = (int) currentWeek;
        }else{
            CurrentDayID = DayID;
            WeekIDChange = WeekID;
        }

        if(workoutTypeList!=null && workoutTypeList.size()>0 &&  traningDataV3!=null && traningDataV3.size()>0){

            for(int i=0; i<traningDataV3.size();i++){
                if(WeekID == traningDataV3.get(i).getmWeek()){
                    CurrentWeek = i;
                    for(int j=0;j<traningDataV3.get(i).getmWDays().size();j++){
                        if(traningDataV3.get(i).getmWDays().get(j).getmDay()==CurrentDayID){
                            CurrentDay=j;
                            break;
                        }
                    }
                    break;
                }
            }
            List<W_Day> days = new ArrayList<>();
            days.addAll(traningDataV3.get(CurrentWeek).getmWDays());
            Collections.sort(traningDataV3);
            Collections.sort(days);

            mTrainingRecyclerView.setVisibility(View.VISIBLE);
            workOutNewUIWeeksAdapter =new WorkOutNewUIWeeksAdapter(WorkOutNewUIDyActivity.this,CurrentWeek,traningDataV3);
            workOutNewUIDaysAdapter =new WorkOutNewUIDaysAdapter(WorkOutNewUIDyActivity.this,CurrentDay,days);

            weekRV.setNestedScrollingEnabled(false);
            mLayoutManagerWeeks.setAutoMeasureEnabled(true);
            weekRV.setLayoutManager(mLayoutManagerWeeks);
            weekRV.setAdapter(workOutNewUIWeeksAdapter);


            dayRV.setNestedScrollingEnabled(false);
            mLayoutManagerDays.setAutoMeasureEnabled(true);
            dayRV.setLayoutManager(mLayoutManagerDays);
            dayRV.setAdapter(workOutNewUIDaysAdapter);

            for(int i =0; i<traningDataV3.size();i++) {
                if (traningDataV3.get(i).getmWeek()==WeekID) {
                    CurrentWeek = i;
                    weekRV.getLayoutManager().scrollToPosition(i);
                    for(int j=0;j<traningDataV3.get(i).getmWDays().size();j++){
                        if(traningDataV3.get(i).getmWDays().get(j).getmDay()==CurrentDayID){
                            CurrentDay=j;
                            dayRV.getLayoutManager().scrollToPosition(j);
                            break;
                        }
                    }
                    break;
                }

            }

            if(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmInstructions()!=null){
                description.setText(Html.fromHtml(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmInstructions().trim()+""));
            }

            List<W_Video> mainTrainingVideoList =new ArrayList();
            for(int i=0;i<traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().size();i++){
                if(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType()!=4 && traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType()!=5){
                    mainTrainingVideoList.add(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i));
                }
            }
            for(int i = 0; i < traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().size(); i++){
                if (traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType() == 4) {
                    startVideoPosition=i;
                    StartVideoBtn.setVisibility(View.VISIBLE);
                    loadImage(startImage, traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmUrlImage());
                    startTitle.setText(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmTitle());
                    break;
                }else{
                    StartVideoBtn.setVisibility(View.GONE);
                }

            }
            for(int i = 0; i < traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().size(); i++){
                if (traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType() == 5) {
                    endVideoPosition=i;
                    EndVideoBtn.setVisibility(View.VISIBLE);
                    loadImage(endImage, traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmUrlImage());
                    endTitle.setText(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmTitle());
                    break;
                }else{
                    EndVideoBtn.setVisibility(View.GONE);
                }
            }
            /*List<WorkoutType> workoutTypeListView =new ArrayList();
            for(int i=0;i<workoutTypeList.size();i++){
                if(workoutTypeList.get(i).getId()!=4 && workoutTypeList.get(i).getId()!=5){
                    workoutTypeListView.add(workoutTypeList.get(i));
                }
            }
*/
            Collections.sort(mainTrainingVideoList);
            mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIDyActivity.this,traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmPlanId(),mainTrainingVideoList);
            mTrainingRecyclerView.setLayoutManager(mLayoutManagerVideos);
            List<SectionedGridRecyclerViewAdapter.Section> sections =
                    new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

            if(Locale.getDefault().getLanguage().equals("en")){
                for (int j = 0; j < workoutTypeList.size(); j++) {
                    for (int i = 0; i < mainTrainingVideoList.size(); i++) {
                        if (mainTrainingVideoList.get(i).getmType()==workoutTypeList.get(j).getId()) {
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, workoutTypeList.get(j).getEn().toUpperCase()));
                            break;
                        }
                    }
                }
            }
            if(Locale.getDefault().getLanguage().equals("sv")){
                for (int j = 0; j < workoutTypeList.size(); j++) {
                    for (int i = 0; i < mainTrainingVideoList.size(); i++) {
                        if (mainTrainingVideoList.get(i).getmType()==workoutTypeList.get(j).getId()) {
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i, workoutTypeList.get(j).getSv().toUpperCase()));
                            break;
                        }
                    }
                }
            }


            //Add your adapter to the sectionAdapter
            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                    SectionedGridRecyclerViewAdapter(WorkOutNewUIDyActivity.this,R.layout.workout_headers,R.id.Header,mTrainingRecyclerView,mDayTrainingAdapter);
            mSectionedAdapter.setSections(sections.toArray(dummy));

            //Apply this adapter to the RecyclerView
            mTrainingRecyclerView.setAdapter(mSectionedAdapter);
        }



        StartVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent startVideo = new Intent(mContext, VideoPlayActivity.class);
                    startVideo.putExtra("planID",traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmPlanId());
                    startVideo.putExtra("VideoID",traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(startVideoPosition).getmId());
                    startActivity(startVideo);

                }catch (Exception e){

                }

            }
        });

        EndVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent endVideo = new Intent(mContext, VideoPlayActivity.class);
                    endVideo.putExtra("planID",traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmPlanId());
                    endVideo.putExtra("VideoID",traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(endVideoPosition).getmId());
                    startActivity(endVideo);
                }catch(Exception e){

                }
            }
        });

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


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private final BroadcastReceiver TrainingWeekBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here
            WeekIDChange = intent.getIntExtra("WeekID", 1);
            CurrentDayID = intent.getIntExtra("DayID", 1);
            int Position = intent.getIntExtra("Position", 1);
            int lastSelecteditem = intent.getIntExtra("lastSelecteditem", 1);

           /* if(Position>workOutNewUIWeeksAdapter.lastSelecteditem){
                SlideInLeftAnimator slideInLeftAnimator = new SlideInLeftAnimator();
                slideInLeftAnimator.setRemoveDuration(0);
                weekRV.setItemAnimator(slideInLeftAnimator);
                workOutNewUIWeeksAdapter.lastSelecteditem = Position;
            }else{
                SlideInRightAnimator slideInRightAnimator = new SlideInRightAnimator();
                slideInRightAnimator.setRemoveDuration(0);
                weekRV.setItemAnimator(slideInRightAnimator);
                workOutNewUIWeeksAdapter.lastSelecteditem = Position;
            }*/
            if(WeekIDChange==currentWeek){
                CurrentDayID = currentDay;
                WeekIDChange = (int) currentWeek;
            }

            if (workoutTypeList != null && workoutTypeList.size() > 0 && traningDataV3 != null && traningDataV3.size() > 0) {

                for (int i = 0; i < traningDataV3.size(); i++) {
                    if (WeekIDChange == traningDataV3.get(i).getmWeek()) {
                        CurrentWeek = i;
                        for (int j = 0; j < traningDataV3.get(i).getmWDays().size(); j++) {
                            if (traningDataV3.get(i).getmWDays().get(j).getmDay() == CurrentDayID) {
                                CurrentDay = j;
                                break;
                            }
                        }
                        break;
                    }

                }

                List<W_Day> days = new ArrayList<>();
                days.addAll(traningDataV3.get(CurrentWeek).getmWDays());
                Collections.sort(days);

                workOutNewUIDaysAdapter = new WorkOutNewUIDaysAdapter(WorkOutNewUIDyActivity.this, CurrentDay, days);
                dayRV.setNestedScrollingEnabled(false);
                mLayoutManagerDays.setAutoMeasureEnabled(true);
                dayRV.setLayoutManager(mLayoutManagerDays);
                dayRV.setAdapter(workOutNewUIDaysAdapter);

                for (int i = 0; i < traningDataV3.size(); i++) {
                    if (traningDataV3.get(i).getmWeek() == WeekIDChange) {
                        for (int j = 0; j < traningDataV3.get(i).getmWDays().size(); j++) {
                            if (traningDataV3.get(i).getmWDays().get(j).getmDay() == CurrentDayID) {
                                dayRV.getLayoutManager().scrollToPosition(j);
                                break;
                            }
                        }
                        break;
                    }

                }

                if (traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmInstructions() != null) {
                    description.setText(Html.fromHtml(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmInstructions().trim() + ""));
                }

                List<W_Video> mainTrainingVideoList = new ArrayList();
                for (int i = 0; i < traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().size(); i++) {
                    if (traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType() != 4 && traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType() != 5) {
                        mainTrainingVideoList.add(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i));
                    }
                }
                for(int i = 0; i < traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().size(); i++){
                    if (traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType() == 4) {
                        startVideoPosition=i;
                        StartVideoBtn.setVisibility(View.VISIBLE);
                        loadImage(startImage, traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmUrlImage());
                        startTitle.setText(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmTitle());
                        break;
                    }else{
                        StartVideoBtn.setVisibility(View.GONE);
                    }

                }
                for(int i = 0; i < traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().size(); i++){
                    if (traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType() == 5) {
                        endVideoPosition=i;
                        EndVideoBtn.setVisibility(View.VISIBLE);
                        loadImage(endImage, traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmUrlImage());
                        endTitle.setText(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmTitle());
                        break;
                    }else{
                        EndVideoBtn.setVisibility(View.GONE);
                    }
                }
                Collections.sort(mainTrainingVideoList);
                mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIDyActivity.this,traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmPlanId(), mainTrainingVideoList);
                mTrainingRecyclerView.setLayoutManager(mLayoutManagerVideos);
                List<SectionedGridRecyclerViewAdapter.Section> sections =
                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();


                if(Locale.getDefault().getLanguage().equals("en")){
                    for (int j = 0; j < workoutTypeList.size(); j++) {
                        for (int i = 0; i < mainTrainingVideoList.size(); i++) {
                            if (mainTrainingVideoList.get(i).getmType()==workoutTypeList.get(j).getId()) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, workoutTypeList.get(j).getEn().toUpperCase()));
                                break;
                            }
                        }
                    }
                }
                if(Locale.getDefault().getLanguage().equals("sv")){
                    for (int j = 0; j < workoutTypeList.size(); j++) {
                        for (int i = 0; i < mainTrainingVideoList.size(); i++) {
                            if (mainTrainingVideoList.get(i).getmType()==workoutTypeList.get(j).getId()) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, workoutTypeList.get(j).getSv().toUpperCase()));
                                break;
                            }
                        }
                    }
                }

                //Add your adapter to the sectionAdapter
                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                        SectionedGridRecyclerViewAdapter(WorkOutNewUIDyActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                mSectionedAdapter.setSections(sections.toArray(dummy));

                //Apply this adapter to the RecyclerView
                mTrainingRecyclerView.setAdapter(mSectionedAdapter);


            }
        }
    };


    private final BroadcastReceiver TrainingDaysBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here
            int DayID = intent.getIntExtra("DayID",1);

            if (workoutTypeList != null && workoutTypeList.size() > 0 && traningDataV3 != null && traningDataV3.size() > 0) {

                for (int i = 0; i < traningDataV3.size(); i++) {
                    if (WeekIDChange == traningDataV3.get(i).getmWeek()) {
                        CurrentWeek = i;
                        for (int j = 0; j < traningDataV3.get(i).getmWDays().size(); j++) {
                            if (traningDataV3.get(i).getmWDays().get(j).getmDay() == DayID) {
                                CurrentDay = j;
                                break;
                            }
                        }
                        break;
                    }

                }

                if (traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmInstructions() != null) {
                    description.setText(Html.fromHtml(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmInstructions().trim() + ""));
                }

                List<W_Video> mainTrainingVideoList = new ArrayList();
                for (int i = 0; i < traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().size(); i++) {
                    if (traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType() != 4 && traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType() != 5) {
                        mainTrainingVideoList.add(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i));
                    }
                }
                for(int i = 0; i < traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().size(); i++){
                    if (traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType() == 4) {
                        startVideoPosition=i;
                        StartVideoBtn.setVisibility(View.VISIBLE);
                        loadImage(startImage, traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmUrlImage());
                        startTitle.setText(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmTitle());
                        break;
                    }else{
                        StartVideoBtn.setVisibility(View.GONE);
                    }

                }
                for(int i = 0; i < traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().size(); i++){
                    if (traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmType() == 5) {
                        endVideoPosition=i;
                        EndVideoBtn.setVisibility(View.VISIBLE);
                        loadImage(endImage, traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmUrlImage());
                        endTitle.setText(traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmWVideos().get(i).getmTitle());
                        break;
                    }else{
                        EndVideoBtn.setVisibility(View.GONE);
                    }
                }

                Collections.sort(mainTrainingVideoList);
                mDayTrainingAdapter = new WorkOutNewUIAdapter(WorkOutNewUIDyActivity.this,traningDataV3.get(CurrentWeek).getmWDays().get(CurrentDay).getmPlanId(), mainTrainingVideoList);
                mTrainingRecyclerView.setLayoutManager(mLayoutManagerVideos);
                List<SectionedGridRecyclerViewAdapter.Section> sections =
                        new ArrayList<SectionedGridRecyclerViewAdapter.Section>();


                if(Locale.getDefault().getLanguage().equals("en")){
                    for (int j = 0; j < workoutTypeList.size(); j++) {
                        for (int i = 0; i < mainTrainingVideoList.size(); i++) {
                            if (mainTrainingVideoList.get(i).getmType()==workoutTypeList.get(j).getId()) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, workoutTypeList.get(j).getEn().toUpperCase()));
                                break;
                            }
                        }
                    }
                }
                if(Locale.getDefault().getLanguage().equals("sv")){
                    for (int j = 0; j < workoutTypeList.size(); j++) {
                        for (int i = 0; i < mainTrainingVideoList.size(); i++) {
                            if (mainTrainingVideoList.get(i).getmType()==workoutTypeList.get(j).getId()) {
                                sections.add(new SectionedGridRecyclerViewAdapter.Section(i, workoutTypeList.get(j).getSv().toUpperCase()));
                                break;
                            }
                        }
                    }
                }

                //Add your adapter to the sectionAdapter
                SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                        SectionedGridRecyclerViewAdapter(WorkOutNewUIDyActivity.this, R.layout.workout_headers, R.id.Header, mTrainingRecyclerView, mDayTrainingAdapter);
                mSectionedAdapter.setSections(sections.toArray(dummy));

                //Apply this adapter to the RecyclerView
                mTrainingRecyclerView.setAdapter(mSectionedAdapter);
            }
        }
    };


    @Override
    public void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(WorkOutNewUIDyActivity.this).registerReceiver(TrainingWeekBroadcastReceiver,
                new IntentFilter("com.tutorialspoint.CUSTOM_INTENT_WEEK_NAME"));

        LocalBroadcastManager.getInstance(WorkOutNewUIDyActivity.this).registerReceiver(TrainingDaysBroadcastReceiver,
                new IntentFilter("com.tutorialspoint.CUSTOM_INTENT_DAY_NAME"));

    }



    @Override
    public void onStop() {

        super.onStop();

        LocalBroadcastManager.getInstance(WorkOutNewUIDyActivity.this).unregisterReceiver(TrainingWeekBroadcastReceiver);
        LocalBroadcastManager.getInstance(WorkOutNewUIDyActivity.this).unregisterReceiver(TrainingDaysBroadcastReceiver);

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
    public void setStartUpData(String message) {

    }

    @Override
    public void setStartUpDataFailed(String message) {

    }

    @Override
    public void setPresenter(WeekWorkOutPresenter mPresenter) {

    }

    private void loadImage(final ImageView imageView, final String imageUrl){

        GlideApp
                .with(mContext)
                .load(imageUrl)
                .centerCrop()
                //.error(R.drawable.avatar_new)
                .into(imageView);

       /* Picasso.get()
                .load(imageUrl)
                .resize(500,700)
                .centerCrop()
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
                });*/
    }

}
