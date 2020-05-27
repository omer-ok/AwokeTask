package com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.DailyActivityModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.DailyDiaryQuestionModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.MotivationalVideo;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.QuestionResponceModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

import static com.kampen.riksSe.utils.UtilityTz.getTimeAgo;


public class DailyDiaryAdapter extends RecyclerView.Adapter<DailyDiaryAdapter.ViewHolder> {

    public ArrayList<QuestionResponceModel> mDailyDiaryQuestionArrayList=new ArrayList<>();

    private  Context mContext;


    public DailyDiaryAdapter(Context context, List<QuestionResponceModel> diaryQuestionDBList)
    {
        mDailyDiaryQuestionArrayList.addAll(diaryQuestionDBList);
        mContext=context;
    }

    public void  updateAdapter(List<QuestionResponceModel> diaryQuestionDBList)
    {
        mDailyDiaryQuestionArrayList.clear();

        this.mDailyDiaryQuestionArrayList.addAll(diaryQuestionDBList);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_daily_diary, viewGroup, false);

        return new DailyDiaryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

       if(mDailyDiaryQuestionArrayList != null &&  mDailyDiaryQuestionArrayList.size() >0 ){

            viewHolder.dailyQuestion.setText(mDailyDiaryQuestionArrayList.get(i).getQuestion());

            viewHolder.questionResponce.setChecked(mDailyDiaryQuestionArrayList.get(i).ismResponse());
       }

        manageImageClick(viewHolder,viewHolder.DiaryView,viewHolder.questionResponce,i);

    }


    @Override
    public int getItemCount() {
        return mDailyDiaryQuestionArrayList.size();
    }





    private  void manageImageClick(ViewHolder viewHolder,final View DiaryView,final View questionCheck,final int position)
    {
        DiaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    if(viewHolder.questionResponce.isChecked()){
                        viewHolder.questionResponce.setChecked(false);
                        mDailyDiaryQuestionArrayList.get(position).setmResponse(false);
                    }else{
                        viewHolder.questionResponce.setChecked(true);
                        mDailyDiaryQuestionArrayList.get(position).setmResponse(true);
                    }
                    Intent intent = new Intent();
                    intent.setAction("com.tutorialspoint.CUSTOM_DAILY_DIARY_QUESTION");
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

                }catch (Exception ex){

                }
            }
        });
        questionCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            if(viewHolder.questionResponce.isChecked()){
                                mDailyDiaryQuestionArrayList.get(position).setmResponse(true);
                            }else{
                                mDailyDiaryQuestionArrayList.get(position).setmResponse(false);
                            }
                            Intent intent = new Intent();
                            intent.setAction("com.tutorialspoint.CUSTOM_DAILY_DIARY_QUESTION");
                            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                        }
                    }, 100);


                }catch (Exception ex){

                }
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public CheckBox questionResponce;
        public TextView dailyQuestion;
        public View DiaryView;

        public ViewHolder(View v) {
            super(v);
            questionResponce = v.findViewById(R.id.diaryCheckBox);
            dailyQuestion = v.findViewById(R.id.diary_question);
            DiaryView = v.findViewById(R.id.diaryView);
        }
    }

}
