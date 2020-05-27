package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import android.widget.TextView;

import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.Alergy;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.UserAlergies;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AllergyResult;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AllergyV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.UserAllergyV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.adapter.AddAllergiesAdapter;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.adapter.AddUserAllergiesAdapter;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.adapter.AllergiesAdapterV3;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AddAllergies extends AppCompatActivity implements AddAllergiesAdapter.AlergyRemoveListner, AddAllergiesContract.View{

    AddAllergiesPresenter addAllergiesPresenter;

    List<AllergyV3> addAllergiesDetailsList;
    List<UserAllergyV3> addUserAllergiesDetailsList;

    RecyclerView allergiesRV,userAllergiesRV;

    AddAllergiesAdapter addAllergiesAdapter;
    AddUserAllergiesAdapter addUserAllergiesAdapter;

    AllergiesAdapterV3 allergiesAdapterV3;

    FloatingActionButton myFab;

    ArrayAdapter<String> adapterAuto;

    AutoCompleteTextView actv;

    List<String> AllergiNameList;

    Boolean AllergiesStateChange = false;
    private Timer timer;

    ArrayList<String> allergyIDList;
    ArrayList<String> userAllergyIDList;
    ArrayList<String> removeAllergyIDList;
    ArrayList<String> userRemoveAllergyIDList;
    Array [] list;

    Button addUserAllergies;
    TextView userAllergiesLabel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_allergies);

        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        allergiesRV = findViewById(R.id.addAlergiesRV);

        userAllergiesRV = findViewById(R.id.addUserAlergiesRV);

        addUserAllergies = findViewById(R.id.AddUserAlergy);

        userAllergiesLabel = findViewById(R.id.view);

        myFab = (FloatingActionButton) findViewById(R.id.fab);

        addAllergiesPresenter = new AddAllergiesPresenter(AddAllergies.this,AddAllergies.this);

        ProgressManager.showProgress(AddAllergies.this,"Pulling Allergies Data...");

        addAllergiesPresenter.getAddAllergiesAllData(AddAllergies.this);

        addUserAllergies.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                String userAllegies = actv.getText().toString();

                try{

                    if(userAllegies.isEmpty() || userAllegies.isEmpty() || userAllegies.startsWith(" ") || userAllegies.startsWith(" ")) {

                        actv.setText("");
                        actv.setHint("Search Allergy Name");
                        addUserAllergies.setVisibility(View.GONE);
                        MyApplication.showSimpleSnackBar(AddAllergies.this, "White spaces cant be entred!");

                    }else{

                        addUserAllergiesAdapter.AddItem((String) userAllegies);
                        actv.setText("");
                        actv.setHint("Search Allergy Name");
                        addUserAllergies.onEditorAction(EditorInfo.IME_ACTION_DONE);

                        Constants.hideSoftKeyboard(v, AddAllergies.this);
                    }

                }catch (Exception e){

                }

            }
        });


        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try{
                    if(addAllergiesAdapter.getAlergyIDList().size() !=0){

                        AllergiesStateChange=true;
                        Log.i("Selected Allergi ID", addAllergiesAdapter.getAlergyIDList().toString());

                    }
                    if(addUserAllergiesAdapter.getUserAlergyNameList().size() !=0){
                        AllergiesStateChange=true;
                        Log.i("Selected Allergi NameID",addUserAllergiesAdapter.getUserAlergyNameList().toString());
                    }

                    if(addAllergiesAdapter.alergyIDRemovedList.size() !=0){
                        AllergiesStateChange=true;
                        Log.i("Deleted Allergi ID", addAllergiesAdapter.alergyIDRemovedList.toString());
                    }

                    if(addUserAllergiesAdapter.alergyUserIDRemovedList.size() !=0){
                        AllergiesStateChange=true;
                        Log.i("Deleted Allergi UserID", addUserAllergiesAdapter.alergyUserIDRemovedList.toString());
                    }

                    if(addAllergiesAdapter.getAlergyIDList().size() != 0 || addUserAllergiesAdapter.getUserAlergyNameList() .size() != 0 ){


                    }

                    if(AllergiesStateChange){
                        ProgressManager.showProgress(AddAllergies.this,"Save Allergies");
                        addAllergiesPresenter.addAllergiesServer(AddAllergies.this,addAllergiesAdapter.getAlergyIDList(),addUserAllergiesAdapter.getUserAlergyNameList(),addAllergiesAdapter.alergyIDRemovedList,addUserAllergiesAdapter.alergyUserIDRemovedList);
                    }
                }catch (Exception e){
                    finish();
                }





            }
        });



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

                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // do your actual work here

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                // Stuff that updates the UI
                              /*  if(adapterAuto.getCount()== 0 && s.length()>0 *//*&& !actv.getText().toString().equals(" ")*//*){

                                    addUserAllergies.setVisibility(View.VISIBLE);

                                }
                                else{

                                    addUserAllergies.setVisibility(View.GONE);
                                }*/
                                if(s.length()>=2){
                                    addAllergiesPresenter.searchServerforAllergis(AddAllergies.this,s.toString());
                                }

                            }
                        });

                    }
                }, 1000);


            }
        }
        );


        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
                Object item = parent.getItemAtPosition(position);
                Log.i("Selected Allergie", String.valueOf(item));


                try{

                    AllergiNameList.remove(String.valueOf(item));

                    addAllergiesAdapter.AddItem((String) item);

                    adapterAuto = new ArrayAdapter<String>(AddAllergies.this, android.R.layout.select_dialog_item, AllergiNameList);

                    actv.setAdapter(adapterAuto);

                    actv.setText("");

                    actv.setHint("Search Allergy Name");

                    actv.onEditorAction(EditorInfo.IME_ACTION_DONE);

                    Constants.hideSoftKeyboard(arg1,AddAllergies.this);


                }catch (Exception e){

                }


            }
        });

/*

        //Creating the instance of ArrayAdapter containing list of fruit names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, fruits);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.WHITE);


*/







    }






    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {


        try {
            if(addAllergiesAdapter.getAlergyIDList().size() !=0){

                AllergiesStateChange=true;

                Log.i("Selected Allergi ID", addAllergiesAdapter.getAlergyIDList().toString());

            }
            if(addUserAllergiesAdapter.getUserAlergyNameList().size() !=0){
                AllergiesStateChange=true;

                Log.i("Selected Allergi NameID",addUserAllergiesAdapter.getUserAlergyNameList().toString());
            }

            if(addAllergiesAdapter.alergyIDRemovedList.size() !=0){
                AllergiesStateChange=true;

                Log.i("Deleted Allergi ID", addAllergiesAdapter.alergyIDRemovedList.toString());
            }

            if(addUserAllergiesAdapter.alergyUserIDRemovedList.size() !=0){
                AllergiesStateChange=true;
                Log.i("Deleted Allergi UserID", addUserAllergiesAdapter.alergyUserIDRemovedList.toString());
            }

            if(addAllergiesAdapter.getAlergyIDList().size() != 0 || addUserAllergiesAdapter.getUserAlergyNameList() .size() != 0 ){


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


    @Override
    public void setUserAddedAllergiesSucess(String message) {

        try {

            ProgressManager.hideProgress();
            Log.i("Allergies Data S",message);
            addAllergiesDetailsList =  addAllergiesPresenter.getAllergiesAllDataLocal(AddAllergies.this);
            //addUserAllergiesDetailsList =  addAllergiesPresenter.getUserAllergiesAllDataLocal(AddAllergies.this);

            List<AllergyV3> allergyV3List =new ArrayList();
            allergyV3List.addAll(addAllergiesDetailsList);

            if(addAllergiesDetailsList != null) {

                allergiesAdapterV3 = new AllergiesAdapterV3(AddAllergies.this,allergyV3List);
                addAllergiesAdapter = new AddAllergiesAdapter(AddAllergies.this);
                addAllergiesAdapter.SetAlergyRemovedListner(AddAllergies.this);
                allergiesRV.setAdapter(addAllergiesAdapter);
                allergiesRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

            }

            if(addUserAllergiesDetailsList != null){

                addUserAllergiesAdapter = new AddUserAllergiesAdapter(AddAllergies.this);
                userAllergiesRV.setAdapter(addUserAllergiesAdapter);
                userAllergiesRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

            }


        }catch (Exception e){

        }


    }


    @Override
    public void setUserAddedAllergiesFailed(String message) {
        ProgressManager.hideProgress();
    }

    @Override
    public void setAddAllergiesServerSucess(String message) {
       // ProgressManager.showProgress(AddAllergies.this,"Pulling Alergies Data...");


        try{

            /*addAllergiesPresenter.getAddAllergiesAllData(AddAllergies.this);
            ProgressManager.hideProgress();*/
            MyApplication.showSimpleSnackBarSucess(AddAllergies.this, message);

            if(message.equals("Framgång")){
                AllergiesStateChange =false;
                //MyApplication.showSimpleSnackBarSucess(AddAllergies.this, message);
                addAllergiesDetailsList =  addAllergiesPresenter.getAllergiesAllDataLocal(AddAllergies.this);
                //addUserAllergiesDetailsList =  addAllergiesPresenter.getUserAllergiesAllDataLocal(AddAllergies.this);
            }else{

            }


        }catch (Exception e){

        }



    }

    @Override
    public void setAddAllergiesServerFailed(String message) {
        ProgressManager.hideProgress();

        MyApplication.showSimpleSnackBar(AddAllergies.this, message);
    }

    @Override
    public void setSearchServerforAllergisSucess(String message, List<AllergyResult> AllergyName) {
        try {

            ProgressManager.hideProgress();

            if(AllergyName != null) {

                AllergiNameList = new ArrayList<>();

                for (int i = 0; i < AllergyName.size(); i++) {
                    //Log.i("Auto Value", AllergyName.get(i).getTitle());
                    AllergiNameList.add(AllergyName.get(i).getTitle());
                }
                adapterAuto = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, AllergiNameList);


                actv.setThreshold(1); //will start working from first character
                actv.setAdapter(adapterAuto); //setting the adapter data into the AutoCompleteTextView
                actv.setTextColor(Color.BLACK);

            }



        }catch (Exception e){

        }

    }

    @Override
    public void setSearchServerforAllergisFailed(String message) {

    }

    @Override
    public void setSyncSucessAPI(String message, String NutritionPDf) {

    }

    @Override
    public void setSyncFailAPI(String message) {

    }

    @Override
    public void setPresenter(AddAllergiesContract.Presenter mPresenter) {

    }


    /*private List<AllergyV3> getModel(){
        List<AllergyV3> list = new ArrayList<>();

        for(int i = 0; i <  addAllergiesDetailsList.size(); i++){

            Alergy addAllergiesDetails = new Alergy();
            addAllergiesDetails.setSelected(addAllergiesDetailsList.get(i).getSelected());
            addAllergiesDetails.setAllergyName(String.valueOf(addAllergiesDetailsList.get(i).getAllergyName()));
            list.add(addAllergiesDetails);
        }
        return list;
    }
*/

    @Override
    public void OnAlergyRemoved(Alergy alergy) {

        try {

            AllergiNameList.add(alergy.getAllergyName());
            adapterAuto = new ArrayAdapter<String>(AddAllergies.this, android.R.layout.select_dialog_item, AllergiNameList);

            actv.setAdapter(adapterAuto);


        }catch (Exception e){

        }


    }

    public void DialogeBoxSaveAllergies(){

        LayoutInflater li = LayoutInflater.from(AddAllergies.this);
        View promptsView = li.inflate(R.layout.dialog_box_allergy_save_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(AddAllergies.this);

        builder.setView(promptsView);

        final Button CancelBtn = (Button) promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = (Button) promptsView.findViewById(R.id.okBtn);

        AlertDialog alertDialog = builder.create();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressManager.showProgress(AddAllergies.this,"Save Allergies");
                addAllergiesPresenter.addAllergiesServer(AddAllergies.this,addAllergiesAdapter.getAlergyIDList(),addUserAllergiesAdapter.getUserAlergyNameList(),addAllergiesAdapter.alergyIDRemovedList,addUserAllergiesAdapter.alergyUserIDRemovedList);


                alertDialog.dismiss();
                finish();
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

    public void onBackClick(View view) {

        try {

            if(addAllergiesAdapter.getAlergyIDList().size() !=0){

                AllergiesStateChange=true;
                Log.i("Selected Allergi ID", addAllergiesAdapter.getAlergyIDList().toString());

            }
            if(addUserAllergiesAdapter.getUserAlergyNameList().size() !=0){
                AllergiesStateChange=true;
                Log.i("Selected Allergi NameID",addUserAllergiesAdapter.getUserAlergyNameList().toString());
            }

            if(addAllergiesAdapter.alergyIDRemovedList.size() !=0){
                AllergiesStateChange=true;
                Log.i("Deleted Allergi ID", addAllergiesAdapter.alergyIDRemovedList.toString());
            }

            if(addUserAllergiesAdapter.alergyUserIDRemovedList.size() !=0){
                AllergiesStateChange=true;
                Log.i("Deleted Allergi UserID", addUserAllergiesAdapter.alergyUserIDRemovedList.toString());
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
}
