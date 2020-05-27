package com.kampen.riksSe.leader.activity.fragments.home.traings.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;


import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;

import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.WorkOutNewUIDyActivity;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTimePopDaysHoursMinutes;
import static com.kampen.riksSe.utils.UtilityTz.DialogeBoxContestEndDate;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;

public class TrainingAndFitnessAdapter extends RecyclerView.Adapter<TrainingAndFitnessAdapter.ViewHolder>  {


    ArrayList<W_Plans> mTrainingArrayList=new ArrayList<>();


    private Context mContext;
    private long currentWeek;
    private long currentday;

    private int[] WeekArray = {
            R.drawable.ic_week_1,
            R.drawable.ic_week_2,
            R.drawable.ic_week_3,
            R.drawable.ic_week_4,
            R.drawable.ic_week_5,
            R.drawable.ic_week_6,
            R.drawable.ic_week_7,
            R.drawable.ic_week_8,
            R.drawable.ic_week_9,
            R.drawable.ic_week_10
    };



    public TrainingAndFitnessAdapter(Context context,long currentday,long currentWeek,List<W_Plans> trainingArrayList)
    {

        mContext=context;

        this.mTrainingArrayList.addAll(trainingArrayList);
        this.currentWeek =currentWeek;
        this.currentday =currentday;

    }


    public void  updateAdapter(long currentday,long currentWeek,List<W_Plans> trainingArrayList)
    {

        mTrainingArrayList.clear();

        this.mTrainingArrayList.addAll(trainingArrayList);

        this.currentWeek =currentWeek;
        this.currentday =currentday;

        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_stress_relief, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        try {

            if(mTrainingArrayList.get(i).getmWeekImage() !=null &&  mTrainingArrayList.get(i).getmWeekImage().length() >0){
            /*  loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek()));
                viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);*/
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                String currentDateandTime = sdf.format(new Date());
                Realm_User mUser = provideUserLocal(mContext);
                Competition CompitionDate = Repository.getCompitionDate();

                if(CompitionDate.getStartDate()!=null){
                    Boolean ContestStatus = getCompitionStartDate(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                    Boolean ContestEndStatus = getCompitionStartDate(mContext,convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);

                    if (ContestStatus && !ContestEndStatus) {
                        if(mTrainingArrayList.get(i).getmWeek()==currentWeek) {
//                viewHolder.stressNameTV.setText(mTrainingArrayList.get(i).getWeekName());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(80F);
                            }
                            loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.INVISIBLE);

                        }else if(mTrainingArrayList.get(i).getmWeek()>currentWeek){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.INVISIBLE);
                        }
                        else{
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            //viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.INVISIBLE);
                        }
                    }else if(ContestEndStatus){
                        if(mTrainingArrayList.get(i).getmWeek()==currentWeek) {
//                viewHolder.stressNameTV.setText(mTrainingArrayList.get(i).getWeekName());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(80F);
                            }
                            loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);

                        }else if(mTrainingArrayList.get(i).getmWeek()>currentWeek){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                        }
                        else{
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            //viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                        }
                    }else if(!ContestStatus) {
                        if(mTrainingArrayList.get(i).getmWeek()==currentWeek) {
//                viewHolder.stressNameTV.setText(mTrainingArrayList.get(i).getWeekName());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(80F);
                            }
                            loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);

                        }else if(mTrainingArrayList.get(i).getmWeek()>currentWeek){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                        }
                        else{
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            //viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                        }
                    }
                }else{

                    if(mTrainingArrayList.get(i).getmWeek()==currentWeek) {
//                viewHolder.stressNameTV.setText(mTrainingArrayList.get(i).getWeekName());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            viewHolder.ParentView.setElevation(80F);
                        }
                        loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                        viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                        viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                        viewHolder.mLockWorkout.setVisibility(View.VISIBLE);


                    }else if(mTrainingArrayList.get(i).getmWeek()>currentWeek){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            viewHolder.ParentView.setElevation(0F);
                        }
                        loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                        viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                        viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                        viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                    }
                    else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            viewHolder.ParentView.setElevation(0F);
                        }
                        loadImage(viewHolder.nutritionImage, mTrainingArrayList.get(i).getmWeekImage());
                        viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                        //viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
                        viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                        viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                    }
                }

            }else{
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                String currentDateandTime = sdf.format(new Date());
                Realm_User mUser = provideUserLocal(mContext);
                Competition CompitionDate = Repository.getCompitionDate();

                if(CompitionDate.getStartDate()!=null){
                    Boolean ContestStatus = getCompitionStartDate(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                    Boolean ContestEndStatus = getCompitionStartDate(mContext,convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);

                    if (ContestStatus && !ContestEndStatus) {
                        if(mTrainingArrayList.get(i).getmWeek()==currentWeek) {
//                viewHolder.stressNameTV.setText(mTrainingArrayList.get(i).getWeekName());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(80F);
                            }
                            GlideApp
                                    .with(mContext)
                                    .load(R.drawable.avatar_new)
                                    .into(viewHolder.nutritionImage);
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.INVISIBLE);

                        }else if(mTrainingArrayList.get(i).getmWeek()>currentWeek){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            GlideApp
                                    .with(mContext)
                                    .load(R.drawable.avatar_new)
                                    .into(viewHolder.nutritionImage);
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.INVISIBLE);
                        }
                        else{
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            GlideApp
                                    .with(mContext)
                                    .load(R.drawable.avatar_new)
                                    .into(viewHolder.nutritionImage);
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            //viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.INVISIBLE);
                        }

                    }else if(ContestEndStatus){

                        if(mTrainingArrayList.get(i).getmWeek()==currentWeek) {
//                viewHolder.stressNameTV.setText(mTrainingArrayList.get(i).getWeekName());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(80F);
                            }
                            GlideApp
                                    .with(mContext)
                                    .load(R.drawable.avatar_new)
                                    .into(viewHolder.nutritionImage);
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);

                        }else if(mTrainingArrayList.get(i).getmWeek()>currentWeek){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            GlideApp
                                    .with(mContext)
                                    .load(R.drawable.avatar_new)
                                    .into(viewHolder.nutritionImage);
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                        }
                        else{
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            GlideApp
                                    .with(mContext)
                                    .load(R.drawable.avatar_new)
                                    .into(viewHolder.nutritionImage);
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            //viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                        }
                    }else if(!ContestStatus) {
                        if(mTrainingArrayList.get(i).getmWeek()==currentWeek) {
//                viewHolder.stressNameTV.setText(mTrainingArrayList.get(i).getWeekName());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(80F);
                            }
                            GlideApp
                                    .with(mContext)
                                    .load(R.drawable.avatar_new)
                                    .into(viewHolder.nutritionImage);
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);

                        }else if(mTrainingArrayList.get(i).getmWeek()>currentWeek){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            GlideApp
                                    .with(mContext)
                                    .load(R.drawable.avatar_new)
                                    .into(viewHolder.nutritionImage);
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                        }
                        else{
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                viewHolder.ParentView.setElevation(0F);
                            }
                            GlideApp
                                    .with(mContext)
                                    .load(R.drawable.avatar_new)
                                    .into(viewHolder.nutritionImage);
                            viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                            //viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
                            viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                            viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                        }
                    }
                }else{
                    if(mTrainingArrayList.get(i).getmWeek()==currentWeek) {
//                viewHolder.stressNameTV.setText(mTrainingArrayList.get(i).getWeekName());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            viewHolder.ParentView.setElevation(80F);
                        }
                        GlideApp
                                .with(mContext)
                                .load(R.drawable.avatar_new)
                                .into(viewHolder.nutritionImage);
                        viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                        viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                        viewHolder.mLockWorkout.setVisibility(View.VISIBLE);

                    }else if(mTrainingArrayList.get(i).getmWeek()>currentWeek){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            viewHolder.ParentView.setElevation(0F);
                        }
                        GlideApp
                                .with(mContext)
                                .load(R.drawable.avatar_new)
                                .into(viewHolder.nutritionImage);
                        viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                        viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                        viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                    }
                    else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            viewHolder.ParentView.setElevation(0F);
                        }
                        GlideApp
                                .with(mContext)
                                .load(R.drawable.avatar_new)
                                .into(viewHolder.nutritionImage);
                        viewHolder.StartFromTV.setText(NutritionAdapter.getFormattedWeek(mTrainingArrayList.get(i).getmWeek(),mContext));
                        //viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
                        viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
                        viewHolder.mLockWorkout.setVisibility(View.VISIBLE);
                    }
                }
            }

        }catch (Exception e){

            Log.i("E train",e.toString());
        }

        manageClick(i,viewHolder.view);

    }


    private void loadImage(final ImageView imageView, final String imageUrl){

        GlideApp
                .with(mContext)
                .load(imageUrl)
                .error(R.drawable.img_video_empty)
                .into(imageView);


    }


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Toast.makeText(mContext, ""+mTrainingArrayList.get(position).getWeekID(), Toast.LENGTH_SHORT).show();

                try {
                    /*Intent intent = new Intent(mContext, WeekWorkOutActivity.class);
                    intent.putExtra("selected_week",Integer.parseInt(mTrainingArrayList.get(position).getWeekID()));
                    mContext.startActivity(intent);*/

                    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                    String currentDateandTime = sdf.format(new Date());
                    Realm_User mUser = provideUserLocal(mContext);
                    Competition CompitionDate = Repository.getCompitionDate();

                    if(CompitionDate.getStartDate()!=null){
                        Boolean ContestStatus = getCompitionStartDate(mContext,CompitionDate.getStartDate(),currentDateandTime);
                        Boolean ContestEndStatus = getCompitionStartDate(mContext,CompitionDate.getmEndDate(),currentDateandTime);

                        if (ContestStatus && !ContestEndStatus) {

                        Intent intent = new Intent(mContext, WorkOutNewUIDyActivity.class);
                        intent.putExtra("WeekID",mTrainingArrayList.get(position).getmWeek());
                        intent.putExtra("CurrentDayID",mTrainingArrayList.get(position).getmWDays().get(0).getmDay());
                        mContext.startActivity(intent);

                        }else if(ContestEndStatus){
                            DialogeBoxContestEndDate(mContext,mUser);
                        }else if(!ContestStatus) {
                            DialogeBoxContestDate(mContext);
                        }
                    }else{
                        DialogeBoxContestDate(mContext);
                    }



                }
                catch (Exception ex){

            }


            }
        });
    }


    @Override
    public int getItemCount() {
        return mTrainingArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView stressNameTV;
        public  TextView JoinTV,StartFromTV;

        public ImageView nutritionImage,mLockWorkout;

        public View view;
        public View previosWeekFade,ParentView;

        public ViewHolder(View v) {
            super(v);

            StartFromTV=v.findViewById(R.id.timeTV);
            nutritionImage=v.findViewById(R.id.workoutImage);
            mLockWorkout=v.findViewById(R.id.lockImage);
            previosWeekFade=v.findViewById(R.id.previousWeek);
            ParentView=v.findViewById(R.id.parentView);
            view=v;
        }


    }
    public void DialogeBoxContestDate(Context context){

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_box_contest_date_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(promptsView);

        final TextView contestStartTime = (TextView) promptsView.findViewById(R.id.textView1);

        final Button CancelBtn = (Button) promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = (Button) promptsView.findViewById(R.id.okBtn);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        ContestWeekDayTimeModel contestWeekDayTimeModel =new ContestWeekDayTimeModel();


        Realm_User mUser = provideUserLocal(mContext);
        Competition CompitionDate = Repository.getCompitionDate();

        if(CompitionDate.getStartDate()!=null) {

            contestWeekDayTimeModel = CompititionStartDateAndTimePopDaysHoursMinutes(CompitionDate.getStartDate(), currentDateandTime);

            contestStartTime.setText(context.getResources().getString(R.string.Competition_Start_In)+" "+contestWeekDayTimeModel.getDays()+" "+context.getResources().getString(R.string.Competition_Time_Ticker_Days)+" "+ contestWeekDayTimeModel.getHours()+" "+context.getResources().getString(R.string.Competition_Time_Ticker_Hours)+" "+ contestWeekDayTimeModel.getMiniutes()+" "+context.getResources().getString(R.string.Competition_Time_Ticker_Minutes));

        }else{
            contestStartTime.setText(context.getResources().getString(R.string.Competition_Start_Soon));
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder

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
