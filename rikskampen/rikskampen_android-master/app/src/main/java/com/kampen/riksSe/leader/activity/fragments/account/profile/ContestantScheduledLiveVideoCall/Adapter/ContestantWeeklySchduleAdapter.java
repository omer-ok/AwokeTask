package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.DaySchduleActivity;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.utils.Log;

public class ContestantWeeklySchduleAdapter extends RecyclerView.Adapter<ContestantWeeklySchduleAdapter.ViewHolder>  {


    ArrayList<String> mWeekDaysArrayList=new ArrayList<>();
    ArrayList<String>mWeekDaysArrayList2=new ArrayList<>();

    private  Context mContext;

    private int index;

    public N_DaysDB DaysObj1;

    public int weekNamePosition;

    public int lastSelecteditem;

    public ContestantWeeklySchduleAdapter(Context context, int weekNamePosition, List<String> mWorkOutWeekArrayList)
    {

        this.mWeekDaysArrayList.addAll(mWorkOutWeekArrayList);
        this.mWeekDaysArrayList2.addAll(mWorkOutWeekArrayList);
        mContext=context;
        this.weekNamePosition=weekNamePosition;
        this.lastSelecteditem=weekNamePosition;
    //DaysObj1 = mNutritionArrayList.get(0).getDays();

    }



    public void  updateAdapter(List<String> mWorkOutWeekArrayList)
    {
        this.mWeekDaysArrayList.clear();
        this.mWeekDaysArrayList2.clear();
        this.mWeekDaysArrayList.addAll(mWorkOutWeekArrayList);
        this.mWeekDaysArrayList2.addAll(mWorkOutWeekArrayList);

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_weekly_day_schdule, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.habitStartFromTV1.setText(mWeekDaysArrayList2.get(i));
        viewHolder.habitStartFromTV.setText(mWeekDaysArrayList.get(i));

        if(weekNamePosition==i){
          /*  viewHolder.habitStartFromTV.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
            viewHolder.habitStartFromTV.setTextColor(Color.parseColor("#FFFFFF"));*/
            if(weekNamePosition<lastSelecteditem){
                viewHolder.tabView.bringToFront();
                viewHolder.tabWeekLayout.bringToFront();
                viewHolder.habitStartFromTV.bringToFront();
                //setAnimationLeft(viewHolder.tabWeekLayout,weekNamePosition);
                viewHolder.habitStartFromTV.setBackgroundResource(R.drawable.circle_view_selected_background);
                viewHolder.habitStartFromTV.setTextColor(Color.parseColor("#FFFFFF"));

                lastSelecteditem=weekNamePosition;
            }else{
                viewHolder.tabView.bringToFront();
                viewHolder.tabWeekLayout.bringToFront();
                viewHolder.habitStartFromTV.bringToFront();
                //setAnimationRight(viewHolder.tabWeekLayout,weekNamePosition);
                viewHolder.habitStartFromTV.setBackgroundResource(R.drawable.circle_view_selected_background);
                viewHolder.habitStartFromTV.setTextColor(Color.parseColor("#FFFFFF"));

                lastSelecteditem=weekNamePosition;
            }
        }
        else{

            viewHolder.habitStartFromTV.setBackgroundResource(R.drawable.circle_view_background);
            viewHolder.habitStartFromTV.setTextColor(Color.parseColor("#BB9767"));
        }


        manageClick(i,viewHolder.tabWeekLayout);
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
        return mWeekDaysArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView habitNameTV,habitJoinTV,habitStartFromTV,habitStartFromTV1;

        public ImageView nutritionImage;

        public View view;

        public View tabView;

        public View tabWeekLayout;

        public ViewHolder(View v) {
            super(v);
            habitStartFromTV=v.findViewById(R.id.tab_week2);
            habitStartFromTV1=v.findViewById(R.id.tab_week2_1);
            tabWeekLayout=v.findViewById(R.id.tab_week_layout);
            tabView=v.findViewById(R.id.tab_view);
            view=v;
        }


    }


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                    //W_Plans w_plans = mWeekDaysArrayList.get(position);
                 /*   removeSingleItem(position);
                    insertSingleItem(view,position,w_plans);*/

                    Intent i =new Intent(mContext, DaySchduleActivity.class);
                    mContext.startActivity(i);
                    weekNamePosition =  position;
                    notifyDataSetChanged();



                }catch (Exception ex){

                    Log.i("Error",ex.toString());
                }


            }
        });
    }


    private void insertSingleItem(View coloume,int insertIndex,W_Plans wPlans) {
        //mWeekDaysArrayList.add(insertIndex, wPlans);
        //notifyItemRangeInserted(0, mWorkOutWeekArrayList.size()-1);
        notifyItemInserted(insertIndex);
    }

    private void removeSingleItem(int removeIndex) {
        mWeekDaysArrayList.remove(removeIndex);
        //notifyItemRangeRemoved(0, mWorkOutWeekArrayList.size()-1);
        notifyItemRemoved(removeIndex);
    }

        public static String  getFormattedWeek(int week,Context context)
    {

        String weekstring="";
        int i=week;
        String  weekName =  context.getResources().getString(R.string.Week);

        /*if(i==1) {
            weekstring=("VECKA"+i);
        }
        else if(i==2)
        {
            weekstring=(i + "nd week");
        }
        else if(i==2)
        {
            weekstring=(i + "rd week");
        }
        else
        {
            weekstring=(i + "th week");
        }*/
        weekstring=(weekName+" "+i);

        return  weekstring;
    }



}
