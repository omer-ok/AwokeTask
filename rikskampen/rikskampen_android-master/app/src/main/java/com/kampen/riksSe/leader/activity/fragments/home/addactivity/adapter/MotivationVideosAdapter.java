package com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.DailyActivityModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.MotivationalVideo;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.VideoM;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

import static com.kampen.riksSe.utils.UtilityTz.getTimeAgo;
import static com.kampen.riksSe.utils.UtilityTz.timeAgo;


public class MotivationVideosAdapter extends RecyclerView.Adapter<MotivationVideosAdapter.ViewHolder> {

    private  ArrayList<DailyActivityModel>  list;

    private ActivityFragment tempFragment;

    public String MotivationVideosURL;

    private   Realm  mDataBase;

    ArrayList<MotivationalVideo> mMotivatArrayList=new ArrayList<>();

    private  Context mContext;

    private int[] tranformPic = {

            R.mipmap.girl_transform3,
            R.mipmap.girl_transform2,
            R.mipmap.girl_transform1,
            R.mipmap.girl_transform1,
            R.mipmap.girl_transform1,
            R.mipmap.girl_transform1,
            R.mipmap.girl_transform1,
            R.mipmap.girl_transform1,
            R.mipmap.girl_transform1,
            R.mipmap.girl_transform1,
    };




    public MotivationVideosAdapter(Context context, List<MotivationalVideo> weekDBList)
    {


        mMotivatArrayList.addAll(weekDBList);
        mContext=context;
    }



    public void  updateAdapter(List<MotivationalVideo> weekDBList)
    {
        mMotivatArrayList.clear();

        this.mMotivatArrayList.addAll(weekDBList);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_motivation_video, viewGroup, false);

        return new MotivationVideosAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        try{
            if(mMotivatArrayList != null &&  mMotivatArrayList.size() >0 ){

                /*GlideApp
                   .with(mContext)
                   .load(mActivityArrayList.get(i).getImagePath())
                   .into(viewHolder.dailyPick);*/



                if(mMotivatArrayList.get(i).getMediaThumb().isEmpty()){



                }else{

                    loadImage(viewHolder.dailyPick,mMotivatArrayList.get(i).getMediaThumb());
                   /* GlideApp
                            .with(mContext)
                            .load(mMotivatArrayList.get(i).getMediaThumb())
                            .into(viewHolder.dailyPick);*/
                }


                manageImageClickWeekly(viewHolder.dailyPick,i);
                viewHolder.timeTV.setText(getTimeAgo(mMotivatArrayList.get(i).getPostedDate()));

            }else{
                GlideApp
                        .with(mContext)
                        .load(R.drawable.user_avatar)
                        .into(viewHolder.dailyPick);
                //manageImageClickWeekly(viewHolder.dailyPick,i);
                //viewHolder.timeTV.setText(getFormattedWeek(Integer.parseInt(mActivityArrayList.get(i).getWeekName())));
            }

        }catch (Exception e){

        }



    }


    private void loadImage(final ImageView imageView, final String imageUrl){
        GlideApp
                .with(mContext)
                .load(imageUrl)
                .centerInside()
                .into(imageView);
        /*Picasso.get()
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
    }




    @Override
    public int getItemCount() {
        return mMotivatArrayList.size();
    }


    public   void addYourDailyPick(String imagePath)
    {
        if(list!=null)
        {
            Bitmap  bitmap=BitmapFactory.decodeFile(imagePath);
            DailyActivityModel dailyPick= list.get(0);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
            final byte[] byteArray = stream.toByteArray();

            dailyPick.setPicData(byteArray);

            notifyDataSetChanged();

        }

    }

    private  void manageImageClick(final ImageView imageView,final DailyActivityModel dailyPick)
    {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tempFragment.onAddPicture();



            }
        });
    }


    private  void manageImageClickWeekly(final ImageView imageView,final int position)
    {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try{

                    /*Intent intent = new Intent(imageView.getContext(), WeeklyActivity.class);
                    intent.putExtra("selected_week",mMotivatArrayList.get(position).getMediaUrl());;
                    imageView.getContext().startActivity(intent);*/

                    //MotivationVideosURL = mMotivatArrayList.get(position).getMediaUrl();

                }catch (Exception ex){

                }


            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView timeTV;
        public ImageView dailyPick;

        public ViewHolder(View v) {
            super(v);
            timeTV=v.findViewById(R.id.timeTV);
            dailyPick=v.findViewById(R.id.dailyPick);
        }


    }


    public static String  getFormattedWeek(int week)
    {

        String weekstring="";
        int i=week;

        if(i==1) {
            weekstring=(i + ":a VECKA");
        }
        else if(i==2)
        {
            weekstring=(i + ":e VECKA");
        }
        else if(i==3)
        {
            weekstring=(i + ":e VECKA");
        }
        else
        {
            weekstring=(i + ":e VECKA");
        }

        return  weekstring;
    }

}
