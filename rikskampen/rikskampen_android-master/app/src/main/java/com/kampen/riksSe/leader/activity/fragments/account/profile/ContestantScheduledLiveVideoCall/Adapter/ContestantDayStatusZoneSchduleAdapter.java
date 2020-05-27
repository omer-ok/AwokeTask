package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.SchduleSlots;
import com.kampen.riksSe.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.vov.vitamio.utils.Log;

import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToTimeFormat;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToTimeFormatUI;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;

public class ContestantDayStatusZoneSchduleAdapter extends RecyclerView.Adapter<ContestantDayStatusZoneSchduleAdapter.ViewHolder>  {




    private  Context mContext;

    ArrayList<SchduleSlots> mStatusZoneDaySchduleArrayList=new ArrayList<>();
    ArrayList<SchduleSlots>mStatusZoneDaySchduleArrayList2=new ArrayList<>();


    private int index;

    public int mDayTimePosition;

    public int lastSelecteditem;

    Boolean mReservedSlotStatus=false;

    public ContestantDayStatusZoneSchduleAdapter(Context context, int DayTimePosition,Boolean ReservedSlotStatus ,List<SchduleSlots> StatusZoneDaySchduleArrayList)
    {


        this.mStatusZoneDaySchduleArrayList.addAll(StatusZoneDaySchduleArrayList);
        this.mStatusZoneDaySchduleArrayList2.addAll(StatusZoneDaySchduleArrayList);
        mContext=context;
        this.mDayTimePosition=DayTimePosition;
        this.lastSelecteditem=DayTimePosition;
        this.mReservedSlotStatus=ReservedSlotStatus;


    }



    public void  updateAdapter(List<SchduleSlots> StatusZoneDaySchduleArrayList, int DayTimePosition)
    {

        this.mStatusZoneDaySchduleArrayList.clear();
        this.mStatusZoneDaySchduleArrayList2.clear();
        this.mStatusZoneDaySchduleArrayList.addAll(StatusZoneDaySchduleArrayList);
        this.mStatusZoneDaySchduleArrayList2.addAll(StatusZoneDaySchduleArrayList);
        this.mDayTimePosition=DayTimePosition;
        this.lastSelecteditem=DayTimePosition;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_day_status_zone_schdule, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDateandTimeStart = sdf.format(new Date());
        String uTcToLocalTimeSessionStartTime = convertUTCToLocalTime(currentDateandTimeStart+" "+mStatusZoneDaySchduleArrayList.get(i).getSessionStartsAt());
        String uTcToLocalTimeSessionEmdTime = convertUTCToLocalTime(currentDateandTimeStart+" "+mStatusZoneDaySchduleArrayList.get(i).getSessionEndsAt());
        String mSessionStartDate = convertTimeToTimeFormatUI(convertDateTimeToTimeFormat(uTcToLocalTimeSessionStartTime));
        String mSessionEndDate = convertTimeToTimeFormatUI(convertDateTimeToTimeFormat(uTcToLocalTimeSessionEmdTime));



        viewHolder.dayTimeTV.setText(mSessionStartDate+" - "+mSessionEndDate);
       // viewHolder.dayTimeTV1.setText(mSessionStartDate2+" - "+mSessionEndDate2);

        if(mStatusZoneDaySchduleArrayList.get(i).getStatus().equals("available") || mStatusZoneDaySchduleArrayList.get(i).getStatus().equals("reserved")){
           /* if(mStatusZoneDaySchduleArrayList.get(i).getStatus().equals("reserved")){
                mReservedSlotStatus =true;
            }*/
            viewHolder.mUnavailableDay.setVisibility(View.GONE);
            if(mDayTimePosition==i){
          /*  viewHolder.habitStartFromTV.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
            viewHolder.habitStartFromTV.setTextColor(Color.parseColor("#FFFFFF"));*/
                if(mDayTimePosition<lastSelecteditem){
                    viewHolder.tabView.bringToFront();
                    viewHolder.tabDayLayout.bringToFront();
                    viewHolder.dayTimeTV.bringToFront();
                    //setAnimationLeft(viewHolder.tabWeekLayout,weekNamePosition);
                    viewHolder.dayTimeTV.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
                    viewHolder.dayTimeTV.setTextColor(Color.parseColor("#FFFFFF"));

                    lastSelecteditem=mDayTimePosition;

                }else{
                    viewHolder.tabView.bringToFront();
                    viewHolder.tabDayLayout.bringToFront();
                    viewHolder.dayTimeTV.bringToFront();
                    //setAnimationRight(viewHolder.tabWeekLayout,weekNamePosition);
                    viewHolder.dayTimeTV.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
                    viewHolder.dayTimeTV.setTextColor(Color.parseColor("#FFFFFF"));

                    lastSelecteditem=mDayTimePosition;
                }
            }
            else{
                viewHolder.dayTimeTV.setBackgroundResource(R.drawable.tab_schdule_available_white_rounded_courners);
                viewHolder.dayTimeTV.setTextColor(Color.parseColor("#BB9767"));
            }

            manageClick(i,viewHolder.tabDayLayout);

        }else if(mStatusZoneDaySchduleArrayList.get(i).getStatus().equals("unavailable")){

            viewHolder.mUnavailableDay.setVisibility(View.VISIBLE);
            viewHolder.dayTimeTV.setBackgroundResource(R.drawable.tab_schdule_available_white_rounded_courners);
            viewHolder.dayTimeTV.setTextColor(Color.parseColor("#BB9767"));

        }/*else if(mStatusZoneDaySchduleArrayList.get(i).getStatus().equals("reserved")){
            viewHolder.mUnavailableDay.setVisibility(View.GONE);
            ReservedSlotStatus =true;
            viewHolder.tabView.bringToFront();
            viewHolder.tabDayLayout.bringToFront();
            viewHolder.dayTimeTV.bringToFront();
            //setAnimationRight(viewHolder.tabWeekLayout,weekNamePosition);
            viewHolder.dayTimeTV.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
            viewHolder.dayTimeTV.setTextColor(Color.parseColor("#FFFFFF"));

            lastSelecteditem=mDayTimePosition;
        }*/







    }



    @Override
    public int getItemCount() {
        return mStatusZoneDaySchduleArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {



        public  TextView habitNameTV,habitJoinTV,dayTimeTV,dayTimeTV1;

        public ImageView nutritionImage;

        public View view;

        public View tabView;

        public View tabDayLayout,mUnavailableDay;

        public ViewHolder(View v) {
            super(v);
            dayTimeTV=v.findViewById(R.id.tab_dayTime2);
            dayTimeTV1=v.findViewById(R.id.tab_dayTime2_1);
            tabDayLayout=v.findViewById(R.id.tab_dayTime_layout);
            tabView=v.findViewById(R.id.tab_view);
            mUnavailableDay=v.findViewById(R.id.unavailableDay);
            view=v;
        }
    }


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{


                    if(mReservedSlotStatus){

                        if(mDayTimePosition !=position){
                            DialogeBoxReschdule(position,mStatusZoneDaySchduleArrayList2.get(position).getId());
                        }else{
                            Toast.makeText(mContext, "You have already booked this slot", Toast.LENGTH_SHORT).show();
                        }
                        
                    }else{
                        mDayTimePosition =  position;
                        notifyDataSetChanged();
                        //mReservedSlotStatus =true;
                        Intent intent2 = new Intent();
                        intent2.putExtra("slotID",mStatusZoneDaySchduleArrayList2.get(position).getId());
                        intent2.setAction("com.Rikskampen.CUSTOM_INTENT_BOOK_SCHDULE_SLOT");
                        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent2);
                    }

                }catch (Exception ex){
                    Log.i("Error",ex.toString());
                }


            }
        });
    }


    public void DialogeBoxReschdule(int position,int SlotID){

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.dialog_box_reschdule_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setView(promptsView);

        final TextView contestStartTime = promptsView.findViewById(R.id.textView1);

        final Button CancelBtn = promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = promptsView.findViewById(R.id.okBtn);
        final EditText mCommentReSchdule = promptsView.findViewById(R.id.commentReSchdule);


        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder

                mDayTimePosition =  position;
                notifyDataSetChanged();
                //mReservedSlotStatus = true;

                Intent intent2 = new Intent();
                intent2.putExtra("slotID",SlotID);
                intent2.putExtra("Comment",mCommentReSchdule.getText().toString());
                intent2.setAction("com.Rikskampen.CUSTOM_INTENT_RE_BOOK_SCHDULE_SLOT");
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent2);
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

}
