package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.DaySchduleActivity;
import com.kampen.riksSe.leader.activity.fragments.account.profile.LiveVideoCall.LiveVideoCall;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
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

public class ContestantTodayScheduledLiveVideoCallAdapter extends RecyclerView.Adapter<ContestantTodayScheduledLiveVideoCallAdapter.ViewHolder>  {


    ArrayList<ScheduledLiveVideoCall> mScheduledLiveVideoCallArrayList =new ArrayList<>();


    private  Context mContext;

    private int index;

    public N_DaysDB DaysObj1;

    public int schduleNamePosition;

    public int lastSelecteditem;

    CountDownTimer cdt = null;


    public ContestantTodayScheduledLiveVideoCallAdapter(Context context, List<ScheduledLiveVideoCall> mScheduledLiveVideoCallArrayList)
    {
        this.mScheduledLiveVideoCallArrayList.addAll(mScheduledLiveVideoCallArrayList);
        mContext=context;
    }



    public void  updateAdapter(List<ScheduledLiveVideoCall> mTrainerSchdulArrayList)
    {
        this.mScheduledLiveVideoCallArrayList.clear();
        this.mScheduledLiveVideoCallArrayList.addAll(mTrainerSchdulArrayList);


        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_contestant_schdule, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        if(mScheduledLiveVideoCallArrayList.get(i).getSessionStartsAt()!=null && mScheduledLiveVideoCallArrayList.get(i).getSessionEndsAt()!=null){
            String mScheduledSessionStartTime =convertUTCToLocalTime(mScheduledLiveVideoCallArrayList.get(i).getSessionStartsAt());
            String mScheduledSessionEndTime =convertUTCToLocalTime(mScheduledLiveVideoCallArrayList.get(i).getSessionEndsAt());
            loadImage(viewHolder.ContestantImage, mScheduledLiveVideoCallArrayList.get(i).getTrainer().getProfileImage());
            viewHolder.SchduleDay.setText(convertDateTimeForUIDay(mScheduledSessionStartTime));
            viewHolder.SchduleDate.setText(convertDateTimeForUIDate(mScheduledSessionStartTime));
            viewHolder.ContestantName.setText(mScheduledLiveVideoCallArrayList.get(i).getTrainer().getName().substring(0,1).toUpperCase() + mScheduledLiveVideoCallArrayList.get(i).getTrainer().getName().substring(1));
            viewHolder.mSchduleDateTime.setText(convertDateTimeForUI(mScheduledSessionStartTime));
            viewHolder.mSchduleStartTimeTV.setText(convertDateTimeToTimeFormatUI(mScheduledSessionStartTime));
            CounterContest(viewHolder,viewHolder.mSchduleTimeLayout,mScheduledSessionStartTime);
            if(mScheduledSessionEndTime!=null){
                viewHolder.mSchduleEndTimeTV.setText(" - "+convertDateTimeToTimeFormatUI(mScheduledSessionEndTime));
            }
        }
        manageClick(i,viewHolder.mSchduleTimeLayout);
        EditClick(i,viewHolder.mEditSchdule);
        DeleteClick(i,viewHolder.mDeleteSchdule);
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
            mEditSchdule = v.findViewById(R.id.editSchdule);
            mDeleteSchdule = v.findViewById(R.id.deleteSchdule);
            view=v;

        }


    }


    private void  manageClick(final int position,View view)
    {

        try{
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{
                        Realm_User mUser = provideUserLocal(mContext);
                        String mScheduledSessionEndTime =convertUTCToLocalTime(mScheduledLiveVideoCallArrayList.get(position).getSessionEndsAt());
                        Intent i = new Intent(mContext, LiveVideoCall.class);
                        i.putExtra("SessionEndTime",mScheduledSessionEndTime);
                        i.putExtra("TrainerID",String.valueOf(mScheduledLiveVideoCallArrayList.get(position).getTrainer().getId()));
                        i.putExtra("ContestantID",mUser.getId());
                        mContext.startActivity(i);

                    }catch (Exception ex){
                        Log.i("Error",ex.toString());
                    }
                }
            });
        }catch (Exception ex){

        }


    }

    private void  EditClick(final int position,View view)
    {

        try{
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{
                        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);

                        String currentDate = sdf1.format(new Date());
                        Intent i = new Intent(mContext, DaySchduleActivity.class);
                        i.putExtra("SchduleDate",currentDate);
                        mContext.startActivity(i);

                    }catch (Exception ex){
                        Log.i("Error",ex.toString());
                    }
                }
            });
        }catch (Exception ex){

        }


    }

    private void  DeleteClick(final int position,View view)
    {

        try{
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{

                        DialogeBoxReschdule(position);
                    }catch (Exception ex){
                        Log.i("Error",ex.toString());
                    }
                }
            });
        }catch (Exception ex){

        }


    }

    public void RemoveSchdule(int position){
        mScheduledLiveVideoCallArrayList.remove(position);
        notifyItemRemoved(position);
    }

    private void loadImage(final ImageView imageView, final String imageUrl){

        GlideApp
                .with(mContext)
                .load(imageUrl)
                .error(R.drawable.avatar_new)
                .into(imageView);


    }

    public void CounterContest(ViewHolder viewHolder,View view,String SchduleDate){

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
    public void DialogeBoxReschdule(int position){

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.dialog_box_delete_schdule_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setView(promptsView);

        final TextView contestStartTime = promptsView.findViewById(R.id.textView1);

        final Button CancelBtn = promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = promptsView.findViewById(R.id.okBtn);


        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder
                Intent intent2 = new Intent();
                intent2.putExtra("SchduleID",mScheduledLiveVideoCallArrayList.get(position).getId());
                intent2.setAction("com.Rikskampen.CUSTOM_INTENT_DELETE_SCHDULE_SLOT");
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent2);
                RemoveSchdule(position);
                alertDialog.dismiss();
            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

    }

    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;

    }
}
