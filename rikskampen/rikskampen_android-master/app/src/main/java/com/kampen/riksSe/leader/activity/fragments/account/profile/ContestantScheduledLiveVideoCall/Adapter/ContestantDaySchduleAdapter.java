package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.Adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.DaySchduleActivity;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelDayList.DaySchduleList;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.utils.Log;

public class ContestantDaySchduleAdapter extends RecyclerView.Adapter<ContestantDaySchduleAdapter.ViewHolder>  {


    ArrayList<DaySchduleList> mDaySchduleArrayList=new ArrayList<>();

    ContestantDayStatusZoneSchduleAdapter contestantDayStatusZoneSchduleAdapter;

    LinearLayoutManager mLayoutManagerDayTime;

    private  Context mContext;

    List<Integer> Postion= new ArrayList();

    ViewHolder updateViewHolder;

    public ContestantDaySchduleAdapter(Context context, int weekNamePosition, List<DaySchduleList> DaySchduleArrayList)
    {

        this.mDaySchduleArrayList.addAll(DaySchduleArrayList);

        mContext=context;


    }



    public void  updateAdapter(List<DaySchduleList> DaySchduleArrayList)
    {
        this.mDaySchduleArrayList.clear();
        this.mDaySchduleArrayList.addAll(DaySchduleArrayList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_day_schdule, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.DayStatus.setText(mDaySchduleArrayList.get(i).getDayStatus());

       /* if(contestantDayStatusZoneSchduleAdapter!=null){
            contestantDayStatusZoneSchduleAdapter.updateAdapter(mDaySchduleArrayList.get(i).getDayTime());
        }else {

        }*/
        Postion.add(i);

        updateViewHolder =viewHolder;

       /*if(mDaySchduleArrayList.get(i).getDayTime()!=null){
           contestantDayStatusZoneSchduleAdapter = new ContestantDayStatusZoneSchduleAdapter(mContext, -1, mDaySchduleArrayList.get(i).getDayTime());

           mLayoutManagerDayTime = new GridLayoutManager(mContext, 2);

           mLayoutManagerDayTime.setOrientation(GridLayoutManager.VERTICAL);

           viewHolder.mDaySchduleRV.setLayoutManager(mLayoutManagerDayTime);

           viewHolder.mDaySchduleRV.setAdapter(contestantDayStatusZoneSchduleAdapter);
       }*/
        manageClick(0,viewHolder.ViewRV,viewHolder);
    }



    @Override
    public int getItemCount() {
        return mDaySchduleArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {



        RecyclerView mDaySchduleRV;
        TextView DayStatus;
        public View view, ViewRV;


        public ViewHolder(View v) {
            super(v);
            DayStatus=v.findViewById(R.id.dayTitleView);
            mDaySchduleRV=v.findViewById(R.id.ScheduledLiveVideoCallDayRV);
            ViewRV=v.findViewById(R.id.tab_view);
            view=v;
        }
    }


    private void  manageClick(final int position,View view,ViewHolder viewHolder)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{


                  /*  for(int i=0;i<Postion.size();i++){
                        if(Postion.get(i)!=position){
                            if(mDaySchduleArrayList.get(i).getDayTime()!=null){

                            }
                        }
                    }*/


                }catch (Exception ex){
                    Log.i("Error",ex.toString());
                }


            }
        });
    }


}
