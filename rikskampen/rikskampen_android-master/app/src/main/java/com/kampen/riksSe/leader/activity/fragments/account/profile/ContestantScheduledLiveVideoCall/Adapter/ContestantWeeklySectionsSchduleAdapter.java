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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.DaySchduleActivity;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ModelList.MonthWeekSchduleList;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.adapter.SectionedGridRecyclerViewAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.utils.Log;

public class ContestantWeeklySectionsSchduleAdapter extends RecyclerView.Adapter<ContestantWeeklySectionsSchduleAdapter.ViewHolder>  {


    ArrayList<MonthWeekSchduleList> mWeekDaysArrayList=new ArrayList<>();

    ContestantWeeklySchduleAdapter contestantWeeklySchduleAdapter;

    LinearLayoutManager mLayoutManagerWeekDaysSchdule;
    List<SectionedGridRecyclerViewAdapter.Section> sections;
    private  Context mContext;




    public ContestantWeeklySectionsSchduleAdapter(Context context, List<MonthWeekSchduleList> mWorkOutWeekArrayList)
    {

        this.mWeekDaysArrayList.addAll(mWorkOutWeekArrayList);
        mContext=context;

   /*     mLayoutManagerWeekDaysSchdule = new LinearLayoutManager(context);
        mLayoutManagerWeekDaysSchdule.setOrientation(LinearLayoutManager.HORIZONTAL);*/


    }



    public void  updateAdapter()
    {


        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_month_weeks_schdule, viewGroup, false);



        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.mWeekTitle.setText(mWeekDaysArrayList.get(i).getWeekName());

        contestantWeeklySchduleAdapter = new ContestantWeeklySchduleAdapter(mContext,0,mWeekDaysArrayList.get(i).getDayName());

        mLayoutManagerWeekDaysSchdule = new LinearLayoutManager(mContext);

        mLayoutManagerWeekDaysSchdule.setOrientation(LinearLayoutManager.HORIZONTAL);

        viewHolder.mScheduledLiveVideoCallWeekRV.setLayoutManager(mLayoutManagerWeekDaysSchdule);

        viewHolder.mScheduledLiveVideoCallWeekRV.setAdapter(contestantWeeklySchduleAdapter);

       // manageClick(i,viewHolder.tabWeekLayout);
    }

    @Override
    public int getItemCount() {
        return mWeekDaysArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RecyclerView mScheduledLiveVideoCallWeekRV;
        TextView mWeekTitle;

        public View view;


        public View tabWeekLayout;

        public ViewHolder(View v) {
            super(v);

            mScheduledLiveVideoCallWeekRV = v.findViewById(R.id.ScheduledLiveVideoCallWeekRV);
            mWeekTitle = v.findViewById(R.id.weekTitleView);


            view=v;
        }


    }

    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{




                    //notifyDataSetChanged();



                }catch (Exception ex){

                    Log.i("Error",ex.toString());
                }


            }
        });
    }

}
