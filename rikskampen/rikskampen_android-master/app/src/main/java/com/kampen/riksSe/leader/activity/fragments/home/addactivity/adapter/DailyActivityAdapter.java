package com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter;

import android.content.Context;
import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.adapterListModel.activityAdapterListModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.DailyActivityModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.WeeklyActivity;
import com.kampen.riksSe.utils.Constants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;


public class DailyActivityAdapter extends RecyclerView.Adapter<DailyActivityAdapter.ViewHolder> {

    private  ArrayList<DailyActivityModel>  list;

    private ActivityFragment tempFragment;


    private   Realm  mDataBase;

    private  String currentDateandTime;

    ArrayList<activityAdapterListModel> mActivityArrayList=new ArrayList<>();

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

   /* public DailyActivityAdapter(Context context, List<A_WeekDB> weekDBList)
    {


        mActivityArrayList.addAll(weekDBList);
        mContext=context;
    }



    public void  updateAdapter(List<A_WeekDB> weekDBList)
    {
        mActivityArrayList.clear();

        this.mActivityArrayList.addAll(weekDBList);

        notifyDataSetChanged();
    }*/

    public DailyActivityAdapter(Context context, List<activityAdapterListModel> weekDBList)
    {


        mActivityArrayList.addAll(weekDBList);
        mContext=context;
    }



    public void  updateAdapter(List<activityAdapterListModel> weekDBList)
    {
        mActivityArrayList.clear();

        this.mActivityArrayList.addAll(weekDBList);

        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_daily_pick, viewGroup, false);

        return new DailyActivityAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        currentDateandTime = sdf.format(new Date());

        if(mActivityArrayList!=null){

             if(mActivityArrayList.get(i).getCurrentWeek().equals("Present")){
                 if(mActivityArrayList.get(i).getmMediaImage() !=null &&  mActivityArrayList.get(i).getmMediaImage().length() >0 ) {
                     loadImage(viewHolder.dailyPick, mActivityArrayList.get(i).getmMediaImage());

                     manageImageClickWeekly(viewHolder.dailyPick, i);
                     viewHolder.timeTV.setText(getFormattedWeek(mActivityArrayList.get(i).getmWeek(),mContext));
                     viewHolder.previosWeekFade.setVisibility(View.GONE);
                 }else{
                     viewHolder.previosWeekFade.setVisibility(View.GONE);
                     GlideApp
                             .with(mContext)
                             .load(R.drawable.avatar_new)
                             .into(viewHolder.dailyPick);
                     manageImageClickWeekly(viewHolder.dailyPick,i);
                     viewHolder.timeTV.setText(getFormattedWeek(mActivityArrayList.get(i).getmWeek(),mContext));
                 }
                }else{
                 if(mActivityArrayList.get(i).getmMediaImage() !=null &&  mActivityArrayList.get(i).getmMediaImage().length() >0 ) {
                     viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
                     loadImage(viewHolder.dailyPick, mActivityArrayList.get(i).getmMediaImage());
                     manageImageClickWeekly(viewHolder.dailyPick, i);
                     viewHolder.timeTV.setText(getFormattedWeek(mActivityArrayList.get(i).getmWeek(),mContext));
                 }else{
                     viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
                     GlideApp
                             .with(mContext)
                             .load(R.drawable.avatar_new)
                             .into(viewHolder.dailyPick);
                     manageImageClickWeekly(viewHolder.dailyPick,i);
                     viewHolder.timeTV.setText(getFormattedWeek(mActivityArrayList.get(i).getmWeek(),mContext));
                 }

            }
        }

      /* if(mActivityArrayList.get(i).getImagePath() !=null &&  mActivityArrayList.get(i).getImagePath().length() >0 ){

           if(mActivityArrayList.get(i).getCurrentWeek().equals("present")){
               loadImage(viewHolder.dailyPick,mActivityArrayList.get(i).getImagePath());

               manageImageClickWeekly(viewHolder.dailyPick,i);
               viewHolder.timeTV.setText(getFormattedWeek(Integer.parseInt(mActivityArrayList.get(i).getWeekName())));
               viewHolder.previosWeekFade.setVisibility(View.GONE);

           }else{

               viewHolder.previosWeekFade.setVisibility(View.VISIBLE);
               loadImage(viewHolder.dailyPick,mActivityArrayList.get(i).getImagePath());
               manageImageClickWeekly(viewHolder.dailyPick,i);
               viewHolder.timeTV.setText(getFormattedWeek(Integer.parseInt(mActivityArrayList.get(i).getWeekName())));

           }


       }else{
              GlideApp
                   .with(mContext)
                   .load(R.drawable.avatar_new)
                   .into(viewHolder.dailyPick);
           manageImageClickWeekly(viewHolder.dailyPick,i);
           viewHolder.timeTV.setText(getFormattedWeek(Integer.parseInt(mActivityArrayList.get(i).getWeekName())));
       }
*/


    }


    private void loadImage(final ImageView imageView, final String imageUrl){

        GlideApp
                .with(mContext)
                .load(imageUrl)
                /*.skipMemoryCache( true )
                .diskCacheStrategy(DiskCacheStrategy.NONE)*/
                /*.signature(new ObjectKey(imageUrl + System.currentTimeMillis()))*/
                .error(R.drawable.avatar_new)
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
        return mActivityArrayList.size();
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

                    Intent intent = new Intent(imageView.getContext(), WeeklyActivity.class);
                    intent.putExtra("week_id",mActivityArrayList.get(position).getmWeek());;
                    imageView.getContext().startActivity(intent);

                }catch (Exception ex){

                }


            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView timeTV;
        public ImageView dailyPick;
        public View previosWeekFade;

        public ViewHolder(View v) {
            super(v);

            timeTV=v.findViewById(R.id.timeTV);
            dailyPick=v.findViewById(R.id.dailyPick);
            previosWeekFade=v.findViewById(R.id.previousWeek);
        }


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

}
