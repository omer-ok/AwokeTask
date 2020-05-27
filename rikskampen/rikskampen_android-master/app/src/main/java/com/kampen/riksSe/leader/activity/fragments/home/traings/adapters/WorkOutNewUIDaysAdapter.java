package com.kampen.riksSe.leader.activity.fragments.home.traings.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Day;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkOutNewUIDaysAdapter extends RecyclerView.Adapter<WorkOutNewUIDaysAdapter.ViewHolder>  {


    ArrayList<W_Day> mTrainingArrayList=new ArrayList<>();

    private  Context mContext;
    int index = 0;
    int dayNamePosition;


    public WorkOutNewUIDaysAdapter(Context context,int dayNamePosition, List<W_Day> weekDBList)
    {
        mTrainingArrayList.addAll(weekDBList);
        mContext=context;
        this.dayNamePosition=dayNamePosition;
    }



    public void  updateAdapter(List<W_Day> weekDBList)
    {
        mTrainingArrayList.clear();
        this.mTrainingArrayList.addAll(weekDBList);

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_nutrition_new_ui_days, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if(Locale.getDefault().getLanguage().equals("en")){
            viewHolder.dayValue.setText(getFormattedDaysEng(mTrainingArrayList.get(i).getmDay()));
        }
        if(Locale.getDefault().getLanguage().equals("sv")){
            viewHolder.dayValue.setText(getFormattedDaysSV(mTrainingArrayList.get(i).getmDay()));
        }


        if(dayNamePosition==i){
            viewHolder.dayValue.setTextColor(Color.parseColor("#776E6E"));
            viewHolder.dayValue.setTextSize(17);
            viewHolder.dayValue.setTypeface(null, Typeface.BOLD);
        }
        else{
            viewHolder.dayValue.setTextColor(Color.parseColor("#999292"));
            viewHolder.dayValue.setTextSize(12);
            viewHolder.dayValue.setTypeface(null, Typeface.BOLD);
        }

        manageClick(i,viewHolder.itemView);
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

    @Override
    public int getItemCount() {
        return mTrainingArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        View itemView;
        TextView dayValue;
        public View view;

        public ViewHolder(View v) {
            super(v);
            itemView=v.findViewById(R.id.mealTypeView);
            dayValue=v.findViewById(R.id.meal_Type);
            view=v;
        }


    }


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{

                    dayNamePosition =  position;
                    notifyDataSetChanged();


                    Intent intent = new Intent();
                    intent.putExtra("DayID",mTrainingArrayList.get(position).getmDay());
                    intent.setAction("com.tutorialspoint.CUSTOM_INTENT_DAY_NAME");
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

                }catch (Exception ex){

                }


            }
        });
    }



    public static String  getFormattedDaysSV(int day)
    {

        String weekstring="";
        int i=day;

        if(i==1) {
            weekstring=("Mån");
        }
        else if(i==2)
        {
            weekstring=("Tis");
        }
        else if(i==3)
        {
            weekstring=("Ons");
        }
        else if(i==4)
        {
            weekstring=("Tors");
        }
        else if(i==5)
        {
            weekstring=("Fre");
        }
        else if(i==6)
        {
            weekstring=("Lör");
        }
        else if(i==7)
        {
            weekstring=("Sön");
        }

        return  weekstring;
    }

    public static String  getFormattedDaysEng(int day)
    {

        String weekstring="";
        int i=day;

        if(i==1) {
            weekstring=("Mon");
        }
        else if(i==2)
        {
            weekstring=("Tus");
        }
        else if(i==3)
        {
            weekstring=("Wed");
        }
        else if(i==4)
        {
            weekstring=("Thur");
        }
        else if(i==5)
        {
            weekstring=("Fri");
        }
        else if(i==6)
        {
            weekstring=("Sat");
        }
        else if(i==7)
        {
            weekstring=("Sun");
        }

        return  weekstring;
    }

}
