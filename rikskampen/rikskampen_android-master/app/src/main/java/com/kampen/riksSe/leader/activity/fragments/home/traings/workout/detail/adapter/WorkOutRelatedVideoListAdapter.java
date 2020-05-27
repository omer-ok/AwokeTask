package com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.adapter;

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
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.WorkOutVideoActivityNew;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.model.DailyVideo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WorkOutRelatedVideoListAdapter extends RecyclerView.Adapter<WorkOutRelatedVideoListAdapter.ViewHolder>  {


    private T_DayDB mDay;

    private  Context mContext;


    private  int weekId;





    public WorkOutRelatedVideoListAdapter(Context context, T_DayDB  day, int weekId)
    {
       // mDailyVideoArrayList =generateDummyData();
        this.weekId=weekId;
        mDay=day;
        mContext=context;
    }

    public void  updateAdapter(T_DayDB  day)
    {

        mDay=day;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_daily_video, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {




        try {
/*            viewHolder.headingTV.setText(mDailyVideoArrayList.get(i).getTitle());
            viewHolder.joinTV.setText(mDailyVideoArrayList.get(i).getShortDes());
            viewHolder.timeTV.setText(mDailyVideoArrayList.get(i).getDateTime());*/
            //viewHolder.thumbNail
            //Picasso.with(mContext).load(mDay.getDayactivitesList().get(i).getMediaImage()).into(viewHolder.thumbNail);
            //Picasso.get().load(mDay.getDayactivitesList().get(i).getMediaImage()).into(viewHolder.thumbNail);
            loadImage(viewHolder.thumbNail,mDay.getDayactivitesList().get(i).getMediaImage());
          /*  Glide.with(mContext)
                    .load(mDay.getDayactivitesList().get(i).getMediaImage())
                    .into(viewHolder.thumbNail);*/
           // Glide.with(mContext).load(mDay.getDayactivitesList().get(i).getMediaImage()).into(viewHolder.thumbNail);
            viewHolder.headingTV.setText(mDay.getDayactivitesList().get(i).getTitle());
            //viewHolder.timeTV.setText(mDailyVideoArrayList.get(i).getDateTime());
           /* viewHolder.joinTV.setText(mDailyVideoArrayList.get(i).getShortDes());
            viewHolder.timeTV.setText(mDailyVideoArrayList.get(i).getDateTime());*/
            manageClick(i,viewHolder.view);
        }catch (Exception ex)
        {
            ex.toString();
        }





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
        return mDay.getDayactivitesList().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView headingTV,joinTV,timeTV;

        public ImageView thumbNail;

        public View view;

        public ViewHolder(View v) {
            super(v);
            headingTV=v.findViewById(R.id.headingTV);
           // joinTV=v.findViewById(R.id.joinTV);
            timeTV=v.findViewById(R.id.timeTV);
            thumbNail=v.findViewById(R.id.thumbNail);
            view=v;

        }


    }


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    /*Intent intent = new Intent(mContext, VideoPlayActivity.class);
                    intent.putExtra("selected_week",weekId);
                    intent.putExtra("selected_day",mDay.getDayID());
                    intent.putExtra("selected_video",(position));
                    mContext.startActivity(intent);*/

                    Intent intent = new Intent(mContext, WorkOutVideoActivityNew.class);
                    intent.putExtra("selected_week",weekId);
                    intent.putExtra("selected_day",mDay.getDayID());
                    intent.putExtra("selected_video",(position));
                    mContext.startActivity(intent);

                }catch (Exception ex){

                }



            }
        });
    }


    private   ArrayList<DailyVideo> generateDummyData()
    {
        ArrayList<DailyVideo> hmArray=new ArrayList<>();
        String [][] habitNames=new String[5][2];
        habitNames[0][0]="Shoulder";
        habitNames[1][0]="Your Legs";
        habitNames[2][0]="Neck";
        habitNames[3][0]="Push ups";
        habitNames[4][0]="Running";


        habitNames[0][1]=(R.drawable.ic_sardines)+"";
        habitNames[1][1]=(R.drawable.ic_patatoes)+"";
        habitNames[2][1]=(R.drawable.ic_lentils)+"";
        habitNames[3][1]=(R.drawable.ic_wheat_germ)+"";
        habitNames[4][1]=(R.drawable.ic_kale)+"";


          for(int i=0; i<habitNames.length; i++)
          {
              DailyVideo hm=new DailyVideo();
              hm.setId(i+"");
              hm.setTitle(habitNames[i][0]);
              hm.setDateTime("12-08-2018");
              hm.setShortDes("This is test des");

              hmArray.add(hm);
          }


          return  hmArray;
    }
}
