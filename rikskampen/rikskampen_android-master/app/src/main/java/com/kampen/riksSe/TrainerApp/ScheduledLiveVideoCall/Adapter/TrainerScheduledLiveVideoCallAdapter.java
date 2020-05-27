package com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.Adapter.ContestantTodayScheduledLiveVideoCallAdapter;
import com.kampen.riksSe.leader.activity.fragments.account.profile.LiveVideoCall.LiveVideoCall;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.vov.vitamio.utils.Log;

import static com.kampen.riksSe.utils.Constants.DATE_TIME_FORMAT;
import static com.kampen.riksSe.utils.UtilityTz.AddTimeinStaticTimeUI;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTimePopDaysHoursMinutes;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeForUI;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeForUIDate;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeForUIDay;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToTimeFormat;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToTimeFormatUI;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;

public class TrainerScheduledLiveVideoCallAdapter extends RecyclerView.Adapter<TrainerScheduledLiveVideoCallAdapter.ViewHolder>  {


    ArrayList<ScheduledLiveVideoCall> mScheduledLiveVideoCallArrayList =new ArrayList<>();


    private  Context mContext;

    private int index;

    public N_DaysDB DaysObj1;

    public int schduleNamePosition;

    public int lastSelecteditem;

    CountDownTimer cdt = null;

    Boolean firstIndexTimer;

    Boolean mCurrentDaySchdule;
    int PositionOfActiveSchdule=-1;



    public TrainerScheduledLiveVideoCallAdapter(Context context,boolean CurrentDaySchdule, List<ScheduledLiveVideoCall> mScheduledLiveVideoCallArrayList)
    {

        this.mScheduledLiveVideoCallArrayList.addAll(mScheduledLiveVideoCallArrayList);
        mContext=context;
        this.mCurrentDaySchdule=CurrentDaySchdule;
        firstIndexTimer =true;
    }



    public void  updateAdapter(List<ScheduledLiveVideoCall> mTrainerSchdulArrayList,boolean CurrentDaySchdule)
    {
        this.mScheduledLiveVideoCallArrayList.clear();
        this.mScheduledLiveVideoCallArrayList.addAll(mTrainerSchdulArrayList);
        this.mCurrentDaySchdule=CurrentDaySchdule;
        firstIndexTimer =true;

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_trainer_schdule, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if(mScheduledLiveVideoCallArrayList.get(i).getSessionStartsAt()!=null && mScheduledLiveVideoCallArrayList.get(i).getSessionEndsAt()!=null){
            String mScheduledSessionStartTime =convertUTCToLocalTime(mScheduledLiveVideoCallArrayList.get(i).getSessionStartsAt());
            String mScheduledSessionEndTime =convertUTCToLocalTime(mScheduledLiveVideoCallArrayList.get(i).getSessionEndsAt());
            loadImage(viewHolder.ContestantImage, mScheduledLiveVideoCallArrayList.get(i).getContestant().getProfileImage());
            viewHolder.SchduleDay.setText(convertDateTimeForUIDay(mScheduledSessionStartTime));
            viewHolder.SchduleDate.setText(convertDateTimeForUIDate(mScheduledSessionStartTime));
            viewHolder.ContestantName.setText(mScheduledLiveVideoCallArrayList.get(i).getContestant().getName().substring(0,1).toUpperCase() + mScheduledLiveVideoCallArrayList.get(i).getContestant().getName().substring(1));
            viewHolder.mSchduleDateTime.setText(convertDateTimeForUI(mScheduledSessionStartTime));
            viewHolder.mSchduleStartTimeTV.setText(convertDateTimeToTimeFormatUI(mScheduledSessionStartTime));
            CounterContest(viewHolder,viewHolder.mSchduleTimeLayout,mScheduledSessionStartTime);
            if(mScheduledSessionEndTime!=null){
                viewHolder.mSchduleEndTimeTV.setText(" - "+convertDateTimeToTimeFormatUI(mScheduledSessionEndTime));
            }

        if(mCurrentDaySchdule){
            if(firstIndexTimer){
                PositionOfActiveSchdule =i;
                CounterContest(viewHolder,viewHolder.mSchduleTimeLayout,mScheduledSessionStartTime);
                firstIndexTimer=false;
                if(viewHolder.mContestantSchdulerTimer.getVisibility()==View.GONE){
                    viewHolder.mSchduleActiveLayout.setVisibility(View.VISIBLE);
                }
                viewHolder.mHourCounter.setVisibility(View.VISIBLE);
                viewHolder.mMiniuteCounter.setVisibility(View.VISIBLE);
                viewHolder.mSecondsCounter.setVisibility(View.VISIBLE);
            }
        }else{
            PositionOfActiveSchdule=-1;
            viewHolder.mContestantSchdulerTimer.setVisibility(View.GONE);
            viewHolder.mSchduleActiveLayout.setVisibility(View.GONE);
            viewHolder.mHourCounter.setVisibility(View.GONE);
            viewHolder.mMiniuteCounter.setVisibility(View.GONE);
            viewHolder.mSecondsCounter.setVisibility(View.GONE);
            viewHolder.mSchduleTimeLayout.setClickable(false);
        }



            if(mScheduledSessionEndTime!=null){
                viewHolder.mSchduleEndTimeTV.setText(" - "+convertDateTimeToTimeFormatUI(mScheduledSessionEndTime));
            }
        }




        manageClick(i,viewHolder.mSchduleTimeLayout);
    }

    private void setAnimationLeft(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        // if (position > mealNamePosition) {
        Animation animation = AnimationUtils.loadAnimation(mContext,  R.anim.slide_in_right);

        viewToAnimate.startAnimation(animation);


        // }
    }

    private void setAnimationRight(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        // if (position > mealNamePosition) {
        Animation animation = AnimationUtils.loadAnimation(mContext,  R.anim.slide_in_left);

        viewToAnimate.startAnimation(animation);

        // }
    }


    @Override
    public int getItemCount() {
        return mScheduledLiveVideoCallArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public  TextView SchduleDay,SchduleDate,ContestantName,mSchduleStartTimeTV,mSchduleEndTimeTV,mSchduleDateTime,mHourCounter,mMiniuteCounter,mSecondsCounter;

        public ImageView ContestantImage, mDeleteSchdule,mEditSchdule;

        public View view;

        public View mSchduleTimeLayout,mContestantSchdulerTimer,mSchduleActiveLayout;



        public ViewHolder(View v) {
            super(v);

            ContestantImage=v.findViewById(R.id.profileImage);
            ContestantName=v.findViewById(R.id.ContestantName);
            SchduleDay=v.findViewById(R.id.day);
            SchduleDate=v.findViewById(R.id.date);
            mSchduleStartTimeTV=v.findViewById(R.id.startTime);
            mSchduleEndTimeTV=v.findViewById(R.id.endTime);
            mSchduleDateTime=v.findViewById(R.id.SchduleDateTime);
            mSchduleTimeLayout=v.findViewById(R.id.SchduleTimeLayout);
            mContestantSchdulerTimer=v.findViewById(R.id.ContestantSchdulerTimer);
            mSchduleActiveLayout=v.findViewById(R.id.SchduleActiveLayout);
            mHourCounter = v.findViewById(R.id.hourCounter);
            mMiniuteCounter = v.findViewById(R.id.miniuteCounter);
            mSecondsCounter = v.findViewById(R.id.secondsCounter);
       /*     mEditSchdule = v.findViewById(R.id.editSchdule);
            mDeleteSchdule = v.findViewById(R.id.deleteSchdule);*/
            view=v;

        }


    }


    private void  manageClick(final int position,View view)
    {
        if(position==PositionOfActiveSchdule){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{
                        Realm_User mUser = provideUserLocal(mContext);
                        String mScheduledSessionEndTime =convertUTCToLocalTime(mScheduledLiveVideoCallArrayList.get(position).getSessionEndsAt());
                        Intent i =new Intent(mContext, LiveVideoCall.class);
                        i.putExtra("SessionEndTime",mScheduledSessionEndTime);
                        i.putExtra("TrainerID",mUser.getId());
                        i.putExtra("ContestantID",String.valueOf(mScheduledLiveVideoCallArrayList.get(position).getContestant().getId()));
                        mContext.startActivity(i);
                    }catch (Exception ex){
                        Log.i("Error",ex.toString());
                    }
                }
            });
        }

    }


    private void loadImage(final ImageView imageView, final String imageUrl){

        GlideApp
                .with(mContext)
                .load(imageUrl)
                .error(R.drawable.avatar_new)
                .into(imageView);


    }


    public void CounterContest(TrainerScheduledLiveVideoCallAdapter.ViewHolder viewHolder, View view, String SchduleDate){

        try{

            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            long total_millis = 0;

            ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTimePopDaysHoursMinutes(SchduleDate,currentDateandTime);

            if(cdt!=null){
                total_millis = (contestWeekDayTimeModel.getStratTimeInMilli() - contestWeekDayTimeModel.getEndTimeInMilli());
                cdt.cancel();
            }else{
                total_millis = (contestWeekDayTimeModel.getStratTimeInMilli() - contestWeekDayTimeModel.getEndTimeInMilli());
            }

            cdt = new CountDownTimer( total_millis,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    view.setClickable(false);
                    viewHolder.mContestantSchdulerTimer.setVisibility(View.VISIBLE);
                    viewHolder.mSchduleActiveLayout.setVisibility(View.GONE);
                    long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                    long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                    long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                    long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                    viewHolder.mHourCounter.setText(hours+"h");
                    viewHolder.mMiniuteCounter.setText(" : "+minutes+"m");
                    viewHolder.mSecondsCounter.setText(" : "+seconds+"s");
                }
                @Override
                public void onFinish() {
                    cdt.cancel();
                    view.setClickable(true);
                    viewHolder.mContestantSchdulerTimer.setVisibility(View.GONE);
                    viewHolder.mSchduleActiveLayout.setVisibility(View.VISIBLE);
                }
            };
            if(cdt!=null){
                cdt.start();
            }
        }catch (Exception e){

        }

    }
    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;

    }
}
