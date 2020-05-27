package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly;

import android.content.Context;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.adapterListModel.activityAdapterListModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.QuestionResponceModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.adapter.WeeklyActivityAdapter;
import com.kampen.riksSe.leader.activity.modelV3.Question;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.ArrayList;
import java.util.List;

public class WeeklyActivity extends AppCompatActivity implements WeeklyActivityContract.View{


    WeeklyActivityAdapter mAdapter;

    RecyclerView    mWeeklyActivityRV;

    WeeklyActivityPresenter weeklyActivityPresenter;

    SwipeRefreshLayout pullToRefresh;

    private Realm_User mUser;

    private ImageView  mProfileImage;

    public TextView userName;

    public ImageView weeklyPick1,weeklyPick2,weeklyPick3,weeklyPick4,weeklyPick5,weeklyPick6,weeklyPick7;
    public TextView stepsValue1,calValue1,DayTV1,DayAddress1,stepsValue2,calValue2,DayTV2,DayAddress2,stepsValue3,calValue3,DayTV3,DayAddress3,stepsValue4,calValue4,DayTV4,DayAddress4,stepsValue5,calValue5,DayTV5,DayAddress5,stepsValue6,calValue6,DayTV6,DayAddress6,stepsValue7,calValue7,DayTV7,DayAddress7;

    long WeekNo;
    int weekid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        mUser=provideUserLocal(WeeklyActivity.this);

        mProfileImage=findViewById(R.id.profile_image);

        userName=findViewById(R.id.username);

        WeekNo = getIntent().getLongExtra("week_id",1);

        weeklyActivityPresenter = new WeeklyActivityPresenter(WeeklyActivity.this,getApplicationContext());

        mWeeklyActivityRV=findViewById(R.id.weeklyActivityRV);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

//        ArrayList<A_DayDB> a_weekDB = weeklyActivityPresenter.getActivyDayDetail(Integer.parseInt(WeekNo));
        List<activityAdapterListModel> activityAdapterWeekDaysSortedList = weeklyActivityPresenter.getActivitySyncDayDetailData(WeeklyActivity.this,(int)WeekNo);

        List<QuestionResponceModel> diaryTodayQuestions = weeklyActivityPresenter.getTodayQuestion();

        mAdapter=new WeeklyActivityAdapter(WeeklyActivity.this,(int) WeekNo,activityAdapterWeekDaysSortedList,diaryTodayQuestions);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mWeeklyActivityRV.setLayoutManager(mLayoutManager);

        mWeeklyActivityRV.setAdapter(mAdapter);

        userName.setText(mUser.getF_name()+" "+mUser.getL_name());


    }

    private  void setUser()
    {


        if(mUser!=null)
        {
            Constants.setProfileImage(mUser.getProfile_image(),mUser.getProfilePicData(),mProfileImage,WeeklyActivity.this);
        }
    }
    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUser();

    }
    public void updateNotify()
    {
        if(mAdapter!=null)
        {

           // ArrayList<A_DayDB> a_weekDB= weeklyActivityPresenter.getActivyDayDetail(Integer.parseInt(WeekNo));
            List<activityAdapterListModel> activityAdapterWeekDaysSortedList= weeklyActivityPresenter.getActivitySyncDayDetailData(WeeklyActivity.this,(int)WeekNo);

            mAdapter.updateAdapter(activityAdapterWeekDaysSortedList);

            pullToRefresh.setRefreshing(false);

        }
    }


    public void onBackClick(View view) {

        finish();
    }

    @Override
    public void setActivitiesData() {

    }

    @Override
    public void setStartUpData(String message) {

    }

    @Override
    public void setStartUpDataFailed(String message) {

    }

    @Override
    public void setPresenter(WeeklyActivityContract.Presenter mPresenter) {

    }
}
