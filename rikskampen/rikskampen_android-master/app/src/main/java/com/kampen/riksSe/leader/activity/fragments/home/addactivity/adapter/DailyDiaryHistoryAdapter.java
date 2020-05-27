package com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.QuestionResponceModel;

import java.util.ArrayList;
import java.util.List;


public class DailyDiaryHistoryAdapter extends RecyclerView.Adapter<DailyDiaryHistoryAdapter.ViewHolder> {

    ArrayList<QuestionResponceModel> mDailyDiaryQuestionArrayList=new ArrayList<>();

    private  Context mContext;


    public DailyDiaryHistoryAdapter(Context context, List<QuestionResponceModel> diaryQuestionDBList)
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
                .inflate(R.layout.item_daily_diary_history, viewGroup, false);

        return new DailyDiaryHistoryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

       if(mDailyDiaryQuestionArrayList != null &&  mDailyDiaryQuestionArrayList.size() >0 ){

            viewHolder.dailyQuestion.setText(mDailyDiaryQuestionArrayList.get(i).getQuestion());

            viewHolder.questionResponce.setChecked(mDailyDiaryQuestionArrayList.get(i).ismResponse());
       }

       // manageImageClick(viewHolder,viewHolder.DiaryView,i);

    }


    @Override
    public int getItemCount() {
        return mDailyDiaryQuestionArrayList.size();
    }





    private  void manageImageClick(ViewHolder viewHolder,final View DiaryView,final int position)
    {
        DiaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try{

                    if(viewHolder.questionResponce.isChecked()){
                        viewHolder.questionResponce.setChecked(false);
                    }else{
                        viewHolder.questionResponce.setChecked(true);
                    }

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
