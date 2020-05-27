package com.kampen.riksSe.UpdateLoginProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class updateProfileStartOneActivity extends AppCompatActivity {

    private EditText mUserDOB;
    private TextView mGenderMale,mGenderFemale,mScreenViewOne,mScreenViewTwo;
    private Button Next;
    String GenderStatus,DOB,Gender;
    private Realm mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_start_one);

        mUserDOB=findViewById(R.id.dobValuetxt);
        mGenderMale=findViewById(R.id.genderMale);
        mGenderFemale=findViewById(R.id.genderFemale);
        mScreenViewOne=findViewById(R.id.screenViewOne);
        mScreenViewTwo=findViewById(R.id.screenViewTwo);
        Next=findViewById(R.id.button_next);

        String[] params= SaveSharedPreference.getLoggedParams(getApplicationContext());
/*
        final RealmResults<Realm_User> user = mRealm.where(Realm_User.class)
                .equalTo(Constants.USER_EMAIL_TAG,params[0].trim())
                .and()
                .equalTo(Constants.USER_PASS_TAG,params[1].trim())
                .findAll();

        if(user.get(0).getUser_gender()!=null){

        }*/

        /*mGenderMale.setBackgroundColor(Color.parseColor("#BB9767"));
        mGenderFemale.setBackground(ContextCompat.getDrawable(updateProfileStartOneActivity.this, R.drawable.grey_border_tint));
        GenderStatus="Male";*/


        Realm_User  user=provideUserLocal(updateProfileStartOneActivity.this);

        if(user!=null){
            if(user.getDob()!=null){
                mUserDOB.setText(user.getDob());
            }
            if(user.getUser_gender()!=null){
                if(user.getUser_gender().equals("Man")){
                    mGenderMale.setBackgroundColor(Color.parseColor("#BB9767"));
                    mGenderFemale.setBackground(ContextCompat.getDrawable(updateProfileStartOneActivity.this, R.drawable.grey_border_tint));
                    GenderStatus="Man";
                    mGenderMale.setText(getResources().getString(R.string.UpdateProfile_Male));
                    mGenderMale.setTextColor(getResources().getColor(R.color.white));
                    mGenderFemale.setTextColor(getResources().getColor(R.color.dark_gray));
                }else{
                    mGenderFemale.setBackgroundColor(Color.parseColor("#BB9767"));
                    mGenderMale.setBackground(ContextCompat.getDrawable(updateProfileStartOneActivity.this, R.drawable.grey_border_tint));
                    GenderStatus="Kvinna";
                    mGenderFemale.setText(getResources().getString(R.string.UpdateProfile_Female));
                    mGenderFemale.setTextColor(getResources().getColor(R.color.white));
                    mGenderMale.setTextColor(getResources().getColor(R.color.dark_gray));
                }
            }
        }


        mGenderMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGenderMale.setBackgroundColor(Color.parseColor("#BB9767"));
                mGenderFemale.setBackground(ContextCompat.getDrawable(updateProfileStartOneActivity.this, R.drawable.grey_border_tint));
                GenderStatus="Man";
                mGenderMale.setText(getResources().getString(R.string.UpdateProfile_Male));
                mGenderMale.setTextColor(getResources().getColor(R.color.white));
                mGenderFemale.setTextColor(getResources().getColor(R.color.dark_gray));

            }
        });

        mGenderFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGenderFemale.setBackgroundColor(Color.parseColor("#BB9767"));
                mGenderMale.setBackground(ContextCompat.getDrawable(updateProfileStartOneActivity.this, R.drawable.grey_border_tint));
                GenderStatus="Kvinna";
                mGenderFemale.setText(getResources().getString(R.string.UpdateProfile_Female));
                mGenderFemale.setTextColor(getResources().getColor(R.color.white));
                mGenderMale.setTextColor(getResources().getColor(R.color.dark_gray));
            }
        });

        /*mScreenViewTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGenderFemale.setBackgroundColor(Color.parseColor("#BB9767"));
                mGenderMale.setBackground(ContextCompat.getDrawable(updateProfileStartOneActivity.this, R.drawable.grey_border_tint));
                GenderStatus="Female";

            }
        });*/

    }

    public void onNextClick(View view){


        /*if(GenderStatus.equals("Male")){
            Gender = GenderStatus;
        }else{
            Gender = GenderStatus;
        }*/
        DOB=mUserDOB.getText().toString();

        if(DOB.isEmpty()){
            mUserDOB.setError("\n" + getResources().getString(R.string.UpdateProfile_EnterValidDOB));
            mUserDOB.requestFocus();
            MyApplication.showSimpleSnackBar(updateProfileStartOneActivity.this,getResources().getString(R.string.UpdateProfile_EnterValidDOB));
            return;
        }
        if(GenderStatus==null){
            mGenderMale.setError(getResources().getString(R.string.UpdateProfile_EnterValidGender));
            mGenderMale.requestFocus();
            MyApplication.showSimpleSnackBar(updateProfileStartOneActivity.this,getResources().getString(R.string.UpdateProfile_EnterValidGender));
            return;
        }
        Intent i = new Intent(updateProfileStartOneActivity.this,updateProfileStartTwoActivity.class);
        i.putExtra("DOB",mUserDOB.getText().toString());
        i.putExtra("Gender",GenderStatus);
        startActivity(i);


    }


    public void onDOBClick(View view) {


        final Calendar myCalendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date date = new Date();
        try {//dd-MM-yyyy
            date = dateFormat.parse("1990-01-01");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        myCalendar.setTime(date);

        DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year );
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault());

                mUserDOB.setText(sdf.format(myCalendar.getTime()));

            }

        };
        //new DatePickerDialog(EditPofileSimpleActivity.this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

        DatePickerDialog dialog =new DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog ,datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Date d=new Date();
        Calendar cal=Calendar.getInstance();
        cal.set(2007, 1, 1, 0, 0);
        d.setTime(cal.getTimeInMillis());


        Date d2=new Date();
        Calendar cal2=Calendar.getInstance();
        cal2.set(1950, 1, 1, 0, 0);
        d2.setTime(cal2.getTimeInMillis());

        dialog.getDatePicker().setMaxDate(d.getTime());
        dialog.getDatePicker().setMinDate(d2.getTime());
        dialog.show();

       /* mUserDOB.setVisibility(View.GONE);
        picker.setVisibility(View.VISIBLE);*/

    }


    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }

}
