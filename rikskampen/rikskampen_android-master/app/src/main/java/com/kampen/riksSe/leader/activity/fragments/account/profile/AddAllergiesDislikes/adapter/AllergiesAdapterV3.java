package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.Alergy;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.UserAlergies;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AllergyV3;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.NutritionNewUIMealActivity;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTimePopDaysHoursMinutes;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;

public class AllergiesAdapterV3 extends RecyclerView.Adapter<AllergiesAdapterV3.ViewHolder>  {


    public ArrayList<AllergyV3> mAllergiesArrayList=new ArrayList<>();
    public ArrayList<String> alergyIDRemovedList;
    public ArrayList<String> newUserAlergyArrayList;
    public ArrayList<String> alergyUserIDRemovedList;

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


    public AllergiesAdapterV3(Context context, List<AllergyV3> allergyV3List)
    {

        mAllergiesArrayList.addAll(allergyV3List);
        mContext=context;
        this.alergyIDRemovedList = new ArrayList<>();
        this.newUserAlergyArrayList = new ArrayList<>();
        this.alergyUserIDRemovedList = new ArrayList<>();
    }

    public void  updateAdapter(List<AllergyV3> allergyV3List)
    {
        //mAllergiesArrayList.clear();
        this.mAllergiesArrayList.addAll(allergyV3List);

        notifyDataSetChanged();
    }

    public ArrayList<AllergyV3> getData() {
        return mAllergiesArrayList;
    }

    public ArrayList<String> getAlergyIDList() {
        ArrayList<String> alergyIDList = new ArrayList<>();

        for(int i=0;i<mAllergiesArrayList.size();i++){
            if(mAllergiesArrayList.get(i).getRecentlySelected()) {
                if(mAllergiesArrayList.get(i).getStatus().equals("SystemAllrgies")){
                    int alergyID = mAllergiesArrayList.get(i).getAllergyId();
                    alergyIDList.add(String.valueOf(alergyID));
                    //break;
                } else {
                   /* String userAlergyName = mAllergiesArrayList.get(i).getTitle();
                    newUserAlergyArrayList.add(String.valueOf(userAlergyName));
                    break;*/
                    //alergyIDList.add(userAlergyName);
                }
            }/*else{
                if(mAllergiesArrayList.get(i).getStatus().equals("SystemAllrgies")){
                    int alergyID = mAllergiesArrayList.get(i).getAllergyId();
                    alergyIDList.add(String.valueOf(alergyID));
                    break;
                } else {
                    *//*String userAlergyName = mAllergiesArrayList.get(i).getTitle();
                    newUserAlergyArrayList.add(String.valueOf(userAlergyName));
                    break;*//*
                    //alergyIDList.add(userAlergyName);
                }
            }*/
        }
        return alergyIDList;
    }

    public ArrayList<String> getUserAlergyNameList(){
        ArrayList<String> userAlergyNameList = new ArrayList<>();
        for(int i=0;i<mAllergiesArrayList.size();i++) {
            if (mAllergiesArrayList.get(i).getRecentlySelected()) {
                if (mAllergiesArrayList.get(i).getStatus().equals("UserAllergies")) {
                    String userAlergyName = mAllergiesArrayList.get(i).getTitle();
                    userAlergyNameList.add(String.valueOf(userAlergyName));
                    break;
                } else {

                }
            }
        }
        return userAlergyNameList;
    }


    public void getAlergyIDRemovedList(int id){

            alergyIDRemovedList.add(String.valueOf(id));
    }

    public void removeItem(int position) {
        if(!mAllergiesArrayList.get(position).getRecentlySelected()){
            if(mAllergiesArrayList.get(position).getStatus().equals("SystemAllrgies")){
                alergyIDRemovedList.add(String.valueOf(mAllergiesArrayList.get(position).getAllergyId()));
                mAllergiesArrayList.remove(position);
                notifyItemRemoved(position);
            }else{
                alergyUserIDRemovedList.add(String.valueOf(mAllergiesArrayList.get(position).getAllergyId()));
                mAllergiesArrayList.remove(position);
                notifyItemRemoved(position);
            }

        }else{
            if(mAllergiesArrayList.get(position).getStatus().equals("SystemAllrgies")){

                alergyIDRemovedList.add(String.valueOf(mAllergiesArrayList.get(position).getAllergyId()));
                mAllergiesArrayList.remove(position);
                notifyItemRemoved(position);

        }else{
            alergyUserIDRemovedList.add(String.valueOf(mAllergiesArrayList.get(position).getAllergyId()));
            mAllergiesArrayList.remove(position);
            notifyItemRemoved(position);
        }

    }

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_allergies, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

       try{
           if(mAllergiesArrayList!=null && mAllergiesArrayList.size()>0){

               viewHolder.AllergiesView.setText(mAllergiesArrayList.get(i).getTitle());

           }
       }catch (Exception e){

       }
        manageClick(i,viewHolder.view);



    }


    private void loadImage(final ImageView imageView, final String imageUrl){

        GlideApp
                .with(mContext)
                .load(imageUrl)
                .centerInside()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")){
                            updatedImageUrl = imageUrl.replace("https", "http");
                        }else{
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return mAllergiesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView AllergiesView;

        public View view;

        public ViewHolder(View v) {
            super(v);
            AllergiesView=v.findViewById(R.id.allergiesView);



            view=v;
        }


    }



    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
    }



    public static String  getFormattedWeek(int week)
    {

        String weekstring="";
        int i=week;

        if(i==1) {
            weekstring=("VECKA "+i);
        }
        else if(i==2)
        {
            weekstring=("VECKA "+i);
        }
        else if(i==2)
        {
            weekstring=("VECKA "+i);
        }
        else
        {
            weekstring=("VECKA "+i);
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
            contestWeekDayTimeModel = CompititionStartDateAndTimePopDaysHoursMinutes(mUser.getContestStartDate(), currentDateandTime);

            contestStartTime.setText("Tävlingen startar i "+ contestWeekDayTimeModel.getDays()+" dagar "+ contestWeekDayTimeModel.getHours()+" timmar "+ contestWeekDayTimeModel.getMiniutes()+" Minuter");

        }else{
            contestStartTime.setText("Tävlingsstartdatum tilldelas inte");
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
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

    }


    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;

    }

}
