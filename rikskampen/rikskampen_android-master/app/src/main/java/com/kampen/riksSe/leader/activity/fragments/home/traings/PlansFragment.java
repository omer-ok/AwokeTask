package com.kampen.riksSe.leader.activity.fragments.home.traings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.RecipeSummary;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Days_V;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionMealOptionsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.adapters.NutritionAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.traings.adapters.TrainingAndFitnessAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.WeekNutritionModel;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.CustomSwipeToRefresh;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;

import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTimePopDaysHoursMinutes;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlansFragment
 * to handle interaction events.
 */
public class PlansFragment extends Fragment implements Plans_Frag_Contract.View{


    private  RecyclerView mNutritionRecycleView;
    private  NutritionAdapter  mNutritionAdapter;
    private  RecyclerView mTrainingRecyclerView;
    private  TrainingAndFitnessAdapter mTrainingAdapter;
    CustomSwipeToRefresh pullToRefresh;
    TextView nutritionMsg,trainingMsg;
    Button summaryClick;
    N_DaysDB n_daysDB;
    View noDataNutrition,noDataTraining;
    long currentDay;
    long currentWeek;
    //List<DayNutritionMealOptionsDB> dayNutritionMealOptionsDB;

    Plans_Frag_Presenter plans_frag_presenter;
    Realm_User mUser;

    public PlansFragment() {

    }
    ArrayList<WeekNutritionModel> hmArray;
    public static PlansFragment newInstance() {
        PlansFragment fragment = new PlansFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_nutrition, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        plans_frag_presenter = new Plans_Frag_Presenter(PlansFragment.this,getContext());

        mNutritionRecycleView = (RecyclerView) rootView.findViewById(R.id.nutritionRV);
        mTrainingRecyclerView = (RecyclerView) rootView.findViewById(R.id.trainingRV);
        nutritionMsg = rootView.findViewById(R.id.nutritionMsg1);
        trainingMsg = rootView.findViewById(R.id.trainingMsg2);
        summaryClick = rootView.findViewById(R.id.summaryBtn);
        noDataNutrition = rootView.findViewById(R.id.NoDataView);
        noDataTraining = rootView.findViewById(R.id.NoDataView1);
        pullToRefresh = rootView.findViewById(R.id.pullToRefresh);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());


        pullToRefresh.setOnRefreshListener(new CustomSwipeToRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //refreshData(); //your code

                //ProgressManager.showProgress(getContext(),"Dagens bild och uppdatering...");
                MainLeaderActivity activity= (MainLeaderActivity) getContext();
                activity.getAllDataCallFromFragment();

            }
        }
        );




        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity());
        mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        String recipeSummaryPath = plans_frag_presenter.getRecipeFilePath(getContext());



       //V3 List Data

        try{
            mUser  = plans_frag_presenter.provideUserLocal(getContext());

            Competition CompitionDate = Repository.getCompitionDate();
            if(CompitionDate!=null) {
                if (CompitionDate.getStartDate() != null) {
                    ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(currentDateandTime, convertUTCToLocalTime(CompitionDate.getStartDate()));
                    currentDay = contestWeekDayTimeModel.getDays() + 1;
                    currentWeek = contestWeekDayTimeModel.getWeeks() + 1;
                }
            }else{
                ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(currentDateandTime, convertUTCToLocalTime(CompitionDate.getStartDate()));
                currentDay = contestWeekDayTimeModel.getDays() + 1;
                currentWeek = contestWeekDayTimeModel.getWeeks() + 1;
            }
            if(CompitionDate!=null) {
                if(CompitionDate.getStartDate()!=null){
                    Boolean ContestStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                    if(ContestStatus){
                List<MealType> nutritionDataMealType = plans_frag_presenter.getNutritionMealTypeDataV3(getContext(),convertUTCToLocalTime(CompitionDate.getStartDate()));
                if (nutritionDataMealType != null && nutritionDataMealType.size() > 0) {
                    noDataNutrition.setVisibility(View.GONE);
                    mNutritionRecycleView.setVisibility(View.VISIBLE);
                    mNutritionAdapter = new NutritionAdapter(getContext(), nutritionDataMealType);
                    mNutritionRecycleView.setNestedScrollingEnabled(false);
                    mLayoutManager1.setAutoMeasureEnabled(true);
                    mNutritionRecycleView.setLayoutManager(mLayoutManager1);
                    mNutritionRecycleView.setAdapter(mNutritionAdapter);
                } else {
                    noDataNutrition.setVisibility(View.VISIBLE);
                    mNutritionRecycleView.setVisibility(View.GONE);
                }
            }else{
                        noDataNutrition.setVisibility(View.VISIBLE);
                        mNutritionRecycleView.setVisibility(View.GONE);

                    }
                }else {


            }
            }else{
                noDataNutrition.setVisibility(View.VISIBLE);
                mNutritionRecycleView.setVisibility(View.GONE);

            }
            if(CompitionDate!=null) {
                if (CompitionDate.getStartDate() != null) {
                    List<W_Plans> traningDataV3 = plans_frag_presenter.getTrainingWeek(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()));

                    if (traningDataV3 != null && traningDataV3.size() > 0) {
                        noDataTraining.setVisibility(View.GONE);
                        mTrainingRecyclerView.setVisibility(View.VISIBLE);

                        mTrainingAdapter = new TrainingAndFitnessAdapter(getContext(), currentDay, currentWeek, traningDataV3);

                        mTrainingRecyclerView.setNestedScrollingEnabled(false);
                        mLayoutManager2.setAutoMeasureEnabled(true);
                        mTrainingRecyclerView.setLayoutManager(mLayoutManager2);

                        mTrainingRecyclerView.setAdapter(mTrainingAdapter);

                        for (int i = 0; i < traningDataV3.size(); i++) {
                            if (traningDataV3.get(i).getmWeek() == currentWeek) {
                                mTrainingRecyclerView.getLayoutManager().scrollToPosition(i);
                                break;
                            }
                        }
                    }else{
                        noDataTraining.setVisibility(View.VISIBLE);
                        mTrainingRecyclerView.setVisibility(View.GONE);
                    }
                }
            }else {
                noDataTraining.setVisibility(View.VISIBLE);
                mTrainingRecyclerView.setVisibility(View.GONE);
            }
        }catch (Exception e){
            noDataNutrition.setVisibility(View.VISIBLE);
            mNutritionRecycleView.setVisibility(View.GONE);
            Log.i("TraingloadEXLoad",e.toString());
        }







        summaryClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                String currentDateandTime = sdf.format(new Date());
                Competition CompitionDate = Repository.getCompitionDate();
                if(CompitionDate!=null) {

                    if (CompitionDate.getStartDate() != null && CompitionDate.getmEndDate() != null) {
                        Boolean ContestEndStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);
                        Boolean ContestStatus = getCompitionStartDate(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()), currentDateandTime);

                        if (ContestStatus && !ContestEndStatus) {
                            Intent intent = new Intent(getContext(), RecipeSummary.class);
                            startActivity(intent);
                        }else if(ContestEndStatus){
                            DialogeBoxContestEndDate();
                        }else{
                            DialogeBoxContestDate();
                        }
                    }else{
                        //DialogeBoxContestDate();
                    }
                }else{
                    //DialogeBoxContestDate();
                }
            }
        });

     /* if(nutritionData.size() == 0 ){

            mNutritionRecycleView.setVisibility(View.GONE);
            nutritionMsg.setVisibility(View.VISIBLE);

        }
        else{

        }
        if(trainingData.size() == 0){

            mTrainingRecyclerView.setVisibility(View.GONE);
            trainingMsg.setVisibility(View.VISIBLE);
        }else{

        }*/

     }


    @Override
    public void onResume() {
        super.onResume();

        //List<N_WeekDB> nutritionData=plans_frag_presenter.getNutritionData(getContext());



       /* for(int i =0; i<nutritionData.size();i++) {
            if (nutritionData.get(i).getCurrentWeek().equals("present")) {
                mNutritionRecycleView.getLayoutManager().scrollToPosition(i);
                break;
            }
        }*/
       try{
           Competition CompitionDate = Repository.getCompitionDate();
           if(CompitionDate!=null) {
               if (CompitionDate.getStartDate() != null) {
                   List<W_Plans> traningDataV3 = plans_frag_presenter.getTrainingWeek(getContext(),convertUTCToLocalTime(CompitionDate.getStartDate()));
                   for (int i = 0; i < traningDataV3.size(); i++) {
                       if (traningDataV3.get(i).getmWeek() == currentWeek) {
                           mTrainingRecyclerView.getLayoutManager().scrollToPosition(i);
                           break;
                       }
                   }
               }
           }else{
                   if (CompitionDate.getStartDate() != null) {
                       List<W_Plans> traningDataV3 = plans_frag_presenter.getTrainingWeek(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()));
                       for (int i = 0; i < traningDataV3.size(); i++) {
                           if (traningDataV3.get(i).getmWeek() == currentWeek) {
                               mTrainingRecyclerView.getLayoutManager().scrollToPosition(i);
                               break;
                           }
                       }
                   }
                   else{

                   }
           }

       }catch (Exception e){
           Log.i("TraingloadEXResume",e.toString());
       }




    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

        mNutritionRecycleView =null;
        mNutritionAdapter =null;
        mTrainingRecyclerView =null;
        mTrainingAdapter =null;


    }


    @Override
    public void setTrainings(List<WeekNutritionModel> list) {




    }

    @Override
    public void setNutritions(List<WeekTrainingModel> list) {



    }

    @Override
    public void setStartUpData(String message) {

    }

    @Override
    public void setStartUpDataFailed(String message) {

    }

    @Override
    public void setPresenter(Plans_Frag_Contract.Presenter mPresenter) {



    }

    @Override
    public void onPause() {
        super.onPause();
        ProgressManager.hideProgress();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ProgressManager.hideProgress();
    }

    public void updateNotify() {
        // Stuff that updates the UI
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());
        try {

            if(mNutritionAdapter!= null){
                Competition CompitionDate = Repository.getCompitionDate();
                if(CompitionDate.getStartDate()!=null) {
                    List<MealType> nutritionDataMealType = plans_frag_presenter.getNutritionMealTypeDataV3(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()));
                    if (nutritionDataMealType != null && nutritionDataMealType.size() > 0) {
                        noDataNutrition.setVisibility(View.GONE);
                        mNutritionRecycleView.setVisibility(View.VISIBLE);
                        mNutritionAdapter.updateAdapter(nutritionDataMealType);
                              /*  mNutritionRecycleView.setNestedScrollingEnabled(false);
                                mNutritionRecycleView.setAdapter(mNutritionAdapter);*/
                    } else {
                        noDataNutrition.setVisibility(View.VISIBLE);
                        mNutritionRecycleView.setVisibility(View.GONE);
                    }

                }else{
                    noDataNutrition.setVisibility(View.VISIBLE);
                    mNutritionRecycleView.setVisibility(View.GONE);
                }
            }else {
                Competition CompitionDate = Repository.getCompitionDate();
                if (CompitionDate.getStartDate() != null) {
                    List<MealType> nutritionDataMealType = plans_frag_presenter.getNutritionMealTypeDataV3(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()));
                    LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
                    mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                    if (nutritionDataMealType != null && nutritionDataMealType.size() > 0) {
                        noDataNutrition.setVisibility(View.GONE);
                        mNutritionRecycleView.setVisibility(View.VISIBLE);
                        mNutritionAdapter = new NutritionAdapter(getContext(), nutritionDataMealType);
                        mNutritionRecycleView.setNestedScrollingEnabled(false);
                        mLayoutManager1.setAutoMeasureEnabled(true);
                        mNutritionRecycleView.setLayoutManager(mLayoutManager1);
                        mNutritionRecycleView.setAdapter(mNutritionAdapter);
                    } else {
                        noDataNutrition.setVisibility(View.VISIBLE);
                        mNutritionRecycleView.setVisibility(View.GONE);
                    }
                }else{
                    noDataNutrition.setVisibility(View.VISIBLE);
                    mNutritionRecycleView.setVisibility(View.GONE);
                }
            }

        }catch (Exception e){
            //noDataNutrition.setVisibility(View.VISIBLE);
            //mNutritionRecycleView.setVisibility(View.GONE);
        }

        try{
            if (mTrainingAdapter != null) {
                Competition CompitionDate = Repository.getCompitionDate();
                if (CompitionDate.getStartDate() != null) {
                    List<W_Plans> traningDataV3 = plans_frag_presenter.getTrainingWeek(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()));
                    if (traningDataV3 != null && traningDataV3.size() > 0) {
                        noDataTraining.setVisibility(View.GONE);
                        mTrainingRecyclerView.setVisibility(View.VISIBLE);
                        mTrainingAdapter.updateAdapter(currentDay, currentWeek, traningDataV3);
                        for (int i = 0; i < traningDataV3.size(); i++) {
                            if (traningDataV3.get(i).getmWeek() == currentWeek) {
                                mTrainingRecyclerView.getLayoutManager().scrollToPosition(i);
                                break;
                            }
                        }
                    }else{
                        noDataTraining.setVisibility(View.VISIBLE);
                        mTrainingRecyclerView.setVisibility(View.GONE);
                    }
                }else{
                    noDataTraining.setVisibility(View.VISIBLE);
                    mTrainingRecyclerView.setVisibility(View.GONE);
                }
            }else{
                Competition CompitionDate = Repository.getCompitionDate();
                if (CompitionDate.getStartDate() != null) {
                    List<W_Plans> traningDataV3 = plans_frag_presenter.getTrainingWeek(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()));
                    LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity());
                    mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                    if (traningDataV3 != null && traningDataV3.size() > 0) {
                        noDataTraining.setVisibility(View.GONE);
                        mTrainingRecyclerView.setVisibility(View.VISIBLE);

                        mTrainingAdapter = new TrainingAndFitnessAdapter(getContext(), currentDay, currentWeek, traningDataV3);

                        mTrainingRecyclerView.setNestedScrollingEnabled(false);
                        mLayoutManager2.setAutoMeasureEnabled(true);
                        mTrainingRecyclerView.setLayoutManager(mLayoutManager2);

                        mTrainingRecyclerView.setAdapter(mTrainingAdapter);

                        for (int i = 0; i < traningDataV3.size(); i++) {
                            if (traningDataV3.get(i).getmWeek() == currentWeek) {
                                mTrainingRecyclerView.getLayoutManager().scrollToPosition(i);
                                break;
                            }
                        }
                    }else{
                        noDataTraining.setVisibility(View.VISIBLE);
                        mTrainingRecyclerView.setVisibility(View.GONE);
                    }
                }else{
                    noDataTraining.setVisibility(View.VISIBLE);
                    mTrainingRecyclerView.setVisibility(View.GONE);
                }
            }
        }catch (Exception e){
            //noDataTraining.setVisibility(View.VISIBLE);
            //mTrainingRecyclerView.setVisibility(View.GONE);
            Log.i("TraingloadEX",e.toString());
        }
        pullToRefresh.setRefreshing(false);

    }

    public void ApiFail()
    {
        pullToRefresh.setRefreshing(false);
    }


    public void DialogeBoxContestDate(){

        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.dialog_box_contest_date_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(promptsView);

        final TextView contestStartTime = (TextView) promptsView.findViewById(R.id.textView1);

        final Button CancelBtn = (Button) promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = (Button) promptsView.findViewById(R.id.okBtn);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        ContestWeekDayTimeModel contestWeekDayTimeModel =new ContestWeekDayTimeModel();

        Competition CompitionDate = Repository.getCompitionDate();

        if(CompitionDate.getStartDate()!=null){
            contestWeekDayTimeModel = CompititionStartDateAndTimePopDaysHoursMinutes(convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);

            contestStartTime.setText(getResources().getString(R.string.Competition_Start_In)+" "+ contestWeekDayTimeModel.getDays()+" "+getResources().getString(R.string.Competition_Time_Ticker_Days)+" "+ contestWeekDayTimeModel.getHours()+" "+getResources().getString(R.string.Competition_Time_Ticker_Hours)+" "+contestWeekDayTimeModel.getMiniutes()+" "+getResources().getString(R.string.Competition_Time_Ticker_Minutes));

        }else{
            contestStartTime.setText(getResources().getString(R.string.Competition_Start_Soon));
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

    public void DialogeBoxContestEndDate(){

        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.dialog_box_contest_date_end_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(promptsView);

        final TextView contestEnd = (TextView) promptsView.findViewById(R.id.textView1);

        final Button CancelBtn = (Button) promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = (Button) promptsView.findViewById(R.id.okBtn);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        // ContestWeekDayTimeModel contestWeekDayTimeModel =new ContestWeekDayTimeModel();

        Competition CompitionDate = Repository.getCompitionDate();

        Boolean ContestEndStatus = getCompitionStartDate(getContext(),currentDateandTime,CompitionDate.getmEndDate());
        Boolean ContestEndStatus1 = getCompitionStartDate(getContext(), "2020-03-09 00:00:00",CompitionDate.getmEndDate());
        if(ContestEndStatus1){
            contestEnd.setText(getResources().getString(R.string.General_PleaseUpdatePackage));
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

      /*  CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });*/
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

    }

}
