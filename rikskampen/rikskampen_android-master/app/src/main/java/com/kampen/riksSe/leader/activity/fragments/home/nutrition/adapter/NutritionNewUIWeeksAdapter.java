package com.kampen.riksSe.leader.activity.fragments.home.nutrition.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.vov.vitamio.utils.Log;

public class NutritionNewUIWeeksAdapter extends RecyclerView.Adapter<NutritionNewUIWeeksAdapter.ViewHolder>  {


    ArrayList<MealType> mNutritionArrayList=new ArrayList<>();

    private  Context mContext;

    private int index;

    public N_DaysDB DaysObj1;

    public int mealNamePosition;

    public int lastSelecteditem=0;

    public NutritionNewUIWeeksAdapter(Context context,int mealNamePosition, List<MealType> weekDBList)
    {

        mNutritionArrayList.addAll(weekDBList);
        mContext=context;
        this.mealNamePosition=mealNamePosition;

        //DaysObj1 = mNutritionArrayList.get(0).getDays();

    }



    public void  updateAdapter(List<MealType> weekDBList)
    {
        mNutritionArrayList.clear();
        this.mNutritionArrayList.addAll(weekDBList);

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_nutrition_new_ui_weeks, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        //viewHolder.habitStartFromTV.setText(getFormattedWeek(Integer.parseInt(mNutritionArrayList.get(i))));
        if(Locale.getDefault().getLanguage().equals("en")){
            viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameEn());
            viewHolder.habitStartFromTV1.setText(mNutritionArrayList.get(i).getNameEn());
        }
        if(Locale.getDefault().getLanguage().equals("sv")){
            viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameSv());
            viewHolder.habitStartFromTV1.setText(mNutritionArrayList.get(i).getNameSv());
        }


        viewHolder.tabWeekLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //mealNamePosition =  i;
                notifyDataSetChanged();

            }
        });


        if(mealNamePosition==i){

            if(mealNamePosition<lastSelecteditem){
                viewHolder.habitStartFromTV.bringToFront();
                setAnimationLeft(viewHolder.tabWeekLayout,mealNamePosition);
                viewHolder.habitStartFromTV.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
                viewHolder.habitStartFromTV.setTextColor(Color.parseColor("#FFFFFF"));
                lastSelecteditem=mealNamePosition;
            }else{
                viewHolder.habitStartFromTV.bringToFront();
                setAnimationRight(viewHolder.tabWeekLayout,mealNamePosition);
                viewHolder.habitStartFromTV.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
                viewHolder.habitStartFromTV.setTextColor(Color.parseColor("#FFFFFF"));
                lastSelecteditem=mealNamePosition;
            }

        }
        else{
            viewHolder.habitStartFromTV.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
            viewHolder.habitStartFromTV.setTextColor(Color.parseColor("#BB9767"));
        }

        manageClick(i,viewHolder.tabWeekLayout);
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
        return mNutritionArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView habitNameTV,habitJoinTV,habitStartFromTV,habitStartFromTV1;

        public ImageView nutritionImage;

        public View view;

        public View tabWeekLayout,tabWeekLayout2;

        public ViewHolder(View v) {
            super(v);
            habitStartFromTV1=v.findViewById(R.id.tab_week2_1);
            habitStartFromTV=v.findViewById(R.id.tab_week2);
            tabWeekLayout=v.findViewById(R.id.tab_week_layout);
            tabWeekLayout2=v.findViewById(R.id.tab_week_layout1);
            view=v;
        }


    }


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{

                    mealNamePosition =  position;

                    notifyDataSetChanged();

                    Intent intent = new Intent();
                    intent.putExtra("MealTypeID",mNutritionArrayList.get(position).getId());
                    intent.setAction("com.tutorialspoint.CUSTOM_INTENT_MEAL_NAME");
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);


                }catch (Exception ex){

                    Log.i("Error",ex.toString());
                }


            }
        });
    }



    public static String  getFormattedWeek(int week)
    {

        String weekstring="";
        int i=week;

        if(i==1) {
            weekstring=(i + "st week");
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
        }

        return  weekstring;
    }



}
