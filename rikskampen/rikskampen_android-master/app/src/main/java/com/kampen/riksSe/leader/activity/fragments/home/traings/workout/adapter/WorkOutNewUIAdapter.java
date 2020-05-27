package com.kampen.riksSe.leader.activity.fragments.home.traings.workout.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Video;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.VideoPlayActivity;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.model.DailyVideo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WorkOutNewUIAdapter extends RecyclerView.Adapter<WorkOutNewUIAdapter.ViewHolder>{


    private List<W_Video> wVideo;

    private Context mContext;

    public int weekId;

    public int planID;

    public int dayID;


    public WorkOutNewUIAdapter(Context context,int planID, List<W_Video> w_video)
    {
        // mDailyVideoArrayList = generateDummyData();
        this.wVideo=w_video;
        this.planID = planID;
        mContext=context;
    }

    public void  updateAdapter(List<W_Video>  w_video)
    {

        wVideo=w_video;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorkOutNewUIAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_traing_day, viewGroup, false);

        return new WorkOutNewUIAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull WorkOutNewUIAdapter.ViewHolder viewHolder, int i) {

        try {

            loadImage(viewHolder.thumbNail,wVideo.get(i).getmUrlImage());

            viewHolder.headingTV.setText(wVideo.get(i).getmTitle());

            viewHolder.rapsView.setText(wVideo.get(i).getmReps()+" Raps");

            viewHolder.setsView.setText(wVideo.get(i).getmSets()+" Sets");

            manageClick(i,viewHolder.view);
/*
            if(mDay.getDayactivitesList().get(i).getVideoType().equals("startVideo") || mDay.getDayactivitesList().get(i).getVideoType().equals("endVideo")){


                viewHolder.itemView.setVisibility(View.GONE);

            }
            else{

                loadImage(viewHolder.thumbNail,mDay.getDayactivitesList().get(i).getMediaImage());

                viewHolder.headingTV.setText(mDay.getDayactivitesList().get(i).getTitle());

                viewHolder.rapsView.setText(mDay.getDayactivitesList().get(i).getReps()+" Raps");

                viewHolder.setsView.setText(mDay.getDayactivitesList().get(i).getSets()+" Sets");

                manageClick(i,viewHolder.view);

            }*/



        }catch (Exception ex)
        {
            ex.toString();
        }





    }

    private void loadImage(final ImageView imageView, final String imageUrl){
      /*  Picasso.get()
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
                });*/
        GlideApp
                .with(mContext)
                .load(imageUrl)
                .error(R.drawable.img_video_empty)
                .into(imageView);
    }


    @Override
    public int getItemCount() {

        //int listSize = mDay.getDayactivitesList().size();

        return wVideo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView headingTV,joinTV,timeTV,rapsView,setsView;

        public ImageView thumbNail;

        public View ItemView;

        public View view;

        public ViewHolder(View v) {
            super(v);
            headingTV=v.findViewById(R.id.timeTV);
            thumbNail=v.findViewById(R.id.workoutImage);
            ItemView=v.findViewById(R.id.itemView);
            rapsView=v.findViewById(R.id.rapsTV);
            setsView=v.findViewById(R.id.setsTV);
            view=v;
        }


    }


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try{


                    Intent intent = new Intent(mContext, VideoPlayActivity.class);
                    intent.putExtra("planID",planID);
                    intent.putExtra("VideoID",wVideo.get(position).getmId());
                    mContext.startActivity(intent);
                    //((Activity)mContext).finish();



                }catch (Exception ex){

                }


            }
        });
    }


    private ArrayList<DailyVideo> generateDummyData()
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
