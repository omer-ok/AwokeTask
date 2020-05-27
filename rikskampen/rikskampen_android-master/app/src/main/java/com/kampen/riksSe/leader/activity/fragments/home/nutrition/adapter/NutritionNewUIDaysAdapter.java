package com.kampen.riksSe.leader.activity.fragments.home.nutrition.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MainIngredient;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Meal;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NutritionNewUIDaysAdapter extends RecyclerView.Adapter<NutritionNewUIDaysAdapter.ViewHolder>  {


    ArrayList<MainIngredient> mNutritionArrayList=new ArrayList<>();

    private  Context mContext;
    int index = 0;


    public NutritionNewUIDaysAdapter(Context context, List<MainIngredient> weekDBList)
    {
        mNutritionArrayList.addAll(weekDBList);
        mContext=context;
    }



    public void  updateAdapter(List<MainIngredient> weekDBList)
    {
        mNutritionArrayList.clear();
        this.mNutritionArrayList.addAll(weekDBList);
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
            viewHolder.mealValue.setText(mNutritionArrayList.get(i).getTitleEn());
        }
        if(Locale.getDefault().getLanguage().equals("sv")){
            viewHolder.mealValue.setText(mNutritionArrayList.get(i).getTitleSv());
        }


        if(index==i){
            viewHolder.mealValue.setTextColor(Color.parseColor("#776E6E"));
            viewHolder.mealValue.setTextSize(17);
            viewHolder.mealValue.setTypeface(null, Typeface.BOLD);
        }
        else{
            viewHolder.mealValue.setTextColor(Color.parseColor("#999292"));
            viewHolder.mealValue.setTextSize(12);
            viewHolder.mealValue.setTypeface(null, Typeface.BOLD);
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
        return mNutritionArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        View itemView;
        TextView mealValue;
        public View view;

        public ViewHolder(View v) {
            super(v);
            itemView=v.findViewById(R.id.mealTypeView);
            mealValue=v.findViewById(R.id.meal_Type);
            view=v;
        }


    }


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{

                    index =  position;
                    notifyDataSetChanged();


                    Intent intent = new Intent();
                    intent.putExtra("MealIngrediantID",mNutritionArrayList.get(position).getId());
                    intent.setAction("com.tutorialspoint.CUSTOM_INTENT_MEAL_TYPE");
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

                }catch (Exception ex){

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
