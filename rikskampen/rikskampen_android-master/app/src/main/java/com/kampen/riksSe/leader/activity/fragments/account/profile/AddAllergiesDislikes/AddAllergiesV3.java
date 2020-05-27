package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AllergyResult;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AllergyV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.UserAllergyV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.adapter.AddAllergiesAdapter;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.adapter.AddUserAllergiesAdapter;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.adapter.AllergiesAdapterV3;
import com.kampen.riksSe.leader.activity.fragments.home.HomeFragment;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.kampen.riksSe.utils.SwipeToDeleteCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.kampen.riksSe.utils.Constants.DATE_TIME_FORMAT;
import static com.kampen.riksSe.utils.UtilityTz.convertStaticTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;

public class AddAllergiesV3  extends AppCompatActivity implements  AddAllergiesContract.View{

    AddAllergiesPresenter addAllergiesPresenter;

    List<AllergyV3> addAllergiesDetailsList;

    List<UserAllergyV3> addUserAllergiesDetailsList;

    AllergiesAdapterV3 allergiesAdapterV3;

    ArrayAdapter<String> adapterAuto;

    AutoCompleteTextView actv;

    RecyclerView allergiesRV;

    private Timer timer;

    Boolean AllergiesStateChange = false;

    Button addUserAllergies,mSaveAllergiesBtn;

    List<AllergyResult> SearchedAllergies;

    Boolean UserAllergiesStatus=false;


    Context mContext;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_allergies);
        mContext=AddAllergiesV3.this;

        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        allergiesRV = findViewById(R.id.addAlergiesRV);

        addUserAllergies = findViewById(R.id.AddUserAlergy);

        mSaveAllergiesBtn = findViewById(R.id.SaveAllergiesBtn);

        addAllergiesPresenter = new AddAllergiesPresenter(AddAllergiesV3.this,mContext);

        ProgressManager.showProgress(mContext,getResources().getString(R.string.progress_dialog_message));

        addAllergiesPresenter.getAddAllergiesAllData(mContext);

        AllergiesSearch();
        enableSwipeToDeleteAndUndo();

        addUserAllergies.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                String userAllegies = actv.getText().toString();

                try{

                    if(userAllegies.isEmpty() || userAllegies.isEmpty() || userAllegies.startsWith(" ") || userAllegies.startsWith(" ")) {

                        actv.setText("");
                        actv.setHint(getResources().getString(R.string.AllergyModule_Search_And_Allergy));
                        addUserAllergies.setVisibility(View.GONE);
                        MyApplication.showSimpleSnackBar(mContext, "White spaces cant be entred!");

                    }else{

                        //addUserAllergiesAdapter.AddItem((String) userAllegies);
                        mSaveAllergiesBtn.setVisibility(View.VISIBLE);
                        actv.setText("");
                        actv.setHint(getResources().getString(R.string.AllergyModule_Search_And_Allergy));
                        addUserAllergies.onEditorAction(EditorInfo.IME_ACTION_DONE);

                        Constants.hideSoftKeyboard(v, mContext);

                        AllergyV3 allergyV3 =new AllergyV3();
                        allergyV3.setTitle((String)userAllegies);
                        allergyV3.setStatus("UserAllergies");
                        allergyV3.setRecentlySelected(true);

                        List<AllergyV3> userAllergyV3List =new ArrayList();
                        userAllergyV3List.add(allergyV3);
                        allergiesAdapterV3.updateAdapter(userAllergyV3List);
                        /*if(addAllergiesDetailsList != null) {
                            allergiesAdapterV3 = new AllergiesAdapterV3(mContext,addAllergiesDetailsList);
                            allergiesRV.setAdapter(allergiesAdapterV3);
                            allergiesRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

                        }*/
                    }

                }catch (Exception e){

                }

            }
        });

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
                Object item = parent.getItemAtPosition(position);
                Log.i("Selected Allergie", String.valueOf(item));


                try{

                    //AllergiNameList.remove(String.valueOf(item));

                    /*allergiesAdapterV3.AddItem((String) item);

                    adapterAuto = new ArrayAdapter<String>(mContext, android.R.layout.select_dialog_item, AllergiNameList);

                    actv.setAdapter(adapterAuto);*/

                    actv.setText("");

                    actv.setHint(getResources().getString(R.string.AllergyModule_Search_And_Allergy));

                    actv.onEditorAction(EditorInfo.IME_ACTION_DONE);

                    Constants.hideSoftKeyboard(arg1,mContext);
                    String SeletedAllergy = String.valueOf(item);
                    if(SeletedAllergy.equals(getResources().getString(R.string.AllergyModule_No_Allery_Found))){
                        return;
                    }
                    if(allergiesAdapterV3.mAllergiesArrayList.size()>0){
                        for(int j=0;j<allergiesAdapterV3.mAllergiesArrayList.size();j++){
                            if(!SeletedAllergy.equals(allergiesAdapterV3.mAllergiesArrayList.get(j).getTitle())){

                            }else{
                                Toast.makeText(mContext, getResources().getString(R.string.AllergyModule_Allergies_Allready_Added), Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                                if(!UserAllergiesStatus){
                                    for(int i=0;i<SearchedAllergies.size();i++){
                                        if(SeletedAllergy.equals(SearchedAllergies.get(i).getTitle())){

                                            AllergyV3 allergyV3 =new AllergyV3();
                                            allergyV3.setTitle(SearchedAllergies.get(i).getTitle());
                                            allergyV3.setAllergyId(SearchedAllergies.get(i).getAllergyId());
                                            allergyV3.setStatus("SystemAllrgies");
                                            List<AllergyV3> allergyV3List =new ArrayList();
                                            allergyV3List.add(allergyV3);
                                            allergyV3.setRecentlySelected(true);
                                            allergiesAdapterV3.updateAdapter(allergyV3List);
                                        }
                                    }
                                }else{
                                    AllergyV3 allergyV3 =new AllergyV3();
                                    allergyV3.setTitle((String)SeletedAllergy);
                                    allergyV3.setStatus("UserAllergies");
                                    allergyV3.setRecentlySelected(true);

                                    List<AllergyV3> userAllergyV3List =new ArrayList();
                                    userAllergyV3List.add(allergyV3);
                                    allergiesAdapterV3.updateAdapter(userAllergyV3List);
                                }

                    }else{
                        if(!UserAllergiesStatus){
                            for(int i=0;i<SearchedAllergies.size();i++){
                                if(SeletedAllergy.equals(SearchedAllergies.get(i).getTitle())){

                                    AllergyV3 allergyV3 =new AllergyV3();
                                    allergyV3.setTitle(SearchedAllergies.get(i).getTitle());
                                    allergyV3.setAllergyId(SearchedAllergies.get(i).getAllergyId());
                                    allergyV3.setStatus("SystemAllrgies");
                                    List<AllergyV3> allergyV3List =new ArrayList();
                                    allergyV3List.add(allergyV3);
                                    allergyV3.setRecentlySelected(true);
                                    allergiesAdapterV3.updateAdapter(allergyV3List);
                                }
                            }
                        }else{
                            AllergyV3 allergyV3 =new AllergyV3();
                            allergyV3.setTitle((String)SeletedAllergy);
                            allergyV3.setStatus("UserAllergies");
                            allergyV3.setRecentlySelected(true);

                            List<AllergyV3> userAllergyV3List =new ArrayList();
                            userAllergyV3List.add(allergyV3);
                            allergiesAdapterV3.updateAdapter(userAllergyV3List);
                        }
                    }

                    mSaveAllergiesBtn.setVisibility(View.VISIBLE);


                    /*if(allergyV3List != null) {

                        allergiesAdapterV3 = new AllergiesAdapterV3(mContext,allergyV3List);
                        allergiesRV.setAdapter(allergiesAdapterV3);
                        allergiesRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

                    }*/
                }catch (Exception e){

                    Log.i("AllergyAdd Eception",e.toString());
                }


            }
        });


        mSaveAllergiesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try{

                    if(allergiesAdapterV3.getAlergyIDList().size() !=0){

                        AllergiesStateChange=true;
                        //Log.i("Selected Allergi ID", allergiesAdapterV3.getAlergyIDList().toString());
                    }
                    if(allergiesAdapterV3.getUserAlergyNameList().size() !=0){
                        AllergiesStateChange=true;
                        //Log.i("Selected Allergi NameID",allergiesAdapterV3.newUserAlergyArrayList.toString());
                    }
                    if(allergiesAdapterV3.alergyIDRemovedList.size() !=0){
                        AllergiesStateChange=true;
                        //Log.i("Deleted Allergi ID", allergiesAdapterV3.alergyIDRemovedList.toString());
                    }
                    if(allergiesAdapterV3.alergyUserIDRemovedList.size() !=0){
                        AllergiesStateChange=true;
                        //Log.i("Deleted Allergi UserID", allergiesAdapterV3.alergyUserIDRemovedList.toString());
                    }

                    if(AllergiesStateChange){
                        ProgressManager.showProgress(mContext,getResources().getString(R.string.progress_dialog_message));
                        addAllergiesPresenter.addAllergiesServer(mContext,allergiesAdapterV3.getAlergyIDList(),allergiesAdapterV3.getUserAlergyNameList(),allergiesAdapterV3.alergyIDRemovedList,allergiesAdapterV3.alergyUserIDRemovedList);
                    }

                }catch (Exception e){
                    finish();
                }

            }
        });

    }


    public void AllergiesSearch(){
        actv.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                            if (timer != null) {
                                                timer.cancel();
                                            }

                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {
                                            if(s.length()<=0){
                                                addUserAllergies.setVisibility(View.GONE);
                                            }
                                            runOnUiThread(new Runnable() {

                                                @Override
                                                public void run() {

                                                    // Stuff that updates the UI

                                                    if(s.length()>=1){
                                                        addAllergiesPresenter.searchServerforAllergis(mContext,s.toString());
                                                    }

                                                }
                                            });
                                           /* timer = new Timer();
                                            timer.schedule(new TimerTask() {
                                                @Override
                                                public void run() {
                                                    // do your actual work here



                                                }
                                            }, 800);*/


                                        }
                                    }
        );
    }

    @Override
    public void setUserAddedAllergiesSucess(String message) {
        try {

            ProgressManager.hideProgress();
            Log.i("Allergies Data S",message);
            addAllergiesDetailsList =  addAllergiesPresenter.getAllergiesAllDataLocal(mContext);
            //addUserAllergiesDetailsList =  addAllergiesPresenter.getUserAllergiesAllDataLocal(AddAllergies.this);

            List<AllergyV3> allergyV3List =new ArrayList();
            allergyV3List.addAll(addAllergiesDetailsList);

            if(allergyV3List != null) {

                allergiesAdapterV3 = new AllergiesAdapterV3(mContext,allergyV3List);
                allergiesRV.setAdapter(allergiesAdapterV3);
                allergiesRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

            }

        }catch (Exception e){

        }
    }

    @Override
    public void setUserAddedAllergiesFailed(String message) {
        ProgressManager.hideProgress();
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(mContext, getResources().getString(R.string.General_NoInternetConnection));
        }
        else{
            MyApplication.showSimpleSnackBar(mContext, getResources().getString(R.string.AllergyModule_AllergiesSavedFailed));
        }
    }

    @Override
    public void setAddAllergiesServerSucess(String message) {
        SaveSharedPreference.setSyncApiFirstTimeStatus(mContext,true);
        SaveSharedPreference.setApiSyncedDate(mContext,convertTimeToUTC());
        addAllergiesPresenter.syncApiV3(mContext);
    }

    @Override
    public void setAddAllergiesServerFailed(String message) {
        ProgressManager.hideProgress();
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(mContext, getResources().getString(R.string.General_NoInternetConnection));
        }
        else{
            //MyApplication.showSimpleSnackBar(mContext, "Feed not refreshed...");
        }
        finish();
    }

    @Override
    public void setSearchServerforAllergisSucess(String message, List<AllergyResult> AllergyName) {

        try {
            UserAllergiesStatus=false;
            ProgressManager.hideProgress();
            addUserAllergies.setVisibility(View.GONE);
            if(AllergyName != null) {
                SearchedAllergies =new ArrayList();
                SearchedAllergies.addAll(AllergyName);
                List<String> AllergiNameList = new ArrayList<>();

                for (int i = 0; i < AllergyName.size(); i++) {
                    AllergiNameList.add(AllergyName.get(i).getTitle());
                }
                adapterAuto = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, AllergiNameList);

                actv.setThreshold(1); //will start working from first character
                actv.setAdapter(adapterAuto); //setting the adapter data into the AutoCompleteTextView
                actv.setTextColor(Color.BLACK);
                adapterAuto.notifyDataSetChanged();
            }
        }catch (Exception e){

        }
    }

    @Override
    public void setSearchServerforAllergisFailed(String message) {

        //addUserAllergies.setVisibility(View.VISIBLE);

      /*  String userAllegies = actv.getText().toString();

        List<String> AllergiNameList = new ArrayList<>();

        AllergiNameList.add(userAllegies);*/

        List<String> AllergiNameList = new ArrayList<>();

        AllergiNameList.add(getResources().getString(R.string.AllergyModule_No_Allery_Found));
        adapterAuto = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, AllergiNameList);

        actv.setThreshold(1); //will start working from first character
        actv.setAdapter(adapterAuto); //setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);
        adapterAuto.notifyDataSetChanged();
        UserAllergiesStatus=true;

       // Toast.makeText(mContext, "No allergy found ", Toast.LENGTH_SHORT).show();

        ProgressManager.hideProgress();
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(mContext, getResources().getString(R.string.General_NoInternetConnection));
        }
        else{
            //MyApplication.showSimpleSnackBar(mContext, "Please Add ...");

        }
    }

    @Override
    public void setSyncSucessAPI(String message, String NutritionPDF) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {


                    /*HomeFragment homeFragment =new HomeFragment();
                    homeFragment.updateNotify();*/


                    SaveSharedPreference.setApiSyncedDate(mContext,convertTimeToUTC());
                    SaveSharedPreference.setNutritionPDF(mContext,NutritionPDF);
                    ProgressManager.hideProgress();
                    mSaveAllergiesBtn.setVisibility(View.VISIBLE);
                    finish();
                //MyApplication.showSimpleSnackBarSucess(context,"API Syncy Works");


               /* final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms

                    }
                }, 2000);*/



            }
        });


    }

    @Override
    public void setSyncFailAPI(String message) {

    }

    @Override
    public void setPresenter(AddAllergiesContract.Presenter mPresenter) {

    }


    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                //final String item = allergiesAdapterV3.getAlergyIDList().get(position);

                allergiesAdapterV3.removeItem(position);
                mSaveAllergiesBtn.setVisibility(View.VISIBLE);

               /* Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        allergiesAdapterV3.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();*/

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(allergiesRV);
    }


    public void onBackClick(View view) {

        try {



            if(allergiesAdapterV3.getAlergyIDList().size() !=0){

                AllergiesStateChange=true;
                //Log.i("Selected Allergi ID", addAllergiesAdapter.getAlergyIDList().toString());

            }
            if(allergiesAdapterV3.getUserAlergyNameList().size() !=0){
                AllergiesStateChange=true;
                //Log.i("Selected Allergi NameID",addUserAllergiesAdapter.getUserAlergyNameList().toString());
            }

            if(allergiesAdapterV3.alergyIDRemovedList.size() !=0){
                AllergiesStateChange=true;
                //Log.i("Deleted Allergi ID", addAllergiesAdapter.alergyIDRemovedList.toString());
            }

            if(allergiesAdapterV3.alergyUserIDRemovedList.size() !=0){
                AllergiesStateChange=true;
                //Log.i("Deleted Allergi UserID", addUserAllergiesAdapter.alergyUserIDRemovedList.toString());
            }

            if(AllergiesStateChange){
                DialogeBoxSaveAllergies();
            }else{
                finish();
            }
        }catch (Exception e){
            finish();
        }


    }



    public void DialogeBoxSaveAllergies(){

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.dialog_box_allergy_save_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setView(promptsView);

        final Button CancelBtn = (Button) promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = (Button) promptsView.findViewById(R.id.okBtn);

        AlertDialog alertDialog = builder.create();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressManager.showProgress(mContext,"Spara allergier");
                addAllergiesPresenter.addAllergiesServer(mContext,allergiesAdapterV3.getAlergyIDList(),allergiesAdapterV3.getUserAlergyNameList(),allergiesAdapterV3.alergyIDRemovedList,allergiesAdapterV3.alergyUserIDRemovedList);
                alertDialog.dismiss();
                //finish();
            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();

                alertDialog.dismiss();
            }
        });



        alertDialog.show();

    }

}
