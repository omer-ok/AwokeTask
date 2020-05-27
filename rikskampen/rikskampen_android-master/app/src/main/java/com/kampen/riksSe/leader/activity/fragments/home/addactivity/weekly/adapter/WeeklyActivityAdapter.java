package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.DailyDiaryAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.DailyDiaryHistoryAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.adapterListModel.activityAdapterListModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.QuestionResponceModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_DayActivityList;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.DailyActivity;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.model.WeeklyActivityModel;
import com.kampen.riksSe.utils.Constants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kampen.riksSe.utils.Constants.DATE_TIME_UI_FORMAT;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeForUI;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalDate;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;

public class WeeklyActivityAdapter extends  RecyclerView.Adapter<WeeklyActivityAdapter.MyViewHolder>{



    ArrayList<WeeklyActivityModel>  list;

    List<activityAdapterListModel>  ma_dayDB;

    List<QuestionResponceModel> mdiaryTodayQuestions;

    int WeekNo;

    A_DayActivityList ma_dayActivityList;

    A_WeekDB a_weekDB;

    Context context;


    public  WeeklyActivityAdapter(Context context, int WeekNo , List<activityAdapterListModel> aDayDB,List<QuestionResponceModel> diaryTodayQuestions)
    {

        this.WeekNo = WeekNo;
        list=getDummyData();
        this.ma_dayDB = aDayDB;
        this.mdiaryTodayQuestions =diaryTodayQuestions;
        this.context = context;

    }

    public void  updateAdapter(List<activityAdapterListModel>  aDayDB)
    {
        ma_dayDB.clear();
        this.ma_dayDB.addAll(aDayDB);

        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


      View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weekly_activity,viewGroup,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDateandTime = sdf.format(new Date());
        if(ma_dayDB.get(i) !=null){

                String apiActivityTime = ma_dayDB.get(i).getmDate();
            if(ma_dayDB.get(i).getmMediaImage()!=null && !ma_dayDB.get(i).getmMediaImage().isEmpty()) {
                loadImage(myViewHolder.weeklyPick, ma_dayDB.get(i).getmMediaImage());
            }else{
                  GlideApp
                        .with(myViewHolder.itemView.getContext())
                        .load(R.drawable.avatar_new)
                        .into(myViewHolder.weeklyPick);
            }
                //myViewHolder.stepsValue.setText(ma_dayDB.get(i).getDayactivitesList().get(0).getSteps());
                long StepsInt = ma_dayDB.get(i).getmSteps();

                if(StepsInt < 1000){
                    myViewHolder.stepsValue.setText(Math.abs(StepsInt)+"");
                }else if(StepsInt < 1000000){
                    double stepsValue = Math.abs(StepsInt)/1000.0;
                    myViewHolder.stepsValue.setText(String.format("%.2f",stepsValue)+""+"K");
                }else if(StepsInt < 100000000){
                    double stepsValue = Math.abs(StepsInt)/1000000.0;
                    myViewHolder.stepsValue.setText(String.format("%.2f",stepsValue)+""+"M");
                }else{
                    double stepsValue = Math.abs(StepsInt)/100000000.0;
                    myViewHolder.stepsValue.setText(String.format("%.2f",stepsValue)+""+"B");
                }
                myViewHolder.starvalue.setText(ma_dayDB.get(i).getmStars()+"");

                SimpleDateFormat inFormat = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                Date date = null;
                try {
                    date = inFormat.parse(ma_dayDB.get(i).getmDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                String DayName = outFormat.format(date);
                //activityAdapterListModel1.setDayName(DayName);
                myViewHolder.DayTV.setText(DayName);

                //myViewHolder.DayTV.setText(ma_dayDB.get(i).getDayName());
                if(ma_dayDB.get(i).getmLocationAddress()!=null){
                    myViewHolder.DayAddress.setText(ma_dayDB.get(i).getmLocationAddress()+"");
                }else{
                    myViewHolder.DayAddress.setText(context.getResources().getString(R.string.General_NA));
                }
                myViewHolder.date.setText(convertTimeForUI(apiActivityTime)+"");
                if(ma_dayDB.get(i).getmWeight()!=0){
                    myViewHolder.weight.setText(ma_dayDB.get(i).getmWeight()+"");
                }else{
                    myViewHolder.weight.setText("N/A");
                }
                if(ma_dayDB.get(i).getmWaist()!=0){
                    myViewHolder.waist.setText(ma_dayDB.get(i).getmWaist()+"");
                }else{
                    myViewHolder.waist.setText("N/A");
                }

                double calories = ma_dayDB.get(i).getmCalories();
                if(calories>999){
                    myViewHolder.calUnit.setText("(Kcal)");
                    double CaloriesValue = calories/1000.0;
                    myViewHolder.calValue.setText(String.format("%.2f",CaloriesValue)+""+"K");
                }else{
                    myViewHolder.calUnit.setText("(Kcal)");
                    myViewHolder.calValue.setText(String.format("%.2f",calories)+"");
                }
                double distance = ma_dayDB.get(i).getmDistance();
                if(distance>999){
                    myViewHolder.disUnit.setText("(Km)");
                    double DistanceValue = distance/1000.0;
                    myViewHolder.distance.setText(DistanceValue+""+"K");
                }else{
                    myViewHolder.disUnit.setText("(Km)");
                    double DistanceValue = distance/1000.0;
                    myViewHolder.distance.setText(String.format("%.2f",DistanceValue)+"");
                }

  /*          myViewHolder.mDayDescription.setMovementMethod(new ScrollingMovementMethod());
            myViewHolder.mDayDescription.setScroller(new Scroller(context));
            myViewHolder.mDayDescription.setVerticalScrollBarEnabled(true);
*/
           /* mScrollView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    myViewHolder.mDayDescription.getParent().requestDisallowInterceptTouchEvent(false);

                    return false;
                }
            });
*/


/*            myViewHolder.mDayDescription.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    myViewHolder.mDayDescription.getParent().requestDisallowInterceptTouchEvent(true);

                    return false;
                }
            });*/
            if(ma_dayDB.get(i).getDailyDiaryQuestionModel().getmDayDescription()!=null || ma_dayDB.get(i).getDailyDiaryQuestionModel().ismDayStatus()!=null || ma_dayDB.get(i).getDailyDiaryQuestionModel().getmQuestions()!=null){
                //if(ma_dayDB.get(i).getDailyDiaryQuestionModel().getId()==null && ma_dayDB.get(i).getDailyDiaryQuestionModel().getDate().equals(currentDateandTime)){

                    if(ma_dayDB.get(i).getDailyDiaryQuestionModel().getmDayDescription()!=null){
                        myViewHolder.mdayDecView.setVisibility(View.VISIBLE);
                        myViewHolder.mDayDescription.setText(ma_dayDB.get(i).getDailyDiaryQuestionModel().getmDayDescription());
                    }else{
                        //myViewHolder.mdayDecView.setVisibility(View.GONE);
                    }

                    if(ma_dayDB.get(i).getDailyDiaryQuestionModel().ismDayStatus()!=null){
                        myViewHolder.mthumbCheck.setVisibility(View.VISIBLE);
                        if(ma_dayDB.get(i).getDailyDiaryQuestionModel().ismDayStatus()){
                            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                myViewHolder.mThumpUp.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorPrimary));
                            }*/
                            myViewHolder.mThumpUp.setBackgroundResource(R.drawable.ic_thumb_up_selected);
                            myViewHolder.mThumpDown.setBackgroundResource(R.drawable.ic_thumbdown);
                        }else if(!ma_dayDB.get(i).getDailyDiaryQuestionModel().ismDayStatus()){
                            myViewHolder.mThumpDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);
                            myViewHolder.mThumpUp.setBackgroundResource(R.drawable.ic_thumbup);
                            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                myViewHolder.mThumpDown.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorPrimary));
                            }*/
                        }
                    }else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            myViewHolder.mThumpUp.setBackgroundTintList(null);
                            myViewHolder.mThumpDown.setBackgroundTintList(null);
                            //myViewHolder.mthumbCheck.setVisibility(View.GONE);
                        }
                    }

                    if(ma_dayDB.get(i).getDailyDiaryQuestionModel().getmQuestions()!=null){

                        DailyDiaryHistoryAdapter dailyDiaryAdapter = new DailyDiaryHistoryAdapter(context, ma_dayDB.get(i).getDailyDiaryQuestionModel().getmQuestions());

                        myViewHolder.dailyDiaryQuestionRV.setLayoutManager(Constants.getVerticalLayoutManager(context));

                        myViewHolder.dailyDiaryQuestionRV.setAdapter(dailyDiaryAdapter);
                    }
                }else{
                    /*myViewHolder.mdayDecView.setVisibility(View.GONE);
                    myViewHolder.mthumbCheck.setVisibility(View.GONE);*/
                   /* List<QuestionResponceModel> diaryQuestionDBList =new ArrayList();
                    for(int k=0;k<ma_dayDB.size();k++){
                        if(ma_dayDB.get(k).getDailyDiaryQuestionModel().getmQuestions()!=null){
                            for(int l=0;l<ma_dayDB.get(k).getDailyDiaryQuestionModel().getmQuestions().size();l++){
                                ma_dayDB.get(k).getDailyDiaryQuestionModel().getmQuestions().get(l).setmResponse(false);
                            }
                            diaryQuestionDBList.addAll(ma_dayDB.get(k).getDailyDiaryQuestionModel().getmQuestions());
                            break;
                        }
                    }*/
                    DailyDiaryHistoryAdapter dailyDiaryAdapter = new DailyDiaryHistoryAdapter(context, mdiaryTodayQuestions);

                    myViewHolder.dailyDiaryQuestionRV.setLayoutManager(Constants.getVerticalLayoutManager(context));

                    myViewHolder.dailyDiaryQuestionRV.setAdapter(dailyDiaryAdapter);
                }



        }

        manageItemClick(myViewHolder.item,i);

    }

    private void loadImage(final ImageView imageView, final String imageUrl){

        GlideApp
                .with(context)
                .load(imageUrl)
                .skipMemoryCache( true )
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                /*.signature(new ObjectKey(imageUrl + System.currentTimeMillis()))*/
                .error(R.drawable.avatar_new)
                .into(imageView);

        /*Picasso.get()
                .load(imageUrl)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
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
                });*/
    }



    private  void  manageItemClick(final View item, final int pos)
    {
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{

                    //if(ma_dayDB.get(pos).getDayactivitesList()!=null &&  ma_dayDB.get(pos).getDayactivitesList().size() >0){


                    /*}else{

                    }*/

                }catch (Exception ex){

                }






            }
        });
    }



    @Override
    public int getItemCount() {

        return ma_dayDB.size();
    }




    public static class   MyViewHolder extends RecyclerView.ViewHolder
    {

       public View  item,mdayDecView,mthumbCheck;
       public ImageView  weeklyPick;
       public TextView stepsValue,starvalue,calValue,distance,weight,waist,DayTV,DayAddress,date,calUnit,disUnit,mDayDescription;
       public Button mThumpUp,mThumpDown;
       public  RecyclerView dailyDiaryQuestionRV;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            weeklyPick=itemView.findViewById(R.id.thumbNail);
            stepsValue=itemView.findViewById(R.id.stepsValue);
            starvalue=itemView.findViewById(R.id.starValue);
            calValue=itemView.findViewById(R.id.calValue);
            DayTV=itemView.findViewById(R.id.DayTV);
            DayAddress=itemView.findViewById(R.id.addressValue);
            date=itemView.findViewById(R.id.dateValue);
            weight=itemView.findViewById(R.id.weightValue);
            waist=itemView.findViewById(R.id.waistValue);
            distance=itemView.findViewById(R.id.distanceValue);
            calUnit=itemView.findViewById(R.id.CalUnit);
            disUnit=itemView.findViewById(R.id.DisUnit);
            mDayDescription=itemView.findViewById(R.id.dayDescriptionText);
            mThumpUp=itemView.findViewById(R.id.thumbUp);
            mThumpDown=itemView.findViewById(R.id.thumbDown);
            dailyDiaryQuestionRV=itemView.findViewById(R.id.checklistRV);
            mdayDecView=itemView.findViewById(R.id.dayDecView);
            mthumbCheck=itemView.findViewById(R.id.thumbCheck);

            item=itemView;
        }



    }


    private  ArrayList<WeeklyActivityModel>  getDummyData()
    {

        ArrayList<WeeklyActivityModel>  weeklyArrayList=new ArrayList<>();


        WeeklyActivityModel weeklyActivityModel=new WeeklyActivityModel();

        weeklyActivityModel.setActivityPic(R.mipmap.girl_transform3+"");
        weeklyActivityModel.setActiveTime("DAY 1");
        weeklyActivityModel.setGoalSteps("6000");
        weeklyActivityModel.setId("1");
        weeklyActivityModel.setCalBurn("1500");
        weeklyActivityModel.setStars("1");
        weeklyActivityModel.setSteps("5340");


        WeeklyActivityModel weeklyActivityModel2=new WeeklyActivityModel();
        weeklyActivityModel2.setActivityPic(R.mipmap.girl_transform2+"");
        weeklyActivityModel2.setActiveTime("DAY 2");
        weeklyActivityModel2.setGoalSteps("5500");
        weeklyActivityModel2.setId("1");
        weeklyActivityModel2.setCalBurn("1400");
        weeklyActivityModel2.setStars("2");
        weeklyActivityModel2.setSteps("4700");


        WeeklyActivityModel weeklyActivityModel3=new WeeklyActivityModel();
        weeklyActivityModel3.setActivityPic(R.mipmap.girl_transform1+"");
        weeklyActivityModel3.setActiveTime("DAY 3");
        weeklyActivityModel3.setGoalSteps("5000");
        weeklyActivityModel3.setId("1");
        weeklyActivityModel3.setCalBurn("1800");
        weeklyActivityModel3.setStars("3");
        weeklyActivityModel3.setSteps("4200");

        WeeklyActivityModel weeklyActivityModel4=new WeeklyActivityModel();
        weeklyActivityModel4.setActivityPic(R.mipmap.girl_transform1+"");
        weeklyActivityModel4.setActiveTime("DAY 4");
        weeklyActivityModel4.setGoalSteps("6000");
        weeklyActivityModel4.setId("1");
        weeklyActivityModel4.setCalBurn("1900");
        weeklyActivityModel4.setStars("4");
        weeklyActivityModel4.setSteps("5200");

        WeeklyActivityModel weeklyActivityModel5=new WeeklyActivityModel();
        weeklyActivityModel5.setActivityPic(R.mipmap.girl_transform1+"");
        weeklyActivityModel5.setActiveTime("DAY 5");
        weeklyActivityModel5.setGoalSteps("8000");
        weeklyActivityModel5.setId("1");
        weeklyActivityModel5.setCalBurn("2000");
        weeklyActivityModel5.setStars("2");
        weeklyActivityModel5.setSteps("7200");

        WeeklyActivityModel weeklyActivityModel6=new WeeklyActivityModel();
        weeklyActivityModel6.setActivityPic(R.mipmap.girl_transform1+"");
        weeklyActivityModel6.setActiveTime("DAY 6");
        weeklyActivityModel6.setGoalSteps("10000");
        weeklyActivityModel6.setId("1");
        weeklyActivityModel6.setCalBurn("2500");
        weeklyActivityModel6.setStars("3");
        weeklyActivityModel6.setSteps("8500");

        WeeklyActivityModel weeklyActivityModel7=new WeeklyActivityModel();
        weeklyActivityModel7.setActivityPic(R.mipmap.girl_transform1+"");
        weeklyActivityModel7.setActiveTime("DAY 7");
        weeklyActivityModel7.setGoalSteps("11000");
        weeklyActivityModel7.setId("1");
        weeklyActivityModel7.setCalBurn("3000");
        weeklyActivityModel7.setStars("3");
        weeklyActivityModel7.setSteps("9000");




        weeklyArrayList.add(weeklyActivityModel);
        weeklyArrayList.add(weeklyActivityModel2);
        weeklyArrayList.add(weeklyActivityModel3);
        weeklyArrayList.add(weeklyActivityModel4);
        weeklyArrayList.add(weeklyActivityModel5);
        weeklyArrayList.add(weeklyActivityModel6);
        weeklyArrayList.add(weeklyActivityModel7);


        return  weeklyArrayList;


    }

}
