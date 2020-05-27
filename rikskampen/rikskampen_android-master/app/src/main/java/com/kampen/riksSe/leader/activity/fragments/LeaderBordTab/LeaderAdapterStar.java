package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.StarsDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.TopContestant;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LeaderAdapterStar extends RecyclerView.Adapter<LeaderAdapterStar.ViewHolder>  {


    ArrayList<StarsDataV3> list=new ArrayList<>();

    Context  mContext;

    private  int  mWidth=320;

    private  int  mHeight=720;


    private  int  mMaxSteps=120;




    public LeaderAdapterStar(Context  context)
    {
        this.mContext=context;
        //generateDummyData();


        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mHeight = (int) (displayMetrics.heightPixels / displayMetrics.density);
        mWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);

        mMaxSteps=36;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_stars_new_leader_board, viewGroup, false);

        return new ViewHolder(v);
    }


    public void setData(List<StarsDataV3> topContestantList)
    {
        if(list!=null)
        {
            list.clear();
            list.addAll(topContestantList);
        }
        else
        {
            list=new ArrayList<>();
            list.addAll(topContestantList);
        }

        //notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        try {

           /* viewHolder.positionTV.bringToFront();

            viewHolder.profileImage1.bringToFront();

            viewHolder.positionTV.setText("#" + (i + 1));

            viewHolder.nameTV.setText(list.get(i).getName().split(" ")[0] + "\n" + list.get(i).getStars());

            RelativeLayout.LayoutParams parems = (RelativeLayout.LayoutParams) viewHolder.leftContainer.getLayoutParams();

            parems.width = (int) convertDpToPixel(calculateItemWidth(Integer.parseInt(list.get(i).getStars().replace(",", "")), calculateItemMaxWidth(), Integer.parseInt(list.get(0).getStars())), mContext);//(int)convertDpToPixel(list.get(i).getPosition(),mContext);

            viewHolder.leftContainer.setLayoutParams(parems);
*/

            viewHolder.leaderStar.setMax(list.get(0).getCount());

            viewHolder.leaderStar.setProgress(list.get(i).getCount());

            //viewHolder.counterTV.setText((i + 1)+". ");

            viewHolder.counterTV.setText(list.get(i).getRank()+". ");

            viewHolder.nameTV.setText(list.get(i).getFirstName().substring(0,1).toUpperCase() + list.get(i).getFirstName().substring(1));

            viewHolder.starTV.setText(list.get(i).getCount()+"");


            //viewHolder.stepsIconContainer.bringToFront();
            // no url is receiving from Api
            //viewHolder.profileImage1.setImageResource(list.get(i).tempResId);
            if (list.get(i).getProfileImage() != null)
                loadImage(viewHolder.profileImage1, list.get(i).getProfileImage());

            //viewHolder.stepsIconContainer.bringToFront();


            onItemClick(viewHolder.selfView, i);
        }catch (Exception ex)
        {
            ex.toString();
        }

    }

    private void loadImage(final ImageView imageView, final String imageUrl){

        GlideApp
                .with(mContext)
                .load(imageUrl)
                .centerInside()
                .error(R.drawable.avatar_new)
                .into(imageView);

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
                            loadImage(imageView, updatedImageUrl);
                        }else{
                            updatedImageUrl = imageUrl.replace("http", "https");
                            loadImage(imageView, updatedImageUrl);
                        }
                    }
                });
    }

    private int  calculateItemWidth(int totalSteps,int maxAvailableWidth,int maxSteps)
    {

        double temp1=totalSteps;
        double temp2=maxSteps;

        double percentile=(temp1/temp2);

        percentile = (percentile/2)+0.5;

        int itemWidth= (int) (percentile*maxAvailableWidth);

        return itemWidth;
    }


    private int  calculateItemMaxWidth(  )
    {

        int maxAvailableWidth= (int) (mWidth*.70);

        return maxAvailableWidth;
    }



    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView  positionTV;
        public TextView  counterTV,nameTV,starTV;
        public LinearLayout leftContainer;
        public CircleImageView profileImage1;
        public View    stepsIconContainer;
        public View    selfView;
        public ProgressBar leaderStar;

        public ViewHolder(View v) {
            super(v);
            selfView=v;
            //positionTV=v.findViewById(R.id.position1);
            counterTV=v.findViewById(R.id.counter);
            nameTV=v.findViewById(R.id.userName);
            starTV=v.findViewById(R.id.starValue);
            //leftContainer=v.findViewById(R.id.leftContainer1);
            profileImage1=v.findViewById(R.id.profileImage1);
            leaderStar=v.findViewById(R.id.pb_star);
            //stepsIconContainer=v.findViewById(R.id.steps_icon_container);
        }


    }


    private  void onItemClick(View view,final int pos)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TopContestant temp=list.get(pos);


                //Toast.makeText(mContext, "Clicked "+temp.getName(), Toast.LENGTH_SHORT).show();




            }
        });
    }

    /*private void generateDummyData()
    {
        list = new ArrayList<>();

        TopContestant topContestant1=new TopContestant();
        topContestant1.setName("Sami");
        topContestant1.setSteps("2000");
        topContestant1.setPosition(250);
        topContestant1.tempResId=R.drawable.profile4;
        list.add(topContestant1);

        TopContestant topContestant2=new TopContestant();
        topContestant2.setName("Yum");
        topContestant2.setSteps("1800");
        topContestant2.setPosition(240);
        topContestant2.tempResId=R.drawable.profile2;
        list.add(topContestant2);

        TopContestant topContestant3=new TopContestant();
        topContestant3.setName("Loki");
        topContestant3.setSteps("1700");
        topContestant3.setPosition(230);
        topContestant3.tempResId=R.drawable.profile3;
        list.add(topContestant3);

        TopContestant topContestant4=new TopContestant();
        topContestant4.setName("Jamil");
        topContestant4.setSteps("1600");
        topContestant4.setPosition(210);
        topContestant4.tempResId=R.drawable.profile4;
        list.add(topContestant4);

        TopContestant topContestant5=new TopContestant();
        topContestant5.setName("Sara");
        topContestant5.setSteps("1567");
        topContestant5.setPosition(190);
        topContestant5.tempResId=R.drawable.profile5;
        list.add(topContestant5);

        TopContestant topContestant6=new TopContestant();
        topContestant6.setName("Sami");
        topContestant6.setSteps("1495");
        topContestant6.setPosition(180);
        topContestant6.tempResId=R.drawable.profile2;
        list.add(topContestant6);

        TopContestant topContestant7=new TopContestant();
        topContestant7.setName("Jones");
        topContestant7.setSteps("1350");
        topContestant7.setPosition(170);
        topContestant7.tempResId=R.drawable.profile3;
        list.add(topContestant7);

        TopContestant topContestant8=new TopContestant();
        topContestant8.setName("Smith");
        topContestant8.setSteps("1200");
        topContestant8.setPosition(170);
        topContestant8.tempResId=R.drawable.profile4;
        list.add(topContestant8);

        TopContestant topContestant9=new TopContestant();
        topContestant9.setName("Kool");
        topContestant9.setSteps("1180");
        topContestant9.setPosition(160);
        topContestant9.tempResId=R.drawable.profile5;
        list.add(topContestant9);


    }
*/

    private void  showDetailedPopup(final TopContestant topContestant)
    {
        Button showPopupBtn, closePopupBtn;
        PopupWindow popupWindow;

        LayoutInflater layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.pop_up_contestent_details,null);

        //closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);

        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        popupWindow.showAtLocation(((Activity)mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        //close the popup window on button click
        /*closePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });*/



    }
}
