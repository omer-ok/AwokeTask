package com.kampen.riksSe.leader.activity.fragments.home.traings.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
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
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.NutritionNewUIMealActivity;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionMealOptionsDB;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.vov.vitamio.utils.Log;

import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTimePopDaysHoursMinutes;
import static com.kampen.riksSe.utils.UtilityTz.DialogeBoxContestEndDate;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.ViewHolder>  {


    ArrayList<MealType> mNutritionArrayList=new ArrayList<>();

    private  Context mContext;
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


    public NutritionAdapter(Context context, List<MealType> weekDBList)
    {

        mNutritionArrayList.addAll(weekDBList);
        mContext=context;
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
                .inflate(R.layout.item_nutrition, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


       /* if(mNutritionArrayList.get(i).getWeekName().equals("Recipe Schdule")){
            GlideApp
                    .with(viewHolder.itemView.getContext())
                    .load(R.drawable.icon_nutrition_empty_img)
                    .into(viewHolder.nutritionImage);

            viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getWeekName());

        }
*/
       try{
           SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
           String currentDateandTime = sdf.format(new Date());
           Realm_User mUser = provideUserLocal(mContext);
           Competition CompitionDate = Repository.getCompitionDate();
           if(CompitionDate.getStartDate()!=null) {
               Boolean ContestStatus = getCompitionStartDate(mContext, convertUTCToLocalTime(CompitionDate.getStartDate()), currentDateandTime);
               Boolean ContestEndStatus = getCompitionStartDate(mContext, convertUTCToLocalTime(CompitionDate.getmEndDate()), currentDateandTime);

               if (ContestStatus && !ContestEndStatus) {
                   if(mNutritionArrayList!=null && mNutritionArrayList.size()>0){
                       if(Locale.getDefault().getLanguage().equals("en")){
                           if(mNutritionArrayList.get(i).getImage()!=null){
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameEn());
                               loadImage(viewHolder.nutritionImage, mNutritionArrayList.get(i).getImage());
                           }else {
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameEn());
                               GlideApp
                                       .with(mContext)
                                       .load(R.drawable.icon_nutrition_empty_img)
                                       .into(viewHolder.nutritionImage);
                           }
                           viewHolder.mLockView.setVisibility(View.INVISIBLE);
                       }
                       if(Locale.getDefault().getLanguage().equals("sv")){
                           if(mNutritionArrayList.get(i).getImage()!=null){
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameSv());
                               loadImage(viewHolder.nutritionImage, mNutritionArrayList.get(i).getImage());

                           }else {
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameSv());
                               GlideApp
                                       .with(mContext)
                                       .load(R.drawable.icon_nutrition_empty_img)
                                       .into(viewHolder.nutritionImage);

                           }
                           viewHolder.mLockView.setVisibility(View.INVISIBLE);
                       }
                   }
               } else if (ContestEndStatus) {
                   if(mNutritionArrayList!=null && mNutritionArrayList.size()>0){
                       if(Locale.getDefault().getLanguage().equals("en")){
                           if(mNutritionArrayList.get(i).getImage()!=null){
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameEn());
                               loadImage(viewHolder.nutritionImage, mNutritionArrayList.get(i).getImage());
                           }else {
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameEn());
                               GlideApp
                                       .with(mContext)
                                       .load(R.drawable.icon_nutrition_empty_img)
                                       .into(viewHolder.nutritionImage);
                           }
                           viewHolder.mLockView.setVisibility(View.VISIBLE);
                       }
                       if(Locale.getDefault().getLanguage().equals("sv")){
                           if(mNutritionArrayList.get(i).getImage()!=null){
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameSv());
                               loadImage(viewHolder.nutritionImage, mNutritionArrayList.get(i).getImage());

                           }else {
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameSv());
                               GlideApp
                                       .with(mContext)
                                       .load(R.drawable.icon_nutrition_empty_img)
                                       .into(viewHolder.nutritionImage);

                           }
                           viewHolder.mLockView.setVisibility(View.VISIBLE);
                       }
                   }

               } else if (!ContestStatus) {
                   if(mNutritionArrayList!=null && mNutritionArrayList.size()>0){
                       if(Locale.getDefault().getLanguage().equals("en")){
                           if(mNutritionArrayList.get(i).getImage()!=null){
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameEn());
                               loadImage(viewHolder.nutritionImage, mNutritionArrayList.get(i).getImage());
                           }else {
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameEn());
                               GlideApp
                                       .with(mContext)
                                       .load(R.drawable.icon_nutrition_empty_img)
                                       .into(viewHolder.nutritionImage);
                           }
                           viewHolder.mLockView.setVisibility(View.VISIBLE);
                       }
                       if(Locale.getDefault().getLanguage().equals("sv")){
                           if(mNutritionArrayList.get(i).getImage()!=null){
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameSv());
                               loadImage(viewHolder.nutritionImage, mNutritionArrayList.get(i).getImage());

                           }else {
                               viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getNameSv());
                               GlideApp
                                       .with(mContext)
                                       .load(R.drawable.icon_nutrition_empty_img)
                                       .into(viewHolder.nutritionImage);

                           }
                           viewHolder.mLockView.setVisibility(View.VISIBLE);
                       }
                   }
               } else {

               }
           }else{

           }

       }catch (Exception e){
           Log.i("NutritionImg",e.toString());
       }
        manageClick(i,viewHolder.view);



/*        if(mNutritionArrayList.get(i). !=null &&  mNutritionArrayList.get(i).getImagePath().length() >0 ){

            if(mNutritionArrayList.get(i).getCurrentWeek().equals("present")) {
                //viewHolder.ShadowView.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    viewHolder.ParentView.setElevation(80F);
                }
                loadImage(viewHolder.nutritionImage, mNutritionArrayList.get(i).getImagePath());
                viewHolder.habitStartFromTV.setText(getFormattedWeek(Integer.parseInt(mNutritionArrayList.get(i).getWeekName())));
                viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
            }else if(mNutritionArrayList.get(i).getCurrentWeek().equals("future")){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    viewHolder.ParentView.setElevation(0F);
                }
                loadImage(viewHolder.nutritionImage, mNutritionArrayList.get(i).getImagePath());
                viewHolder.habitStartFromTV.setText(getFormattedWeek(Integer.parseInt(mNutritionArrayList.get(i).getWeekName())));
                viewHolder.previosWeekFade.setVisibility(View.INVISIBLE);
            }
            else{
                loadImage(viewHolder.nutritionImage, mNutritionArrayList.get(i).getImagePath());
                viewHolder.habitStartFromTV.setText(getFormattedWeek(Integer.parseInt(mNutritionArrayList.get(i).getWeekName())));
                viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
            }
        }*/




    }


    private void loadImage(final ImageView imageView, final String imageUrl){

        GlideApp
                .with(mContext)
                .load(imageUrl)
                .error(R.drawable.icon_nutrition_empty_img)
                .into(imageView);

        /*Picasso.get()
                .load(imageUrl)

                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")) {
                            updatedImageUrl = imageUrl.replace("https", "http");
                        } else {
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);
                    }
                });*/
    }

    @Override
    public int getItemCount() {
        return mNutritionArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView habitNameTV,habitJoinTV,habitStartFromTV;

        public ImageView nutritionImage,mLockView;

        public View previosWeekFade,ParentView,ShadowView;

        public View view;

        public ViewHolder(View v) {
            super(v);
            //habitJoinTV=v.findViewById(R.id.habitJoinTV);
            habitStartFromTV=v.findViewById(R.id.timeTV);
            nutritionImage=v.findViewById(R.id.nutrition_image);
            mLockView=v.findViewById(R.id.lockView);
            previosWeekFade=v.findViewById(R.id.previousWeek);
            ParentView=v.findViewById(R.id.parentView);


            view=v;
        }


    }


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{



                    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                    String currentDateandTime = sdf.format(new Date());
                    Realm_User mUser = provideUserLocal(mContext);
                    Competition CompitionDate = Repository.getCompitionDate();

                    if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate() != null) {
                        Boolean ContestStatus = getCompitionStartDate(mContext, convertUTCToLocalTime(CompitionDate.getStartDate()), currentDateandTime);
                        Boolean ContestEndStatus = getCompitionStartDate(mContext,convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);

                        if (ContestStatus && !ContestEndStatus) {

                            Intent intent = new Intent(mContext, NutritionNewUIMealActivity.class);
                            intent.putExtra("MealTypeID", mNutritionArrayList.get(position).getId());
                            mContext.startActivity(intent);

                        }else if(ContestEndStatus){
                            DialogeBoxContestEndDate(mContext,mUser);
                        }else if(!ContestStatus) {
                            DialogeBoxContestDate(mContext);
                        }
                    }else {
                        DialogeBoxContestDate(mContext);
                    }

                }catch (Exception ex){

                }


            }
        });
    }



    public static String  getFormattedWeek(long week,Context context)
    {

        String weekstring="";
        String  weekName =  context.getResources().getString(R.string.Week);
        long i=week;

        if(i==1)
        {

            weekstring=(weekName+" "+i );
        }
        else if(i==2)
        {
            weekstring=(weekName+" "+i );
        }
        else if(i==3)
        {
            weekstring=(weekName+" "+i );
        }
        else
        {
            weekstring=(weekName+" "+i );
        }

        return  weekstring;
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
        // mUser.getContestStartDate()
        Competition CompitionDate = Repository.getCompitionDate();

        if(CompitionDate.getStartDate()!=null) {
            contestWeekDayTimeModel = CompititionStartDateAndTimePopDaysHoursMinutes(convertUTCToLocalTime(CompitionDate.getStartDate()), currentDateandTime);

            contestStartTime.setText(context.getResources().getString(R.string.Competition_Start_In)+ contestWeekDayTimeModel.getDays()+" "+context.getResources().getString(R.string.Competition_Time_Ticker_Days)+ contestWeekDayTimeModel.getHours()+" "+context.getResources().getString(R.string.Competition_Time_Ticker_Hours)+ contestWeekDayTimeModel.getMiniutes()+" "+context.getResources().getString(R.string.Competition_Time_Ticker_Minutes));

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
