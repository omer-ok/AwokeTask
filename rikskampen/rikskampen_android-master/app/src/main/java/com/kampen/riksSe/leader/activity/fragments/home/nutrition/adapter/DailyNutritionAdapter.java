/*
package com.kampen.riksSe.leader.activity.fragments.home.nutrition.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail.NutritionActivity;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.WeekNutritionModel;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DayDB;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DailyNutritionAdapter extends RecyclerView.Adapter<DailyNutritionAdapter.ViewHolder>  {


    private N_DayDB mDay;

    private Context mContext;

    private  int weekId;

    private String NutritionTitle;

    public DailyNutritionAdapter(Context context,N_DayDB  mday,int weekId)
    {
        mContext=context;
        this.weekId=weekId;
        mDay=mday;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_week_nutrition, viewGroup, false);

        return new DailyNutritionAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {




        try {

           //DayNutritionDB nutrition= mDay.getDayactivitesList().get(i);
            //nutrition.getMediaUrl()

                   //Picasso.with(mContext).load(nutrition.getMediaUrl()).into(viewHolder.thumbNail);



           // Picasso.get().load(nutrition.getMediaUrl()).into(viewHolder.thumbNail);


            loadImage(viewHolder.thumbNail,nutrition.getMediaUrl());
            */
/*Glide.with(mContext)
                    .load(nutrition.getMediaUrl())
                    .into(viewHolder.thumbNail);*//*

            //viewHolder.headingTV.setText(mDailyNutritionArrayList.get(i).getWeekName());
           // viewHolder.joinTV.setText(mDailyNutritionArrayList.get(i).getJoined());
            viewHolder.headingTV.setText(nutrition.getMealType());

            manageClick(i,viewHolder.view);
        }catch (Exception ex)
        {
            ex.toString();
        }

    }



    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DayNutritionDB nutrition= mDay.getDayactivitesList().get(position);
                Intent intent = new Intent(mContext, NutritionActivity.class);
                intent.putExtra("selected_week",weekId);
                intent.putExtra("selected_day",mDay.getDayID());
                intent.putExtra("selected_video",(position));
                intent.putExtra("Title",nutrition.getTitle());
                intent.putExtra("RecipeName",nutrition.getMealType());
                mContext.startActivity(intent);

                */
/*try {



                    //DayNutritionDB nutrition= mDay.getDayactivitesList().get(position);
                    if(mDay == null){

                    }else{

                    }

                }catch (Exception ex){
                   // DayNutritionDB nutrition= mDay.getDayactivitesList().get(position);
                }
*//*


            }
        });
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
        try {
            //return mDay.getDayactivitesList().size();
        }catch (Exception ex){

        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView headingTV,joinTV,timeTV;

        public ImageView thumbNail;

        public View view;

        public ViewHolder(View v) {
            super(v);
            headingTV=v.findViewById(R.id.headingTV);
            joinTV=v.findViewById(R.id.joinTV);
            timeTV=v.findViewById(R.id.timeTV);
            thumbNail=v.findViewById(R.id.thumbNail);
            view=v;

        }


    }


    private   ArrayList<WeekNutritionModel> generateDummyData()
    {
        ArrayList<WeekNutritionModel> hmArray=new ArrayList<>();
        String [][] nutritionNames=new String[5][2];
        nutritionNames[0][0]="Breakfast";
        nutritionNames[1][0]="Brunch";
        nutritionNames[2][0]="Lunch";
        nutritionNames[3][0]="Snack";
        nutritionNames[4][0]="Dinner";



        nutritionNames[0][1]=(R.drawable.breakfast)+"";
        nutritionNames[1][1]=(R.drawable.snacks)+"";
        nutritionNames[2][1]=(R.drawable.lunch)+"";
        nutritionNames[3][1]=(R.drawable.snacks)+"";
        nutritionNames[4][1]=(R.drawable.dinners)+"";


        for(int i=0; i<nutritionNames.length; i++)
        {
            WeekNutritionModel hm=new WeekNutritionModel();
            hm.setWeekID(i+"");
            hm.setWeekName(nutritionNames[i][0]);
            hm.setImagePath(Integer.parseInt(nutritionNames[i][1])+"");
            //hm.setJoined();


            hmArray.add(hm);
        }


        return  hmArray;
    }

}
*/
